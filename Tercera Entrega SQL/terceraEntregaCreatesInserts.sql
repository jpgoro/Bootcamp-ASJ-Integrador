CREATE DATABASE sgc

CREATE TABLE Paises (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    pais_nombre VARCHAR(50)
);

CREATE TABLE Provincias (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    prov_nombre VARCHAR(50),
    pais_id INT,
    FOREIGN KEY (pais_id) REFERENCES Paises(id)
);

CREATE TABLE Localidades (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    localidad_nombre VARCHAR(50),
    provincia_id INT,
    FOREIGN KEY (provincia_id) REFERENCES Provincias(id)
);

/*CREATE TABLE Direcciones (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    calle VARCHAR(50),
    numero INT,
    codigo_postal VARCHAR(50),
    localidad_id INT,
    FOREIGN KEY (localidad_id) REFERENCES Localidades(id)
);*/

INSERT INTO Paises (pais_nombre) VALUES
('Argentina'),
('Bolivia'),
('Brasil'),
('Chile'),
('Colombia'),
('Ecuador'),
('Guyana'),
('Paraguay'),
('Perú'),
('Surinam'),
('Uruguay'),
('Venezuela');

INSERT INTO Provincias (prov_nombre, pais_id) VALUES
('Buenos Aires', 1),
('Córdoba', 1),
('Santa Fe', 1),
('Mendoza', 1),
('Tucumán', 1),
('Entre Ríos', 1),
('Salta', 1),
('Misiones', 1),
('Chaco', 1),
('Corrientes', 1),
('Santiago del Estero', 1),
('Jujuy', 1),
('San Juan', 1),
('Río Negro', 1),
('Formosa', 1),
('Neuquén', 1),
('Chubut', 1),
('San Luis', 1),
('Catamarca', 1),
('La Rioja', 1),
('La Pampa', 1),
('Santa Cruz', 1),
('Tierra del Fuego', 1),
('Ciudad Autónoma de Buenos Aires', 1),

-- Bolivia
('La Paz', 2),
('Santa Cruz', 2),
('Cochabamba', 2),

-- Brasil
('São Paulo', 3),
('Río de Janeiro', 3),
('Bahía', 3),

-- Chile
('Santiago', 4),
('Valparaíso', 4),
('Bío Bío', 4),

-- Colombia
('Bogotá', 5),
('Antioquia', 5),
('Valle del Cauca', 5),

-- Ecuador
('Pichincha', 6),
('Guayas', 6),
('Azuay', 6),

-- Guyana
('Demerara-Mahaica', 7),
('Essequibo Islands-West Demerara', 7),

-- Paraguay
('Asunción', 8),
('Central', 8),
('Cordillera', 8),

-- Perú
('Lima', 9),
('Arequipa', 9),
('Cusco', 9),

-- Surinam
('Paramaribo', 10),
('Nickerie', 10),

-- Uruguay
('Montevideo', 11),
('Canelones', 11),
('Maldonado', 11),

-- Venezuela
('Distrito Capital', 12),
('Miranda', 12),
('Zulia', 12)

select * from Localidades

INSERT INTO Localidades (localidad_nombre, provincia_id) VALUES
-- Buenos Aires
('Mar del Plata', 1),
('La Plata', 1),
('Quilmes', 1),

-- Córdoba
('Córdoba', 2),
('Villa María', 2),
('Río Cuarto', 2),

-- Santa Fe
('Rosario', 3),
('Santa Fe', 3),
('Venado Tuerto', 3),

-- Mendoza
('Mendoza', 4),
('San Rafael', 4),
('Godoy Cruz', 4),

-- Tucumán
('San Miguel de Tucumán', 5),
('Yerba Buena', 5),
('Tafí Viejo', 5),

-- Entre Ríos
('Paraná', 6),
('Concordia', 6),
('Gualeguaychú', 6),

-- Salta
('Salta', 7),
('San Ramón de la Nueva Orán', 7),
('Tartagal', 7),

-- Misiones
('Posadas', 8),
('Oberá', 8),
('Eldorado', 8),

-- Chaco
('Resistencia', 9),
('Barranqueras', 9),
('Fontana', 9),

-- Corrientes
('Corrientes', 10),
('Goya', 10),
('Mercedes', 10),

