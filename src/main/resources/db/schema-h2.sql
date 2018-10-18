
/*Table structure for table `sys_permission` */
CREATE TABLE `sys_permission` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`pid` int(11) DEFAULT NULL,
	`name` varchar(50) DEFAULT NULL,
	`permission_url` varchar(255) DEFAULT NULL,
	`description` varchar(255) DEFAULT NULL,
	`method` varchar(200) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_permission_role` */

CREATE TABLE `sys_permission_role` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`role_id` int(11) DEFAULT NULL,
	`permission_id` int(11) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;




/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(50) DEFAULT NULL,
	`password` varchar(50) DEFAULT NULL,
	`cnname` varchar(50) DEFAULT NULL,
	`re_password` varchar(200) DEFAULT NULL,
	`history_password` varchar(200) DEFAULT NULL,
	`email` varchar(50) DEFAULT NULL,
	`telephone` varchar(20) DEFAULT NULL,
	`mobile_phone` varchar(20) DEFAULT NULL,
	`wechat_id` varchar(30) DEFAULT NULL,
	`skill` varchar(255) DEFAULT NULL,
	`department_id` int(11) DEFAULT NULL,
	`login_count` bigint(11) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`user_id` int(11) DEFAULT NULL,
	`role_id` int(11) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
