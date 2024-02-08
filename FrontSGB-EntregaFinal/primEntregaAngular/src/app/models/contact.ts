import { Supplier } from "./supplier";

export interface Contact {
  id: number;
    name: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    role: string;
    supplier: Supplier;
}