-- Santiago del Estero
('Santiago del Estero', 11),
('La Banda', 11),
('Termas de Río Hondo', 11),

-- Jujuy
('San Salvador de Jujuy', 12),
('Palpalá', 12),
('Libertador General San Martín', 12),

-- San Juan
('San Juan', 13),
('Rivadavia', 13),
('Rawson', 13),

-- Río Negro
('Viedma', 14),
('General Roca', 14),
('Cipolletti', 14),

-- Formosa
('Formosa', 15),
('Clorinda', 15),
('Pirané', 15),

-- Neuquén
('Neuquén', 16),
('Cutral-Có', 16),
('Plottier', 16),

-- Chubut
('Rawson', 17),
('Comodoro Rivadavia', 17),
('Trelew', 17),

-- San Luis
('San Luis', 18),
('Villa Mercedes', 18),
('Merlo', 18),

-- Catamarca
('San Fernando del Valle de Catamarca', 19),
('San Isidro', 19),
('La Rioja', 19),

-- La Rioja
('La Rioja', 20),
('Chilecito', 20),
('Aimogasta', 20),

-- La Pampa
('Santa Rosa', 21),
('General Pico', 21),
('Toay', 21),

-- Santa Cruz
('Río Gallegos', 22),
('Caleta Olivia', 22),
('Puerto Deseado', 22),

-- Tierra del Fuego
('Ushuaia', 23),
('Río Grande', 23),
('Tolhuin', 23),

-- Ciudad Autónoma de Buenos Aires
('Balvanera', 24),
('Belgrano', 24),
('Recoleta', 24),

-- La Paz (Bolivia)
('La Paz', 25),
('El Alto', 25),
('Viacha', 25),

-- Santa Cruz (Bolivia)
('Santa Cruz de la Sierra', 26),
('El Torno', 26),
('Montero', 26),

-- Cochabamba (Bolivia)
('Cochabamba', 27),
('Quillacollo', 27),
('Sacaba', 27),

-- São Paulo (Brasil)
('São Paulo', 28),
('Guarulhos', 28),
('Campinas', 28),

-- Río de Janeiro (Brasil)
('Río de Janeiro', 29),
('Nova Iguaçu', 29),
('São Gonçalo', 29),

-- Bahía (Brasil)
('Salvador', 30),
('Feira de Santana', 30),
('Vitória da Conquista', 30),

-- Santiago (Chile)
('Santiago', 31),
('Providencia', 31),
('Las Condes', 31),

-- Valparaíso (Chile)
('Valparaíso', 32),
('Viña del Mar', 32),
('Quilpué', 32),

-- Bío Bío (Chile)
('Concepción', 33),
('Talcahuano', 33),
('Chillán', 33),

-- Bogotá (Colombia)
('Bogotá', 34),
('Suba', 34),
('Usaquén', 34),

-- Antioquia (Colombia)
('Medellín', 35),
('Bello', 35),
('Itagüí', 35),

-- Valle del Cauca (Colombia)
('Cali', 36),
('Palmira', 36),
('Buenaventura', 36),
-- Pichincha (Ecuador)
('Quito', 37),
('Cayambe', 37),
('Sangolquí', 37),

-- Guayas (Ecuador)
('Guayaquil', 38),
('Samborondón', 38),
('Daule', 38),

-- Azuay (Ecuador)
('Cuenca', 39),
('Azuay1', 39),
('Azuay2', 39),

-- Demerara-Mahaica (Guyana)
('Georgetown', 40),
('Paradise', 40),
('Mahaica Village', 40),

-- Essequibo Islands-West Demerara (Guyana)
('Vreed-en-Hoop', 41),
('Parika', 41),
('Leonora', 41),

-- Asunción (Paraguay)
('Asunción', 42),
('San Lorenzo', 42),
('Luque', 42),

-- Central (Paraguay)
('Fernando de la Mora', 43),
('Lambaré', 43),
('Capiatá', 43),

-- Cordillera (Paraguay)
('Caacupé', 44),
('Itauguá', 44),
('Altos', 44),

-- Lima (Perú)
('Lima', 45),
('Callao', 45),
('Surco', 45),

-- Arequipa (Perú)
('Arequipa', 46),
('Cayma', 46),
('Yanahuara', 46),

-- Cusco (Perú)
('Cusco', 47),
('Wanchaq', 47),
('San Sebastián', 47),

