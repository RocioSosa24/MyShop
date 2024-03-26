CREATE DATABASE  IF NOT EXISTS `productos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `productos`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: productos
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `precio` double NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `imagen` varchar(1055) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `talle` varchar(5) DEFAULT NULL,
  `creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_tipo_producto` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_ibfk_1` (`id_tipo_producto`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_tipo_producto`) REFERENCES `tipo_producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,354534,'Remera rosa','https://http2.mlstatic.com/D_NQ_NP_903927-MLA50771897000_072022-O.webp','Remera rosa básica','s',NULL,1),(2,6484,'Jogging negro','https://http2.mlstatic.com/D_NQ_NP_605657-MLA71078903239_082023-O.webp','Jogging negro básico','xs',NULL,2),(3,50,'Pantalón estampado','https://hips.hearstapps.com/hmg-prod/images/pantalon-tendencia-mujer-1653664467.jpg?crop=0.668xw:1.00xh;0.167xw,0&resize=640:*','Pantalón estampado colorido','l',NULL,2),(4,500,'Remera vans','https://http2.mlstatic.com/D_NQ_NP_729335-MLA53143153694_012023-O.webp','Remera vans negra','xl','2024-02-02 03:39:03',1),(5,300,'Ojotas tiburon','https://m.media-amazon.com/images/I/61q4r+GpOaL.jpg','Ojotas con forma de tiburón','45','2024-02-02 03:55:15',3),(6,5000,'Pantalón cargo','https://acdn.mitiendanube.com/stores/001/041/463/products/sin-titulo-111-7da5f41c63ff18140d16947906243411-1024-1024.jpg','Pantalón cargo ','s','2024-02-02 04:09:10',2),(7,200,'Pantalón azul','https://www.abrafersrl.com.ar/wp-content/uploads/RPA-0007-.jpg','Pantalón azul largo','s','2024-03-01 15:48:36',2),(8,1000,'Zapatillas Rave','https://equipovallejo.vtexassets.com/arquivos/ids/228458-800-auto?v=638150235353000000&width=800&height=auto&aspect=true','Zapatillas Rave negras','38','2024-03-21 01:30:51',3),(9,5500,'Pantalón engomado','https://acdn.mitiendanube.com/stores/781/476/products/3252e12a-9c04-4c84-a021-d9b69d9845eb-2d58400a4187e66eb017062121676189-1024-1024.jpg','Pantalón engomado','m','2024-03-21 04:44:34',2),(11,2000,'Remera roja','https://acdn.mitiendanube.com/stores/003/588/275/products/diseno-sin-titulo-92-1f369277b98f5a43ff16971212744973-1024-1024.png','Remera roja básica','s','2024-03-22 03:10:39',1),(12,3000,'Remera anime','https://acdn.mitiendanube.com/stores/806/999/products/de2a26851-3971a38571ea67fa7616934210783250-1024-1024.jpg','Remera anime negra','s','2024-03-22 03:10:39',1);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'remera'),(2,'pantalon'),(3,'zapato');
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-25 23:53:37
