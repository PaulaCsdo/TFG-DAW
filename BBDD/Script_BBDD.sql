CREATE DATABASE RECETAS_DB;
USE RECETAS_DB;

CREATE TABLE `perfiles` (
  `ID_PERFIL` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `TIPO` varchar(20) NOT NULL
);


CREATE TABLE `categorias` (
  `ID_CATEGORIA` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `DESCRIPCION` varchar(50) DEFAULT NULL
);

CREATE TABLE `tipos_dietas` (
  `ID_TIPO_DIETA` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `NOMBRE` varchar(20) NOT NULL,
  `DESCRIPCION` varchar(45) DEFAULT NULL
);

CREATE TABLE `nivel_cocina` (
  `ID_NIVEL` int NOT NULL PRIMARY KEY,
  `NIVEL` varchar(20) NOT NULL,
  `DESCRIPCION` varchar(100) DEFAULT NULL
);

CREATE TABLE `usuarios` (
  `USERNAME` varchar(50) NOT NULL PRIMARY KEY,
  `PASSWORD` varchar(200) NOT NULL,
  `ENABLED` int DEFAULT NULL,
  `EMAIL` varchar(100) NOT NULL UNIQUE,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDO` varchar(50) DEFAULT NULL,
  `FECHA_ALTA` date DEFAULT NULL,
  `ID_TIPO_DIETA` int DEFAULT NULL,
  `ID_NIVEL` int DEFAULT NULL,
  FOREIGN KEY (`ID_NIVEL`) REFERENCES `nivel_cocina` (`ID_NIVEL`),
  FOREIGN KEY (`ID_TIPO_DIETA`) REFERENCES `tipos_dietas` (`ID_TIPO_DIETA`)
);

CREATE TABLE `usuario_perfiles` (
  `USERNAME` varchar(50) NOT NULL,
  `ID_PERFIL` int NOT NULL,
  PRIMARY KEY (`USERNAME`,`ID_PERFIL`),
  FOREIGN KEY (`USERNAME`) REFERENCES `usuarios` (`USERNAME`),
  FOREIGN KEY (`ID_PERFIL`) REFERENCES `perfiles` (`ID_PERFIL`)
);

CREATE TABLE `ingredientes` (
  `ID_INGREDIENTE` int NOT NULL PRIMARY KEY,
  `DESCRIPCION` varchar(50) DEFAULT NULL,
  `IMAGEN` blob DEFAULT NULL
);

CREATE TABLE `recetas` (
  `ID_RECETA` int NOT NULL PRIMARY KEY,
  `TITULO` varchar(200) NOT NULL,
  `NUM_PORCIONES` int DEFAULT NULL,
  `ID_NIVEL` int DEFAULT NULL,
  `ID_CATEGORIA` int NOT NULL,
  `TIEMPO` int DEFAULT NULL,
  `MOMENTO` varchar(200) NOT NULL,
  `KCAL` int DEFAULT NULL,
  `PUNTUACION` decimal(9,2) DEFAULT NULL,
  `ID_TIPO_DIETA` int DEFAULT NULL,
  `PASOS` mediumtext,
  `NOVEDAD` char(1) DEFAULT NULL,
  `IMAGEN` blob,
  `Autor` varchar(50) DEFAULT NULL,
  FOREIGN KEY (`Autor`) REFERENCES `usuarios` (`USERNAME`),
  FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `categorias` (`ID_CATEGORIA`),
  FOREIGN KEY (`ID_TIPO_DIETA`) REFERENCES `tipos_dietas` (`ID_TIPO_DIETA`),
  FOREIGN KEY (`ID_NIVEL`) REFERENCES `nivel_cocina` (`ID_NIVEL`)
);

CREATE TABLE `recetas_tipo_dieta` (
  `ID_RECETA` int NOT NULL,
  `ID_TIPO_DIETA` int NOT NULL,
  FOREIGN KEY (`ID_RECETA`) REFERENCES `recetas` (`ID_RECETA`),
  FOREIGN KEY (`ID_TIPO_DIETA`) REFERENCES `tipos_dietas` (`ID_TIPO_DIETA`)
);

CREATE TABLE `listas_compra` (
  `ID_LISTA_COMPRA` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `USERNAME` varchar(50) NOT NULL,
  FOREIGN KEY (`USERNAME`) REFERENCES `usuarios` (`USERNAME`)
);

CREATE TABLE `lineas_compra` (
  `ID_LINEA` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ID_LISTA_COMPRA` int NOT NULL,
  `ID_INGREDIENTE` int NOT NULL,
  `CANTIDAD` int DEFAULT NULL,
  FOREIGN KEY (`ID_LISTA_COMPRA`) REFERENCES `listas_compra` (`ID_LISTA_COMPRA`),
  FOREIGN KEY (`ID_INGREDIENTE`) REFERENCES `ingredientes` (`ID_INGREDIENTE`)
);

