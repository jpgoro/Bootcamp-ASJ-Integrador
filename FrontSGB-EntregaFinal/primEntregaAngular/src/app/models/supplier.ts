import { ConditionIva } from "./condition-iva";
import { Contact } from "./contact";
import { Industry } from "./industry";

export interface Supplier {
  id: number;
    code: string;
    legalName: string;
    email: string;
    web: string;
    tel: string;
    image: string;
    active: boolean;
    cuit: string;
    industry: Industry;
    conditionIva: ConditionIva;
    contact: Contact[];
}