-- Paramaribo (Surinam)
('Paramaribo', 48),
('Latour', 48),
('Flora', 48),

-- Nickerie (Surinam)
('Nieuw Nickerie', 49),
('Oostelijke Polders', 49),
('Paradise', 49),

-- Montevideo (Uruguay)
('Montevideo', 50),
('Pocitos', 50),
('Carrasco', 50),

-- Canelones (Uruguay)
('Canelones', 51),
('Las Piedras', 51),
('Progreso', 51),

-- Maldonado (Uruguay)
('Maldonado', 52),
('Punta del Este', 52),
('San Carlos', 52),

-- Distrito Capital (Venezuela)
('Caracas', 53),
('La Candelaria', 53),
('Chacao', 53),

-- Miranda (Venezuela)
('Los Teques', 54),
('Petare', 54),
('Guarenas', 54),

-- Zulia (Venezuela)
('Maracaibo', 55),
('Cabimas', 55),
('Ciudad Ojeda', 55);

CREATE TABLE Direcciones (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    calle VARCHAR(50),
    numero INT,
    codigo_postal VARCHAR(50),
    localidad_id INT,
    FOREIGN KEY (localidad_id) REFERENCES Localidades(id)
);

INSERT INTO Direcciones (calle, numero, codigo_postal, localidad_id) VALUES
('Avenida H', 505, '54321', 1), 
('Rua I', 303, '01010-123', 28),
('Calle J', 404, '8300', 46), 
('Avenida K', 101, '67890', 11), 
('Calle L', 202, '20001', 34),
('Rua M', 303, '30002', 41),
('Calle N', 404, '40003', 52);

CREATE TABLE Rubros (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    rubro_titulo VARCHAR(50) NOT NULL
);

INSERT INTO Rubros (rubro_titulo) VALUES
('Telecomunicaciones'),
('Suministros de oficina y mobiliario'),
('Servicios Financieros'),
('Logística y Transporte'),
('Construcción'),
('Servicios de Marketing y Publicidad'),
('Energía y Servicios Públicos'),
('Salud y Farmacéutica'),
('Alimentación y Bebidas');

CREATE TABLE Condicion_Iva (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    iva_nombre VARCHAR(50) NOT NULL
);

INSERT INTO Condicion_Iva (iva_nombre) VALUES
('Responsable Inscripto'),
('Responsable no Inscripto'),
('No Responsable'),
('Sujeto Exento'),
('Consumidor Final');

CREATE TABLE Proveedores (
	id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	proveedor_codigo VARCHAR(50),
	proveedor_razon VARCHAR(50),
	proveedor_web VARCHAR(50),
	proveedor_tel VARCHAR(50),
	proveedor_email VARCHAR(50),
	proveedor_cuit INT,
	created_at DATETIME,
	update_at DATETIME,
	rubro_id INT,
	direccion_id INT,
	iva_id INT,
	FOREIGN KEY (rubro_id)REFERENCES Rubros(id),
	FOREIGN KEY (direccion_id)REFERENCES Direcciones(id),
	FOREIGN KEY (iva_id)REFERENCES Condicion_Iva(id),
	)

CREATE TABLE Contactos (
	id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	contacto_nomb VARCHAR(50),
	contacto_apell VARCHAR(50),
	contacto_tel VARCHAR(50),
	contacto_email VARCHAR(50),
	contacto_rol VARCHAR(50),
	proveedor_id INT,
	FOREIGN KEY (proveedor_id)REFERENCES Proveedores(id)
	)

