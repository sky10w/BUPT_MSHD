-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: mshd
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `origin`
--

DROP TABLE IF EXISTS `origin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `origin` (
  `oid` varchar(45) NOT NULL,
  `parento` varchar(45) DEFAULT NULL,
  `parentoid` varchar(45) DEFAULT NULL,
  `childo` varchar(45) DEFAULT NULL,
  `childoid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `origin`
--

LOCK TABLES `origin` WRITE;
/*!40000 ALTER TABLE `origin` DISABLE KEYS */;
INSERT INTO `origin` VALUES ('100','业务报送数据','1','前方地震应急指挥部','00'),('101','业务报送数据','1','后方地震应急指挥部','01'),('120','业务报送数据','1','应急指挥技术系统','20'),('121','业务报送数据','1','社会服务工程应急救援系统','21'),('140','业务报送数据','1','危险区预评估工作组','40'),('141','业务报送数据','1','地震应急指挥技术协调组','41'),('142','业务报送数据','1','震后政府信息支持工作项目','42'),('180','业务报送数据','1','灾情快速上报接收处理系统','80'),('181','业务报送数据','1','地方地震局应急信息服务相','81'),('199','业务报送数据','1','其他','99'),('200','泛在感知数据','2','互联网感知','00'),('201','泛在感知数据','2','通信网感知','01'),('202','泛在感知数据','2','舆情网感知','02'),('203','泛在感知数据','2','电力系统感知','03'),('204','泛在感知数据','2','交通系统感知','04'),('205','泛在感知数据','2','其他','05'),('300','其他数据','3','其他','00');
/*!40000 ALTER TABLE `origin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-25 12:23:21
