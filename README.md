# amazon-inventory-service (mini-monorepo)

A portfolio project demonstrating:
1) a minimal **Inventory API** (Java/Spring Boot), and  
2) a **Server-Driven UI (SDUI) Layout API** that composes UI from server-side JSON.

This shows how retail inventory data can power **server-driven cards** that a client renders without app releases, and how this maps cleanly to AWS (API Gateway/Lambda or ECS, DynamoDB, SQS).

> Status: **In Progress** — stubs first, iterate quickly.

---

## 🧭 Why SDUI here?
Mobile apps typically need client releases to change UI. **SDUI** flips that: the **server** sends a JSON layout (e.g., a “Card” with title, text, button), and the **client** renders it. This enables:
- Instant UI updates (no app-store wait),
- Consistent cross-platform UI,
- Personalization at scale.

This repo demonstrates that end-to-end with inventory data.

---

## 🧱 Services

### 1) `inventory-api`
- **Purpose:** read/write inventory entities (items/stock), expose simple REST.
- **Example endpoint (stub):**
  - `GET /items/{sku}` → `{"sku":"P525","name":"Yamaha P525","stock":3}`

### 2) `sdui-layout-api`
- **Purpose:** compose **server-driven card** JSON for a given SKU by calling `inventory-api` (stubbed locally at first).
- **Example endpoint (stub):**
  - `GET /sdui/item/{sku}` → JSON describing a card, text, button.

#### Example SDUI JSON (response)
```json
{
  "screen": "InventoryItem",
  "version": 1,
  "components": [
    { "type": "Card", "props": { "title": "Yamaha P525", "subtitle": "SKU P525" } },
    { "type": "Text", "props": { "value": "In stock: 3" } },
    { "type": "Button", "props": { "label": "Reserve",
      "action": { "type": "POST", "url": "/reserve", "body": { "sku": "P525" } }
    }}
  ]
}

---

## 📚 Docs

- [SDUI Design Notes](docs/SDUI.md) — explains the Server-Driven UI approach, supported schema, example responses, AWS mapping, and roadmap.