INSERT INTO Proveedores (
    proveedor_codigo, 
    proveedor_razon, 
    proveedor_web, 
    proveedor_tel, 
    proveedor_email, 
    created_at, 
    update_at, 
    rubro_id, 
    direccion_id, 
    iva_id,
    proveedor_cuit
) VALUES
('PRV001', 'InnovateTech Solutions', 'www.innovatetech.com', '123-456-7890', 'info@innovatetech.com', GETDATE(), GETDATE(), 1, 1, 1, 30568974201),
('PRV002', 'Global Build Solutions', 'www.globalbuild.com', '987-654-3210', 'info@globalbuild.com', GETDATE(), GETDATE(), 5, 2, 2, 20867543145),
('PRV003', 'MediPharm Innovations', 'www.medipharm.com', '555-123-4567', 'info@medipharm.com', GETDATE(), GETDATE(), 8, 3, 3, 15283749023),
('PRV004', 'LogiExpress Solutions', 'www.logiexpress.com', '789-987-6543', 'info@logiexpress.com', GETDATE(), GETDATE(), 4, 4, 4, 30456789012),
('PRV005', 'EcoPower Utilities', 'www.ecopower.com', '234-567-8901', 'info@ecopower.com', GETDATE(), GETDATE(), 7, 5, 5, 10345678901),
('PRV006', 'OfficeSupp Solutions', 'www.officesupp.com', '876-543-2109', 'info@officesupp.com', GETDATE(), GETDATE(), 2, 6, 1, 20678901234),
('PRV007', 'FinanTech Services', 'www.finantech.com', '321-654-0987', 'info@finantech.com', GETDATE(), GETDATE(), 3, 7, 2, 30901234567),
('PRV008', 'Delicious Delights', 'www.deliciousdelights.com', '654-321-0987', 'info@deliciousdelights.com', GETDATE(), GETDATE(), 9, 3, 3, 40876543210),
('PRV009', 'AdvertiseHub Solutions', 'www.advertisehub.com', '987-654-3210', 'info@advertisehub.com', GETDATE(), GETDATE(), 6,1, 4, 50987654321),
('PRV010', 'TeleConnect Innovations', 'www.teleconnect.com', '555-555-5555', 'info@teleconnect.com', GETDATE(), GETDATE(), 1, 7, 5, 10234567890);


INSERT INTO Contactos (
    contacto_nomb, 
    contacto_apell, 
    contacto_tel, 
    contacto_email, 
    contacto_rol, 
    proveedor_id
) VALUES
('Carlos', 'Gómez', '555-111-2222', 'carlos.gomez@innovatetech.com', 'Gerente de Ventas', 27),
('Laura', 'Martínez', '777-333-4444', 'laura.martinez@globalbuild.com', 'Director de Operaciones', 28),
('Rodrigo', 'Fernández', '999-555-6666', 'rodrigo.fernandez@medipharm.com', 'Responsable de Compras', 29),
('Ana', 'Pérez', '111-777-8888', 'ana.perez@logiexpress.com', 'Coordinador de Logística', 30),
('Diego', 'Hernández', '222-888-9999', 'diego.hernandez@ecopower.com', 'Analista de Sostenibilidad', 31),
('Martina', 'López', '444-000-1111', 'martina.lopez@officesupp.com', 'Especialista en Suministros', 32),
('Pablo', 'Sánchez', '666-222-3333', 'pablo.sanchez@finantech.com', 'Analista Financiero', 33);


CREATE TABLE Categoria (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    categoria_nombre VARCHAR(50) NOT NULL
);

