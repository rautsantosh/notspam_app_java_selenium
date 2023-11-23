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
-- Table structure for table `compose_details`
--

DROP TABLE IF EXISTS `compose_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compose_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `compose_count` varchar(250) NOT NULL,
  `compose_subject` varchar(250) NOT NULL,
  `compose_message` text NOT NULL,
  `compose_isp` varchar(10) NOT NULL,
  `upload_on` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compose_details`
--

LOCK TABLES `compose_details` WRITE;
/*!40000 ALTER TABLE `compose_details` DISABLE KEYS */;
INSERT INTO `compose_details` VALUES (1,'25','test','message','aol','2020-03-23 21:57:04'),(2,'25','test','<center><br>\n<a href=\"http://sharinting.net//{rurl}\">\n<table width=\"49%\" align=\"CENTER\" style=\"border:10px solid #052234;\"><tr><br><td align=\"center\" ><br>\n<p><font face=\"arial\" size=\"5\" color=\"#09365F\"><b>\n\nEXCLUSIVE Hollywood Secret to Eliminate Wrinkles\n\n</font><br><br>\n<img src=\"http://sharinting.net//4001/w412437AllureBeautySale4.jpg\">\n</p></td></tr><tr><td width=\"100%\" height=\"50\" align=\"CENTER\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:3px solid #ffffff;\" >\n<font face=\"arial\" size=\"4\" color=\"#09365F\"><b>\n\n[SAFETY NOTICE] New Way to Safely Remove Unsightly Wrinkles...\n\n</font></td></tr></table></a><br>\n\n<a href=\"http://sharinting.net//{ourl}\">\n<img src=\"http://sharinting.net//4001/w412437AllureBeautySaleUNS.jpg\" border=0></A>\n<br>\n<a href=\"http://sharinting.net//{uurl}\">\n<img src=\"http://sharinting.net//bd889-1.png\" border=0></A>\n<br>\n<img src=\"http://sharinting.net//{iurl}\"width=1/> <br>\n<a href=\"http://sharinting.net//{gurl}\" style=\'display:none\'></a>\n</CENTER>','yahoo','2020-03-23 21:59:24'),(3,'55','test','<h1>Hey there</h1>','aol','2020-03-23 22:07:46'),(4,'5','Test Mail','<h3>Welcome India</h3>','aol','2020-03-23 22:09:44'),(5,'3','Corona virus update','<h1 class=\"title\">Sending Mail</h1>','aol','2020-03-24 18:32:12'),(6,'3','Corona update','<h1 class=\"title\">Breaking News:</h1>','aol','2020-03-24 19:02:45'),(7,'3','Friday sell','<center>\n<a href=\"http://storesdrop.com/click.html?r-gBHfcGWAXRAqAGGcUHqBEXcqBXqFc/AtnfffgyDUZce.v_ekeTekWw|l3gNhewe6W6ekN_mqMcgforoH|EcMhaxB6vS45519\" style=\"text-decoration:none;color:black;\"><b>   You  already approved. Limited Time Offer  !!!<br><br> ROCKET MORTGAGE Official Confirmation<br><br>  Pay off your home in half the time!  IMMEDIATELY!!! <br><br> Click Here To View\n<h3><b><img alt=\"\" src=\"http://storesdrop.com/yrco3doh/sd13563sdfhamatch53915.jpg\"> <br></a>\n<br>\n<br>\n<a href=\"http://storesdrop.com/click.html?o-gBHfcGWAXRAqAGGcUHqBEXcqBXqFc/AtnfffgyDUZce.v_ekeTekWw|l3gNhewe6W6ekN_mqMcgforoH|EcMhaxB6vS45519\"><img src=\"http://storesdrop.com/yrco3doh/sd13563sdfhamatch53915_uns.jpg\"><br></a><br/>\n</center>','aol','2020-03-26 13:38:23');
/*!40000 ALTER TABLE `compose_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-12 19:35:10
