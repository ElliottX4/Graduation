/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.54 : Database - graduationproject
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`graduationproject` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `graduationproject`;

/*Table structure for table `carport_device` */

DROP TABLE IF EXISTS `carport_device`;

CREATE TABLE `carport_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `ip` char(32) DEFAULT NULL COMMENT '设备ip',
  `carportNumber` char(32) DEFAULT NULL COMMENT '车位号',
  `x` double DEFAULT NULL COMMENT '坐标x',
  `y` double DEFAULT NULL COMMENT '坐标y',
  `isOnline` int(11) unsigned zerofill DEFAULT NULL COMMENT '是否在线',
  `isOnMap` int(11) unsigned zerofill DEFAULT NULL COMMENT '是否在地图上',
  `isHaveCar` int(11) unsigned zerofill DEFAULT NULL COMMENT '是否有车辆',
  `carNumber` char(32) DEFAULT 'null' COMMENT '车牌号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `carport_device` */

/*Table structure for table `exit` */

DROP TABLE IF EXISTS `exit`;

CREATE TABLE `exit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type` int(11) DEFAULT NULL COMMENT '类型1：楼梯2：出口',
  `x` double DEFAULT NULL COMMENT '坐标x',
  `y` double DEFAULT NULL COMMENT '坐标y',
  `isOnMap` int(11) unsigned zerofill DEFAULT NULL COMMENT '是否在地图上',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exit` */

/*Table structure for table `map_pic` */

DROP TABLE IF EXISTS `map_pic`;

CREATE TABLE `map_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pwd` char(128) DEFAULT NULL COMMENT '图片路径',
  `isMainPic` int(11) unsigned zerofill DEFAULT NULL COMMENT '是否为主图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `map_pic` */

/*Table structure for table `point` */

DROP TABLE IF EXISTS `point`;

CREATE TABLE `point` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `x` double DEFAULT NULL COMMENT '坐标x',
  `y` double DEFAULT NULL COMMENT '坐标y',
  `nextIds` char(128) DEFAULT NULL COMMENT '连接的点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `point` */

/*Table structure for table `search_device` */

DROP TABLE IF EXISTS `search_device`;

CREATE TABLE `search_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `ip` char(32) DEFAULT NULL COMMENT '设备IP',
  `name` char(32) DEFAULT NULL COMMENT '设备名称',
  `x` double DEFAULT NULL COMMENT '坐标x',
  `y` double DEFAULT NULL COMMENT '坐标y',
  `isOnline` int(11) unsigned zerofill DEFAULT NULL COMMENT '是否在线',
  `isOnMap` int(11) unsigned zerofill DEFAULT NULL COMMENT '是否部署到地图上',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `search_device` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
