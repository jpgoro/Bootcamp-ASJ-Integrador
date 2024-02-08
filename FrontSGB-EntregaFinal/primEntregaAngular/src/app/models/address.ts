import { Locality } from "./locality";
import { Supplier } from "./supplier";

export interface Address {
  id: number;
    street: string;
    number: number;
    postalCode: string;
    locality: Locality;
    supplier: Supplier;
}
