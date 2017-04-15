CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
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
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (1,1),(1,2),(1,3),(2,1),(3,2);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (1,'recipe 1 ','recipe 1 description',1),(2,'recipe 2 ','recipe 2 description',1),(3,'recipe 3 ','recipe 3 descriptiojn',2),(4,'recipe 4 ','recipe 4 description',2),(5,'spanakorizo','oraiotato spanakorizo!!!!!!',2),(6,'carbonara','carbonara italianoooo!!!!!!',12),(7,'fakes','pare fakes gia na ma8eis!!!!!',12),(8,'revithia','ta kalitera stin makedonia',12),(21,'kotopoulo','kotopoulaki!!!!!!',12),(22,'tiropita','i kaliteri tiropita sti makedonia',3),(23,'kimadopita','ela kosme na fas',3),(24,'omeleta','omeleta me loukanika!!!!!!',12),(26,'mpougatsa me krema','I kaliteri mpougatsa sti makedonia!!!',12),(43,'oti na nai','I kaliteri mpougatsa sti makedonia!!!',12),(44,'oti na na2i','I kaliteri mpougatsa sti makedonia!!!',12),(46,'kati kalo ','pw pw pw',5),(47,'spanakotiropita','i kaliteri',5),(49,'Kotopoulo','prwteini',5),(50,'test','test',5),(51,'pastitsio','the bestesest',5),(52,'prasopita','ela kosme na fas',12),(53,'cookies','ela kosme na fas',5),(54,'mpla mpla mpla','dfasdfasdfas',5);
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'test','test'),(2,'test1','test1'),(3,'test2','test2'),(5,'admin','admin'),(12,'test_panos','1234551');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-07 19:05:14
