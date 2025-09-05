# Server-Driven UI (SDUI) Design Notes

This document explains the **Server-Driven UI (SDUI)** approach used in this project and provides details about the JSON schema and response structure.

---

## 🌱 What is SDUI?

Traditional mobile apps require a new client release (and often app store approval) every time the UI changes.  
**Server-Driven UI (SDUI)** flips this model: the **server defines the UI layout** in JSON, and the **client renders it dynamically**.

### Benefits
- 🚀 **Instant UI changes** without app store releases
- 🎯 **Personalization at scale** (different users get different layouts)
- 📱 **Consistency across platforms** (iOS, Android, Web render the same schema)
- ⚡ **Faster iteration** for developers and product teams

---

## 📐 SDUI Schema

The schema defines the structure of responses supported by the SDUI Layout API.

- **Schema file:** [`schema/sdui-v1.json`](../schema/sdui-v1.json)
- **Supported components:**
    - `Card` → title + subtitle
    - `Text` → simple text value
    - `Button` → label + action (POST/GET, URL, body)

---

## 🧩 Example Response

The Layout API returns JSON describing a "screen" for a given inventory item.

```json
{
  "screen": "InventoryItem",
  "version": 1,
  "components": [
    { "type": "Card", "props": { "title": "Yamaha P525", "subtitle": "SKU P525" } },
    { "type": "Text", "props": { "value": "In stock: 3" } },
    { "type": "Button", "props": {
      "label": "Reserve",
      "action": { "type": "POST", "url": "/reserve", "body": { "sku": "P525" } }
    }}
  ]
}
```

---

## ☁️ AWS Mapping (Future Plan)

- **API Gateway** → expose both Inventory and SDUI services

- **Lambda or ECS** → host services

- **DynamoDB** → store inventory items (PK = SKU)

- **SQS** → manage stock reservation events

- **CloudFront + S3** → host a simple web demo client

## 📌 Next Steps

- Extend schema with new components (e.g., Image, List)

- Build a sample web client that renders these components

- Wire SDUI Layout API to real inventory data in DynamoDB
