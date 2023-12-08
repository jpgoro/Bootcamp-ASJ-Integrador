let formulario = document.getElementById("proveedorForm");
  let botonGuardar = document.getElementById("guardar");

  // Cargar proveedores existentes desde localStorage
  let proveedoresGuardados = JSON.parse(localStorage.getItem("proveedores")) || [];
  
  botonGuardar.addEventListener("click", function () {
    

    let codigo = document.getElementById("codigo").value;
    let razon = document.getElementById("razon").value;
    let rubro = document.getElementById("rubro").value;
    let web = document.getElementById("web").value;
    let direccion = document.getElementById("direccion").value;
    let fiscales = document.getElementById("fiscales").value;
    let datosContacto = document.getElementById("floatingTextarea2").value;
    
    let nuevoProveedor = {
      codigo: codigo,
      razon: razon,
      rubro: rubro,
      web: web,
      direccion: direccion,
      fiscales: fiscales,
      datosContacto: datosContacto,
    };
    // Agregar el nuevo proveedor al array existente
    proveedoresGuardados.push(nuevoProveedor);

    // Convertir el array a JSON y almacenarlo en localStorage
    localStorage.setItem("proveedores", JSON.stringify(proveedoresGuardados));

    // Limpia el formulario
    formulario.reset();
  });