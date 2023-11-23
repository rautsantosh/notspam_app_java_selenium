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
-- Table structure for table `task_master`
--

DROP TABLE IF EXISTS `task_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(250) NOT NULL,
  `from_name` varchar(250) NOT NULL,
  `action_type` varchar(5) NOT NULL,
  `process_inbox` varchar(5) NOT NULL,
  `process_junk` varchar(5) NOT NULL,
  `compose_mail` varchar(5) NOT NULL,
  `archive_delete` varchar(5) NOT NULL,
  `bulk_not_junk` varchar(5) NOT NULL,
  `delete_all` varchar(5) NOT NULL,
  `safe_sender` varchar(5) NOT NULL,
  `color_category` varchar(5) NOT NULL,
  `add_contact` varchar(5) NOT NULL,
  `mark_flag` varchar(5) NOT NULL,
  `click_link` varchar(5) NOT NULL,
  `reply_to` varchar(5) NOT NULL,
  `forward_mail` varchar(5) NOT NULL,
  `recovery_email` varchar(5) NOT NULL,
  `change_passwd` varchar(5) NOT NULL,
  `report_not_spam` varchar(5) DEFAULT NULL,
  `add_addon` varchar(5) DEFAULT NULL,
  `report_message` varchar(5) DEFAULT NULL,
  `loop_count` int(11) NOT NULL,
  `inbox_count` int(11) NOT NULL,
  `not_junk_count` int(11) NOT NULL,
  `delete_count` int(11) NOT NULL,
  `reply_count` int(11) NOT NULL,
  `flag_count` int(11) NOT NULL,
  `forward_count` int(11) DEFAULT NULL,
  `cc_count` int(11) DEFAULT NULL,
  `ss_count` int(11) DEFAULT NULL,
  `ac_count` int(11) DEFAULT NULL,
  `upload_on` varchar(45) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `task_master_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=362 DEFAULT CHARSET=utf8;
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
