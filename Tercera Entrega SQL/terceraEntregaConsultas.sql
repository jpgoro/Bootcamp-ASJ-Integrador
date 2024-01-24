/*ALTER TABLE Proveedores
ADD proveedor_cuit_new BIGINT;

UPDATE Proveedores
SET proveedor_cuit_new = CAST(proveedor_cuit AS BIGINT);

ALTER TABLE Proveedores
DROP COLUMN proveedor_cuit;

EXEC sp_rename 'Proveedores.proveedor_cuit_new', 'proveedor_cuit', 'COLUMN';
ALTER TABLE Ordenes_Compras
ADD total FLOAT,
    activa BIT;*/

--punto1
SELECT
    P.producto_nombre AS 'Nombre del Producto',
    C.categoria_nombre AS 'Categoría',
    PR.proveedor_razon AS 'Proveedor',
    PR.proveedor_codigo AS 'Código de Proveedor',
    P.producto_precio AS 'Precio'
FROM
    Productos AS P
    JOIN Categoria AS C ON P.categoria_id = C.id
    JOIN Proveedores AS PR ON P.proveedor_id = PR.id;
	


--punto2

	SELECT
    P.producto_nombre AS 'Nombre del Producto',
    C.categoria_nombre AS 'Categoría',
    PR.proveedor_razon AS 'Proveedor',
    PR.proveedor_codigo AS 'Código de Proveedor',
    P.producto_precio AS 'Precio',
    ISNULL(P.producto_imagen, '-') AS 'Imagen'
FROM
    Productos AS P
    JOIN Categoria AS C ON P.categoria_id = C.id
    JOIN Proveedores AS PR ON P.proveedor_id = PR.id;

	--punto 3
	SELECT 
    id,
    producto_SKU,
    producto_nombre,
    producto_descripcion,
    producto_precio,
    producto_imagen,
    categoria_id,
    proveedor_id
FROM Productos
WHERE id = 2;


--punto 4
SELECT Proveedores.*, Provincias.prov_nombre AS provincia_nombre
FROM Proveedores
LEFT JOIN Direcciones ON Proveedores.direccion_id = Direcciones.id
LEFT JOIN Localidades ON Direcciones.localidad_id = Localidades.id
LEFT JOIN Provincias ON Localidades.provincia_id = Provincias.id
WHERE 
    Proveedores.proveedor_tel LIKE '351%'
    OR Provincias.id IN (
        SELECT TOP 3 id
        FROM Provincias
        ORDER BY (SELECT COUNT(*) FROM Proveedores WHERE Proveedores.direccion_id = Direcciones.id) DESC
    )
    OR Proveedores.proveedor_tel IS NULL
ORDER BY Proveedores.id;



--punto 5
SELECT
    proveedor_razon,
    proveedor_web,
    proveedor_email,
    proveedor_tel,
    contacto_nomb,
    contacto_apell
FROM
    Proveedores
    LEFT JOIN Contactos ON Proveedores.id = Contactos.proveedor_id
ORDER BY
    proveedor_razon ASC,
    proveedor_codigo ASC,
    created_at ASC;

	

	--punto 6

	SELECT TOP 1
    p.proveedor_razon AS Razon_Social,
    p.proveedor_codigo AS Codigo_Proveedor,
    pr.contacto_nomb AS Contacto_Nombre,
    pr.contacto_apell AS Contacto_Apellido,
    p.proveedor_web AS Web,
    p.proveedor_email AS Email,
    p.proveedor_tel AS Telefono
FROM Proveedores p
JOIN Contactos pr ON p.id = pr.proveedor_id
JOIN Ordenes_Compras oc ON p.id = oc.proveedor_id
GROUP BY
    p.proveedor_razon,
    p.proveedor_codigo,
    pr.contacto_nomb,
    pr.contacto_apell,
    p.proveedor_web,
    p.proveedor_email,
    p.proveedor_tel
ORDER BY COUNT(oc.id) DESC;



--punto 7

SELECT
    OC.fecha_emision,
    OC.numero_orden,
    P.proveedor_razon,
    P.proveedor_codigo,
    COUNT(DOC.producto_id) AS cantidad_productos
FROM
    Ordenes_Compras OC
JOIN Proveedores P ON OC.proveedor_id = P.id
LEFT JOIN Detalles_OC DOC ON OC.id = DOC.orden_compra_id
GROUP BY
    OC.fecha_emision,
    OC.numero_orden,
    P.proveedor_razon,
    P.proveedor_codigo
ORDER BY
    OC.fecha_emision DESC, OC.numero_orden;

	

	--punto 8

	

	SELECT
    OC.fecha_emision,
    OC.numero_orden,
    P.proveedor_razon,
    P.proveedor_codigo,
    COUNT(DOC.producto_id) AS cantidad_productos,
    OC.activa,
    OC.total
FROM
    Ordenes_Compras OC
JOIN Proveedores P ON OC.proveedor_id = P.id
LEFT JOIN Detalles_OC DOC ON OC.id = DOC.orden_compra_id
GROUP BY
    OC.fecha_emision,
    OC.numero_orden,
    P.proveedor_razon,
    P.proveedor_codigo,
    OC.activa,
    OC.total
ORDER BY
    OC.fecha_emision DESC, OC.numero_orden;


--punto 9

-- detalle de compra esta tratando de usar total, esa columna pertene a oc. Corregir eso
	SELECT
    P.producto_SKU,
    P.producto_nombre,
    DOC.cantidad,
    OC.total
FROM
    Ordenes_Compras OC
JOIN
    Detalles_OC DOC ON OC.id = DOC.orden_compra_id
JOIN
    Productos P ON DOC.producto_id = P.id
WHERE
    OC.proveedor_id = 3;


