let formulario = document.getElementById("ordenForm");
        let botonGuardar = document.getElementById("guardar");
    
        // Cargar órdenes existentes desde localStorage
        let ordenesGuardadas = JSON.parse(localStorage.getItem("ordenes")) || [];
    
        botonGuardar.addEventListener("click", function (event) {
            event.preventDefault();
    
            let numero = document.getElementById("numero").value;
            let emision = document.getElementById("emision").value;
            let entrega = document.getElementById("entrega").value;
            let recepcion = document.getElementById("recepcion").value;
            let proveedor = document.getElementById("proveedor2").value;
            let producto2 = document.getElementById("producto2").value;
            let cantidad = document.getElementById("cantidad").value;
            let total = document.getElementById("total").value;
    
            let nuevaOrden = {
                numero: numero,
                emision: emision,
                entrega: entrega,
                recepcion: recepcion,
                proveedor: proveedor,
                producto2: producto2,
                cantidad: cantidad,
                total: total,
            };
    
            // Agregar la nueva orden al array existente
            ordenesGuardadas.push(nuevaOrden);
    
            // Convertir el array a JSON y almacenarlo en localStorage
            localStorage.setItem("ordenes", JSON.stringify(ordenesGuardadas));
    
            // Limpia el formulario
            formulario.reset();
        });