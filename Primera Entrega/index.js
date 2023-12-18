// Obtengo los datos del localStorage para proveedores
let proveedoresGuardados = JSON.parse(localStorage.getItem("proveedores")) || [];

// Obtengo los datos del localStorage para productos
let productosGuardados = JSON.parse(localStorage.getItem("productos")) || [];

// Obtengo los datos del localStorage para órdenes
let ordenesGuardadas = JSON.parse(localStorage.getItem("ordenes")) || [];

// Función para generar una nueva tarjeta de proveedor
function generarNuevaTarjetaProveedor(proveedor, index) {
  let cartasContainer = document.getElementById("cartas");

  let nuevaTarjeta = document.createElement("div");
    nuevaTarjeta.className = "card mb-3";
    nuevaTarjeta.innerHTML = `
        <div class="card-body bg-dark text-white">
            <p>Código: <strong>${proveedor.codigo}</strong></p>
            <p>Razón Social: <strong>${proveedor.razon}</strong></p>
            <p>Rubro: <strong>${proveedor.rubro}</strong></p>
            <p>Web: <strong>${proveedor.web}</strong></p>
            <p>Dirección: <strong>${proveedor.direccion}</strong></p>
            <p>Datos Fiscales: <strong>${proveedor.fiscales}</strong></p>
            <p>Datos de contacto: <strong>${proveedor.datosContacto}</strong></p>
            <button class="btn btn-danger" onclick="eliminarTarjetaProveedor(${index})">Eliminar</button>
        </div>
    `;

    cartasContainer.appendChild(nuevaTarjeta);
}

// Función para generar una nueva tarjeta de producto
function generarNuevaTarjetaProducto(producto, index) {
  let tarjetasContainer = document.getElementById("tarjetas");

  let nuevaTarjeta = document.createElement("div");
    nuevaTarjeta.className = "card mb-3";
    nuevaTarjeta.innerHTML = `
        <div class="card-body bg-secondary text-white">
            <p>Proveedor: <strong>${producto.proveedor}</strong></p>
            <p>Código/SKU: <strong>${producto.sku}</strong></p>
            <p>Categoría: <strong>${producto.categoria}</strong></p>
            <p>Nombre del Producto: <strong>${producto.nombre}</strong></p>
            <p>Descripción: <strong>${producto.descripcion}</strong></p>
            <p>Precio: <strong>${producto.precio}</strong></p>
            <button class="btn btn-danger" onclick="eliminarTarjetaProducto(${index})">Eliminar</button>
        </div>
    `;

    tarjetasContainer.appendChild(nuevaTarjeta);
}

// Función para generar una nueva tarjeta de orden
function generarNuevaTarjetaOrden(orden, index) {
  let tarjetasContainer = document.getElementById("tarjetas2");

  let nuevaTarjeta = document.createElement("div");
    nuevaTarjeta.className = "card mb-3";
    nuevaTarjeta.innerHTML = `
        <div class="card-body bg-secondary-subtle ">
            <p>Número de Orden: <strong>${orden.numero}</strong></p>
            <p>Fecha de Emisión: <strong>${orden.emision}</strong></p>
            <p>Fecha de Entrega Esperada: <strong>${orden.entrega}</strong></p>
            <p>Dirección de Recepción: <strong>${orden.recepcion}</strong></p>
            <p>Proveedor: <strong>${orden.proveedor2}</strong></p>
            <p>Producto: <strong>${orden.producto2}</strong></p>
            <p>Cantidad: <strong>${orden.cantidad}</strong></p>
            <p>Total: <strong>${orden.total}</strong></p>
            <button class="btn btn-danger" onclick="eliminarTarjetaOrden(${index})">Eliminar</button>
        </div>
    `;

    tarjetasContainer.appendChild(nuevaTarjeta);
}

// Función para eliminar una tarjeta de proveedor
function eliminarTarjetaProveedor(index) {
    proveedoresGuardados.splice(index, 1);
    localStorage.setItem("proveedores", JSON.stringify(proveedoresGuardados));
    let cartasContainer = document.getElementById("cartas");
    cartasContainer.innerHTML = "";
    proveedoresGuardados.forEach(function (proveedor, index) {
        generarNuevaTarjetaProveedor(proveedor, index);
    });
}

// Función para eliminar una tarjeta de producto
function eliminarTarjetaProducto(index) {
    productosGuardados.splice(index, 1);
    localStorage.setItem("productos", JSON.stringify(productosGuardados));
    let tarjetasContainer = document.getElementById("tarjetas");
    tarjetasContainer.innerHTML = "";
    productosGuardados.forEach(function (producto, index) {
        generarNuevaTarjetaProducto(producto, index);
    });
}

// Función para eliminar una tarjeta de orden
function eliminarTarjetaOrden(index) {
    ordenesGuardadas.splice(index, 1);
    localStorage.setItem("ordenes", JSON.stringify(ordenesGuardadas));
    let tarjetasContainer = document.getElementById("tarjetas2");
    tarjetasContainer.innerHTML = "";
    ordenesGuardadas.forEach(function (orden, index) {
        generarNuevaTarjetaOrden(orden, index);
    });
}

// Muestra las tarjetas de proveedores si hay proveedores guardados
if (proveedoresGuardados.length > 0) {
    proveedoresGuardados.forEach(function (proveedor, index) {
        generarNuevaTarjetaProveedor(proveedor, index);
    });
}

// Muestra las tarjetas de productos si hay productos guardados
if (productosGuardados.length > 0) {
    productosGuardados.forEach(function (producto, index) {
        generarNuevaTarjetaProducto(producto, index);
    });
}

// Muestra las tarjetas de órdenes si hay órdenes de compra guardadas
if (ordenesGuardadas.length > 0) {
    ordenesGuardadas.forEach(function (orden, index) {
        generarNuevaTarjetaOrden(orden, index);
    });
}