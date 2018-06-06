CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'???a?'),(2,'?a?a????'),(3,'F???ta'),(4,'Ta?ass???'),(5,'?pa?a????'),(6,'??µa????'),(7,'??d? ??????'),(8,'???p? e?d?'),(9,'?at?ta'),(10,'?sp??a');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorites` (
  `user_id` int(11) NOT NULL,
  `recipes_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`recipes_id`),
  KEY `fk_users_has_recipes_recipes1_idx` (`recipes_id`),
  KEY `fk_users_has_recipes_users1_idx` (`user_id`),
  CONSTRAINT `FKk7du8b8ewipawnnpg76d55fus` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_users_has_recipes_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`recipes_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_recipes_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredients` (
  `ingredients_id` int(11) NOT NULL AUTO_INCREMENT,
  `ingredient` varchar(45) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`ingredients_id`,`category_id`),
  KEY `fk_ingredients_category1_idx` (`category_id`),
  CONSTRAINT `FK44j0v4g89hhdoe09s8fp7kos6` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `fk_ingredients_category1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,'??s?a??s??? ??µ??',1),(2,'???????? ??µ??',1),(3,'F???t? ??t?p????',1),(4,'?p??t? ??t?p????',1),(5,'F???t? Ga??p???a?',1),(6,'?p??t? Ga??p???a?',1),(7,'??t?p????',1),(8,'Ga??p???a',1),(9,'?p?????e? ??s?a??s?e?',1),(10,'?p?????e? ????????',1),(11,'???????',2),(12,'??eµµ?d?',2),(13,'??????p?d?',2),(14,'?p?µ?e?',2),(15,'Spa????',2),(16,'?t?µ?ta',3),(17,'?eµ???',3),(18,'???t?????',3),(19,'?pa???a',3),(20,'?a?ta????',3),(21,'Staf?de?',3),(22,'?????',3),(23,'???s??e? ??pe????',2),(24,'??????e? ??pe????',2),(25,'Ga??de?',4),(26,'Sa?d??e?',4),(27,'S???µ??',4),(28,'??d?a',4),(29,'?a?aµa????a',4),(30,'??p???',5),(31,'?s???',5),(32,'?p????ﬂ?',5),(33,'???a??',5),(34,'S???d?',5),(35,'?????',5),(36,'F???a ??f???',5),(37,'??µ???',5),(38,'?as??????',5),(39,'???sµ??',5),(40,'?e?d????ﬂa??',5),(41,'?a???a',5),(42,'????',5),(43,'Spa???t?',6),(44,'????e?',6),(45,'?a????a',6),(46,'?a??at??e?',6),(47,'??a?a???',6),(48,'???? G?ass?',7),(49,'???? ?a?????a',7),(50,'???? ??????',7),(51,'??a???ad?',8),(52,'?????a??',8),(53,'?a??t?',2),(54,'???st??da',8),(55,'??tsap',8),(56,'?t?µ?ta ???-?as?',8),(57,'?t?µat?p??t??',8),(58,'??µ?? ?eµ???',8),(59,'S?????',2),(60,'??as? ?e???',8),(61,'??as? ???????',8),(62,'??d?',8),(63,'??d? ?a?s?µ???',8),(64,'?a??ta???',5),(65,'??µ? ?pa???t???',8),(66,'??e???',8),(67,'S?da fa??t??',8),(68,'?at?te? ?a???????',9),(69,'?at?te? Baby',9),(70,'Fa???',10),(71,'Fas???a',10),(72,'?eﬂ???a',10),(73,'???t?',5);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipes` (
  `recipes_id` int(11) NOT NULL,
  `title` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(5000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`recipes_id`,`user_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `FKlc3x6yty3xsupx80hqbj9ayos` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `recipes_has_ingredients`
--

DROP TABLE IF EXISTS `recipes_has_ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipes_has_ingredients` (
  `recipes_id` int(11) NOT NULL,
  `ingredients_id` int(11) NOT NULL,
  `quantity` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`recipes_id`,`ingredients_id`),
  KEY `fk_recipes_has_ingredients_ingredients1_idx` (`ingredients_id`),
  KEY `fk_recipes_has_ingredients_recipes1_idx` (`recipes_id`),
  CONSTRAINT `FK2nsvs00tq3dqq2c17nop4afw7` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`recipes_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK97cvcqe1omd2r30wg4cvmh9e5` FOREIGN KEY (`ingredients_id`) REFERENCES `ingredients` (`ingredients_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_recipes_has_ingredients_ingredients1` FOREIGN KEY (`ingredients_id`) REFERENCES `ingredients` (`ingredients_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_ingredients_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`recipes_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes_has_ingredients`
--

LOCK TABLES `recipes_has_ingredients` WRITE;
/*!40000 ALTER TABLE `recipes_has_ingredients` DISABLE KEYS */;
INSERT INTO `recipes_has_ingredients` VALUES (896,12,'1 µ?t???'),(896,34,'2 s?e??de?'),(896,36,'2'),(896,51,'2 ?.s.'),(896,53,'1'),(896,56,'1 ???s??ﬂa'),(896,57,'1 ?.s.'),(896,70,'300??'),(896,73,'1 ?.s.'),(1193,12,'2 ?e??'),(1193,23,'1'),(1193,24,'1'),(1193,51,'5 ?.s.'),(1193,56,'100??'),(1193,59,'1'),(1193,71,'500??');
/*!40000 ALTER TABLE `recipes_has_ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-28 18:39:06
