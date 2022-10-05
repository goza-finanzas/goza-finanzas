use gozafinanzas;

CREATE TABLE usuarios(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(40) NOT NULL,
    apellido VARCHAR(40) NOT NULL,
    telefono VARCHAR(40) ,
    email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL
);

CREATE TABLE movimientos(
	id INT PRIMARY KEY auto_increment NOT NULL,
    usuarios_id INT NOT NULL,
    nombre VARCHAR(45) NOT NULL,
    fecha VARCHAR(45) NOT NULL,
    monto DOUBLE NOT NULL,
    categoria VARCHAR(45),
    descripcion VARCHAR(100) NOT NULL,
    FOREIGN KEY (usuarios_id) REFERENCES usuarios(id)
);

ALTER TABLE usuarios
MODIFY password VARCHAR(255) NOT NULL;

SELECT * FROM usuarios;

DROP TABLE usuarios;

DROP TABLE movimientos;