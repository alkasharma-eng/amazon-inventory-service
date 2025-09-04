# amazon-inventory-service
Retail Offers &amp; Inventory Service

Prototype API + mini UI for exploring product offers and inventory.

• Simulates products, seller offers (price, shipping), inventory.

• Ranks a “winning” offer (buy‑box‑style) using a simple, explainable scoring function.

• Exposes endpoints: list products, product details with ranked offers, reserve inventory.

• Comes with synthetic data + unit test + AWS SAM stub (API Gateway + Lambda + DynamoDB path).

## Quickstart (Local)

```bash
python3 -m venv .venv && source .venv/bin/activate
pip install fastapi uvicorn pydantic boto3 pytest
uvicorn src.app:app --reload
# Open http://127.0.0.1:8000/docs and then open ui-mini/index.html
