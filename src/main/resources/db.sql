-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: attend
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `blend_schedule`
--

DROP TABLE IF EXISTS `blend_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blend_schedule` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `month` int(11) NOT NULL,
  `child_schedule_id` varchar(20) DEFAULT NULL,
  `schedule_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blend_schedule`
--

LOCK TABLES `blend_schedule` WRITE;
/*!40000 ALTER TABLE `blend_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `blend_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_clock`
--

DROP TABLE IF EXISTS `class_clock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_clock` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `allow_early_time` int(11) NOT NULL,
  `allow_late_time` int(11) NOT NULL,
  `off_work_clock_date` datetime DEFAULT NULL,
  `off_work_clock_state` int(11) NOT NULL,
  `off_work_date` datetime DEFAULT NULL,
  `on_work_clock_date` datetime DEFAULT NULL,
  `on_work_clock_state` int(11) NOT NULL,
  `on_work_date` datetime DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `clock_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_clock`
--

LOCK TABLES `class_clock` WRITE;
/*!40000 ALTER TABLE `class_clock` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_clock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classes` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `is_elastic` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `total_hours` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clock`
--

DROP TABLE IF EXISTS `clock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clock` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clock`
--

LOCK TABLES `clock` WRITE;
/*!40000 ALTER TABLE `clock` DISABLE KEYS */;
/*!40000 ALTER TABLE `clock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `flag` int(11) NOT NULL,
  `is_private` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `tag` varchar(100) DEFAULT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('13361303','admin','2019-02-20 13:58:31','2019-02-21 10:24:56','admin',2,1,1,'海景科技',0,NULL,'7faea816'),('fdd966ec','admin','2019-02-20 14:02:29','2019-02-20 14:02:29','admin',2,1,2,'研发部',0,NULL,'13361303'),('7faea816','admin','2019-02-21 10:23:03','2019-02-21 10:23:03','admin',2,1,0,'我的公司',0,NULL,NULL),('4ee30970','admin','2019-02-22 10:49:14','2019-02-25 15:37:54','admin',0,0,0,'测试公司1',0,NULL,'7faea816'),('0edc7d31','admin','2019-02-22 10:49:23','2019-02-22 10:49:23','admin',0,0,0,'测试部门1',0,NULL,'4ee30970'),('92220258','admin','2019-02-22 10:49:33','2019-02-22 10:49:33','admin',0,0,0,'测试部门2',0,NULL,'4ee30970'),('43670a2e','admin','2019-03-12 11:17:51','2019-03-12 11:17:51','admin',0,0,0,'测试部门3',0,NULL,'4ee30970'),('ba6b37db','admin','2019-03-12 11:18:46','2019-03-12 11:18:46','admin',0,0,0,'子公司1',0,NULL,'4ee30970'),('e5827d95','admin','2019-03-12 11:20:22','2019-03-12 11:20:22','admin',0,0,0,'测试',0,NULL,'ba6b37db');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `duty`
--

DROP TABLE IF EXISTS `duty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `duty` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `compare_score` float NOT NULL,
  `date` datetime DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `terminal_id` varchar(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `duty`
--

LOCK TABLES `duty` WRITE;
/*!40000 ALTER TABLE `duty` DISABLE KEYS */;
/*!40000 ALTER TABLE `duty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `holiday`
--

DROP TABLE IF EXISTS `holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `holiday` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `is_work_date` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sup_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holiday`
--

LOCK TABLES `holiday` WRITE;
/*!40000 ALTER TABLE `holiday` DISABLE KEYS */;
/*!40000 ALTER TABLE `holiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `ref` varchar(100) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `user_limit` int(11) NOT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('8d62eea3','admin','2019-02-20 14:03:16','2019-02-20 14:03:16','admin','org','组织结构',NULL,4,1,'/org',1,NULL),('0fdd7dff','admin','2019-02-20 14:04:09','2019-02-20 14:04:09','admin','user','人员管理',NULL,1,11,'/org/user',1,'8d62eea3'),('3f460682','admin','2019-02-20 14:04:09','2019-02-20 14:04:09','admin','depart','部门管理',NULL,2,11,'/org/depart',1,'8d62eea3'),('aebb90e9','admin','2019-02-20 14:04:09','2019-02-20 14:04:09','admin','role','角色管理',NULL,3,11,'/org/role',1,'8d62eea3'),('0a8e5e2b','admin','2019-02-20 14:40:06','2019-02-20 14:40:06','admin','my_attend','我的考勤',NULL,1,1,'/attend',1,NULL),('91f227b1','admin','2019-02-20 14:43:18','2019-02-20 14:43:18','admin','attend_time','期间考勤',NULL,1,11,'/attend/time',1,'0a8e5e2b'),('30df1fc8','admin','2019-02-20 14:43:18','2019-02-20 14:43:18','admin','attend_day','考勤日历',NULL,2,11,'/attend/day',1,'0a8e5e2b'),('4c6e75dd','admin','2019-02-20 14:43:18','2019-02-20 14:43:18','admin','attend_month','我的月报',NULL,3,11,'/attend/month',1,'0a8e5e2b'),('c0b2dbc5','admin','2019-02-20 14:52:25','2019-02-20 14:52:25','admin','terminal','终端管理',NULL,7,1,'/terminal',1,NULL),('51325569','admin','2019-02-20 14:54:03','2019-02-20 14:54:03','admin','device','终端设备管理',NULL,1,11,'/terminal/device',1,'c0b2dbc5'),('890aaa2d','admin','2019-02-20 14:54:03','2019-02-20 14:54:03','admin','param','终端参数管理',NULL,2,11,'/terminal/param',1,'c0b2dbc5'),('fb2b692c','admin','2019-02-20 14:54:03','2019-02-20 14:54:03','admin','upgrade','终端升级管理',NULL,3,11,'/terminal/upgrade',1,'c0b2dbc5'),('2c59e67f','admin','2019-03-13 16:57:21','2019-03-13 16:57:21','admin',NULL,'考勤管理',NULL,5,1,'/attend_mgr',1,NULL),('2d701b95','admin','2019-03-13 16:59:30','2019-03-13 16:59:30','admin',NULL,'班次管理',NULL,1,11,'/attend_mgr/classes',1,'2c59e67f'),('db8d06ae','admin','2019-03-13 16:59:30','2019-03-13 16:59:30','admin',NULL,'时段管理',NULL,2,11,'/attend_mgr/clock',1,'2c59e67f'),('bae1dc2f','admin','2019-03-13 16:59:30','2019-03-13 16:59:30','admin',NULL,'智能排班',NULL,3,11,'/attend_mgr/schedule',1,'2c59e67f');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_post`
--

DROP TABLE IF EXISTS `menu_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_post` (
  `menu_id` varchar(20) NOT NULL,
  `post_id` varchar(20) NOT NULL,
  PRIMARY KEY (`menu_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_post`
--

LOCK TABLES `menu_post` WRITE;
/*!40000 ALTER TABLE `menu_post` DISABLE KEYS */;
INSERT INTO `menu_post` VALUES ('0a8e5e2b','1dc0e898'),('0a8e5e2b','2b410b8f'),('0a8e5e2b','2e7edf36'),('0a8e5e2b','33bcd86d'),('0a8e5e2b','69afafcc'),('0a8e5e2b','7cc473b9'),('0a8e5e2b','7f6f292d'),('0a8e5e2b','a725169f'),('0a8e5e2b','cf6e1aac'),('0a8e5e2b','e0c66d22'),('0fdd7dff','1dc0e898'),('0fdd7dff','2e7edf36'),('0fdd7dff','33bcd86d'),('0fdd7dff','7f6f292d'),('0fdd7dff','e0c66d22'),('2c59e67f','2e7edf36'),('2d701b95','2e7edf36'),('30df1fc8','1dc0e898'),('30df1fc8','2b410b8f'),('30df1fc8','2e7edf36'),('30df1fc8','33bcd86d'),('30df1fc8','69afafcc'),('30df1fc8','7cc473b9'),('30df1fc8','7f6f292d'),('30df1fc8','a725169f'),('30df1fc8','cf6e1aac'),('30df1fc8','e0c66d22'),('3f460682','1dc0e898'),('3f460682','2e7edf36'),('3f460682','33bcd86d'),('3f460682','7f6f292d'),('3f460682','e0c66d22'),('4c6e75dd','1dc0e898'),('4c6e75dd','2b410b8f'),('4c6e75dd','2e7edf36'),('4c6e75dd','33bcd86d'),('4c6e75dd','69afafcc'),('4c6e75dd','7cc473b9'),('4c6e75dd','7f6f292d'),('4c6e75dd','a725169f'),('4c6e75dd','cf6e1aac'),('4c6e75dd','e0c66d22'),('51325569','1dc0e898'),('51325569','2e7edf36'),('51325569','33bcd86d'),('51325569','7f6f292d'),('51325569','a725169f'),('51325569','e0c66d22'),('890aaa2d','1dc0e898'),('890aaa2d','2e7edf36'),('890aaa2d','33bcd86d'),('890aaa2d','7f6f292d'),('890aaa2d','a725169f'),('890aaa2d','e0c66d22'),('8d62eea3','1dc0e898'),('8d62eea3','2e7edf36'),('8d62eea3','33bcd86d'),('8d62eea3','7f6f292d'),('8d62eea3','e0c66d22'),('91f227b1','1dc0e898'),('91f227b1','2b410b8f'),('91f227b1','2e7edf36'),('91f227b1','33bcd86d'),('91f227b1','69afafcc'),('91f227b1','7cc473b9'),('91f227b1','7f6f292d'),('91f227b1','a725169f'),('91f227b1','cf6e1aac'),('91f227b1','e0c66d22'),('aebb90e9','1dc0e898'),('aebb90e9','2e7edf36'),('aebb90e9','33bcd86d'),('aebb90e9','7f6f292d'),('aebb90e9','e0c66d22'),('bae1dc2f','2e7edf36'),('c0b2dbc5','1dc0e898'),('c0b2dbc5','2e7edf36'),('c0b2dbc5','33bcd86d'),('c0b2dbc5','7f6f292d'),('c0b2dbc5','a725169f'),('c0b2dbc5','e0c66d22'),('db8d06ae','2e7edf36'),('fb2b692c','1dc0e898'),('fb2b692c','2e7edf36'),('fb2b692c','33bcd86d'),('fb2b692c','7f6f292d'),('fb2b692c','a725169f'),('fb2b692c','e0c66d22');
/*!40000 ALTER TABLE `menu_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `month_duty`
--

DROP TABLE IF EXISTS `month_duty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `month_duty` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `absence_day` int(11) NOT NULL,
  `actual_day` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `early_day` int(11) NOT NULL,
  `late_day` int(11) NOT NULL,
  `total_day` int(11) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `month_duty`
--

LOCK TABLES `month_duty` WRITE;
/*!40000 ALTER TABLE `month_duty` DISABLE KEYS */;
/*!40000 ALTER TABLE `month_duty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `flag` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `tag` varchar(50) DEFAULT NULL,
  `value` int(11) NOT NULL,
  `department_id` varchar(20) NOT NULL,
  `role_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES ('69afafcc','admin','2019-02-20 14:06:33','2019-02-22 10:24:52','admin',0,'普通员工',0,NULL,1,'fdd966ec','285c7243'),('7f6f292d','admin','2019-02-20 14:06:33','2019-02-20 14:06:33','admin',0,'经理',0,NULL,2,'fdd966ec','b3a0f13c'),('2e7edf36','admin','2019-02-20 14:06:33','2019-02-20 14:06:33','admin',0,'老板',0,NULL,1,'13361303','6c9ae27b'),('e0c66d22','admin','2019-02-22 10:43:00','2019-02-22 10:43:00','admin',0,'大老板',0,NULL,3,'7faea816','6c9ae27b'),('1dc0e898','admin','2019-02-22 10:50:54','2019-02-22 10:51:21','admin',0,'测试大佬',0,NULL,3,'4ee30970','6c9ae27b'),('7cc473b9','admin','2019-03-08 15:09:58','2019-03-08 15:09:58','admin',0,'测试小弟1',0,NULL,0,'0edc7d31','285c7243'),('cf6e1aac','admin','2019-03-08 15:10:12','2019-03-08 15:10:12','admin',0,'测试小弟2',0,NULL,0,'92220258','285c7243'),('33bcd86d','admin','2019-03-12 11:21:04','2019-03-12 11:21:04','admin',0,'经理',0,NULL,2,'e5827d95','b3a0f13c');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('285c7243','admin','2019-02-20 14:04:32','2019-02-20 14:04:32','admin','普通用户',0,3),('b3a0f13c','admin','2019-02-20 14:04:32','2019-02-20 14:04:32','admin','普通管理员',0,2),('6c9ae27b','admin','2019-02-20 14:04:32','2019-02-20 14:04:32','admin','超级管理员',0,1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `is_auto_schedule` int(11) NOT NULL,
  `is_blend_schedule` int(11) NOT NULL,
  `loop_time` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_class`
--

DROP TABLE IF EXISTS `schedule_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule_class` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `class_no` int(11) NOT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `schedule_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_class`
--

LOCK TABLES `schedule_class` WRITE;
/*!40000 ALTER TABLE `schedule_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terminal`
--

DROP TABLE IF EXISTS `terminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terminal` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `accept_state` int(11) NOT NULL,
  `mac_addr` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `online_state` int(11) NOT NULL,
  `password` varchar(36) DEFAULT NULL,
  `limited_num` int(11) DEFAULT NULL,
  `identify_num` int(11) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `registered_num` int(11) DEFAULT NULL,
  `stranger_num` int(11) DEFAULT NULL,
  `storage_space` int(11) DEFAULT NULL,
  `storage_used` int(11) DEFAULT NULL,
  `transmit_state` int(11) NOT NULL,
  `version` varchar(20) DEFAULT NULL,
  `department_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminal`
--

LOCK TABLES `terminal` WRITE;
/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
INSERT INTO `terminal` VALUES ('df6f10bf',NULL,NULL,'2019-03-11 10:05:58','admin',1,'00000002','设备1',0,NULL,1000,0,'127.0.0.1',8721,'2019-03-11 10:05:58',178,0,100,0,1,'V1.1','fdd966ec'),('24cba43e',NULL,NULL,'2019-03-11 10:06:11','admin',1,'00000003','设备2',0,NULL,1000,0,'127.0.0.1',8721,'2019-03-11 10:06:11',176,0,100,0,1,'V1.1','fdd966ec'),('e3846c75',NULL,NULL,'2019-03-11 10:06:23','admin',1,'00000004','设备3',0,NULL,1000,0,'127.0.0.1',8721,'2019-03-11 10:06:23',178,0,100,0,1,'V1.1','0edc7d31'),('988976d4',NULL,NULL,'2019-03-11 10:06:33','admin',1,'00000005','设备4',0,NULL,1000,0,'127.0.0.1',8721,'2019-03-11 10:06:33',176,0,100,0,1,'V1.1','92220258'),('c455cc2c',NULL,NULL,'2019-03-11 10:06:56','admin',1,'00000007','设备6',0,NULL,1000,0,'127.0.0.1',8721,'2019-03-11 10:06:56',175,0,100,0,1,'V1.1','0edc7d31');
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `ic_no` varchar(50) DEFAULT NULL,
  `work_state` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `avatar` longtext,
  `born_date` datetime DEFAULT NULL,
  `card_num` varchar(50) DEFAULT NULL,
  `hire_date` datetime DEFAULT NULL,
  `is_attendancer` int(11) NOT NULL,
  `leave_date` datetime DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `reg_time` datetime DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `template` longtext,
  `terminal_role` varchar(10) DEFAULT NULL,
  `agent_id` varchar(20) DEFAULT NULL,
  `schedule_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('fc99a570',NULL,NULL,'2019-03-03 14:57:02','admin','111111111111111111',1,0,'','2019-02-20 14:08:00','00000001','2019-02-20 14:08:00',1,NULL,'su','123456',NULL,'男',NULL,NULL,'fc99a570',NULL),('017440ad','admin','2019-02-20 14:08:01','2019-02-20 14:08:01','admin',NULL,1,0,'','2019-02-20 14:08:01','00000003','2019-02-20 14:08:01',1,NULL,'su','123','2019-02-20 14:08:01',NULL,'','1001',NULL,NULL),('fd671478','admin','2019-02-20 14:08:01','2019-02-27 16:45:47','admin',NULL,1,0,'','2019-02-20 14:08:01','00000004','2019-02-20 14:08:01',1,NULL,'su','123','2019-02-20 14:08:01',NULL,'','1001',NULL,NULL),('1f1b97ca','admin','2019-02-20 14:08:01','2019-02-20 14:08:01','admin',NULL,1,0,'','2019-02-20 14:08:01','00000005','2019-02-20 14:08:01',1,NULL,'su','123456','2019-02-20 14:08:01',NULL,'','1001',NULL,NULL),('4178f7e7','admin','2019-02-20 14:08:01','2019-02-27 16:47:34','admin',NULL,1,0,'','2019-02-20 14:08:01','00000006','2019-02-20 14:08:01',1,NULL,'su','123','2019-02-20 14:08:01',NULL,'','1001',NULL,NULL),('4453c61f','admin','2019-02-20 14:08:01','2019-02-20 14:08:01','admin',NULL,1,0,'','2019-02-20 14:08:01','00000008','2019-02-20 14:08:01',1,NULL,'su','123456','2019-02-20 14:08:01',NULL,'','1001',NULL,NULL),('d0ad6d91','admin','2019-02-20 14:08:01','2019-02-20 14:08:01','admin',NULL,1,0,'','2019-02-20 14:08:01','00000009','2019-02-20 14:08:01',1,NULL,'su','123456','2019-02-20 14:08:01',NULL,'','1001',NULL,NULL),('f15e9029','admin','2019-02-20 14:08:02','2019-02-20 14:08:02','admin',NULL,1,0,'','2019-02-20 14:08:02','000000012','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('e58a174a','admin','2019-02-20 14:08:02','2019-02-27 16:47:34','admin',NULL,1,0,'','2019-02-20 14:08:02','000000013','2019-02-20 14:08:02',1,NULL,'su','123','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('84d0e624','admin','2019-02-20 14:08:02','2019-02-20 14:08:02','admin',NULL,1,0,'','2019-02-20 14:08:02','000000014','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('59e8bb69','admin','2019-02-20 14:08:02','2019-02-20 14:08:02','admin',NULL,1,0,'','2019-02-20 14:08:02','000000015','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('bd091481','admin','2019-02-20 14:08:02','2019-02-20 14:08:02','admin',NULL,1,0,'','2019-02-20 14:08:02','000000016','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('ed855905','admin','2019-02-20 14:08:02','2019-02-20 14:08:02','admin',NULL,1,0,'','2019-02-20 14:08:02','000000017','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('9bfe3dc6','admin','2019-02-20 14:08:02','2019-02-20 14:08:02','admin',NULL,1,0,'','2019-02-20 14:08:02','000000018','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('8c3a47f2','admin','2019-02-20 14:08:02','2019-02-20 14:08:02','admin',NULL,1,0,'','2019-02-20 14:08:02','000000019','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('77b3a944','admin','2019-02-20 14:08:02','2019-02-20 14:08:02','admin',NULL,1,0,'','2019-02-20 14:08:02','000000020','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('1111a53f','admin','2019-02-20 14:08:02','2019-02-20 14:08:02','admin',NULL,1,0,'','2019-02-20 14:08:02','000000021','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('8a2ccd90','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:02','000000022','2019-02-20 14:08:02',1,NULL,'su','123456','2019-02-20 14:08:02',NULL,'','1001',NULL,NULL),('df0f57c5','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000023','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('957deca8','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000024','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('fd08d780','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000025','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('c4d28bbf','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000026','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('54236dbe','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000027','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('444eff92','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000028','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('c89c619f','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000029','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('77a67e0d','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000030','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('f43eb014','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000031','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('405c727d','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000032','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('03f94f2b','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000033','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('fdad5c3e','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000034','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('64eb61c4','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000035','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('1fce6e8a','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000036','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('61ff5090','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000037','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('cb358347','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000038','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('df16ed00','admin','2019-02-20 14:08:03','2019-02-20 14:08:03','admin',NULL,1,0,'','2019-02-20 14:08:03','000000039','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('f92f3603','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:03','000000040','2019-02-20 14:08:03',1,NULL,'su','123456','2019-02-20 14:08:03',NULL,'','1001',NULL,NULL),('1b86ccb0','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000041','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('d6b78066','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000042','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('a36ba421','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000043','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('c3feee44','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000044','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('c750e8cf','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000045','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('c5ab274f','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000046','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('af9c1370','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000047','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('1666e9cc','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000048','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('70758217','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000049','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('495f50d6','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000050','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('757cea6c','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000051','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('7e061eb5','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000052','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('1cb825bd','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000053','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('1b98d4bd','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000054','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('cf92ba85','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000055','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('04d1fedb','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000056','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('bbac64f2','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000057','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('446a27a2','admin','2019-02-20 14:08:04','2019-02-20 14:08:04','admin',NULL,1,0,'','2019-02-20 14:08:04','000000058','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('74b4dcaa','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:04','000000059','2019-02-20 14:08:04',1,NULL,'su','123456','2019-02-20 14:08:04',NULL,'','1001',NULL,NULL),('1e56d352','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000060','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('faba5fbb','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000061','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('4c5868c0','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000062','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('a975b9b9','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000063','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('7e33c9ac','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000064','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('740380d7','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000065','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('e28c64cc','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000066','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('856dd0f5','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000067','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('c7b62e2d','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000068','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('2dfcd741','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000069','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('90252242','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000070','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('c7db4012','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000071','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('36c6ad3a','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000072','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('9ddc669e','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000073','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('66d6a1b8','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000074','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('7fd84c14','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000075','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('f111f42e','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000076','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('cf7255f8','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000077','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('44e2be2b','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000078','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('bfe2a226','admin','2019-02-20 14:08:05','2019-02-20 14:08:05','admin',NULL,1,0,'','2019-02-20 14:08:05','000000079','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('dbb986c5','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:05','000000080','2019-02-20 14:08:05',1,NULL,'su','123456','2019-02-20 14:08:05',NULL,'','1001',NULL,NULL),('9a9fb914','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000081','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('f8666c98','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000082','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('66d4460d','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000083','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('fed4225c','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000084','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('ef6abc11','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000085','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('64fd8808','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000086','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('ac8d38c3','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000087','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('147eaa2f','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000088','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('4c0d1e32','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000089','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('26439790','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000090','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('2e0c005e','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000091','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('4d74e695','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000092','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('90643648','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000093','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('2f4f82b0','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000094','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('ab5f5492','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000095','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('24cb0763','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000096','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('731971f2','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000097','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('93615e25','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000098','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('3db5cd42','admin','2019-02-20 14:08:06','2019-02-20 14:08:06','admin',NULL,1,0,'','2019-02-20 14:08:06','000000099','2019-02-20 14:08:06',1,NULL,'su','123456','2019-02-20 14:08:06',NULL,'','1001',NULL,NULL),('cf17afb1','admin','2019-03-03 16:51:02','2019-03-03 16:51:02','admin','222222222222222222',0,0,NULL,'2019-03-13 00:00:00','222','2019-03-14 00:00:00',1,NULL,'222','123456',NULL,'女',NULL,NULL,NULL,NULL),('888d353d','admin','2019-03-03 16:47:55','2019-03-03 16:47:55','admin','111111111111111111',2,0,NULL,'2019-03-04 00:00:00','1111','2019-03-04 00:00:00',0,NULL,'1111',NULL,NULL,'女',NULL,NULL,NULL,NULL),('37e92ff1',NULL,NULL,'2019-03-03 16:47:24','admin','111111111111111111',1,0,NULL,'2019-03-03 00:00:00','999','2019-03-03 00:00:00',1,NULL,'龙哥哥','123456',NULL,'男',NULL,NULL,'dc28e5f1',NULL),('b31c6d42','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000115','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('38ce74a2','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000116','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('9615eebf','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000117','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('30546b53','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000118','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('9a0a66d5','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000119','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('5d6ebd38','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000120','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('176fe306','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000121','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('f440307f','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000122','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('5df466d3','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000123','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('e63dcea4','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000124','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('af5e2949','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000125','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('cb80cb2c','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000126','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('34976327','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000127','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('3ce7962c','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000128','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('90533fa3','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000129','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('1acf8b8f','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000130','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('88f9e1dd','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000131','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('97042fd2','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000132','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('8493f34a','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000133','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('5b53c220','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000134','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('575a2050','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000135','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('155cb80c','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000136','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('f83d1c25','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000137','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('1a8843ec','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000138','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('948f6d78','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000139','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('71167682','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000140','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('a4e0f418','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000141','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('3e4c5d10','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000142','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('090c918e','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000143','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('9ee01b50','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000144','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('b3c0c6b8','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000145','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('2a5f24f0','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000146','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('9fc66899','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000147','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('8d6ec97a','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000148','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('6b3929fa','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000149','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('54ea62f3','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000150','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('ed3db89f','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000151','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('a7430276','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000152','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('6ab121ae','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000153','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('adad66b2','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000154','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('f8f1f9e8','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000155','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('907bb4b8','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000156','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('382a84c3','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000157','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('8b06527c','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000158','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('79ccbcd2','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000159','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('312778d3','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000160','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('55a9c80a','admin','2019-02-21 15:17:39','2019-02-21 15:17:39','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000161','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('c66012b9','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000162','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('62f735da','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000163','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('7aeb27c8','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000164','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('5341d0a9','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000165','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('fdcd3101','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000166','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('260d692e','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000167','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('8e339c51','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000168','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('924724a0','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000169','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('c22b1c9d','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000170','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('10bf2312','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000171','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('52900601','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000172','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('36d31970','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000173','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('b6df8e97','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000174','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('1dede1c4','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000175','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('3c4581ab','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000176','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('ced2e5be','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000177','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('e7681164','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000178','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('41c5ff58','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000179','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('ffd314ea','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000180','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('0da8067e','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000181','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('61d17682','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000182','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('fb79ace9','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000183','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('8eee1d22','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000184','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('441a7ce2','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000185','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('ef9598f4','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000186','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('49af689b','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000187','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('4d4b4ed1','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000188','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('9fec3ae8','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000189','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('370bef50','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000190','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('704ea0fc','admin','2019-02-21 15:17:40','2019-02-21 15:17:40','admin',NULL,1,0,'','2019-02-21 15:17:32','0000000191','2019-02-21 15:17:32',1,NULL,'admin','123456','2019-02-21 15:17:32',NULL,'','1001',NULL,NULL),('dc28e5f1',NULL,NULL,'2019-03-03 16:28:25','admin','111111111111111111',1,0,NULL,'1995-09-12 00:00:00','1099','2019-02-27 16:36:37',0,NULL,'test','123',NULL,'男',NULL,NULL,NULL,NULL),('433bfb88','admin','2019-03-06 15:58:07','2019-03-06 15:58:07','admin','111111111111111111',2,0,NULL,'2019-03-06 00:00:00','999','2019-03-06 00:00:00',1,NULL,'陈敬龙','123456',NULL,'男',NULL,NULL,'dc28e5f1',NULL),('35ca3d46',NULL,NULL,'2019-03-06 16:09:06','admin','111111111111111111',2,0,NULL,'2019-03-13 00:00:00','9527','2019-03-13 00:00:00',1,NULL,'陈敬龙','123456',NULL,'男',NULL,NULL,'dc28e5f1',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_classes`
--

DROP TABLE IF EXISTS `user_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_classes` (
  `id` varchar(20) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_classes`
--

LOCK TABLES `user_classes` WRITE;
/*!40000 ALTER TABLE `user_classes` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_post`
--

DROP TABLE IF EXISTS `user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_post` (
  `user_id` varchar(20) NOT NULL,
  `post_id` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_post`
--

LOCK TABLES `user_post` WRITE;
/*!40000 ALTER TABLE `user_post` DISABLE KEYS */;
INSERT INTO `user_post` VALUES ('017440ad','69afafcc'),('017440ad','7f6f292d'),('017440ad','e0c66d22'),('03f94f2b','69afafcc'),('04d1fedb','69afafcc'),('090c918e','69afafcc'),('0a1b2193','69afafcc'),('0da8067e','69afafcc'),('10bf2312','69afafcc'),('1111a53f','69afafcc'),('147eaa2f','69afafcc'),('155cb80c','69afafcc'),('1666e9cc','69afafcc'),('176fe306','69afafcc'),('1a8843ec','69afafcc'),('1acf8b8f','69afafcc'),('1b86ccb0','69afafcc'),('1b98d4bd','69afafcc'),('1cb825bd','69afafcc'),('1dede1c4','69afafcc'),('1e56d352','69afafcc'),('1f1b97ca','69afafcc'),('1f1b97ca','7f6f292d'),('1fce6e8a','69afafcc'),('24cb0763','69afafcc'),('260d692e','69afafcc'),('26439790','69afafcc'),('2a5f24f0','69afafcc'),('2dfcd741','69afafcc'),('2e0c005e','69afafcc'),('2f4f82b0','69afafcc'),('30546b53','69afafcc'),('312778d3','69afafcc'),('34976327','69afafcc'),('35ca3d46','1dc0e898'),('35ca3d46','33bcd86d'),('35ca3d46','e0c66d22'),('36c6ad3a','69afafcc'),('36d31970','69afafcc'),('370bef50','69afafcc'),('382a84c3','69afafcc'),('38ce74a2','69afafcc'),('3c4581ab','69afafcc'),('3ce7962c','69afafcc'),('3db5cd42','69afafcc'),('3e4c5d10','69afafcc'),('405c727d','69afafcc'),('4178f7e7','69afafcc'),('4178f7e7','7f6f292d'),('41c5ff58','69afafcc'),('441a7ce2','69afafcc'),('444eff92','69afafcc'),('4453c61f','69afafcc'),('446a27a2','69afafcc'),('44e2be2b','69afafcc'),('495f50d6','69afafcc'),('49af689b','69afafcc'),('4c0d1e32','69afafcc'),('4c5868c0','69afafcc'),('4d4b4ed1','69afafcc'),('4d74e695','69afafcc'),('52900601','69afafcc'),('5341d0a9','69afafcc'),('54236dbe','69afafcc'),('54ea62f3','69afafcc'),('55a9c80a','69afafcc'),('575a2050','69afafcc'),('59e8bb69','69afafcc'),('5b53c220','69afafcc'),('5d6ebd38','69afafcc'),('5df466d3','69afafcc'),('61d17682','69afafcc'),('61ff5090','69afafcc'),('62f735da','69afafcc'),('64eb61c4','69afafcc'),('64fd8808','69afafcc'),('66d4460d','69afafcc'),('66d6a1b8','69afafcc'),('6ab121ae','69afafcc'),('6b3929fa','69afafcc'),('704ea0fc','69afafcc'),('70758217','69afafcc'),('71167682','69afafcc'),('731971f2','69afafcc'),('740380d7','69afafcc'),('74b4dcaa','69afafcc'),('757cea6c','69afafcc'),('77a67e0d','69afafcc'),('77b3a944','69afafcc'),('79ccbcd2','69afafcc'),('7aeb27c8','69afafcc'),('7e061eb5','69afafcc'),('7e33c9ac','69afafcc'),('7fd84c14','69afafcc'),('8493f34a','69afafcc'),('84d0e624','69afafcc'),('856dd0f5','69afafcc'),('88f9e1dd','69afafcc'),('8a2ccd90','69afafcc'),('8b06527c','69afafcc'),('8c3a47f2','69afafcc'),('8d6ec97a','69afafcc'),('8e339c51','69afafcc'),('8eee1d22','69afafcc'),('90252242','69afafcc'),('90533fa3','69afafcc'),('90643648','69afafcc'),('907bb4b8','69afafcc'),('924724a0','69afafcc'),('93615e25','69afafcc'),('948f6d78','69afafcc'),('957deca8','69afafcc'),('9615eebf','69afafcc'),('97042fd2','69afafcc'),('9a0a66d5','69afafcc'),('9a9fb914','69afafcc'),('9bfe3dc6','69afafcc'),('9ddc669e','69afafcc'),('9ee01b50','69afafcc'),('9fc66899','69afafcc'),('9fec3ae8','69afafcc'),('a36ba421','69afafcc'),('a4e0f418','69afafcc'),('a7430276','69afafcc'),('a975b9b9','69afafcc'),('ab5f5492','69afafcc'),('ac8d38c3','69afafcc'),('adad66b2','69afafcc'),('af5e2949','69afafcc'),('af9c1370','69afafcc'),('b31c6d42','69afafcc'),('b3c0c6b8','69afafcc'),('b6df8e97','69afafcc'),('bbac64f2','69afafcc'),('bd091481','69afafcc'),('bfe2a226','69afafcc'),('c22b1c9d','69afafcc'),('c3feee44','69afafcc'),('c4d28bbf','69afafcc'),('c5ab274f','69afafcc'),('c66012b9','69afafcc'),('c750e8cf','69afafcc'),('c7b62e2d','69afafcc'),('c7db4012','69afafcc'),('c89c619f','69afafcc'),('cb358347','69afafcc'),('cb80cb2c','69afafcc'),('ced2e5be','69afafcc'),('cf7255f8','69afafcc'),('cf92ba85','69afafcc'),('d0ad6d91','69afafcc'),('d2d297e7','69afafcc'),('d2d297e7','e0c66d22'),('d6b78066','69afafcc'),('dbb986c5','69afafcc'),('dc28e5f1','2e7edf36'),('dc28e5f1','33bcd86d'),('df0f57c5','69afafcc'),('df16ed00','69afafcc'),('e28c64cc','69afafcc'),('e58a174a','69afafcc'),('e63dcea4','69afafcc'),('e7681164','69afafcc'),('ed3db89f','69afafcc'),('ed855905','69afafcc'),('ef6abc11','69afafcc'),('ef9598f4','69afafcc'),('f111f42e','69afafcc'),('f15e9029','69afafcc'),('f43eb014','69afafcc'),('f440307f','69afafcc'),('f83d1c25','69afafcc'),('f8666c98','69afafcc'),('f8f1f9e8','69afafcc'),('f92f3603','69afafcc'),('faba5fbb','69afafcc'),('fb79ace9','69afafcc'),('fc99a570','7f6f292d'),('fc99a570','e0c66d22'),('fd08d780','69afafcc'),('fd671478','69afafcc'),('fd671478','7f6f292d'),('fdad5c3e','69afafcc'),('fdcd3101','69afafcc'),('fed4225c','69afafcc'),('ffd314ea','69afafcc');
/*!40000 ALTER TABLE `user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_terminal`
--

DROP TABLE IF EXISTS `user_terminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_terminal` (
  `user_id` varchar(20) NOT NULL,
  `terminal_id` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`,`terminal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_terminal`
--

LOCK TABLES `user_terminal` WRITE;
/*!40000 ALTER TABLE `user_terminal` DISABLE KEYS */;
INSERT INTO `user_terminal` VALUES ('017440ad','24cba43e'),('017440ad','988976d4'),('017440ad','c455cc2c'),('017440ad','df6f10bf'),('017440ad','e3846c75'),('03f94f2b','24cba43e'),('03f94f2b','988976d4'),('03f94f2b','c455cc2c'),('03f94f2b','df6f10bf'),('03f94f2b','e3846c75'),('04d1fedb','24cba43e'),('04d1fedb','988976d4'),('04d1fedb','c455cc2c'),('04d1fedb','df6f10bf'),('04d1fedb','e3846c75'),('090c918e','24cba43e'),('090c918e','988976d4'),('090c918e','c455cc2c'),('090c918e','df6f10bf'),('090c918e','e3846c75'),('0a1b2193','24cba43e'),('0a1b2193','988976d4'),('0a1b2193','c455cc2c'),('0da8067e','24cba43e'),('0da8067e','988976d4'),('0da8067e','c455cc2c'),('0da8067e','df6f10bf'),('0da8067e','e3846c75'),('10bf2312','24cba43e'),('10bf2312','988976d4'),('10bf2312','c455cc2c'),('10bf2312','df6f10bf'),('10bf2312','e3846c75'),('1111a53f','24cba43e'),('1111a53f','988976d4'),('1111a53f','c455cc2c'),('1111a53f','df6f10bf'),('1111a53f','e3846c75'),('147eaa2f','24cba43e'),('147eaa2f','988976d4'),('147eaa2f','c455cc2c'),('147eaa2f','df6f10bf'),('147eaa2f','e3846c75'),('155cb80c','24cba43e'),('155cb80c','988976d4'),('155cb80c','c455cc2c'),('155cb80c','df6f10bf'),('155cb80c','e3846c75'),('1666e9cc','24cba43e'),('1666e9cc','988976d4'),('1666e9cc','c455cc2c'),('1666e9cc','df6f10bf'),('1666e9cc','e3846c75'),('176fe306','24cba43e'),('176fe306','988976d4'),('176fe306','c455cc2c'),('176fe306','df6f10bf'),('176fe306','e3846c75'),('1a8843ec','24cba43e'),('1a8843ec','988976d4'),('1a8843ec','c455cc2c'),('1a8843ec','df6f10bf'),('1a8843ec','e3846c75'),('1acf8b8f','24cba43e'),('1acf8b8f','988976d4'),('1acf8b8f','c455cc2c'),('1acf8b8f','df6f10bf'),('1acf8b8f','e3846c75'),('1b86ccb0','24cba43e'),('1b86ccb0','988976d4'),('1b86ccb0','c455cc2c'),('1b86ccb0','df6f10bf'),('1b86ccb0','e3846c75'),('1b98d4bd','24cba43e'),('1b98d4bd','988976d4'),('1b98d4bd','c455cc2c'),('1b98d4bd','df6f10bf'),('1b98d4bd','e3846c75'),('1cb825bd','24cba43e'),('1cb825bd','988976d4'),('1cb825bd','c455cc2c'),('1cb825bd','df6f10bf'),('1cb825bd','e3846c75'),('1dede1c4','24cba43e'),('1dede1c4','988976d4'),('1dede1c4','c455cc2c'),('1dede1c4','df6f10bf'),('1dede1c4','e3846c75'),('1e56d352','24cba43e'),('1e56d352','988976d4'),('1e56d352','c455cc2c'),('1e56d352','df6f10bf'),('1e56d352','e3846c75'),('1f1b97ca','24cba43e'),('1f1b97ca','988976d4'),('1f1b97ca','c455cc2c'),('1f1b97ca','df6f10bf'),('1f1b97ca','e3846c75'),('1fce6e8a','24cba43e'),('1fce6e8a','988976d4'),('1fce6e8a','c455cc2c'),('1fce6e8a','df6f10bf'),('1fce6e8a','e3846c75'),('24cb0763','24cba43e'),('24cb0763','988976d4'),('24cb0763','c455cc2c'),('24cb0763','df6f10bf'),('24cb0763','e3846c75'),('260d692e','24cba43e'),('260d692e','988976d4'),('260d692e','c455cc2c'),('260d692e','df6f10bf'),('260d692e','e3846c75'),('26439790','24cba43e'),('26439790','988976d4'),('26439790','c455cc2c'),('26439790','df6f10bf'),('26439790','e3846c75'),('2a5f24f0','24cba43e'),('2a5f24f0','988976d4'),('2a5f24f0','c455cc2c'),('2a5f24f0','df6f10bf'),('2a5f24f0','e3846c75'),('2dfcd741','24cba43e'),('2dfcd741','988976d4'),('2dfcd741','c455cc2c'),('2dfcd741','df6f10bf'),('2dfcd741','e3846c75'),('2e0c005e','24cba43e'),('2e0c005e','988976d4'),('2e0c005e','c455cc2c'),('2e0c005e','df6f10bf'),('2e0c005e','e3846c75'),('2f4f82b0','24cba43e'),('2f4f82b0','988976d4'),('2f4f82b0','c455cc2c'),('2f4f82b0','df6f10bf'),('2f4f82b0','e3846c75'),('30546b53','24cba43e'),('30546b53','988976d4'),('30546b53','c455cc2c'),('30546b53','df6f10bf'),('30546b53','e3846c75'),('312778d3','24cba43e'),('312778d3','988976d4'),('312778d3','c455cc2c'),('312778d3','df6f10bf'),('312778d3','e3846c75'),('34976327','24cba43e'),('34976327','988976d4'),('34976327','c455cc2c'),('34976327','df6f10bf'),('34976327','e3846c75'),('35ca3d46','24cba43e'),('35ca3d46','988976d4'),('35ca3d46','c455cc2c'),('35ca3d46','df6f10bf'),('35ca3d46','e3846c75'),('36c6ad3a','24cba43e'),('36c6ad3a','988976d4'),('36c6ad3a','c455cc2c'),('36c6ad3a','df6f10bf'),('36c6ad3a','e3846c75'),('36d31970','24cba43e'),('36d31970','988976d4'),('36d31970','c455cc2c'),('36d31970','df6f10bf'),('36d31970','e3846c75'),('370bef50','24cba43e'),('370bef50','988976d4'),('370bef50','c455cc2c'),('370bef50','df6f10bf'),('370bef50','e3846c75'),('37e92ff1','df6f10bf'),('37e92ff1','e3846c75'),('382a84c3','24cba43e'),('382a84c3','988976d4'),('382a84c3','c455cc2c'),('382a84c3','df6f10bf'),('382a84c3','e3846c75'),('38ce74a2','24cba43e'),('38ce74a2','988976d4'),('38ce74a2','c455cc2c'),('38ce74a2','df6f10bf'),('38ce74a2','e3846c75'),('3c4581ab','24cba43e'),('3c4581ab','988976d4'),('3c4581ab','c455cc2c'),('3c4581ab','df6f10bf'),('3c4581ab','e3846c75'),('3ce7962c','24cba43e'),('3ce7962c','988976d4'),('3ce7962c','c455cc2c'),('3ce7962c','df6f10bf'),('3ce7962c','e3846c75'),('3db5cd42','24cba43e'),('3db5cd42','988976d4'),('3db5cd42','c455cc2c'),('3db5cd42','df6f10bf'),('3db5cd42','e3846c75'),('3e4c5d10','24cba43e'),('3e4c5d10','988976d4'),('3e4c5d10','c455cc2c'),('3e4c5d10','df6f10bf'),('3e4c5d10','e3846c75'),('405c727d','24cba43e'),('405c727d','988976d4'),('405c727d','c455cc2c'),('405c727d','df6f10bf'),('405c727d','e3846c75'),('4178f7e7','24cba43e'),('4178f7e7','988976d4'),('4178f7e7','c455cc2c'),('4178f7e7','df6f10bf'),('4178f7e7','e3846c75'),('41c5ff58','24cba43e'),('41c5ff58','988976d4'),('41c5ff58','c455cc2c'),('41c5ff58','df6f10bf'),('41c5ff58','e3846c75'),('433bfb88','df6f10bf'),('433bfb88','e3846c75'),('441a7ce2','24cba43e'),('441a7ce2','988976d4'),('441a7ce2','c455cc2c'),('441a7ce2','df6f10bf'),('441a7ce2','e3846c75'),('444eff92','24cba43e'),('444eff92','988976d4'),('444eff92','c455cc2c'),('444eff92','df6f10bf'),('444eff92','e3846c75'),('4453c61f','24cba43e'),('4453c61f','988976d4'),('4453c61f','c455cc2c'),('4453c61f','df6f10bf'),('4453c61f','e3846c75'),('446a27a2','24cba43e'),('446a27a2','988976d4'),('446a27a2','c455cc2c'),('446a27a2','df6f10bf'),('446a27a2','e3846c75'),('44e2be2b','24cba43e'),('44e2be2b','988976d4'),('44e2be2b','c455cc2c'),('44e2be2b','df6f10bf'),('44e2be2b','e3846c75'),('495f50d6','24cba43e'),('495f50d6','988976d4'),('495f50d6','c455cc2c'),('495f50d6','df6f10bf'),('495f50d6','e3846c75'),('49af689b','24cba43e'),('49af689b','988976d4'),('49af689b','c455cc2c'),('49af689b','df6f10bf'),('49af689b','e3846c75'),('4c0d1e32','24cba43e'),('4c0d1e32','988976d4'),('4c0d1e32','c455cc2c'),('4c0d1e32','df6f10bf'),('4c0d1e32','e3846c75'),('4c5868c0','24cba43e'),('4c5868c0','988976d4'),('4c5868c0','c455cc2c'),('4c5868c0','df6f10bf'),('4c5868c0','e3846c75'),('4d4b4ed1','24cba43e'),('4d4b4ed1','988976d4'),('4d4b4ed1','c455cc2c'),('4d4b4ed1','df6f10bf'),('4d4b4ed1','e3846c75'),('4d74e695','24cba43e'),('4d74e695','988976d4'),('4d74e695','c455cc2c'),('4d74e695','df6f10bf'),('4d74e695','e3846c75'),('52900601','24cba43e'),('52900601','988976d4'),('52900601','c455cc2c'),('52900601','df6f10bf'),('52900601','e3846c75'),('5341d0a9','24cba43e'),('5341d0a9','988976d4'),('5341d0a9','c455cc2c'),('5341d0a9','df6f10bf'),('5341d0a9','e3846c75'),('54236dbe','24cba43e'),('54236dbe','988976d4'),('54236dbe','c455cc2c'),('54236dbe','df6f10bf'),('54236dbe','e3846c75'),('54ea62f3','24cba43e'),('54ea62f3','988976d4'),('54ea62f3','c455cc2c'),('54ea62f3','df6f10bf'),('54ea62f3','e3846c75'),('55a9c80a','24cba43e'),('55a9c80a','988976d4'),('55a9c80a','c455cc2c'),('55a9c80a','df6f10bf'),('55a9c80a','e3846c75'),('575a2050','24cba43e'),('575a2050','988976d4'),('575a2050','c455cc2c'),('575a2050','df6f10bf'),('575a2050','e3846c75'),('59e8bb69','24cba43e'),('59e8bb69','988976d4'),('59e8bb69','c455cc2c'),('59e8bb69','df6f10bf'),('59e8bb69','e3846c75'),('5b53c220','24cba43e'),('5b53c220','988976d4'),('5b53c220','c455cc2c'),('5b53c220','df6f10bf'),('5b53c220','e3846c75'),('5d6ebd38','24cba43e'),('5d6ebd38','988976d4'),('5d6ebd38','c455cc2c'),('5d6ebd38','df6f10bf'),('5d6ebd38','e3846c75'),('5df466d3','24cba43e'),('5df466d3','988976d4'),('5df466d3','c455cc2c'),('5df466d3','df6f10bf'),('5df466d3','e3846c75'),('61d17682','24cba43e'),('61d17682','988976d4'),('61d17682','c455cc2c'),('61d17682','df6f10bf'),('61d17682','e3846c75'),('61ff5090','24cba43e'),('61ff5090','988976d4'),('61ff5090','c455cc2c'),('61ff5090','df6f10bf'),('61ff5090','e3846c75'),('62f735da','24cba43e'),('62f735da','988976d4'),('62f735da','c455cc2c'),('62f735da','df6f10bf'),('62f735da','e3846c75'),('64eb61c4','24cba43e'),('64eb61c4','988976d4'),('64eb61c4','c455cc2c'),('64eb61c4','df6f10bf'),('64eb61c4','e3846c75'),('64fd8808','24cba43e'),('64fd8808','988976d4'),('64fd8808','c455cc2c'),('64fd8808','df6f10bf'),('64fd8808','e3846c75'),('66d4460d','24cba43e'),('66d4460d','988976d4'),('66d4460d','c455cc2c'),('66d4460d','df6f10bf'),('66d4460d','e3846c75'),('66d6a1b8','24cba43e'),('66d6a1b8','988976d4'),('66d6a1b8','c455cc2c'),('66d6a1b8','df6f10bf'),('66d6a1b8','e3846c75'),('6ab121ae','24cba43e'),('6ab121ae','988976d4'),('6ab121ae','c455cc2c'),('6ab121ae','df6f10bf'),('6ab121ae','e3846c75'),('6b3929fa','24cba43e'),('6b3929fa','988976d4'),('6b3929fa','c455cc2c'),('6b3929fa','df6f10bf'),('6b3929fa','e3846c75'),('704ea0fc','24cba43e'),('704ea0fc','988976d4'),('704ea0fc','c455cc2c'),('704ea0fc','df6f10bf'),('704ea0fc','e3846c75'),('70758217','24cba43e'),('70758217','988976d4'),('70758217','c455cc2c'),('70758217','df6f10bf'),('70758217','e3846c75'),('71167682','24cba43e'),('71167682','988976d4'),('71167682','c455cc2c'),('71167682','df6f10bf'),('71167682','e3846c75'),('731971f2','24cba43e'),('731971f2','988976d4'),('731971f2','c455cc2c'),('731971f2','df6f10bf'),('731971f2','e3846c75'),('740380d7','24cba43e'),('740380d7','988976d4'),('740380d7','c455cc2c'),('740380d7','df6f10bf'),('740380d7','e3846c75'),('74b4dcaa','24cba43e'),('74b4dcaa','988976d4'),('74b4dcaa','c455cc2c'),('74b4dcaa','df6f10bf'),('74b4dcaa','e3846c75'),('757cea6c','24cba43e'),('757cea6c','988976d4'),('757cea6c','c455cc2c'),('757cea6c','df6f10bf'),('757cea6c','e3846c75'),('77a67e0d','24cba43e'),('77a67e0d','988976d4'),('77a67e0d','c455cc2c'),('77a67e0d','df6f10bf'),('77a67e0d','e3846c75'),('77b3a944','24cba43e'),('77b3a944','988976d4'),('77b3a944','c455cc2c'),('77b3a944','df6f10bf'),('77b3a944','e3846c75'),('79ccbcd2','24cba43e'),('79ccbcd2','988976d4'),('79ccbcd2','c455cc2c'),('79ccbcd2','df6f10bf'),('79ccbcd2','e3846c75'),('7aeb27c8','24cba43e'),('7aeb27c8','988976d4'),('7aeb27c8','c455cc2c'),('7aeb27c8','df6f10bf'),('7aeb27c8','e3846c75'),('7e061eb5','24cba43e'),('7e061eb5','988976d4'),('7e061eb5','c455cc2c'),('7e061eb5','df6f10bf'),('7e061eb5','e3846c75'),('7e33c9ac','24cba43e'),('7e33c9ac','988976d4'),('7e33c9ac','c455cc2c'),('7e33c9ac','df6f10bf'),('7e33c9ac','e3846c75'),('7fd84c14','24cba43e'),('7fd84c14','988976d4'),('7fd84c14','c455cc2c'),('7fd84c14','df6f10bf'),('7fd84c14','e3846c75'),('8493f34a','24cba43e'),('8493f34a','988976d4'),('8493f34a','c455cc2c'),('8493f34a','df6f10bf'),('8493f34a','e3846c75'),('84d0e624','24cba43e'),('84d0e624','988976d4'),('84d0e624','c455cc2c'),('84d0e624','df6f10bf'),('84d0e624','e3846c75'),('856dd0f5','24cba43e'),('856dd0f5','988976d4'),('856dd0f5','c455cc2c'),('856dd0f5','df6f10bf'),('856dd0f5','e3846c75'),('888d353d','df6f10bf'),('888d353d','e3846c75'),('88f9e1dd','24cba43e'),('88f9e1dd','988976d4'),('88f9e1dd','c455cc2c'),('88f9e1dd','df6f10bf'),('88f9e1dd','e3846c75'),('8a2ccd90','24cba43e'),('8a2ccd90','988976d4'),('8a2ccd90','c455cc2c'),('8a2ccd90','df6f10bf'),('8a2ccd90','e3846c75'),('8b06527c','24cba43e'),('8b06527c','988976d4'),('8b06527c','c455cc2c'),('8b06527c','df6f10bf'),('8b06527c','e3846c75'),('8c3a47f2','24cba43e'),('8c3a47f2','988976d4'),('8c3a47f2','c455cc2c'),('8c3a47f2','df6f10bf'),('8c3a47f2','e3846c75'),('8d6ec97a','24cba43e'),('8d6ec97a','988976d4'),('8d6ec97a','c455cc2c'),('8d6ec97a','df6f10bf'),('8d6ec97a','e3846c75'),('8e339c51','24cba43e'),('8e339c51','988976d4'),('8e339c51','c455cc2c'),('8e339c51','df6f10bf'),('8e339c51','e3846c75'),('8eee1d22','24cba43e'),('8eee1d22','988976d4'),('8eee1d22','c455cc2c'),('8eee1d22','df6f10bf'),('8eee1d22','e3846c75'),('90252242','24cba43e'),('90252242','988976d4'),('90252242','c455cc2c'),('90252242','df6f10bf'),('90252242','e3846c75'),('90533fa3','24cba43e'),('90533fa3','988976d4'),('90533fa3','c455cc2c'),('90533fa3','df6f10bf'),('90533fa3','e3846c75'),('90643648','24cba43e'),('90643648','988976d4'),('90643648','c455cc2c'),('90643648','df6f10bf'),('90643648','e3846c75'),('907bb4b8','24cba43e'),('907bb4b8','988976d4'),('907bb4b8','c455cc2c'),('907bb4b8','df6f10bf'),('907bb4b8','e3846c75'),('924724a0','24cba43e'),('924724a0','988976d4'),('924724a0','c455cc2c'),('924724a0','df6f10bf'),('924724a0','e3846c75'),('93615e25','24cba43e'),('93615e25','988976d4'),('93615e25','c455cc2c'),('93615e25','df6f10bf'),('93615e25','e3846c75'),('948f6d78','24cba43e'),('948f6d78','988976d4'),('948f6d78','c455cc2c'),('948f6d78','df6f10bf'),('948f6d78','e3846c75'),('957deca8','24cba43e'),('957deca8','988976d4'),('957deca8','c455cc2c'),('957deca8','df6f10bf'),('957deca8','e3846c75'),('9615eebf','24cba43e'),('9615eebf','988976d4'),('9615eebf','c455cc2c'),('9615eebf','df6f10bf'),('9615eebf','e3846c75'),('97042fd2','24cba43e'),('97042fd2','988976d4'),('97042fd2','c455cc2c'),('97042fd2','df6f10bf'),('97042fd2','e3846c75'),('9a0a66d5','24cba43e'),('9a0a66d5','988976d4'),('9a0a66d5','c455cc2c'),('9a0a66d5','df6f10bf'),('9a0a66d5','e3846c75'),('9a9fb914','24cba43e'),('9a9fb914','988976d4'),('9a9fb914','c455cc2c'),('9a9fb914','df6f10bf'),('9a9fb914','e3846c75'),('9bfe3dc6','24cba43e'),('9bfe3dc6','988976d4'),('9bfe3dc6','c455cc2c'),('9bfe3dc6','df6f10bf'),('9bfe3dc6','e3846c75'),('9ddc669e','24cba43e'),('9ddc669e','988976d4'),('9ddc669e','c455cc2c'),('9ddc669e','df6f10bf'),('9ddc669e','e3846c75'),('9ee01b50','24cba43e'),('9ee01b50','988976d4'),('9ee01b50','c455cc2c'),('9ee01b50','df6f10bf'),('9ee01b50','e3846c75'),('9fc66899','24cba43e'),('9fc66899','988976d4'),('9fc66899','c455cc2c'),('9fc66899','df6f10bf'),('9fc66899','e3846c75'),('9fec3ae8','24cba43e'),('9fec3ae8','988976d4'),('9fec3ae8','c455cc2c'),('9fec3ae8','df6f10bf'),('9fec3ae8','e3846c75'),('a36ba421','24cba43e'),('a36ba421','988976d4'),('a36ba421','c455cc2c'),('a36ba421','df6f10bf'),('a36ba421','e3846c75'),('a4e0f418','24cba43e'),('a4e0f418','988976d4'),('a4e0f418','c455cc2c'),('a4e0f418','df6f10bf'),('a4e0f418','e3846c75'),('a7430276','24cba43e'),('a7430276','988976d4'),('a7430276','c455cc2c'),('a7430276','df6f10bf'),('a7430276','e3846c75'),('a975b9b9','24cba43e'),('a975b9b9','988976d4'),('a975b9b9','c455cc2c'),('a975b9b9','df6f10bf'),('a975b9b9','e3846c75'),('ab5f5492','24cba43e'),('ab5f5492','988976d4'),('ab5f5492','c455cc2c'),('ab5f5492','df6f10bf'),('ab5f5492','e3846c75'),('ac8d38c3','24cba43e'),('ac8d38c3','988976d4'),('ac8d38c3','c455cc2c'),('ac8d38c3','df6f10bf'),('ac8d38c3','e3846c75'),('adad66b2','24cba43e'),('adad66b2','988976d4'),('adad66b2','c455cc2c'),('adad66b2','df6f10bf'),('adad66b2','e3846c75'),('af5e2949','24cba43e'),('af5e2949','988976d4'),('af5e2949','c455cc2c'),('af5e2949','df6f10bf'),('af5e2949','e3846c75'),('af9c1370','24cba43e'),('af9c1370','988976d4'),('af9c1370','c455cc2c'),('af9c1370','df6f10bf'),('af9c1370','e3846c75'),('b31c6d42','24cba43e'),('b31c6d42','988976d4'),('b31c6d42','c455cc2c'),('b31c6d42','df6f10bf'),('b31c6d42','e3846c75'),('b3c0c6b8','24cba43e'),('b3c0c6b8','988976d4'),('b3c0c6b8','c455cc2c'),('b3c0c6b8','df6f10bf'),('b3c0c6b8','e3846c75'),('b6df8e97','24cba43e'),('b6df8e97','988976d4'),('b6df8e97','c455cc2c'),('b6df8e97','df6f10bf'),('b6df8e97','e3846c75'),('bbac64f2','24cba43e'),('bbac64f2','988976d4'),('bbac64f2','c455cc2c'),('bbac64f2','df6f10bf'),('bbac64f2','e3846c75'),('bd091481','24cba43e'),('bd091481','988976d4'),('bd091481','c455cc2c'),('bd091481','df6f10bf'),('bd091481','e3846c75'),('bfe2a226','24cba43e'),('bfe2a226','988976d4'),('bfe2a226','c455cc2c'),('bfe2a226','df6f10bf'),('bfe2a226','e3846c75'),('c22b1c9d','24cba43e'),('c22b1c9d','988976d4'),('c22b1c9d','c455cc2c'),('c22b1c9d','df6f10bf'),('c22b1c9d','e3846c75'),('c3feee44','24cba43e'),('c3feee44','988976d4'),('c3feee44','c455cc2c'),('c3feee44','df6f10bf'),('c3feee44','e3846c75'),('c4d28bbf','24cba43e'),('c4d28bbf','988976d4'),('c4d28bbf','c455cc2c'),('c4d28bbf','df6f10bf'),('c4d28bbf','e3846c75'),('c5ab274f','24cba43e'),('c5ab274f','988976d4'),('c5ab274f','c455cc2c'),('c5ab274f','df6f10bf'),('c5ab274f','e3846c75'),('c66012b9','24cba43e'),('c66012b9','988976d4'),('c66012b9','c455cc2c'),('c66012b9','df6f10bf'),('c66012b9','e3846c75'),('c750e8cf','24cba43e'),('c750e8cf','988976d4'),('c750e8cf','c455cc2c'),('c750e8cf','df6f10bf'),('c750e8cf','e3846c75'),('c7b62e2d','24cba43e'),('c7b62e2d','988976d4'),('c7b62e2d','c455cc2c'),('c7b62e2d','df6f10bf'),('c7b62e2d','e3846c75'),('c7db4012','24cba43e'),('c7db4012','988976d4'),('c7db4012','c455cc2c'),('c7db4012','df6f10bf'),('c7db4012','e3846c75'),('c89c619f','24cba43e'),('c89c619f','988976d4'),('c89c619f','c455cc2c'),('c89c619f','df6f10bf'),('c89c619f','e3846c75'),('cb358347','24cba43e'),('cb358347','988976d4'),('cb358347','c455cc2c'),('cb358347','df6f10bf'),('cb358347','e3846c75'),('cb80cb2c','24cba43e'),('cb80cb2c','988976d4'),('cb80cb2c','c455cc2c'),('cb80cb2c','df6f10bf'),('cb80cb2c','e3846c75'),('ced2e5be','24cba43e'),('ced2e5be','988976d4'),('ced2e5be','c455cc2c'),('ced2e5be','df6f10bf'),('ced2e5be','e3846c75'),('cf17afb1','df6f10bf'),('cf17afb1','e3846c75'),('cf7255f8','24cba43e'),('cf7255f8','988976d4'),('cf7255f8','c455cc2c'),('cf7255f8','df6f10bf'),('cf7255f8','e3846c75'),('cf92ba85','24cba43e'),('cf92ba85','988976d4'),('cf92ba85','c455cc2c'),('cf92ba85','df6f10bf'),('cf92ba85','e3846c75'),('d0ad6d91','24cba43e'),('d0ad6d91','988976d4'),('d0ad6d91','c455cc2c'),('d0ad6d91','df6f10bf'),('d0ad6d91','e3846c75'),('d2d297e7','24cba43e'),('d2d297e7','988976d4'),('d2d297e7','c455cc2c'),('d6b78066','24cba43e'),('d6b78066','988976d4'),('d6b78066','c455cc2c'),('d6b78066','df6f10bf'),('d6b78066','e3846c75'),('dbb986c5','24cba43e'),('dbb986c5','988976d4'),('dbb986c5','c455cc2c'),('dbb986c5','df6f10bf'),('dbb986c5','e3846c75'),('dc28e5f1','24cba43e'),('dc28e5f1','988976d4'),('dc28e5f1','df6f10bf'),('dc28e5f1','e3846c75'),('df0f57c5','24cba43e'),('df0f57c5','988976d4'),('df0f57c5','c455cc2c'),('df0f57c5','df6f10bf'),('df0f57c5','e3846c75'),('df16ed00','24cba43e'),('df16ed00','988976d4'),('df16ed00','c455cc2c'),('df16ed00','df6f10bf'),('df16ed00','e3846c75'),('e28c64cc','24cba43e'),('e28c64cc','988976d4'),('e28c64cc','c455cc2c'),('e28c64cc','df6f10bf'),('e28c64cc','e3846c75'),('e58a174a','24cba43e'),('e58a174a','988976d4'),('e58a174a','c455cc2c'),('e58a174a','df6f10bf'),('e58a174a','e3846c75'),('e63dcea4','24cba43e'),('e63dcea4','988976d4'),('e63dcea4','c455cc2c'),('e63dcea4','df6f10bf'),('e63dcea4','e3846c75'),('e7681164','24cba43e'),('e7681164','988976d4'),('e7681164','c455cc2c'),('e7681164','df6f10bf'),('e7681164','e3846c75'),('ed3db89f','24cba43e'),('ed3db89f','988976d4'),('ed3db89f','c455cc2c'),('ed3db89f','df6f10bf'),('ed3db89f','e3846c75'),('ed855905','24cba43e'),('ed855905','988976d4'),('ed855905','c455cc2c'),('ed855905','df6f10bf'),('ed855905','e3846c75'),('ef6abc11','24cba43e'),('ef6abc11','988976d4'),('ef6abc11','c455cc2c'),('ef6abc11','df6f10bf'),('ef6abc11','e3846c75'),('ef9598f4','24cba43e'),('ef9598f4','988976d4'),('ef9598f4','c455cc2c'),('ef9598f4','df6f10bf'),('ef9598f4','e3846c75'),('f111f42e','24cba43e'),('f111f42e','988976d4'),('f111f42e','c455cc2c'),('f111f42e','df6f10bf'),('f111f42e','e3846c75'),('f15e9029','24cba43e'),('f15e9029','988976d4'),('f15e9029','c455cc2c'),('f15e9029','df6f10bf'),('f15e9029','e3846c75'),('f43eb014','24cba43e'),('f43eb014','988976d4'),('f43eb014','c455cc2c'),('f43eb014','df6f10bf'),('f43eb014','e3846c75'),('f440307f','24cba43e'),('f440307f','988976d4'),('f440307f','c455cc2c'),('f440307f','df6f10bf'),('f440307f','e3846c75'),('f83d1c25','24cba43e'),('f83d1c25','988976d4'),('f83d1c25','c455cc2c'),('f83d1c25','df6f10bf'),('f83d1c25','e3846c75'),('f8666c98','24cba43e'),('f8666c98','988976d4'),('f8666c98','c455cc2c'),('f8666c98','df6f10bf'),('f8666c98','e3846c75'),('f8f1f9e8','24cba43e'),('f8f1f9e8','988976d4'),('f8f1f9e8','c455cc2c'),('f8f1f9e8','df6f10bf'),('f8f1f9e8','e3846c75'),('f92f3603','24cba43e'),('f92f3603','988976d4'),('f92f3603','c455cc2c'),('f92f3603','df6f10bf'),('f92f3603','e3846c75'),('faba5fbb','24cba43e'),('faba5fbb','988976d4'),('faba5fbb','c455cc2c'),('faba5fbb','df6f10bf'),('faba5fbb','e3846c75'),('fb79ace9','24cba43e'),('fb79ace9','988976d4'),('fb79ace9','c455cc2c'),('fb79ace9','df6f10bf'),('fb79ace9','e3846c75'),('fc99a570','24cba43e'),('fc99a570','988976d4'),('fc99a570','c455cc2c'),('fc99a570','df6f10bf'),('fc99a570','e3846c75'),('fd08d780','24cba43e'),('fd08d780','988976d4'),('fd08d780','c455cc2c'),('fd08d780','df6f10bf'),('fd08d780','e3846c75'),('fd671478','24cba43e'),('fd671478','988976d4'),('fd671478','c455cc2c'),('fd671478','df6f10bf'),('fd671478','e3846c75'),('fdad5c3e','24cba43e'),('fdad5c3e','988976d4'),('fdad5c3e','c455cc2c'),('fdad5c3e','df6f10bf'),('fdad5c3e','e3846c75'),('fdcd3101','24cba43e'),('fdcd3101','988976d4'),('fdcd3101','c455cc2c'),('fdcd3101','df6f10bf'),('fdcd3101','e3846c75'),('fed4225c','24cba43e'),('fed4225c','988976d4'),('fed4225c','c455cc2c'),('fed4225c','df6f10bf'),('fed4225c','e3846c75'),('ffd314ea','24cba43e'),('ffd314ea','988976d4'),('ffd314ea','c455cc2c'),('ffd314ea','df6f10bf'),('ffd314ea','e3846c75');
/*!40000 ALTER TABLE `user_terminal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-14 10:28:15
