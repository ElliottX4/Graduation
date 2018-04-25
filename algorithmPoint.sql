/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.54 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `point` (
	`id` int ,
	`x` double ,
	`y` double ,
	`nextIds` char 
); 
insert into `point` (`id`, `x`, `y`, `nextIds`) values('1','0','0','2,3');
insert into `point` (`id`, `x`, `y`, `nextIds`) values('2','100','100','1,3,6');
insert into `point` (`id`, `x`, `y`, `nextIds`) values('3','100','0','1,2,6,5');
insert into `point` (`id`, `x`, `y`, `nextIds`) values('4','200','0','5,6');
insert into `point` (`id`, `x`, `y`, `nextIds`) values('5','100','-100','3,4');
insert into `point` (`id`, `x`, `y`, `nextIds`) values('6','200','50','2,3,4');
