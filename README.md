# Proyect Integrador Final

Desarrollo de un *Sistema de Gestión Compras* para manejar información de Proveedores, Productos y Órdenes de compra.


# IMPORTANTE: Base de datos H2

## Ejecutar localmente

Pasos necesarios para correr el proyecto localmente

- Crear una base de datos llamada
```sql
  CREATE DATABASE miProyecto;
```

- Crear la(s) siguiente(s) tabla(s)
```sql
  CREATE TABLE countries (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    country_name VARCHAR(50),
    created_at DATETIME,
    updated_at DATETIME
);
```
```sql
CREATE TABLE provinces (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    province_name VARCHAR(50),
    id_country INT,
    FOREIGN KEY (id_country) REFERENCES countries(id)
);
```

```sql
CREATE TABLE localities (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    locality_name VARCHAR(50),
    id_province INT,
    FOREIGN KEY (id_province) REFERENCES provinces(id)
);
```

```sql
CREATE TABLE status (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    status_name VARCHAR(50) NOT NULL
	);

```sql
 CREATE TABLE categories (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    category_name VARCHAR(50) NOT NULL,
    is_active BIT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);
```
```sql
CREATE TABLE conditionsIva (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    tax_condition VARCHAR(50) NOT NULL
);

```sql
CREATE TABLE indistries (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    industryName VARCHAR(50) NOT NULL,
    is_active BIT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);
```
- Insertar **Country**

```sql
INSERT INTO countries (country_name, created_at, updated_at)
VALUES ('México', GETDATE(), GETDATE()),
('Argentina', GETDATE(), GETDATE()),
('Chile', GETDATE(), GETDATE());
```
- Insertar  **Provinces**

```sql
 -- Insertar provincia 1: Buenos Aires (Argentina)
INSERT INTO provinces (province_name, id_country)
VALUES ('Buenos Aires', 2); 

-- Insertar provincia 2: Córdoba (Argentina)
INSERT INTO provinces (province_name, id_country)
VALUES ('Córdoba', 2); 

-- Insertar provincia 3: Santa Fe (Argentina)
INSERT INTO provinces (province_name, id_country)
VALUES ('Santa Fe', 2);
```



- Insertar  **locality**

```sql
 INSERT INTO localities (locality_name, id_provinces) VALUES
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
-- Valparaíso (Chile)
('Valparaíso', 3),
('Viña del Mar', 3),
('Quilpué', 3),
```
- Insertar  **industry**

```sql
 -- Insertar industria 1: Tecnología
INSERT INTO indistries (industryName, is_active, created_at, updated_at)
VALUES ('Tecnología', 1, GETDATE(), GETDATE());

-- Insertar industria 2: Agricultura
INSERT INTO indistries (industryName, is_active, created_at, updated_at)
VALUES ('Agricultura', 1, GETDATE(), GETDATE());

-- Insertar industria 3: Manufactura
INSERT INTO indistries (industryName, is_active, created_at, updated_at)
VALUES ('Manufactura', 1, GETDATE(), GETDATE());
```


- Insertar  **status**

```sql
-- Insertar categoría 1: Activa
INSERT INTO status (status_name)
VALUES ('Activa');

-- Insertar categoría 2: Finalizada
INSERT INTO status (status_name)
VALUES ('Finalizada');

-- Insertar categoría 3: Cancelada
INSERT INTO status (status_name)
VALUES ('Cancelada');

-- Insertar categoría 4: En Pausa
INSERT INTO status (status_name)
VALUES ('En Pausa');
```

- Insertar  **categories**

```sql
-- Insertar categoría 1: Electrónica
INSERT INTO categories (category_name, is_active, created_at, updated_at)
VALUES ('Electrónica', 1, GETDATE(), GETDATE());

-- Insertar categoría 2: Ropa
INSERT INTO categories (category_name, is_active, created_at, updated_at)
VALUES ('Ropa', 1, GETDATE(), GETDATE());

-- Insertar categoría 3: Alimentos
INSERT INTO categories (category_name, is_active, created_at, updated_at)
VALUES ('Alimentos', 1, GETDATE(), GETDATE());

-- Insertar categoría 4: Juguetes
INSERT INTO categories (category_name, is_active, created_at, updated_at)
VALUES ('Juguetes', 1, GETDATE(), GETDATE());
```


- Insertar  **conditionIva**

```sql
-- Insertar condición de IVA 1: Responsable Inscripto
INSERT INTO conditionsIva (tax_condition)
VALUES ('Responsable Inscripto');

-- Insertar condición de IVA 2: Monotributista
INSERT INTO conditionsIva (tax_condition)
VALUES ('Monotributista');

-- Insertar condición de IVA 3: Exento
INSERT INTO conditionsIva (tax_condition)
VALUES ('Exento');
```



- Ejecutar el servidor de Angular (*puerto 4200*)

```bash
  ng start -o
```

- Ejecutar el servidor de Java (*puerto 8080*)


- Insertar algunas **suppliers** desde el FRONT

- Insertar algunas **products** desde el FRONT

- Insertar algunas **purchases_orders** desde el FRONT

-  Insertar algunas **details_oc** desde el FRONT
-  Insertar algunas **address** desde el FRONT
-  Insertar algunas **contacts** desde el FRONT


  ## Desarrollado por:

Este proyecto fue desarrollado por: **Pablo Gorosito**
