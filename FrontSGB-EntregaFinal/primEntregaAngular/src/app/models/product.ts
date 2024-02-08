import { Category } from "./category";
import { Supplier } from "./supplier";

export interface Product {
  id: number;
    sku: string;
    name: string;
    description: string;
    price: number;
    image: string;
    active: boolean;
    category: Category;
    supplier: Supplier;
}
