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
  `PASOS` mediumtext,
  `NOVEDAD` char(1) DEFAULT NULL,
  `IMAGEN` blob,
  `Autor` varchar(50) DEFAULT NULL,
  FOREIGN KEY (`Autor`) REFERENCES `usuarios` (`USERNAME`),
  FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `categorias` (`ID_CATEGORIA`),
  FOREIGN KEY (`ID_NIVEL`) REFERENCES `nivel_cocina` (`ID_NIVEL`)
);

CREATE TABLE `tipo_dieta_receta` (
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
 `ID_RECETAUSUARIO` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `USERNAME` varchar(50) NOT NULL,
  `ID_RECETA` int NOT NULL,
  `AGENDADA` char(1) DEFAULT NULL,
  `GUARDADA` char(1) DEFAULT NULL,
  FOREIGN KEY (`USERNAME`) REFERENCES `usuarios` (`USERNAME`),
  FOREIGN KEY (`ID_RECETA`) REFERENCES `recetas` (`ID_RECETA`)
);

CREATE TABLE `ingrediente_en_receta` (
`ID_INGREDIENTERECETA` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ID_RECETA` int NOT NULL,
  `ID_INGREDIENTE` int NOT NULL,
  `CANTIDAD` float DEFAULT NULL,
  `UNIDAD` varchar(45) DEFAULT NULL,
  FOREIGN KEY (`ID_RECETA`) REFERENCES `recetas` (`ID_RECETA`),
  FOREIGN KEY (`ID_INGREDIENTE`) REFERENCES `ingredientes` (`ID_INGREDIENTE`)
);


INSERT INTO recetas_db.categorias VALUES (1,'Arroces'),(2,'Carnes'),(3,'Sopas y cremas'),(4,'Ensaladas y bowls'),(5,'Guisos'),(6,'Legumbres'),(7,'Pastas'),(8,'Pescados y mariscos'),(9,'Revueltos y tortillas'),(10,'Salteados'),(11,'Postres'),(12,'Verduras'),(13,'5 ingredientes o menos'),(14,'20 minutos o menos');

-- INSERT INTO recetas_db.ingredientes VALUES (100,'Leche',NULL),(101,'Leche evaporada',NULL),(200,'Huevo',NULL),(201,'Lubina',NULL),(300,'Cebolla',NULL),(301,'Champiñón',NULL),(400,'Espárrago verde',NULL),(401,'Calabacín',NULL),(402,'Espinaca',NULL),(500,'Limón',NULL),(600,'Pan',NULL),(601,'Arroz',NULL),(700,'Aceite de oliva',NULL),(800,'Sal',NULL),(801,'Canela',NULL),(802,'Azúcar',NULL);
INSERT INTO recetas_db.categorias VALUES (100,'Leche',NULL);
INSERT INTO recetas_db.categorias VALUES (101,'Leche evaporada',NULL);
INSERT INTO recetas_db.categorias VALUES (200,'Huevo',NULL);
INSERT INTO recetas_db.categorias VALUES (201,'Lubina',NULL);
INSERT INTO recetas_db.categorias VALUES (300,'Cebolla',NULL);
INSERT INTO recetas_db.categorias VALUES (301,'Champiñón',NULL);
INSERT INTO recetas_db.categorias VALUES (400,'Espárrago verde',NULL);
INSERT INTO recetas_db.categorias VALUES (401,'Calabacín',NULL);
INSERT INTO recetas_db.categorias VALUES (402,'Espinaca',NULL);
INSERT INTO recetas_db.categorias VALUES (403,'Ajo',NULL);
INSERT INTO recetas_db.categorias VALUES (500,'Limón',NULL);
INSERT INTO recetas_db.categorias VALUES (501,'Manzana',NULL);
INSERT INTO recetas_db.categorias VALUES (600,'Pan',NULL);
INSERT INTO recetas_db.categorias VALUES (601,'Arroz',NULL);
INSERT INTO recetas_db.categorias VALUES (700,'Aceite de oliva',NULL);
INSERT INTO recetas_db.categorias VALUES (701,'Margarina',NULL);
INSERT INTO recetas_db.categorias VALUES (800,'Sal',NULL);
INSERT INTO recetas_db.categorias VALUES (801,'Canela',NULL);
INSERT INTO recetas_db.categorias VALUES (802,'Azúcar',NULL);
INSERT INTO recetas_db.categorias VALUES (803,'Romero',NULL);
INSERT INTO recetas_db.categorias VALUES (804,'Pimienta',NULL);

INSERT INTO recetas_db.nivel_cocina VALUES (1,'Bajo','No sé cocinar.'),(2,'Medio','Hago bien recetas sencillas.'),(3,'Alto','Me gusta aprender a hacer recetas nuevas.');
INSERT INTO recetas_db.perfiles VALUES (1,'Administrador'),(2,'Usuario'),(3,'Usuario Premium');
INSERT INTO recetas_db.tipos_dietas VALUES (1,'Como de todo',''),(2,'Flexitariano','Intento comer poca carne.'),(3,'Pescetariano','No como carne. Sí como pescado.'),(4,'Vegetariano','No como carne ni pescado.'),(5,'Vegano','Solo como alimentos de origen vegetal.'),(6,'Otra','Mi dieta es diferente a las demás.');


-- INSERT INTO recetas_db.recetas VALUES (1,'Crema de calabacín y espárragos verdes',1,1,3,30,'Cualquier momento',102,0.00,'- Pica la cebolla y los espárragos. Echa un chorrito de aceite en tu sartén y saltea durante 10 minutos las verduras que acabas de picar, hasta que la cebolla esté tierna.\n\n- Lava y trocea el calabacín y añádelo a una cazuela junto a los espárragos y la cebolla. Añade un poco de sal y cubre con agua. Tapa la cazuela y cuece durante unos 20 minutos, hasta que el calabacín esté tierno.\n\n- Cuando esté listo, tritura hasta que no queden grumos y ajusta de sal si lo consideras necesario.\n','S',_binary 'null',NULL),(2,'Torrijas',1,1,11,20,'Cualquier momento',497,0.00,'- En un cazo pon la leche, dos cucharadas de azúcar, una ramita de canela y un trozo grande de la cáscara de un limón. Cocina a fuego medio y cuando comience a hervir, apaga el fuego y deja que repose. Cuando esté casi frío, viértelo en un recipiente.\n\n- Bate en un plato hondo el huevo.\n\n- Pon a calentar una sartén con el aceite.\n\n- Cada rebanada de pan sumérgela por ambos lados en el recipiente que contiene la leche. Escúrrelas y pásalas, una a una, por el huevo y luego fríelas por ambos lados.\n\n- Retíralas de la sartén y ponlas sobre papel absorbente para quitarles el aceite.\n\n- En un plato echa el resto de azúcar y media cucharadita de canela en polvo y mézclalo. Pasa ambos lados de cada torrija por el azúcar con canela y sírvelas.','S',NULL,NULL),(3,'Lubina con champiñones cremosos y espinacas',1,1,8,20,'Almuerzo',568,0.00,'- Pica la cebolla en trozos muy pequeños.\n\n- Lava y corta los champiñones en láminas.\n\n- En una sartén echa un poco de aceite de oliva. Cuando esté caliente vierte en ella la cebolla y sofríela a fuego medio con cuidado de que no se queme. Pasados 2-3 minutos añade a la sartén los champiñones y cocínalos junto con la cebolla unos 8 minutos.\n\n- Añade a la sartén las espinacas cortadas. Con el calor irán reduciendo su volumen. Echa la leche evaporada y una pizca de sal a todo. Cocina a fuego bajo hasta que se forme una salsa cremosa.\n\n- Mientras, en otra sartén con un poco de aceite caliente, cocina a la plancha los dos filetes de lubina.\n\n- Coloca en un plato las lubinas acompañadas de los champiñones cremosos con espinacas y añade al plato un poquito de arroz integral cocido.','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (1,'Crema de calabacín y espárragos verdes',1,1,3,30,'Cualquier momento',102,0.00,'- Pica la cebolla y los espárragos. Echa un chorrito de aceite en tu sartén y saltea durante 10 minutos las verduras que acabas de picar, hasta que la cebolla esté tierna.\n\n- Lava y trocea el calabacín y añádelo a una cazuela junto a los espárragos y la cebolla. Añade un poco de sal y cubre con agua. Tapa la cazuela y cuece durante unos 20 minutos, hasta que el calabacín esté tierno.\n\n- Cuando esté listo, tritura hasta que no queden grumos y ajusta de sal si lo consideras necesario.\n','S',?,NULL);
INSERT INTO recetas_db.recetas VALUES (2,'Torrijas',1,1,11,20,'Cualquier momento',497,0.00,'- En un cazo pon la leche, dos cucharadas de azúcar, una ramita de canela y un trozo grande de la cáscara de un limón. Cocina a fuego medio y cuando comience a hervir, apaga el fuego y deja que repose. Cuando esté casi frío, viértelo en un recipiente.\n\n- Bate en un plato hondo el huevo.\n\n- Pon a calentar una sartén con el aceite.\n\n- Cada rebanada de pan sumérgela por ambos lados en el recipiente que contiene la leche. Escúrrelas y pásalas, una a una, por el huevo y luego fríelas por ambos lados.\n\n- Retíralas de la sartén y ponlas sobre papel absorbente para quitarles el aceite.\n\n- En un plato echa el resto de azúcar y media cucharadita de canela en polvo y mézclalo. Pasa ambos lados de cada torrija por el azúcar con canela y sírvelas.','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (3,'Lubina con champiñones cremosos y espinacas',1,1,8,20,'Almuerzo',568,0.00,'- Pica la cebolla en trozos muy pequeños.\n\n- Lava y corta los champiñones en láminas.\n\n- En una sartén echa un poco de aceite de oliva. Cuando esté caliente vierte en ella la cebolla y sofríela a fuego medio con cuidado de que no se queme. Pasados 2-3 minutos añade a la sartén los champiñones y cocínalos junto con la cebolla unos 8 minutos.\n\n- Añade a la sartén las espinacas cortadas. Con el calor irán reduciendo su volumen. Echa la leche evaporada y una pizca de sal a todo. Cocina a fuego bajo hasta que se forme una salsa cremosa.\n\n- Mientras, en otra sartén con un poco de aceite caliente, cocina a la plancha los dos filetes de lubina.\n\n- Coloca en un plato las lubinas acompañadas de los champiñones cremosos con espinacas y añade al plato un poquito de arroz integral cocido.','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (4,'Tostas de setas con puré de manzana y romero',1,2,10,20,'Cena',595,5.00,'- Empieza preparando el puré de manzana y romero. Para ello, pela y descorazona las manzanas.\n\n- Córtalas a trozos regulares de unos 2 cm y rocíalas con un chorrito de limón.\n\n- En un cazo, calienta la margarina y, cuando esté derretida, añade la manzana cortada y el romero. Cuando veas que la manzana está dorada, cúbrela con un poco de agua (sin pasarte) y déjala cocer durante unos 20 minutos (o hasta que veas que se aplasta fácilmente).\n\n- Cuando la manzana esté cocida, pásala al vaso de la batidora y tritúrala hasta obtener una textura fina y homogénea (también puedes aplastarla con un tenedor para un resultado más rústico).\n\n- Mientras tanto, corta los ajos y la cebolla finamente y saltéalos. Lava y corta también las setas y, cuando veas que el ajo y la cebolla se han dorado, añádelas y remuévelo todo hasta que se dore. Añade sal y pimienta al gusto.\n\n- Corta el pan y tuéstalo. Después, unta las tostadas con un poco de puré de manzana y añade las setas por encima.','S',NULL,'pilpil');

-- INSERT INTO recetas_db.ingrediente_en_receta VALUES (1,300,0.25,'unidad'),(1,400,4,'unidades'),(1,401,0.5,'unidad'),(1,700,0.5,'cucharada'),(1,800,0.25,'cucharadita'),(2,100,0.5,'taza'),(2,200,0.5,'unidad'),(2,500,0.5,'unidad'),(2,600,2,'rebanadas'),(2,700,1.5,'cucharadas'),(2,801,1,'cucharadita'),(2,802,3,'cucharaditas'),(3,101,0.25,'taza'),(3,201,1,'filete'),(3,300,0.25,'unidad'),(3,301,6,'unidades'),(3,402,100,'gramos'),(3,601,1,'taza pequeña'),(3,700,1,'cucharada'),(3,800,0.5,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (1,1,300,0.25,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (2,1,400,4,'unidades');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (3,1,700,0.5,'cucharada');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (4,1,800,0.25,'cucharadita');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (5,2,100,0.5,'taza');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (6,2,200,0.5,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (7,2,500,0.5,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (8,2,600,2,'rebanadas');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (9,2,700,1.5,'cucharadas');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (10,2,801,1,'cucharadita');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (11,2,802,3,'cucharaditas');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (12,3,101,0.25,'taza');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (13,3,201,1,'filete');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (14,3,300,0.25,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (15,3,301,6,'unidades');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (16,3,402,100,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (17,3,601,1,'taza pequeña');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (18,3,700,1,'cucharada');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (19,3,800,0.5,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (20,1,401,0.5,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (21,4,500,0.25,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (22,4,501,1,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (23,4,301,1,'puñado');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (24,4,701,1,'cucharada');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (25,4,300,0.25,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (26,4,803,0.5,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (27,4,403,1,'diente');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (28,4,803,0.5,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (29,4,600,2,'rebanadas');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (30,4,804,0.5,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (31,4,800,0.5,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (32,4,700,2,'cucharadas');

INSERT INTO recetas_db.usuarios VALUES ('pilpil','blabla',1,'pilarnaviocolon@gmail.com','Pilar','Navío','2022-09-04',1,3);
INSERT INTO recetas_db.usuarios VALUES ('paula','hola',2,'paulacasado@gmail.com','Paula','Casado','2022-12-04',1,3);
INSERT INTO recetas_db.usuario_perfiles VALUES ('pilpil',1);

-- INSERT INTO recetas_db.recetas_tipo_dieta VALUES ('1','1'),('1','2'),('1','3'), ('2','1'),('2','2'),('2','3'),('3','1'),('3','2'),('3','3'), ('4','5');
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (1,1);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (1,2);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (1,3);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (2,1);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (2,2);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (2,3);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (3,1);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (3,2);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (3,3);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (1,1);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (1,2);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (1,3);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (2,1);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (2,2);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (2,3);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (3,1);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (3,2);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (3,3);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (4,5);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (1,1);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (1,2);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (1,3);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (2,1);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (2,2);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (2,3);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (3,1);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (3,2);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (3,3);
INSERT INTO recetas_db.recetas_tipo_dieta VALUES (4,5);

-- CREATE USER 'ureceta'@'localhost' IDENTIFIED BY 'ureceta';
-- GRANT ALL PRIVILEGES ON RECETAS_DB.* TO  'ureceta'@'localhost';