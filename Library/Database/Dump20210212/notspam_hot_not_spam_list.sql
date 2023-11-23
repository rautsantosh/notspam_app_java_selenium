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

--
-- Dumping data for table `not_spam_list`
--

LOCK TABLES `not_spam_list` WRITE;
/*!40000 ALTER TABLE `not_spam_list` DISABLE KEYS */;
INSERT INTO `not_spam_list` VALUES (349362,'JasmineWelchBuXx@aol.com','Xxh2HjMK0D','216.10.29.170',3128,'NA','NA','AdrianBlacke5iz@aol.com','NA','NA','NA','2020-03-24 19:09:56',344,104,'Unable to sign in'),(349363,'AdrianBlacke5iz@aol.com','1ZGvJnHKvD','45.185.54.44',3128,'NA','NA','JasmineWelchBuXx@aol.com','NA','NA','NA','2020-03-24 19:11:21',344,104,'Unable to sign in'),(349364,'VirginiaFisherPQ8W@aol.com','UupbT0QrRC','45.4.206.89',3128,'NA','NA','JaneForsythA5AH@aol.com','NA','NA','NA','2020-03-26 15:19:59',344,200,'Success'),(349365,'JaneForsythA5AH@aol.com','dECzxxyYV0','45.185.55.133',3128,'NA','NA','VirginiaFisherPQ8W@aol.com','NA','NA','NA','2020-03-24 19:15:51',344,200,'Success'),(349461,'markmatheson339@hotmail.com','FrancesRa','204.153.136.6',3129,'NA','NA','NA','NA','NA','NA','2020-12-03 14:59:23',355,2,'Paused'),(349462,'ashleyharrington139@hotmail.com','GaryNullJ','204.153.136.8',3129,'NA','NA','NA','NA','NA','NA','2020-12-03 14:59:23',355,2,'Paused'),(349463,'markmatheson339@hotmail.com','FrancesRa','204.153.136.6',3129,'NA','NA','NA','NA','NA','NA','2020-12-03 23:21:14',356,2,'Paused'),(349464,'ashleyharrington139@hotmail.com','GaryNullJ','204.153.136.8',3129,'NA','NA','NA','NA','NA','NA','2020-12-03 23:21:14',356,2,'Paused'),(349465,'deirdrehughesqot@yahoo.com','fUxClysSag','172.86.117.62',21270,'NA','NA','NA','NA','NA','NA','2020-12-17 21:18:50',357,200,'Success'),(349467,'rachelwatsonltz@yahoo.com','jvl0tAJa2B','23.106.24.217',29842,'niqbal','38dpHPNf','NA','NA','NA','NA','2020-12-15 19:48:26',359,106,'Sorry, we don\'t recognize this email.'),(349468,'phasosnyuyne@hotmail.com','nlmbc2C0Tyz4','194.35.14.96',29842,'NA','NA','NA','NA','NA','NA','2021-01-02 19:07:24',360,0,'In queue'),(349469,'phompyssqqoo@outlook.com','XrjwL8s8XyLC','66.151.50.96',29842,'NA','NA','NA','NA','NA','NA','2021-01-02 19:07:56',361,0,'In queue');
/*!40000 ALTER TABLE `not_spam_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-12 19:35:11
