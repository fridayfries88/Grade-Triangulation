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
-- Table structure for table `assignment1`
--

DROP TABLE IF EXISTS `assignment1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment1` (
  `assignment1_student_id` int unsigned NOT NULL,
  `TextBox_Text` varchar(100) DEFAULT NULL,
  `Percent_Percentage` varchar(100) DEFAULT NULL,
  `Yes or No_Yes/No` varchar(100) DEFAULT NULL,
  `extra 4_Text` varchar(100) DEFAULT NULL,
  `extra 5_Text` varchar(100) DEFAULT NULL,
  `extra 6_Text` varchar(100) DEFAULT NULL,
  `extra 7_Text` varchar(100) DEFAULT NULL,
  `extra 8_Text` varchar(100) DEFAULT NULL,
  `extra 9_Text` varchar(100) DEFAULT NULL,
  `extra 10_Text` varchar(100) DEFAULT NULL,
  `extra 11_Text` varchar(100) DEFAULT NULL,
  `extra 12_Text` varchar(100) DEFAULT NULL,
  `extra 13_Text` varchar(100) DEFAULT NULL,
  `extra 14_Text` varchar(100) DEFAULT NULL,
  `extra 15_Text` varchar(100) DEFAULT NULL,
  KEY `assignment1_student_id` (`assignment1_student_id`),
  CONSTRAINT `assignment1_student_id` FOREIGN KEY (`assignment1_student_id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment1`
--

LOCK TABLES `assignment1` WRITE;
/*!40000 ALTER TABLE `assignment1` DISABLE KEYS */;
INSERT INTO `assignment1` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `assignment1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment10`
--

DROP TABLE IF EXISTS `assignment10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment10` (
  `assignment_student_id` int unsigned NOT NULL,
  `jhfd_Yes/No` varchar(100) DEFAULT NULL,
  `hidigv_////percentage////` varchar(100) DEFAULT NULL,
  `dsiugfaiu_Text` varchar(100) DEFAULT NULL,
  `misc3_Text` varchar(100) DEFAULT NULL,
  `misc4_Text` varchar(100) DEFAULT NULL,
  `misc5_Text` varchar(100) DEFAULT NULL,
  `misc6_Text` varchar(100) DEFAULT NULL,
  `misc7_Text` varchar(100) DEFAULT NULL,
  `misc8_Text` varchar(100) DEFAULT NULL,
  `misc9_Text` varchar(100) DEFAULT NULL,
  `misc10_Text` varchar(100) DEFAULT NULL,
  `misc11_Text` varchar(100) DEFAULT NULL,
  `misc12_Text` varchar(100) DEFAULT NULL,
  `misc13_Text` varchar(100) DEFAULT NULL,
  `misc14_Text` varchar(100) DEFAULT NULL,
  KEY `assignment_student_id` (`assignment_student_id`),
  CONSTRAINT `assignment_student_id` FOREIGN KEY (`assignment_student_id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment10`
--

LOCK TABLES `assignment10` WRITE;
/*!40000 ALTER TABLE `assignment10` DISABLE KEYS */;
/*!40000 ALTER TABLE `assignment10` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment14`
--

DROP TABLE IF EXISTS `assignment14`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment14` (
  `assignment14_student_id` int unsigned NOT NULL,
  `eghj_Text` varchar(100) DEFAULT NULL,
  `misc1_Text` varchar(100) DEFAULT NULL,
  `misc2_Text` varchar(100) DEFAULT NULL,
  `misc3_Text` varchar(100) DEFAULT NULL,
  `misc4_Text` varchar(100) DEFAULT NULL,
  `misc5_Text` varchar(100) DEFAULT NULL,
  `misc6_Text` varchar(100) DEFAULT NULL,
  `misc7_Text` varchar(100) DEFAULT NULL,
  `misc8_Text` varchar(100) DEFAULT NULL,
  `misc9_Text` varchar(100) DEFAULT NULL,
  `misc10_Text` varchar(100) DEFAULT NULL,
  `misc11_Text` varchar(100) DEFAULT NULL,
  `misc12_Text` varchar(100) DEFAULT NULL,
  `misc13_Text` varchar(100) DEFAULT NULL,
  `misc14_Text` varchar(100) DEFAULT NULL,
  KEY `assignment14_student_id` (`assignment14_student_id`),
  CONSTRAINT `assignment14_student_id` FOREIGN KEY (`assignment14_student_id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment14`
--

LOCK TABLES `assignment14` WRITE;
/*!40000 ALTER TABLE `assignment14` DISABLE KEYS */;
/*!40000 ALTER TABLE `assignment14` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment15`
--

DROP TABLE IF EXISTS `assignment15`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment15` (
  `assignment15_student_id` int unsigned NOT NULL,
  `text_Text` varchar(100) DEFAULT NULL,
  `yes/no_Yes/No` varchar(100) DEFAULT NULL,
  `percent_////percentage////` varchar(100) DEFAULT NULL,
  `extra3_Text` varchar(100) DEFAULT NULL,
  `extra4_Text` varchar(100) DEFAULT NULL,
  `extra5_Text` varchar(100) DEFAULT NULL,
  `extra6_Text` varchar(100) DEFAULT NULL,
  `extra7_Text` varchar(100) DEFAULT NULL,
  `extra8_Text` varchar(100) DEFAULT NULL,
  `extra9_Text` varchar(100) DEFAULT NULL,
  `extra10_Text` varchar(100) DEFAULT NULL,
  `extra11_Text` varchar(100) DEFAULT NULL,
  `extra12_Text` varchar(100) DEFAULT NULL,
  `extra13_Text` varchar(100) DEFAULT NULL,
  `extra14_Text` varchar(100) DEFAULT NULL,
  KEY `assignment15_student_id` (`assignment15_student_id`),
  CONSTRAINT `assignment15_student_id` FOREIGN KEY (`assignment15_student_id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment15`
--

LOCK TABLES `assignment15` WRITE;
/*!40000 ALTER TABLE `assignment15` DISABLE KEYS */;
/*!40000 ALTER TABLE `assignment15` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment16`
--

DROP TABLE IF EXISTS `assignment16`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment16` (
  `assignment16_student_id` int unsigned NOT NULL,
  `text_Text` varchar(100) DEFAULT NULL,
  `bool_Yes/No` varchar(100) DEFAULT NULL,
  `num_Percentage` varchar(100) DEFAULT NULL,
  `extra3_Text` varchar(100) DEFAULT NULL,
  `extra4_Text` varchar(100) DEFAULT NULL,
  `extra5_Text` varchar(100) DEFAULT NULL,
  `extra6_Text` varchar(100) DEFAULT NULL,
  `extra7_Text` varchar(100) DEFAULT NULL,
  `extra8_Text` varchar(100) DEFAULT NULL,
  `extra9_Text` varchar(100) DEFAULT NULL,
  `extra10_Text` varchar(100) DEFAULT NULL,
  `extra11_Text` varchar(100) DEFAULT NULL,
  `extra12_Text` varchar(100) DEFAULT NULL,
  `extra13_Text` varchar(100) DEFAULT NULL,
  `extra14_Text` varchar(100) DEFAULT NULL,
  KEY `assignment16_student_id` (`assignment16_student_id`),
  CONSTRAINT `assignment16_student_id` FOREIGN KEY (`assignment16_student_id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment16`
--

LOCK TABLES `assignment16` WRITE;
/*!40000 ALTER TABLE `assignment16` DISABLE KEYS */;
INSERT INTO `assignment16` VALUES (4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `assignment16` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment17`
--

DROP TABLE IF EXISTS `assignment17`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment17` (
  `assignment17_student_id` int unsigned NOT NULL,
  `TextBox_Text` varchar(100) DEFAULT NULL,
  `Percent_Percentage` varchar(100) DEFAULT NULL,
  `Yes or No_Yes/No` varchar(100) DEFAULT NULL,
  `extra 4_Text` varchar(100) DEFAULT NULL,
  `extra 5_Text` varchar(100) DEFAULT NULL,
  `extra 6_Text` varchar(100) DEFAULT NULL,
  `extra 7_Text` varchar(100) DEFAULT NULL,
  `extra 8_Text` varchar(100) DEFAULT NULL,
  `extra 9_Text` varchar(100) DEFAULT NULL,
  `extra 10_Text` varchar(100) DEFAULT NULL,
  `extra 11_Text` varchar(100) DEFAULT NULL,
  `extra 12_Text` varchar(100) DEFAULT NULL,
  `extra 13_Text` varchar(100) DEFAULT NULL,
  `extra 14_Text` varchar(100) DEFAULT NULL,
  `extra 15_Text` varchar(100) DEFAULT NULL,
  KEY `assignment17_student_id` (`assignment17_student_id`),
  CONSTRAINT `assignment17_student_id` FOREIGN KEY (`assignment17_student_id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment17`
--

LOCK TABLES `assignment17` WRITE;
/*!40000 ALTER TABLE `assignment17` DISABLE KEYS */;
INSERT INTO `assignment17` VALUES (7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `assignment17` ENABLE KEYS */;
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
  CONSTRAINT `class_id` FOREIGN KEY (`assignment_class_id`) REFERENCES `classes` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (8,6,'sfgh','artyu',NULL),(9,6,'ouoig','fyg',NULL),(10,6,'yudwgf','uysadtf',NULL),(11,6,'jhg','tytkl',NULL),(12,6,'dtfhgj','ftgfg',NULL),(13,6,'asdfgjk','sdfghjkl',NULL),(14,6,'dfghj','dfghjk',NULL),(15,6,'a','abcd',NULL),(16,6,'dKNFH','FDAJSH',NULL),(17,7,'Test','TestAssignment',NULL);
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
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,1,'SCH4U.03',2024,2,1),(3,1,'ICS4U.05',2024,2,4),(6,1,'asdfghjk',2024,1,1),(7,5,'TestClass',2024,2,4);
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
  CONSTRAINT `student_class_id` FOREIGN KEY (`student_class_id`) REFERENCES `classes` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (4,6,'asdfg','dfghjk'),(5,6,'sd','ghjk'),(6,6,'sdfg','ohd'),(7,7,'First','Last'),(8,7,'First2','Last2'),(9,7,'First3','Last3');
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
INSERT INTO `types` VALUES ('Yes/No','yes','no',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Text',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Percentage','////percentage////',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'apych1','1234'),(5,'TestAccount','password');
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

-- Dump completed on 2024-06-07  9:02:24
