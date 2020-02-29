/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.5.49 : Database - authentication
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`authentication` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `authentication`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_num` char(8) NOT NULL,
  `name` varchar(20) NOT NULL,
  `theory_time` int(11) NOT NULL,
  `exper_time` int(11) NOT NULL,
  `credit` double NOT NULL,
  `type` varchar(20) NOT NULL,
  `property` varchar(50) NOT NULL,
  `version` char(4) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`course_num`,`name`,`theory_time`,`exper_time`,`credit`,`type`,`property`,`version`,`teacher_id`) values 
(1,'20171170','程序设计基础',6000,6000,3.5,'专业基础课','必修','2017',1),
(2,'20171171','java程序设计',100,100,3.5,'专业基础课','必修','2017',2),
(5,'20171111','计算机网络',2520,2520,3,'专业基础课','必修','2018',1),
(6,'20171112','计算机网络2',1440,1440,3,'专业基础课','必修','2018',1),
(7,'20171234','软件工程',1440,2520,3,'专业基础课','必修','2018',1),
(8,'20201234','数据库设计',3000,2400,2.5,'专业核心课','必修','2017',14),
(10,'12341234','马克思原理',1320,660,1,'素质拓展','必修','2017',14),
(11,'22225555','hddf',720,600,1.2,'素质拓展','必修','2017',11),
(12,'455454','uuu',2400,720,3,'素质拓展','必修','2017',14);

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ratio` double NOT NULL,
  `version` char(4) NOT NULL,
  `examtype_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `examtype_id` (`examtype_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`examtype_id`) REFERENCES `examtype` (`id`),
  CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `exam` */

insert  into `exam`(`id`,`ratio`,`version`,`examtype_id`,`course_id`) values 
(1,0.3,'2017',1,1),
(2,0.7,'2017',2,1),
(3,0.1,'2017',1,5),
(4,0.9,'2017',4,5),
(5,0.7,'2017',1,6),
(6,0.3,'2017',4,6),
(7,0.1,'2017',1,2),
(8,0.2,'2017',2,2),
(9,0.3,'2017',3,2),
(10,0.4,'2017',4,2),
(11,0.1,'2017',1,7),
(12,0.9,'2017',4,7),
(13,0.4,'2017',1,10),
(14,0.6,'2017',4,10),
(15,0.3,'2017',1,11),
(16,0.7,'2017',4,11);

/*Table structure for table `examdetail` */

DROP TABLE IF EXISTS `examdetail`;

CREATE TABLE `examdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `weight` double DEFAULT NULL,
  `score` double DEFAULT NULL,
  `examtype_id` int(11) DEFAULT NULL,
  `target_id` int(11) DEFAULT NULL,
  `version` char(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `examtype_id` (`examtype_id`),
  KEY `target_id` (`target_id`),
  CONSTRAINT `examdetail_ibfk_1` FOREIGN KEY (`examtype_id`) REFERENCES `examtype` (`id`),
  CONSTRAINT `examdetail_ibfk_2` FOREIGN KEY (`target_id`) REFERENCES `target` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `examdetail` */

insert  into `examdetail`(`id`,`name`,`weight`,`score`,`examtype_id`,`target_id`,`version`) values 
(1,'作业1',0.3,30,1,1,'2017'),
(2,'作业2',0.7,70,1,1,'2017'),
(8,'实验1',NULL,40,2,1,'2017'),
(9,'实验2',NULL,60,2,1,'2017');

/*Table structure for table `examtype` */

DROP TABLE IF EXISTS `examtype`;

CREATE TABLE `examtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `examtype` */

insert  into `examtype`(`id`,`name`) values 
(1,'作业'),
(2,'实验'),
(3,'考题'),
(4,'论文');

/*Table structure for table `indication` */

DROP TABLE IF EXISTS `indication`;

