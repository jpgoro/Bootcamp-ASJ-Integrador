import { Supplier } from "./supplier";

export interface Contact {
  id: string;
    name: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    role: string;
    createdAt?: string;
    updatedAt?: string;
    supplier: Supplier;
}
