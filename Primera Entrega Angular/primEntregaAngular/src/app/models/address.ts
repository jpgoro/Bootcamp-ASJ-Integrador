import { Locality } from "./locality";
import { Supplier } from "./supplier";

export interface Address {
  id: string;
    street: string;
    number: number;
    postalCode: string;
    createdAt?: string;
    updatedAt?: string;
    locality: Locality;
    supplier: Supplier;
}
