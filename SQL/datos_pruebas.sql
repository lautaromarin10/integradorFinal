-- Seleccionamos la base
USE mascotaMicrochip;

-- Carga de datos de prueba.

-- Primer animal
INSERT INTO microchip (codigo, fechaImplantacion, veterinaria, observaciones)
VALUES ("ABCAKS", "2025-01-10", "Huellitas", "Perro Amigable");

INSERT INTO mascota (nombre, especie, raza, fechaNacimiento, duenio, microchip)
VALUES ("Luz", "Perro", "Golden Retriever", "2015-12-10", "Lautaro", 1);

-- Segundo animal
INSERT INTO microchip (codigo, fechaImplantacion, veterinaria, observaciones)
VALUES ("AKSALA", "2025-10-10", "Pet Vet", "Gato gordito");

INSERT INTO mascota (nombre, especie, raza, fechaNacimiento, duenio, microchip)
VALUES ("Lola", "Gato", "Carey", "2015-10-24", "Matias", 2);

-- Tercer animal
INSERT INTO microchip (codigo, fechaImplantacion, veterinaria, observaciones)
VALUES ("LACASDF", "2025-12-25", "Huellitas", "Animal amigable");

INSERT INTO mascota (nombre, especie, raza, fechaNacimiento, duenio, microchip)
VALUES ("Roberta", "Perro", "Salchicha", "2022-10-22", "Fernando", 3);

-- Cuarto animal
INSERT INTO microchip (codigo, fechaImplantacion, veterinaria, observaciones)
VALUES ("ASTASK", "2025-11-21", "Pet Vet", "Pez saludable");

INSERT INTO mascota (nombre, especie, raza, fechaNacimiento, duenio, microchip)
VALUES ("Qwerty", "Pez", "dorado", "2025-10-10", "Joana", 4);

-- Quinto animal
INSERT INTO microchip (codigo, fechaImplantacion, veterinaria, observaciones)
VALUES ("KSLAS", "2022-07-21", "Huellitas", "Tortuga saludable");

INSERT INTO mascota (nombre, especie, raza, fechaNacimiento, duenio, microchip)
VALUES ("Manuelita", "Tortuga", "Mediterranea", "2021-10-10", "Fernando", 5);