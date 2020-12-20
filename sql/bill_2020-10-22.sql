# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.28-log)
# Database: bill
# Generation Time: 2020-10-22 05:39:51 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table bill
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `bill_id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键Id',
  `bill_date` varchar(8) NOT NULL DEFAULT '19700101' COMMENT '账单日期',
  `amount` decimal(8,2) NOT NULL COMMENT '金额',
  `in_out` tinyint(1) NOT NULL COMMENT '收支分类',
  `usage_type` varchar(2) NOT NULL DEFAULT '' COMMENT '用途',
  `pay_way` varchar(2) NOT NULL DEFAULT '' COMMENT '支付方式',
  `detail` varchar(255) NOT NULL DEFAULT '' COMMENT '账单详情',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;

INSERT INTO `bill` (`bill_id`, `bill_date`, `amount`, `in_out`, `usage_type`, `pay_way`, `detail`, `create_time`, `update_time`)
VALUES
	('20200101001','20200101',34.50,1,'1','3','创建账单测试1','2020-10-20 19:44:47','2020-10-22 09:19:28'),
	('20200101002','20200101',10.50,1,'2','2','创建账单测试3','2020-10-20 19:46:29','2020-10-22 09:10:36'),
	('20200101003','20200101',50.50,1,'3','5','创建账单测试8','2020-10-20 19:50:58','2020-10-22 09:10:38'),
	('20200102001','20200102',10.50,1,'4','4','创建账单测试2','2020-10-20 19:44:47','2020-10-22 09:10:39'),
	('20200102002','20200102',8.50,1,'5','3','创建账单测试4','2020-10-20 19:46:29','2020-10-22 09:10:40'),
	('20200103001','20200103',298.50,1,'6','5','创建账单测试5','2020-10-20 19:50:58','2020-10-22 09:10:41'),
	('20201023001','20201023',15.00,1,'2','9','美团外卖','2020-10-22 11:56:39','2020-10-22 11:56:39');

/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pay_way
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pay_way`;

CREATE TABLE `pay_way` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `way_name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `pay_way` WRITE;
/*!40000 ALTER TABLE `pay_way` DISABLE KEYS */;

INSERT INTO `pay_way` (`id`, `way_name`, `create_time`, `update_time`)
VALUES
	(2,'微信余额','2020-10-19 19:03:52','2020-10-20 20:16:15'),
	(3,'微信工商银行卡','2020-10-19 19:05:14','2020-10-21 23:18:37'),
	(4,'支付宝余额','2020-10-20 20:16:46','2020-10-20 20:16:46'),
	(5,'支付宝邮政银行卡','2020-10-20 20:18:05','2020-10-21 23:18:26'),
	(8,'支付宝花呗','2020-10-21 23:16:42','2020-10-21 23:16:42'),
	(9,'支付宝工商银行卡','2020-10-21 23:17:11','2020-10-21 23:17:11'),
	(10,'支付宝借呗','2020-10-21 23:17:33','2020-10-21 23:17:33'),
	(11,'支付宝招商银行卡','2020-10-21 23:17:38','2020-10-21 23:17:53'),
	(20,'现金','2020-10-21 23:28:40','2020-10-21 23:28:40');

/*!40000 ALTER TABLE `pay_way` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table usage_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usage_type`;

CREATE TABLE `usage_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type_name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `usage_type` WRITE;
/*!40000 ALTER TABLE `usage_type` DISABLE KEYS */;

INSERT INTO `usage_type` (`id`, `type_name`, `create_time`, `update_time`)
VALUES
	(1,'出行','2020-10-21 23:26:29','2020-10-21 23:26:29'),
	(2,'餐饮','2020-10-21 23:26:36','2020-10-21 23:26:36'),
	(3,'娱乐','2020-10-21 23:26:41','2020-10-21 23:26:41'),
	(4,'学习','2020-10-21 23:26:46','2020-10-21 23:26:46'),
	(5,'衣物','2020-10-21 23:26:53','2020-10-21 23:26:53'),
	(6,'3C','2020-10-21 23:27:05','2020-10-21 23:27:16');

/*!40000 ALTER TABLE `usage_type` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