CREATE TABLE `receta_en_usuario` (
  `USERNAME` varchar(50) NOT NULL,
  `ID_RECETA` int NOT NULL,
  `AGENDADA` char(1) DEFAULT NULL,
  `GUARDADA` char(1) DEFAULT NULL,
  PRIMARY KEY (`USERNAME`,`ID_RECETA`),
  FOREIGN KEY (`USERNAME`) REFERENCES `usuarios` (`USERNAME`),
  FOREIGN KEY (`ID_RECETA`) REFERENCES `recetas` (`ID_RECETA`)
);

CREATE TABLE `ingrediente_en_receta` (
  `ID_RECETA` int NOT NULL,
  `ID_INGREDIENTE` int NOT NULL,
  `CANTIDAD` float DEFAULT NULL,
  `UNIDAD` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_RECETA`,`ID_INGREDIENTE`),
  FOREIGN KEY (`ID_RECETA`) REFERENCES `recetas` (`ID_RECETA`),
  FOREIGN KEY (`ID_INGREDIENTE`) REFERENCES `ingredientes` (`ID_INGREDIENTE`)
);



CREATE USER 'ureceta'@'localhost' IDENTIFIED BY 'ureceta';
GRANT ALL PRIVILEGES ON RECETAS_DB.* TO  'ureceta'@'localhost';



INSERT INTO recetas_db.categorias VALUES (1,'Arroces'),(2,'Carnes'),(3,'Sopas y cremas'),(4,'Ensaladas y bowls'),(5,'Guisos'),(6,'Legumbres'),(7,'Pastas'),(8,'Pescados y mariscos'),(9,'Revueltos y tortillas'),(10,'Salteados'),(11,'Postres'),(12,'Verduras'),(13,'5 ingredientes o menos'),(14,'20 minutos o menos');
INSERT INTO recetas_db.ingredientes VALUES (100,'Leche',NULL),(101,'Leche evaporada',NULL),(200,'Huevo',NULL),(201,'Lubina',NULL),(300,'Cebolla',NULL),(301,'Champiñón',NULL),(400,'Espárrago verde',NULL),(401,'Calabacín',NULL),(402,'Espinaca',NULL),(500,'Limón',NULL),(600,'Pan',NULL),(601,'Arroz',NULL),(700,'Aceite de oliva',NULL),(800,'Sal',NULL),(801,'Canela',NULL),(802,'Azúcar',NULL);
INSERT INTO recetas_db.nivel_cocina VALUES (1,'Bajo','No sé cocinar.'),(2,'Medio','Hago bien recetas sencillas.'),(3,'Alto','Me gusta aprender a hacer recetas nuevas.');
INSERT INTO recetas_db.perfiles VALUES (1,'Administrador'),(2,'Usuario'),(3,'Usuario Premium');
INSERT INTO recetas_db.tipos_dietas VALUES (1,'Como de todo',''),(2,'Flexitariano','Intento comer poca carne.'),(3,'Pescetariano','No como carne. Sí como pescado.'),(4,'Vegetariano','No como carne ni pescado.'),(5,'Vegano','Solo como alimentos de origen vegetal.'),(6,'Otra','Mi dieta es diferente a las demás.');
INSERT INTO recetas_db.recetas VALUES (1,'Crema de calabacín y espárragos verdes',2,1,3,30,'Cualquier momento',102,0.00,1,'- Pica la cebolla y los espárragos. Echa un chorrito de aceite en tu sartén y saltea durante 10 minutos las verduras que acabas de picar, hasta que la cebolla esté tierna.\n\n- Lava y trocea el calabacín y añádelo a una cazuela junto a los espárragos y la cebolla. Añade un poco de sal y cubre con agua. Tapa la cazuela y cuece durante unos 20 minutos, hasta que el calabacín esté tierno.\n\n- Cuando esté listo, tritura hasta que no queden grumos y ajusta de sal si lo consideras necesario.\n','S',_binary 'null',NULL),(2,'Torrijas',1,1,11,20,'Cualquier momento',497,0.00,1,'- En un cazo pon la leche, dos cucharadas de azúcar, una ramita de canela y un trozo grande de la cáscara de un limón. Cocina a fuego medio y cuando comience a hervir, apaga el fuego y deja que repose. Cuando esté casi frío, viértelo en un recipiente.\n\n- Bate en un plato hondo el huevo.\n\n- Pon a calentar una sartén con el aceite.\n\n- Cada rebanada de pan sumérgela por ambos lados en el recipiente que contiene la leche. Escúrrelas y pásalas, una a una, por el huevo y luego fríelas por ambos lados.\n\n- Retíralas de la sartén y ponlas sobre papel absorbente para quitarles el aceite.\n\n- En un plato echa el resto de azúcar y media cucharadita de canela en polvo y mézclalo. Pasa ambos lados de cada torrija por el azúcar con canela y sírvelas.','S',NULL,NULL),(3,'Lubina con champiñones cremosos y espinacas',1,1,8,20,'Almuerzo',568,0.00,3,'- Pica la cebolla en trozos muy pequeños.\n\n- Lava y corta los champiñones en láminas.\n\n- En una sartén echa un poco de aceite de oliva. Cuando esté caliente vierte en ella la cebolla y sofríela a fuego medio con cuidado de que no se queme. Pasados 2-3 minutos añade a la sartén los champiñones y cocínalos junto con la cebolla unos 8 minutos.\n\n- Añade a la sartén las espinacas cortadas. Con el calor irán reduciendo su volumen. Echa la leche evaporada y una pizca de sal a todo. Cocina a fuego bajo hasta que se forme una salsa cremosa.\n\n- Mientras, en otra sartén con un poco de aceite caliente, cocina a la plancha los dos filetes de lubina.\n\n- Coloca en un plato las lubinas acompañadas de los champiñones cremosos con espinacas y añade al plato un poquito de arroz integral cocido.','S',NULL,NULL);
INSERT INTO recetas_db.ingrediente_en_receta VALUES (1,300,0.25,'unidad'),(1,400,4,'unidades'),(1,401,0.5,'unidad'),(1,700,0.5,'cucharada'),(1,800,0.25,'cucharadita'),(2,100,0.5,'taza'),(2,200,0.5,'unidad'),(2,500,0.5,'unidad'),(2,600,2,'rebanadas'),(2,700,1.5,'cucharadas'),(2,801,1,'cucharadita'),(2,802,3,'cucharaditas'),(3,101,0.25,'taza'),(3,201,1,'filete'),(3,300,0.25,'unidad'),(3,301,6,'unidades'),(3,402,100,'gramos'),(3,601,1,'taza pequeña'),(3,700,1,'cucharada'),(3,800,0.5,'pizca');
INSERT INTO recetas_db.usuarios VALUES ('pilpil','blabla',1,'pilarnaviocolon@gmail.com','Pilar','Navío','2022-09-04',1,3);
INSERT INTO recetas_db.usuario_perfiles VALUES ('pilpil',1);
