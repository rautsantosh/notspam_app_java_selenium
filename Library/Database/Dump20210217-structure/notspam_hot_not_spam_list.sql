-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: notspam_hot
-- ------------------------------------------------------
-- Server version	5.6.40-log

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
-- Table structure for table `not_spam_list`
--

DROP TABLE IF EXISTS `not_spam_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `not_spam_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `proxy_ip` varchar(250) NOT NULL,
  `proxy_port` int(11) NOT NULL,
  `proxy_username` varchar(250) DEFAULT NULL,
  `proxy_password` varchar(250) DEFAULT NULL,
  `email_to` varchar(250) DEFAULT NULL,
  `recovery_email` varchar(400) DEFAULT NULL,
  `forward_to` varchar(125) DEFAULT NULL,
  `new_password` varchar(50) DEFAULT NULL,
  `add_on` datetime NOT NULL,
  `task_id` int(11) NOT NULL,
  `status_code` int(11) NOT NULL,
  `status_text` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `status_code` (`status_code`),
  KEY `status_text` (`status_text`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `not_spam_list_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=349470 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-17 19:56:01
