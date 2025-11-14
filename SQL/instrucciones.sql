-- Creamos la base de datos
CREATE DATABASE IF NOT EXISTS mascotaMicrochip;

-- Seleccionamos la base
USE mascotaMicrochip;

-- Creamos la Tabla microchip
CREATE TABLE IF NOT EXISTS microchip (
  id INT NOT NULL AUTO_INCREMENT,
  eliminado TINYINT(1) NULL DEFAULT 0 CHECK (eliminado IN (0,1)),
  codigo VARCHAR(25) NOT NULL,
  fechaImplantacion DATE NULL,
  veterinaria VARCHAR(120) NULL,
  observaciones VARCHAR(255) NULL,
  PRIMARY KEY (id)
);

-- Creamos la Tabla mascota
CREATE TABLE IF NOT EXISTS mascota (
  id INT NOT NULL AUTO_INCREMENT,
  eliminado TINYINT(1) NULL DEFAULT 0 CHECK (eliminado IN (0,1)),
  nombre VARCHAR(60) NOT NULL,
  especie VARCHAR(30) NOT NULL,
  raza VARCHAR(60) NOT NULL,
  fechaNacimiento DATE NULL,
  duenio VARCHAR(120) NOT NULL,
  telefonoDuenio VARCHAR(60),
  microchip INT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uq_microchip (microchip),
  CONSTRAINT fk_mascota_microchip
    FOREIGN KEY (microchip)
    REFERENCES microchip (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);