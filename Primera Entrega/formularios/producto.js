let formularioProducto = document.getElementById("productoForm");
        let botonGuardarProducto = document.getElementById("guardar");
      
        // Cargar productos existentes desde localStorage
        let productosGuardados = JSON.parse(localStorage.getItem("productos")) || [];
      
        botonGuardarProducto.addEventListener("click", function () {
          let proveedor = document.getElementById("proveedor").value;
          let sku = document.getElementById("sku").value;
          let categoria = document.getElementById("categoria").value;
          let nombre = document.getElementById("nombre").value;
          let descripcion = document.getElementById("descripcion").value;
          let precio = document.getElementById("precio").value;
      
          let nuevoProducto = {
            proveedor: proveedor,
            sku: sku,
            categoria: categoria,
            nombre: nombre,
            descripcion: descripcion,
            precio: precio
          };
      
          // Agregar el nuevo producto al array existente
          productosGuardados.push(nuevoProducto);
      
          // Convertir el array a JSON y almacenarlo en localStorage
          localStorage.setItem("productos", JSON.stringify(productosGuardados));
      
          // Limpia el formulario
          formularioProducto.reset();
        });