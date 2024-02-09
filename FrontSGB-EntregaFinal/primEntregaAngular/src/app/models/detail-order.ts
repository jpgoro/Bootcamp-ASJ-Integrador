import { Product } from "./product";
import { PurchaseOrder } from "./purchase-order";

export interface DetailOrder {
  id?: number;
    quantity: number;
    price: number;
    product: Partial<Product>;
    purchaseOrder?: Partial<PurchaseOrder>;
}
