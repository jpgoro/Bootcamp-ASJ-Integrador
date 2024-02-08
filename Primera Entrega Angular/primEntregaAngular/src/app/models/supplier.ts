import { ConditionIva } from "./condition-iva";
import { Industry } from "./industry";

export interface Supplier {
  id: string;
    code: string;
    legalName: string;
    email: string;
    web: string;
    tel: string;
    image: string;
    active: boolean;
    cuit: string;
    createdAt?: string;
    updatedAt?: string;
    industry: Industry;
    conditionIva: ConditionIva;
}
