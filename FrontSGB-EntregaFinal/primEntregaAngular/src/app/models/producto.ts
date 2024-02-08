import { Proveedor } from './proveedor';
export interface Producto {
  id: number;
  proveedor_id: number;
  proveedor_razon: string;
  sku: string;
  categoria: string;
  nombreProducto: string;
  descripcion: string;
  precio: string;
}
