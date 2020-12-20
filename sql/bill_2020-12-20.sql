# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.28-log)
# Database: bill
# Generation Time: 2020-12-20 04:30:30 +0000
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

CREATE TABLE `bill` (
  `bill_id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键Id',
  `bill_date` varchar(10) NOT NULL DEFAULT '19700101' COMMENT '账单日期',
  `amount` decimal(8,2) NOT NULL COMMENT '金额',
  `in_out` tinyint(1) NOT NULL DEFAULT '0' COMMENT '收支分类',
  `usage_type` int(2) DEFAULT NULL COMMENT '用途',
  `pay_way` int(2) DEFAULT NULL COMMENT '支付方式',
  `detail` varchar(255) NOT NULL DEFAULT '' COMMENT '账单详情',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单详情表';

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;

INSERT INTO `bill` (`bill_id`, `bill_date`, `amount`, `in_out`, `usage_type`, `pay_way`, `detail`, `create_time`, `update_time`)
VALUES
	('2020-12-19-001','2020-12-19',32.00,1,2,1,'午饭-美团外卖','2020-12-19 19:53:55','2020-12-19 22:20:54');

/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table in_out
# ------------------------------------------------------------

DROP TABLE IF EXISTS `in_out`;

CREATE TABLE `in_out` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` varchar(32) DEFAULT '支出' COMMENT '收支分类',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收支分类表';

LOCK TABLES `in_out` WRITE;
/*!40000 ALTER TABLE `in_out` DISABLE KEYS */;

INSERT INTO `in_out` (`id`, `type`, `create_time`, `update_time`)
VALUES
	(1,'支出','2020-12-19 20:31:54','2020-12-19 20:31:54'),
	(2,'收入','2020-12-19 20:31:54','2020-12-19 20:31:54'),
	(3,'借出','2020-12-19 20:31:54','2020-12-19 20:31:54'),
	(4,'借入','2020-12-19 20:31:54','2020-12-19 20:31:54');

/*!40000 ALTER TABLE `in_out` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pay_way
# ------------------------------------------------------------

CREATE TABLE `pay_way` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `way_name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付方式表';

LOCK TABLES `pay_way` WRITE;
/*!40000 ALTER TABLE `pay_way` DISABLE KEYS */;

INSERT INTO `pay_way` (`id`, `way_name`, `create_time`, `update_time`)
VALUES
	(1,'工商银行卡','2020-12-19 19:55:45','2020-12-19 19:55:45'),
	(2,'建设银行卡','2020-10-20 20:16:46','2020-12-19 19:57:19'),
	(3,'微信余额','2020-10-19 19:05:14','2020-12-19 19:57:27'),
	(5,'支付宝余额','2020-10-20 20:18:05','2020-12-19 19:57:33'),
	(6,'花呗','2020-10-21 23:16:42','2020-12-19 19:56:41');

/*!40000 ALTER TABLE `pay_way` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table usage_type
# ------------------------------------------------------------

CREATE TABLE `usage_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type_name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单用途表';

LOCK TABLES `usage_type` WRITE;
/*!40000 ALTER TABLE `usage_type` DISABLE KEYS */;

INSERT INTO `usage_type` (`id`, `type_name`, `create_time`, `update_time`)
VALUES
	(1,'出行','2020-10-21 23:26:29','2020-10-21 23:26:29'),
	(2,'餐饮','2020-10-21 23:26:36','2020-10-21 23:26:36'),
	(3,'娱乐','2020-10-21 23:26:41','2020-10-21 23:26:41'),
	(4,'学习','2020-10-21 23:26:46','2020-10-21 23:26:46'),
	(5,'衣物','2020-10-21 23:26:53','2020-10-21 23:26:53'),
	(6,'电子产品','2020-10-21 23:27:05','2020-12-19 20:16:21'),
	(7,'通信','2020-10-23 23:39:22','2020-12-19 20:16:29');

/*!40000 ALTER TABLE `usage_type` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