CREATE TABLE `indication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indication_num` varchar(5) NOT NULL,
  `content` varchar(200) NOT NULL,
  `version` char(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `indication` */

insert  into `indication`(`id`,`indication_num`,`content`,`version`) values 
(1,'1.1','温嘉伟是猪','2017'),
(3,'1.1','谢文德是猪','2018'),
(5,'1.4','看见地方','2018'),
(8,'1.5','打发打发a','2017'),
(9,'1.6','hdhdhhd','2017'),
(10,'1.7','dfdf','2017');

/*Table structure for table `indication_course` */

DROP TABLE IF EXISTS `indication_course`;

CREATE TABLE `indication_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indication_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `weight` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `indication_id` (`indication_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `indication_course_ibfk_1` FOREIGN KEY (`indication_id`) REFERENCES `indication` (`id`),
  CONSTRAINT `indication_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `indication_course` */

insert  into `indication_course`(`id`,`indication_id`,`course_id`,`weight`) values 
(1,1,1,0.4),
(2,1,2,0.1),
(3,1,5,0.5),
(10,3,1,0.3),
(11,3,7,0.7),
(12,5,1,0.2),
(13,5,8,0.5),
(15,8,1,0.2),
(16,8,2,0.4),
(18,9,1,0.3),
(19,9,8,0.2);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`description`) values 
(1,'系统管理员','很有权力得管理员'),
(2,'大纲编撰者','很棒得管理员'),
(3,'课程负责人','系统最高管理者');

/*Table structure for table `sc` */

DROP TABLE IF EXISTS `sc`;

CREATE TABLE `sc` (
  `scid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `score` double DEFAULT '0',
  PRIMARY KEY (`scid`),
  KEY `sid` (`sid`),
  CONSTRAINT `sc_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `stu` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `sc` */

insert  into `sc`(`scid`,`sid`,`score`) values 
(3,2,34),
(4,2,98),
(5,2,98),
(6,3,56),
(7,4,78),
(8,4,98);

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `selcourse_id` int(11) DEFAULT NULL,
  `examdetail_id` int(11) DEFAULT NULL,
  `scores` double NOT NULL,
  `term` char(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `selcourse_id` (`selcourse_id`),
  KEY `examdetail_id` (`examdetail_id`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`selcourse_id`) REFERENCES `selcourse` (`id`),
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`examdetail_id`) REFERENCES `examdetail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`id`,`selcourse_id`,`examdetail_id`,`scores`,`term`) values 
(117,43,1,1,'1'),
(118,43,2,2,'1'),
(119,43,8,2,'1'),
(120,43,9,2,'1'),
(121,44,1,1,'1'),
(122,44,2,2,'1'),
(123,44,8,2,'1'),
(124,44,9,2,'1'),
(125,46,1,1,'1'),
(126,46,2,2,'1'),
(127,46,8,2,'1'),
(128,46,9,2,'1'),
(129,47,1,1,'1'),
(130,47,2,2,'1'),
(131,47,8,2,'1'),
(132,47,9,2,'1'),
(133,49,1,1,'1'),
(134,49,2,2,'1'),
(135,49,8,2,'1'),
(136,49,9,2,'1');

/*Table structure for table `selcourse` */

DROP TABLE IF EXISTS `selcourse`;

CREATE TABLE `selcourse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `leader_id` int(11) DEFAULT NULL,
  `term` char(2) NOT NULL,
  `version` char(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `leader_id` (`leader_id`),
  CONSTRAINT `selcourse_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `selcourse_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `selcourse_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `selcourse_ibfk_4` FOREIGN KEY (`leader_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `selcourse` */

insert  into `selcourse`(`id`,`student_id`,`course_id`,`teacher_id`,`leader_id`,`term`,`version`) values 
(43,1,1,2,1,'1','2017'),
(44,2,1,2,1,'1','2017'),
(45,NULL,1,2,1,'1','2017'),
(46,4,1,2,1,'1','2017'),
(47,5,1,2,1,'1','2017'),
(48,7,10,14,14,'1','2017'),
(49,7,1,2,1,'1','2017');

/*Table structure for table `stu` */

DROP TABLE IF EXISTS `stu`;

CREATE TABLE `stu` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `stu` */

insert  into `stu`(`sid`,`name`) values 
(2,'ls'),
(3,'zs'),
(4,'ww'),
(5,'wenge');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_num` char(12) NOT NULL,
  `name` varchar(10) NOT NULL,
  `major` varchar(20) NOT NULL,
  `class_name` varchar(10) NOT NULL,
  `grade` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`student_num`,`name`,`major`,`class_name`,`grade`) values 
