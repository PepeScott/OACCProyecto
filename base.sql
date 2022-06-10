SET NAMES 'utf8';
DROP DATABASE IF EXISTS base;
CREATE DATABASE IF NOT EXISTS base DEFAULT CHARACTER SET utf8;
USE base;
CREATE TABLE clientes(
id_clientes					INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
nombre_clientes				VARCHAR(25) NOT NULL, 
apellido_clientes			VARCHAR(25) NOT NULL
)DEFAULT CHARACTER SET utf8;

INSERT INTO clientes(nombre_clientes,apellido_clientes) VALUES('Cliente1','Apellido1');
INSERT INTO clientes(nombre_clientes,apellido_clientes) VALUES('Cliente2','Apellido2');
INSERT INTO clientes(nombre_clientes,apellido_clientes) VALUES('Cliente3','Apellido3');

DELIMITER $$
create trigger clientes_mayus before insert on clientes for each row 
begin
  set new.nombre_clientes=upper(new.nombre_clientes);
  set new.apellido_clientes=upper(new.apellido_clientes);
end $$
DELIMITER ;

DELIMITER $$
create trigger clientes_mayus_update before update on clientes for each row 
begin
  set new.nombre_clientes=upper(new.nombre_clientes);
  set new.apellido_clientes=upper(new.apellido_clientes);
end $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarClientes`(IN nombre VARCHAR(25), IN apellido VARCHAR(25))
BEGIN
	INSERT INTO clientes(nombre_clientes, apellido_clientes) VALUES(nombre, apellido);
END $$

DELIMITER $$
CREATE PROCEDURE eliminarCliente(IN id INT)
BEGIN
	DELETE FROM clientes WHERE id_clientes = id;
END $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `todosClientes`()
BEGIN
	SELECT * FROM todosClientes;
END $$

DELIMITER $$
CREATE PROCEDURE modificarClientes(IN id INT, IN nombre VARCHAR(25), IN apellido VARCHAR(25))
BEGIN
	UPDATE clientes SET nombre_clientes = nombre, apellido_clientes = apellido WHERE id_clientes = id;
END $$

CREATE
	ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
VIEW `base`.`todosclientes` AS
	SELECT
		`base`.`clientes`.`id_clientes` AS `id_clientes`,
        `base`.`clientes`.`nombre_clientes` AS `nombre_clientes`,
        `base`.`clientes`.`apellido_clientes` AS `apellido_clientes`
	FROM
		`base`.`clientes`;
