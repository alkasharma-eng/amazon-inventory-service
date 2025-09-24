package com.alka.inventory.repo;

import com.alka.inventory.model.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

@Repository
@Profile("dynamo")
public class DynamoDbItemRepository implements ItemRepository {

    private final DynamoDbClient ddb;
    private final String table;

    public DynamoDbItemRepository(DynamoDbClient ddb,
                                  @Value("${aws.dynamodb.table}") String table) {
        this.ddb = ddb;
        this.table = table;
    }

    @Override
    public Optional<Item> findBySku(String sku) {
        GetItemResponse resp = ddb.getItem(GetItemRequest.builder()
                .tableName(table)
                .key(Map.of("sku", AttributeValue.fromS(sku)))
                .build());
        if (resp.hasItem() && !resp.item().isEmpty()) {
            return Optional.of(fromMap(resp.item()));
        }
        return Optional.empty();
    }

    @Override
    public Item save(Item item) {
        Map<String, AttributeValue> m = toMap(item);
        ddb.putItem(PutItemRequest.builder()
                .tableName(table)
                .item(m)
                .conditionExpression("attribute_not_exists(sku)") // avoid overwrite
                .build());
        return item;
    }

    @Override
    public boolean existsBySku(String sku) {
        return findBySku(sku).isPresent();
    }

    @Override
    public void updateStock(String sku, int stock) {
        ddb.updateItem(UpdateItemRequest.builder()
                .tableName(table)
                .key(Map.of("sku", AttributeValue.fromS(sku)))
                .updateExpression("SET #s = :newStock")
                .expressionAttributeNames(Map.of("#s", "stock"))
                .expressionAttributeValues(Map.of(":newStock", AttributeValue.fromN(Integer.toString(stock))))
                .conditionExpression("attribute_exists(sku)")
                .build());
    }

    private static Map<String, AttributeValue> toMap(Item it) {
        Map<String, AttributeValue> m = new HashMap<>();
        m.put("sku", AttributeValue.fromS(it.getSku()));
        m.put("name", AttributeValue.fromS(it.getName()));
        m.put("stock", AttributeValue.fromN(Integer.toString(it.getStock())));
        return m;
    }

    private static Item fromMap(Map<String, AttributeValue> m) {
        Item it = new Item();
        it.setSku(m.get("sku").s());
        it.setName(m.get("name").s());
        it.setStock(Integer.parseInt(m.get("stock").n()));
        return it;
    }
}