(1,'201711701230','谢文德1','软件工程','软件卓越1171','2017'),
(2,'201711701231','谢文德2','软件工程','软件卓越1171','2017'),
(3,'201711701232','谢文德3','软件工程','软件卓越1171','2017'),
(4,'201711701233','谢文德4','软件工程','软件卓越1171','2017'),
(5,'201711701234','谢文德5','软件工程','软件卓越1171','2018'),
(7,'201711701332','温嘉伟','软件工程','软件1171','2015');

/*Table structure for table `target` */

DROP TABLE IF EXISTS `target`;

CREATE TABLE `target` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(200) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `version` char(4) NOT NULL,
  `targer_num` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `target_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `target` */

insert  into `target`(`id`,`content`,`course_id`,`version`,`targer_num`) values 
(1,'温嘉伟111111',1,'2017','1.1'),
(2,'啊啊啊啊啊啊啊啊啊啊',1,'2017','1.2'),
(4,'温嘉伟去去去去2',2,'2018','1.3'),
(5,'温嘉伟',7,'2017','1.4'),
(6,'温家伟',2,'2017','1.5'),
(8,'哦哦哦哦哦2',2,'2017','2.1'),
(9,'大幅度反对法',10,'2018','8.8');

/*Table structure for table `target_indication` */

DROP TABLE IF EXISTS `target_indication`;

CREATE TABLE `target_indication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target_id` int(11) DEFAULT NULL,
  `indication_id` int(11) DEFAULT NULL,
  `support` double DEFAULT NULL,
  `version` char(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `target_id` (`target_id`),
  KEY `indication_id` (`indication_id`),
  CONSTRAINT `target_indication_ibfk_1` FOREIGN KEY (`target_id`) REFERENCES `target` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `target_indication_ibfk_2` FOREIGN KEY (`indication_id`) REFERENCES `indication` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `target_indication` */

insert  into `target_indication`(`id`,`target_id`,`indication_id`,`support`,`version`) values 
(1,1,3,0.3,'2017'),
(2,1,3,0.7,'2017');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_num` char(6) NOT NULL,
  `name` varchar(10) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `status` char(2) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`teacher_num`,`name`,`PASSWORD`,`status`,`role_id`) values 
(1,'201710','leader','670b14728ad9902aecba32e22fa4f6bd','1',1),
(2,'201711','温嘉伟2','670b14728ad9902aecba32e22fa4f6bd','1',1),
(3,'201712','温嘉伟4','670b14728ad9902aecba32e22fa4f6bd','1',1),
(6,'201714','温嘉伟4','670b14728ad9902aecba32e22fa4f6bd','1',2),
(8,'201715','温嘉伟5','670b14728ad9902aecba32e22fa4f6bd','1',1),
(9,'201716','温嘉伟6','670b14728ad9902aecba32e22fa4f6bd','1',1),
(10,'111111','admin','21232f297a57a5a743894a0e4a801fc3','1',1),
(11,'222222','黄慧玲','670b14728ad9902aecba32e22fa4f6bd','1',2),
(12,'333333','黄慧玲2','670b14728ad9902aecba32e22fa4f6bd','1',3),
(14,'444445','谢文德235','670b14728ad9902aecba32e22fa4f6bd','1',3),
(15,'999999','谢文德555','670b14728ad9902aecba32e22fa4f6bd','1',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
