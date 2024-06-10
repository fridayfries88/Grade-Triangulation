-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: gradetriangulation
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignment21`
--

DROP TABLE IF EXISTS `assignment21`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment21` (
  `assignment21_student_id` int unsigned NOT NULL,
  `text_Text` varchar(500) DEFAULT NULL,
  `grade_Grade` varchar(500) DEFAULT NULL,
  `check_Yes/No` varchar(500) DEFAULT NULL,
  `percent_Percentage` varchar(500) DEFAULT NULL,
  `Additional notes_Text` varchar(500) DEFAULT NULL,
  KEY `assignment21_student_id` (`assignment21_student_id`),
  CONSTRAINT `assignment21_student_id` FOREIGN KEY (`assignment21_student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment21`
--

LOCK TABLES `assignment21` WRITE;
/*!40000 ALTER TABLE `assignment21` DISABLE KEYS */;
INSERT INTO `assignment21` VALUES (22,'n','1-','yes','',''),(23,NULL,NULL,NULL,NULL,NULL),(24,NULL,NULL,NULL,NULL,NULL),(25,NULL,NULL,NULL,NULL,NULL),(26,NULL,NULL,NULL,NULL,NULL),(27,NULL,NULL,NULL,NULL,NULL),(28,NULL,NULL,NULL,NULL,NULL),(29,NULL,NULL,NULL,NULL,NULL),(30,NULL,NULL,NULL,NULL,NULL),(31,NULL,NULL,NULL,NULL,NULL),(32,NULL,NULL,NULL,NULL,NULL),(33,NULL,NULL,NULL,NULL,NULL),(34,NULL,NULL,NULL,NULL,NULL),(35,NULL,NULL,NULL,NULL,NULL),(36,NULL,NULL,NULL,NULL,NULL),(37,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `assignment21` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment22`
--

DROP TABLE IF EXISTS `assignment22`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment22` (
  `assignment22_student_id` int unsigned NOT NULL,
  `grade_Grade` varchar(500) DEFAULT NULL,
  `grade 2_Grade` varchar(500) DEFAULT NULL,
  `text box_Text` varchar(500) DEFAULT NULL,
  `Additional notes_Text` varchar(500) DEFAULT NULL,
  KEY `assignment22_student_id` (`assignment22_student_id`),
  CONSTRAINT `assignment22_student_id` FOREIGN KEY (`assignment22_student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment22`
--

LOCK TABLES `assignment22` WRITE;
/*!40000 ALTER TABLE `assignment22` DISABLE KEYS */;
INSERT INTO `assignment22` VALUES (22,'','','sdf',''),(23,'','','',''),(24,'','','g',''),(25,'','','',''),(26,'','','',''),(27,'','','',''),(28,'','','',''),(29,'','','',''),(30,'','','',''),(31,'','','',''),(32,'','','',''),(33,'','','',''),(34,'','','',''),(35,'','','',''),(36,'','','',''),(37,'','','','');
/*!40000 ALTER TABLE `assignment22` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `assignment_class_id` int unsigned NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `unit` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`assignment_class_id`),
  CONSTRAINT `class_id` FOREIGN KEY (`assignment_class_id`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (21,10,'Lab','Lab 1',NULL),(22,10,'Lab','Lab 2',NULL);
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `course_code` varchar(10) NOT NULL,
  `year` smallint unsigned NOT NULL,
  `semester` tinyint NOT NULL,
  `period` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (10,7,'ICS4U.01',2024,2,4);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `student_class_id` int unsigned NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_class_id` (`student_class_id`),
  CONSTRAINT `student_class_id` FOREIGN KEY (`student_class_id`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (22,10,'a','aa'),(23,10,'b','bb'),(24,10,'c','cc'),(25,10,'d','dd'),(26,10,'e','ee'),(27,10,'f','ff'),(28,10,'g','gg'),(29,10,'h','hh'),(30,10,'i','ii'),(31,10,'j','jj'),(32,10,'k','kk'),(33,10,'l','ll'),(34,10,'m','mm'),(35,10,'n','nn'),(36,10,'o','oo'),(37,10,'p','pp');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `types` (
  `name` varchar(20) NOT NULL,
  `1` varchar(100) DEFAULT NULL,
  `2` varchar(100) DEFAULT NULL,
  `3` varchar(100) DEFAULT NULL,
  `4` varchar(100) DEFAULT NULL,
  `5` varchar(100) DEFAULT NULL,
  `6` varchar(100) DEFAULT NULL,
  `7` varchar(100) DEFAULT NULL,
  `8` varchar(100) DEFAULT NULL,
  `9` varchar(100) DEFAULT NULL,
  `10` varchar(100) DEFAULT NULL,
  `11` varchar(100) DEFAULT NULL,
  `12` varchar(100) DEFAULT NULL,
  `13` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES ('Yes/No','yes','no',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Text',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Percentage','////percentage////',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Grade','R','1-','1','1+','2-','2','2+','3-','3','3+','4-','4','4+');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (7,'Alex Pychyl','password');
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

-- Dump completed on 2024-06-10 11:00:05
