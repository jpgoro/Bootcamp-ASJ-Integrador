import { Product } from "./product";
import { PurchaseOrder } from "./purchase-order";

export interface DetailOrder {
  id: number;
    quantity: number;
    price: number;
    createdAt?: string;
    updatedAt?: string;
    product: Product;
    purchaseOrder: PurchaseOrder;
}