INSERT INTO Categoria (categoria_nombre) 
VALUES
	('Electrónica'),
	('Ropa'),
	('Hogar'),
	('Alimentación'),
	('Salud y Belleza'),
	('Deportes'),
	('Juguetes'),
	('Libros'),
	('Automóviles'),
	('Electrodomésticos');

	CREATE TABLE Productos (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    producto_SKU VARCHAR(20),
    producto_nombre VARCHAR(50),
    producto_descripcion VARCHAR(MAX),
    producto_precio FLOAT,
    producto_imagen VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    categoria_id INT,
    proveedor_id INT,
    FOREIGN KEY (categoria_id) REFERENCES Categoria(id),
    FOREIGN KEY (proveedor_id) REFERENCES Proveedores(id)
	);

	INSERT INTO Productos (
    producto_SKU,
    producto_nombre,
    producto_descripcion,
    producto_precio,
    producto_imagen,
    created_at,
    updated_at,
    categoria_id,
    proveedor_id
) VALUES
    ('SKU001', 'Smartphone Galaxy', 'Teléfono inteligente con pantalla OLED', 799.99, 'https://cdn.phonemore.com/content/2020/jpg/13082.jpg', GETDATE(), GETDATE(), 1, 27),
    ('SKU002', 'Zapatillas Running', 'Zapatillas deportivas para correr', 89.99, 'https://media.revistagq.com/photos/63f5e18c29543f9222599b30/master/pass/nike-running-shoes-streakfly-invincible.jpg', GETDATE(), GETDATE(), 2, 28),
    ('SKU003', 'Sofá Reclinable', 'Sofá cómodo con función reclinable', 499.99, 'https://acdn.mitiendanube.com/stores/281/365/products/corfam29941-07c010ec6b17dd83e916946214640465-640-0.jpg', GETDATE(), GETDATE(), 3, 29),
    ('SKU004', 'Arroz Integral', 'Arroz integral de alta calidad', 3.99, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZIzf13CDEeGJ8TQGwazb2aqyMuI7Ke_8wfxH59X1wMA&s', GETDATE(), GETDATE(), 4, 30),
    ('SKU005', 'Kit de Maquillaje', 'Colección de maquillaje profesional', 49.99, 'https://m.media-amazon.com/images/I/81WF98gtYHL.jpg', GETDATE(), GETDATE(), 5, 31),
    ('SKU006', 'Pelota de Fútbol', 'Pelota oficial para partidos', 19.99, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuDu3reOYVnpz9dq-GANUQ3SWIlqfeY4c4eYdPvCzFIQ&s', GETDATE(), GETDATE(), 6, 32),
    ('SKU007', 'Muñeca Articulada', 'Muñeca articulada para coleccionistas', 29.99, 'https://interpresentes.com.ar/images/thumbs/0508313_Inter%20Presentes.jpg', GETDATE(), GETDATE(), 7, 33),
    ('SKU008', 'Libro de Ciencia Ficción', 'Novela fascinante de ciencia ficción', 14.99, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQj3O_SugqUJCfh6-zJULa6lntzFLSa1NfXIZtSD-V8hQ&s', GETDATE(), GETDATE(), 8, 34),
    ('SKU009', 'Automóvil Eléctrico', 'Automóvil eléctrico de última generación', 29999.99, 'https://media.istockphoto.com/id/1348631007/es/foto/estaci%C3%B3n-de-carga-ev-para-coche-el%C3%A9ctrico-en-concepto-de-energ%C3%ADa-verde-y-energ%C3%ADa-ecol%C3%B3gica.jpg?s=612x612&w=0&k=20&c=IQQlt1Lc6CBcm-xCYDSnI8wji3PMQSiq9Ny0tIKnenc=', GETDATE(), GETDATE(), 9, 35),
    ('SKU010', 'Licuadora Potente', 'Licuadora con múltiples funciones', 59.99, 'https://osterar.vteximg.com.br/arquivos/ids/155977-1000-1000/BLSTPYG1211NBG-1.jpg?v=637063377771970000', GETDATE(), GETDATE(), 10, 36),
    ('SKU011', 'Lavarropas de Carga Frontal', 'Lavarropas eficiente y de bajo consumo', 399.99, 'https://www.lg.com/ar/images/lavarropas/md07539557/gallery/01_Topgun2_WM16WG2S6_F0L9DYP0W_Frontg.jpg', GETDATE(), GETDATE(), 3, 27),
    ('SKU012', 'Auriculares Inalámbricos', 'Auriculares Bluetooth de alta calidad', 79.99, 'https://www.shutterstock.com/image-vector/3d-wireless-headphones-mockup-set-600nw-2130630635.jpg', GETDATE(), GETDATE(), 1, 28);
	

	CREATE TABLE Estados (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    estado_titulo VARCHAR(50) NOT NULL
	);
	

	INSERT INTO Estados (estado_titulo) VALUES
		('Pendiente de Aprobación'),
		('Aprobada'),
		('En Proceso'),
		('En Tránsito'),
		('Entregada'),
		('Cancelada'),
		('Completada');


CREATE TABLE Ordenes_Compras (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    numero_orden INT,
    fecha_emision DATETIME,
    fecha_entrega DATETIME,
    info_recepcion VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    proveedor_id INT,
    estado_id INT,
    FOREIGN KEY (proveedor_id) REFERENCES Proveedores(id),
    FOREIGN KEY (estado_id) REFERENCES Estados(id)
);



CREATE TABLE Detalles_OC (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    cantidad INT,
    created_at DATETIME,
    updated_at DATETIME,
    producto_id INT,
    orden_compra_id INT,
    FOREIGN KEY (producto_id) REFERENCES Productos(id),
    FOREIGN KEY (orden_compra_id) REFERENCES Ordenes_Compras(id)
); 


INSERT INTO Direcciones (calle, numero, codigo_postal, localidad_id)
VALUES
('Avenida de las Flores', 405, '5003', 2),
('Calle de los Sueños', 123, '5000', 2),
('Boulevard del Arte', 789, '5001', 2),
('Rincón del Sol', 210, '5002', 2);



INSERT INTO Proveedores (
    proveedor_codigo, 
    proveedor_razon, 
    proveedor_web, 
    proveedor_tel, 
    proveedor_email, 
    created_at, 
    update_at, 
    rubro_id, 
    direccion_id, 
    iva_id,
    proveedor_cuit
) VALUES
('PRV015', 'Muebles Cordobeses', 'www.mueblescordobeses.com', '351-555-4444', 'info@mueblescordobeses.com', GETDATE(), GETDATE(), 3, 8, 5, 31123456789),
('PRV016', 'ElectroHogar Córdoba', 'www.electrohogarcordoba.com', '351-777-8888', 'info@electrohogarcordoba.com', GETDATE(), GETDATE(), 1, 9, 1, 41123456789),
('PRV017', 'ModaCord', 'www.modacord.com', '351-333-2222', 'info@modacord.com', GETDATE(), GETDATE(), 2, 10, 3, 51123456789),
('PRV018', 'Carnicería El Gaucho', 'www.elgaucho.com', '351-999-9999', 'info@elgaucho.com', GETDATE(), GETDATE(), 9, 11, 2, 61123456789);



INSERT INTO Ordenes_Compras (
    numero_orden,
    fecha_emision,
    fecha_entrega,
    info_recepcion,
    created_at,
    updated_at,
    proveedor_id,
    estado_id
)
VALUES
(1001,  '2023-01-15 14:30:00', '2023-12-07 09:30:00 ','Recepción en almacén', GETDATE(), GETDATE(), 27, 1),
(1002,  '2023-11-13 04:30:00', '2023-12-21 14:45:00 ','En proceso de envío', GETDATE(), GETDATE(), 28, 2),
(1003, '2023-10-05 12:30:00', '2024-01-03 11:20:00', 'En tránsito hacia la sucursal', GETDATE(), GETDATE(), 29, 3),
(1004, '2023-11-11 18:05:00', '2023-12-10 07:50:00', 'Entregada al cliente', GETDATE(), GETDATE(), 30, 4),
(1005, '2023-08-29 09:15:00', '2024-01-17 17:00:00', 'Cancelada por cliente', GETDATE(), GETDATE(), 40, 6),
(1006, '2023-09-14 14:40:00', '2023-12-14 08:15:00', 'Completada con éxito', GETDATE(), GETDATE(), 32, 7),
(1007, '2023-10-18 10:55:00', '2024-02-22 14:40:00', 'En proceso de aprobación', GETDATE(), GETDATE(), 33, 1),
(1008, '2023-11-27 16:25:00', '2024-01-08 09:15:00', 'Aprobada para despacho', GETDATE(), GETDATE(), 34, 2),
(1009, '2023-08-17 07:50:00', '2023-12-19 07:50:00', 'Entregada a cliente corporativo', GETDATE(), GETDATE(), 36, 4),
(1011, '2023-08-30 09:15:00', '2023-12-25 16:25:00', 'Completada y facturada', GETDATE(), GETDATE(), 39, 7),
(1012, '2023-02-13 04:30:00', '2024-02-02 18:05:00', 'Cancelada por falta de stock', GETDATE(), GETDATE(), 40, 6);




INSERT INTO Detalles_OC (cantidad, created_at, updated_at, producto_id, orden_compra_id)
VALUES
(5, GETDATE(), GETDATE(), 1, 1),
(10, GETDATE(), GETDATE(), 2, 2),
(8, GETDATE(), GETDATE(), 3, 3),
(15, GETDATE(), GETDATE(), 4, 4),
(3, GETDATE(), GETDATE(), 5, 5),
(12, GETDATE(), GETDATE(), 6, 6),
(7, GETDATE(), GETDATE(), 7, 7),
(9, GETDATE(), GETDATE(), 8, 4),
(6, GETDATE(), GETDATE(), 9, 9),
(4, GETDATE(), GETDATE(), 10, 10),
(11, GETDATE(), GETDATE(), 11, 1),
(2, GETDATE(), GETDATE(), 12, 11);












	

	



