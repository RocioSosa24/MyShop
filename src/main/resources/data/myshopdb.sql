select * from remera;
select * from zapato;
SELECT * from pantalon;
select * from producto;
select * from tipo_producto;
CREATE TABLE tipo_producto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

-- Insertar tipos de producto
INSERT INTO tipo_producto (nombre) VALUES
    ('remera'),
    ('pantalón'),
    ('zapato');

CREATE TABLE producto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    precio DOUBLE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    imagen VARCHAR(1055) NOT NULL,
    descripcion VARCHAR(100),
    talle varchar(5),
    creacion timestamp(0),
    id_tipo_producto INT NOT NULL,
    FOREIGN KEY (id_tipo_producto) REFERENCES tipo_producto(id)
);
CREATE TABLE carrito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(idusuario)
);

CREATE TABLE detalle_carrito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    carrito_id INT,
    producto_id INT,
    FOREIGN KEY (carrito_id) REFERENCES carrito(id),
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);
