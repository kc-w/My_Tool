-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: kc_w
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `Cid` int(11) NOT NULL AUTO_INCREMENT,
  `Uname` varchar(20) DEFAULT NULL,
  `Unumber` varchar(15) NOT NULL,
  `Upassword` varchar(15) NOT NULL,
  `Utelephone` varchar(12) NOT NULL,
  `Uemail` varchar(30) NOT NULL,
  `Uaddress` varchar(30) NOT NULL,
  `Uztime` datetime NOT NULL,
  PRIMARY KEY (`Cid`),
  UNIQUE KEY `Uname` (`Uname`,`Unumber`,`Utelephone`,`Uemail`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'商城用户2312297207','2312297207','999999999852','17607169189','2312297207@qq.com','收货地址未填写','2017-06-23 06:06:48'),(2,'123','17764209189','17764209189','17764209189','17764209189@qq.com','收货地址未填写','2017-12-23 10:48:45'),(3,'商城用户1446733670','1446733670','1446733670','13100710791','1446733670@qq.com','收货地址未填写','2017-12-24 05:06:45'),(4,'商城用户2350677472','2350677472','12345678','13006129543','2350677472@qq.com','收货地址未填写','2017-12-24 10:53:35'),(5,'商城用户2350677492','2350677492','123456789','13006129541','2350677492@qq.com','收货地址未填写','2017-12-25 01:36:35');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list`
--

DROP TABLE IF EXISTS `list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list` (
  `Oid` int(11) NOT NULL AUTO_INCREMENT,
  `Cid` int(11) NOT NULL,
  `Mid` int(11) NOT NULL,
  `Olist` varchar(30) NOT NULL,
  `Otime` datetime DEFAULT NULL,
  PRIMARY KEY (`Oid`),
  KEY `Cid` (`Cid`),
  KEY `Mid` (`Mid`),
  CONSTRAINT `list_ibfk_1` FOREIGN KEY (`Cid`) REFERENCES `customer` (`Cid`),
  CONSTRAINT `list_ibfk_2` FOREIGN KEY (`Mid`) REFERENCES `merchant` (`Mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list`
--

LOCK TABLES `list` WRITE;
/*!40000 ALTER TABLE `list` DISABLE KEYS */;
/*!40000 ALTER TABLE `list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant` (
  `Mid` int(11) NOT NULL AUTO_INCREMENT,
  `Sname` varchar(20) DEFAULT NULL,
  `Snumber` varchar(15) NOT NULL,
  `Spassword` varchar(15) NOT NULL,
  `Stelephone` varchar(12) NOT NULL,
  `Semail` varchar(30) NOT NULL,
  `Saddress` varchar(30) NOT NULL,
  `Sztime` datetime NOT NULL,
  PRIMARY KEY (`Mid`),
  UNIQUE KEY `Sname` (`Sname`,`Snumber`,`Stelephone`,`Semail`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
INSERT INTO `merchant` VALUES (1,'商户2312297207','2312297207','2312297207','17607169189','2312297207@qq.com','发货地址未填写','2017-12-26 04:09:00');
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sp`
--

DROP TABLE IF EXISTS `sp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sp` (
  `Pid` int(11) NOT NULL AUTO_INCREMENT,
  `Mid` int(11) NOT NULL,
  `Pname` varchar(15) NOT NULL,
  `Pprice` float NOT NULL,
  `Pstock` int(11) NOT NULL,
  `Ptime` datetime NOT NULL,
  PRIMARY KEY (`Pid`),
  UNIQUE KEY `Pname` (`Pname`),
  KEY `Mid` (`Mid`),
  CONSTRAINT `sp_ibfk_1` FOREIGN KEY (`Mid`) REFERENCES `merchant` (`Mid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sp`
--

LOCK TABLES `sp` WRITE;
/*!40000 ALTER TABLE `sp` DISABLE KEYS */;
INSERT INTO `sp` VALUES (1,1,'1',1,1,'2017-12-28 04:56:43'),(3,1,'2',2,2,'2017-12-28 05:30:15'),(4,1,'33',33,33,'2017-12-28 05:43:49'),(6,1,'3',3,3,'2017-12-28 06:05:24'),(7,1,'4',4,4,'2017-12-28 06:07:02'),(8,1,'6',6,6,'2017-12-28 06:10:47'),(10,1,'7',7,7,'2017-12-28 06:14:44');
/*!40000 ALTER TABLE `sp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'kc_w'
--

--
-- Dumping routines for database 'kc_w'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-28 18:16:02
