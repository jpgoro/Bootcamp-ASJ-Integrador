import { Status } from "./status";
import { Supplier } from "./supplier";

export interface PurchaseOrder {
  id: number;
    number: string;
    issueDate: string;
    deliveryDate: string;
    active: boolean;
    reception: string;
    supplier: Supplier;
    status: Status;
}
