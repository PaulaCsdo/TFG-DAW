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
  `IMAGEN` varchar(50),
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
INSERT INTO recetas_db.ingredientes VALUES (100,'Leche',NULL);
INSERT INTO recetas_db.ingredientes VALUES (101,'Leche evaporada',NULL);
INSERT INTO recetas_db.ingredientes VALUES (200,'Huevo',NULL);
INSERT INTO recetas_db.ingredientes VALUES (201,'Lubina',NULL);
INSERT INTO recetas_db.ingredientes VALUES (202,'Solomillo de cerdo',NULL);
INSERT INTO recetas_db.ingredientes VALUES (203,'Langostino',NULL);
INSERT INTO recetas_db.ingredientes VALUES (204,'Muslo de pollo',NULL);
INSERT INTO recetas_db.ingredientes VALUES (300,'Cebolla',NULL);
INSERT INTO recetas_db.ingredientes VALUES (301,'Champiñón',NULL);
INSERT INTO recetas_db.ingredientes VALUES (302,'Ajo',NULL);
INSERT INTO recetas_db.ingredientes VALUES (303,'Tomate frito',NULL);
INSERT INTO recetas_db.ingredientes VALUES (304,'Tomate Seco',NULL);
INSERT INTO recetas_db.ingredientes VALUES (305,'Calabaza',NULL);
INSERT INTO recetas_db.ingredientes VALUES (306,'Patata',NULL);
INSERT INTO recetas_db.ingredientes VALUES (400,'Espárrago verde',NULL);
INSERT INTO recetas_db.ingredientes VALUES (401,'Calabacín',NULL);
INSERT INTO recetas_db.ingredientes VALUES (402,'Espinaca',NULL);
INSERT INTO recetas_db.ingredientes VALUES (403,'Ajo',NULL);
INSERT INTO recetas_db.ingredientes VALUES (404,'Zanahoria',NULL);
INSERT INTO recetas_db.ingredientes VALUES (405,'Aguacate',NULL);
INSERT INTO recetas_db.ingredientes VALUES (406,'Albahaca',NULL);
INSERT INTO recetas_db.ingredientes VALUES (407,'Tofu',NULL);
INSERT INTO recetas_db.ingredientes VALUES (408,'Pimiento verde',NULL);
INSERT INTO recetas_db.ingredientes VALUES (409,'Col lombarda',NULL);
INSERT INTO recetas_db.ingredientes VALUES (410,'Puerro',NULL);
INSERT INTO recetas_db.ingredientes VALUES (500,'Limón',NULL);
INSERT INTO recetas_db.ingredientes VALUES (501,'Manzana',NULL);
INSERT INTO recetas_db.ingredientes VALUES (502,'Aguacate',NULL);
INSERT INTO recetas_db.ingredientes VALUES (503,'Lima',NULL);
INSERT INTO recetas_db.ingredientes VALUES (600,'Pan',NULL);
INSERT INTO recetas_db.ingredientes VALUES (601,'Arroz',NULL);
INSERT INTO recetas_db.ingredientes VALUES (602,'Maicena',NULL);
INSERT INTO recetas_db.ingredientes VALUES (603,'Harina',NULL);
INSERT INTO recetas_db.ingredientes VALUES (604,'Pasta',NULL);
INSERT INTO recetas_db.ingredientes VALUES (700,'Aceite de oliva',NULL);
INSERT INTO recetas_db.ingredientes VALUES (701,'Margarina',NULL);
INSERT INTO recetas_db.ingredientes VALUES (800,'Sal',NULL);
INSERT INTO recetas_db.ingredientes VALUES (801,'Canela',NULL);
INSERT INTO recetas_db.ingredientes VALUES (802,'Azúcar',NULL);
INSERT INTO recetas_db.ingredientes VALUES (803,'Romero',NULL);
INSERT INTO recetas_db.ingredientes VALUES (804,'Pimienta',NULL);
INSERT INTO recetas_db.ingredientes VALUES (805,'Salsa de soja',NULL);
INSERT INTO recetas_db.ingredientes VALUES (806,'Pimienta negra',NULL);

