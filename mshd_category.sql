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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `cateid` varchar(45) NOT NULL,
  `parentcate` varchar(45) DEFAULT NULL,
  `parentcateid` varchar(45) DEFAULT NULL,
  `childcate` varchar(45) DEFAULT NULL,
  `childcateid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cateid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('101','震情','1','震情信息','01'),('201','人员伤亡及失踪','2','死亡','01'),('202','人员伤亡及失踪','2','受伤','02'),('203','人员伤亡及失踪','2','失踪','03'),('301','房屋破坏','3','土木','01'),('302','房屋破坏','3','砖木','02'),('303','房屋破坏','3','砖混','03'),('304','房屋破坏','3','框架','04'),('305','房屋破坏','3','其他','05'),('401','生命线工程灾情','4','交通','01'),('402','生命线工程灾情','4','供水','02'),('403','生命线工程灾情','4','输油','03'),('404','生命线工程灾情','4','燃气','04'),('405','生命线工程灾情','4','电力','05'),('406','生命线工程灾情','4','通信','06'),('407','生命线工程灾情','4','水利','07'),('501','次生灾害','5','崩塌','01'),('502','次生灾害','5','滑坡','02'),('503','次生灾害','5','泥石流','03'),('504','次生灾害','5','岩溶塌陷','04'),('505','次生灾害','5','地裂缝','05'),('506','次生灾害','5','地面沉降','06'),('507','次生灾害','5','其他','07');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
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
