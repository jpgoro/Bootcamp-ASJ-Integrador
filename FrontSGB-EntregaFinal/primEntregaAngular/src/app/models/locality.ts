import { Province } from "./province";

export interface Locality {
  id: number;
    localityName: string;
    province: Province;
}