INSERT INTO recetas_db.nivel_cocina VALUES (1,'Bajo','No sé cocinar.'),(2,'Medio','Hago bien recetas sencillas.'),(3,'Alto','Me gusta aprender a hacer recetas nuevas.');

INSERT INTO recetas_db.perfiles VALUES (1,'Administrador'),(2,'Usuario'),(3,'Usuario Premium');

INSERT INTO recetas_db.tipos_dietas VALUES (1,'Como de todo',''),(2,'Flexitariano','Intento comer poca carne.'),(3,'Pescetariano','No como carne. Sí como pescado.'),(4,'Vegetariano','No como carne ni pescado.'),(5,'Vegano','Solo como alimentos de origen vegetal.'),(6,'Otra','Mi dieta es diferente a las demás.');

INSERT INTO recetas_db.usuarios VALUES ('pilpil','blabla',1,'pilarnaviocolon@gmail.com','Pilar','Navío','2022-09-04',1,3);
INSERT INTO recetas_db.usuarios VALUES ('paula','hola',2,'paulacasado@gmail.com','Paula','Casado','2022-12-04',1,3);

-- INSERT INTO recetas_db.recetas VALUES (1,'Crema de calabacín y espárragos verdes',1,1,3,30,'Cualquier momento',102,0.00,'- Pica la cebolla y los espárragos. Echa un chorrito de aceite en tu sartén y saltea durante 10 minutos las verduras que acabas de picar, hasta que la cebolla esté tierna.\n\n- Lava y trocea el calabacín y añádelo a una cazuela junto a los espárragos y la cebolla. Añade un poco de sal y cubre con agua. Tapa la cazuela y cuece durante unos 20 minutos, hasta que el calabacín esté tierno.\n\n- Cuando esté listo, tritura hasta que no queden grumos y ajusta de sal si lo consideras necesario.\n','S',_binary 'null',NULL),(2,'Torrijas',1,1,11,20,'Cualquier momento',497,0.00,'- En un cazo pon la leche, dos cucharadas de azúcar, una ramita de canela y un trozo grande de la cáscara de un limón. Cocina a fuego medio y cuando comience a hervir, apaga el fuego y deja que repose. Cuando esté casi frío, viértelo en un recipiente.\n\n- Bate en un plato hondo el huevo.\n\n- Pon a calentar una sartén con el aceite.\n\n- Cada rebanada de pan sumérgela por ambos lados en el recipiente que contiene la leche. Escúrrelas y pásalas, una a una, por el huevo y luego fríelas por ambos lados.\n\n- Retíralas de la sartén y ponlas sobre papel absorbente para quitarles el aceite.\n\n- En un plato echa el resto de azúcar y media cucharadita de canela en polvo y mézclalo. Pasa ambos lados de cada torrija por el azúcar con canela y sírvelas.','S',NULL,NULL),(3,'Lubina con champiñones cremosos y espinacas',1,1,8,20,'Almuerzo',568,0.00,'- Pica la cebolla en trozos muy pequeños.\n\n- Lava y corta los champiñones en láminas.\n\n- En una sartén echa un poco de aceite de oliva. Cuando esté caliente vierte en ella la cebolla y sofríela a fuego medio con cuidado de que no se queme. Pasados 2-3 minutos añade a la sartén los champiñones y cocínalos junto con la cebolla unos 8 minutos.\n\n- Añade a la sartén las espinacas cortadas. Con el calor irán reduciendo su volumen. Echa la leche evaporada y una pizca de sal a todo. Cocina a fuego bajo hasta que se forme una salsa cremosa.\n\n- Mientras, en otra sartén con un poco de aceite caliente, cocina a la plancha los dos filetes de lubina.\n\n- Coloca en un plato las lubinas acompañadas de los champiñones cremosos con espinacas y añade al plato un poquito de arroz integral cocido.','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (1,'Crema de calabacín y espárragos verdes',1,1,3,30,'Cualquier momento',102,0.00,'- Pica la cebolla y los espárragos. Echa un chorrito de aceite en tu sartén y saltea durante 10 minutos las verduras que acabas de picar, hasta que la cebolla esté tierna.\n\n- Lava y trocea el calabacín y añádelo a una cazuela junto a los espárragos y la cebolla. Añade un poco de sal y cubre con agua. Tapa la cazuela y cuece durante unos 20 minutos, hasta que el calabacín esté tierno.\n\n- Cuando esté listo, tritura hasta que no queden grumos y ajusta de sal si lo consideras necesario.\n','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (2,'Torrijas',1,1,11,20,'Cualquier momento',497,0.00,'- En un cazo pon la leche, dos cucharadas de azúcar, una ramita de canela y un trozo grande de la cáscara de un limón. Cocina a fuego medio y cuando comience a hervir, apaga el fuego y deja que repose. Cuando esté casi frío, viértelo en un recipiente.\n\n- Bate en un plato hondo el huevo.\n\n- Pon a calentar una sartén con el aceite.\n\n- Cada rebanada de pan sumérgela por ambos lados en el recipiente que contiene la leche. Escúrrelas y pásalas, una a una, por el huevo y luego fríelas por ambos lados.\n\n- Retíralas de la sartén y ponlas sobre papel absorbente para quitarles el aceite.\n\n- En un plato echa el resto de azúcar y media cucharadita de canela en polvo y mézclalo. Pasa ambos lados de cada torrija por el azúcar con canela y sírvelas.','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (3,'Lubina con champiñones cremosos y espinacas',1,1,8,20,'Almuerzo',568,0.00,'- Pica la cebolla en trozos muy pequeños.\n\n- Lava y corta los champiñones en láminas.\n\n- En una sartén echa un poco de aceite de oliva. Cuando esté caliente vierte en ella la cebolla y sofríela a fuego medio con cuidado de que no se queme. Pasados 2-3 minutos añade a la sartén los champiñones y cocínalos junto con la cebolla unos 8 minutos.\n\n- Añade a la sartén las espinacas cortadas. Con el calor irán reduciendo su volumen. Echa la leche evaporada y una pizca de sal a todo. Cocina a fuego bajo hasta que se forme una salsa cremosa.\n\n- Mientras, en otra sartén con un poco de aceite caliente, cocina a la plancha los dos filetes de lubina.\n\n- Coloca en un plato las lubinas acompañadas de los champiñones cremosos con espinacas y añade al plato un poquito de arroz integral cocido.','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (4,'Tostas de setas con puré de manzana y romero',1,2,10,20,'Cena',595,5.00,'- Empieza preparando el puré de manzana y romero. Para ello, pela y descorazona las manzanas.\n\n- Córtalas a trozos regulares de unos 2 cm y rocíalas con un chorrito de limón.\n\n- En un cazo, calienta la margarina y, cuando esté derretida, añade la manzana cortada y el romero. Cuando veas que la manzana está dorada, cúbrela con un poco de agua (sin pasarte) y déjala cocer durante unos 20 minutos (o hasta que veas que se aplasta fácilmente).\n\n- Cuando la manzana esté cocida, pásala al vaso de la batidora y tritúrala hasta obtener una textura fina y homogénea (también puedes aplastarla con un tenedor para un resultado más rústico).\n\n- Mientras tanto, corta los ajos y la cebolla finamente y saltéalos. Lava y corta también las setas y, cuando veas que el ajo y la cebolla se han dorado, añádelas y remuévelo todo hasta que se dore. Añade sal y pimienta al gusto.\n\n- Corta el pan y tuéstalo. Después, unta las tostadas con un poco de puré de manzana y añade las setas por encima.','S',NULL,'pilpil');
INSERT INTO recetas_db.recetas VALUES (5,'Arroz Frito con Lombarda y Pimiento Verde',1,1,1,15,'Almuerzo',450,4.00,'- Saca de la nevera el arroz que tienes del día anterior. Si no tuvieras preparado, no hay problema, puedes prepararlo (mira el apartado trucos y consejo, ahí te explicamos cómo hacerlo o sino busca la receta en nuestra App).\n\n- Lava y corta en trocitos muy pequeños el pimiento, la col lombarda y el puerro.\n\n- Prepara una sartén con aceite a fuego muy alto y vierte en ella las verduras. No dejes de removerlas unos 2 minutos. Lo ideal es que se hagan por fuera pero no por dentro, así le darán a tu plato el toque crujiente necesario.\n\n- Pasados 2 minutos, echa el arroz y sigue removiendo bien otro minuto más. Añade la soja de soja a la sartén y vuelve a remover durante un par de minutos más. Por último, comprueba el punto de sal, añade un poco de pimienta o alguna otra especia al gusto y ¡listo!.\n','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (6,'Solomillo de Cerdo con Cebolla',1,1,2,30,'Almuerzo',359,5.00,'- Lo primero que debes hacer es limpiar bien el solomillo de cerdo. Quítale cualquier trozo de grasa o nervio que pueda tener. Una vez limpio échale sal y pimienta por todos sus lados.\n\n- En una sartén amplia echa un chorro de aceite de oliva y deja que se caliente. Cuando el aceite esté caliente coloca el solomillo en la sartén y séllalo por todos los lados. Retira la carne y colócala en un plato, por ahora.\n\n- Corta una cebolla grande en tiras no muy gruesas.\n\n- En la misma sartén donde has sellado la carne añade un poco más de aceite y saltea durante un minuto la cebolla a fuego alto, removiendo sin parar. Pasado el minuto baja el fuego, añade una pizca de sal y cocina durante 5 minutos más.\n\n- Llegados a este punto, introduce de nuevo el solomillo de cerdo en la sartén, agrega el vino blanco y sube el fuego al máximo hasta que reduzca. En ese momento baja el fuego, añade una cucharada de maicena y revuelve la maicena con la cebolla y el líquido que hay en la sartén. Deja que se cocine el solomillo durante 15 minutos aproximadamente, dándole la vuelta de vez en cuando.\n\n- Pasado el tiempo indicado, puedes comprobar con un cuchillo si está en su punto de cocción el solomillo. Si piensas que le falta un poco, déjalo más tiempo en la sartén. Si está en su punto, apaga el fuego.\n\n- Coloca el solomillo de cerdo en una tabla, ve cortándolo en rodajas y sírvelo en una bandeja acompañado de la cebolla y la salsa que se ha formado.\n','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (7,'Ensalada Verde con Langostinos y Huevo Duro',1,1,4,20,'Cualquier momento',291,3.00,'- Pon agua en una cazuela a calentar. Cuando comience a hervir añade la sal, pon los huevos y cuece durante 10-12 minutos. Pasado ese tiempo saca los huevos, refréscalos con agua fría y quítales la cáscara. Corta cada huevo en rodajas.\n\n- Pela y ralla la zanahoria.\n\n- En una ensaladera coloca las hojas de lechuga lavada y la zanahoria rallada. Con ayuda de unas cucharas mezcla los ingredientes.\n\n- Coloca sobre la ensalada los langostinos pelados y cocidos, las láminas de huevo duro y el aguacate cortado en rodajas.\n','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (8,'Muslos de Pollo con Salsa de Tomate y Albahaca',1,1,5,30,'Almuerzo',852,5.00,'- Echa el aceite en una sartén. Cuando esté caliente, añade el ajo laminado y deja que el aceite coja sabor.\n\n- Añade los muslos de pollo a la sartén, una pizca de sal, y cocínalos a fuego suave durante 8 minutos, dándoles la vuelta de vez en cuando, de manera que queden dorados por todos los lados.\n\n- Pica finamente 5 hojas de albahaca.\n\n- Vierte la salsa de tomate con la albahaca picada en la sartén. Deja que se cocinen los muslos de pollo con la salsa de tomate a fuego suave y con la tapa puesta durante 20 minutos. Pasado ese tiempo, retira la sartén del fuego, añade un par de hojas más de albahaca a modo de decoración, y sirve.\n','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (9,'Hamburguesas de Tofu y Tomates Secos',1,1,6,30,'Cena',191,4.00,'- Precalienta el horno a 200ºC.\n\n- Desmenuza el tofu, colócalo en un colador y presiónalo con los dedos para eliminar el exceso de agua.\n\n- Trocea los tomates secos (ya rehidratados) en trozos muy pequeños y mézclalos con el tofu. Añade la sal, el orégano y la harina y mezcla bien. Si sientes que la masa está aún muy húmeda y no mantiene la forma, añade un poco más de harina.\n\n- Forra una bandeja para el horno con papel vegetal y úntala con un poco de aceite de oliva. Con una cuchara ve cogiendo porciones de masa y dándoles forma redondeada, aplastándolas con las manos un poquito. Colócalas sobre la bandeja.\n\n- Hornea durante 20 minutos.\n','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (10,'Pasta con Mango y Aguacate',1,1,7,20,'Almuerzo',495,5.00,'- Cuece la pasta según las instrucciones del paquete con un poco de sal. Cuando esté lista, déjala enfriar.\n\n- Trocea en mango y el aguacate en dados y mezcla con la pasta. Exprime la lima y mézclala también.\n\n- Termina añadiendo un poco de sal y aliñando con un chorrito de aceite de oliva y limón.\n','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (11,'Tortilla de Patata y Calabaza',1,1,9,40,'Almuerzo',559,5.00,'- Corta la parte dura de la calabaza y con el rallador grueso rállala.\n\n- Lava y pela las patatas y córtalas en láminas muy finas, así se te cocinarán más rápido. También pela la cebolla y córtala en tiras finas.\n\n- En una sartén echa un chorro generoso de aceite de oliva. Cuando el aceite esté caliente, vierte en la sartén las patatas, la calabaza rallada y las tiras de cebolla. Deja que se cocinen los ingredientes a fuego medio, dando vueltas con cierta frecuencia. Este proceso puede durar unos 20 minutos. Cuanto mas blandita y dorada te quede la patata más rica estará tu tortilla así que ¡no tengas prisa! \n\n- Retira los ingredientes de la sartén. En un recipiente bate lo huevos. Cuando los huevos estén bien batidos, mézclalos con la masa de patatas, calabaza rallada y cebolla y, por último, echa sal a la mezcla.\n\n- En la misma sartén que has empleado antes, echa un poco de aceite y vierte la mezcla. Cocina a fuego medio. Con una pala o cuchara de madera, separa la tortilla de los bordes para poder darle la vuelta. Deja que se cocine durante 2 minutos aproximadamente antes de darle la vuelta. Para darle la vuelta, coge un plato más grande que la sartén, coloca el plato encima de la sartén y dale la vuelta de manera que la tortilla quede en el plato, rápidamente vuelve a colocarla en la sartén y deja que se cocine por ese lado (1-2 minutos será suficiente). Una vez esté lista colócala en un plato y ¡a comer!\n','S',NULL,NULL);
INSERT INTO recetas_db.recetas VALUES (12,'Crema de Zanahoria y Calabaza',1,1,12,25,'Cena',248,3.00,'- Pela las zanahorias, la calabaza y la patata, y corta estos ingredientes en trozos pequeños. También pica la cebolla.\n\n- En una olla echa un chorrito de aceite de oliva. Cuando el aceite esté caliente vierte los trozos de cebolla y sofríe durante 2-3 minutos a fuego medio, con cuidado de que no se quemen.\n\n- Añade a la olla los trozos de zanahoria y de calabaza, y cocina a fuego medio durante 10 minutos, hasta que las verduras comiencen a estar blanditas. \n\n- Añade la patata troceada y cubre todo con agua. Con 3 tazas de agua seguramente sea suficiente. Pon un poquito de sal, cubre con la tapa casi por completo (deja una pequeña abertura) y cocina aproximadamente durante 20 minutos, hasta que las verduras estén tiernas.\n\n- A continuación, pon todos los ingredientes en tu batidora y bate muy bien. Sirve en cuencos y ¡a comer! \n','S',NULL,NULL);


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

INSERT INTO recetas_db.ingrediente_en_receta VALUES (33,5,408,0.25,'unidades');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (34,5,409,0.25,'unidades');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (35,5,410,0.25,'unidades');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (36,5,601,0.25,'taza');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (37,5,805,0.25,'cucharada');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (38,5,700,0.25,'cucharada');

INSERT INTO recetas_db.ingrediente_en_receta VALUES (39,6,202,200,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (40,6,300,0.50,'unidades');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (41,6,803,0.50,'taza');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (42,6,806,1,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (43,6,602,0.50,'cucharada');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (44,6,800,1.50,'cucharada');

INSERT INTO recetas_db.ingrediente_en_receta VALUES (45,7,203,0.60,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (46,7,403,0.50,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (47,7,200,1,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (48,7,404,0.50,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (49,7,405,0.50,'unidad');

INSERT INTO recetas_db.ingrediente_en_receta VALUES (50,8,302,0.50,'diente');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (51,8,406,4,'hojas');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (52,8,204,2,'unidades');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (53,8,800,0.50,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (54,8,700,1.50,'cucharadas');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (55,8,303,0.50,'taza');

INSERT INTO recetas_db.ingrediente_en_receta VALUES (56,9,407,100,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (57,9,304,38,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (58,9,800,0.50,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (59,9,603,1,'cucharada');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (60,9,804,0.50,'cucharadita');

INSERT INTO recetas_db.ingrediente_en_receta VALUES (61,10,501,0.25,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (62,10,502,0.25,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (63,10,503,0.25,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (64,10,604,90,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (65,10,800,0.50,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (66,10,700,0.50,'cucharada');

INSERT INTO recetas_db.ingrediente_en_receta VALUES (67,11,300,0.25,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (68,11,200,2.50,'unidades');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (69,11,305,150,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (70,11,306,200,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (71,11,800,1,'pizca');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (72,11,700,1.50,'cucharadas');

INSERT INTO recetas_db.ingrediente_en_receta VALUES (73,12,305,150,'gramos');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (74,12,404,1,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (75,12,306,0.50,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (76,12,300,0.25,'unidad');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (77,12,800,0.50,'cucharadita');
INSERT INTO recetas_db.ingrediente_en_receta VALUES (78,12,700,1,'cucharada');



INSERT INTO recetas_db.usuario_perfiles VALUES ('pilpil',1);

-- INSERT INTO recetas_db.tipo_dieta_receta VALUES ('1','1'),('1','2'),('1','3'), ('2','1'),('2','2'),('2','3'),('3','1'),('3','2'),('3','3'), ('4','5');
INSERT INTO recetas_db.tipo_dieta_receta VALUES (1,1);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (1,2);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (1,3);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (2,1);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (2,2);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (2,3);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (3,1);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (3,2);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (3,3);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (4,5);

INSERT INTO recetas_db.tipo_dieta_receta VALUES (5,5);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (6,5);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (7,5);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (8,5);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (9,5);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (10,5);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (11,5);
INSERT INTO recetas_db.tipo_dieta_receta VALUES (12,5);



-- CREATE USER 'ureceta'@'localhost' IDENTIFIED BY 'ureceta';
-- GRANT ALL PRIVILEGES ON RECETAS_DB.* TO  'ureceta'@'localhost';