/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.17-log : Database - db_kc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_kc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_kc`;

/*Table structure for table `t_kid` */

DROP TABLE IF EXISTS `t_kid`;

CREATE TABLE `t_kid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `brithdate` varchar(45) DEFAULT NULL,
  `photo` varchar(128) DEFAULT NULL,
  `parentsId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parentsId` (`parentsId`),
  CONSTRAINT `t_kid_ibfk_1` FOREIGN KEY (`parentsId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_kid` */

insert  into `t_kid`(`id`,`name`,`brithdate`,`photo`,`parentsId`) values 
(1,'hh','hh',NULL,1);

/*Table structure for table `t_teacher` */

DROP TABLE IF EXISTS `t_teacher`;

CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_teacher` */

insert  into `t_teacher`(`id`,`name`) values 
(1,'hh123124');

/*Table structure for table `t_teaching` */

DROP TABLE IF EXISTS `t_teaching`;

CREATE TABLE `t_teaching` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kidId` int(11) NOT NULL,
  `teacherId` int(11) NOT NULL,
  `teachDate` varchar(45) NOT NULL,
  `content` text NOT NULL,
  `effect` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kidId` (`kidId`),
  KEY `teacherId` (`teacherId`),
  CONSTRAINT `t_teaching_ibfk_1` FOREIGN KEY (`kidId`) REFERENCES `t_kid` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_teaching_ibfk_2` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_teaching` */

insert  into `t_teaching`(`id`,`kidId`,`teacherId`,`teachDate`,`content`,`effect`) values 
(1,1,1,'web','web','servlet和jsp');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`role`,`name`,`pwd`,`tel`,`address`) values 
(1,'admin','111','111','111','111');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
