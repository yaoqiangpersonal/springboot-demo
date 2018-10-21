
/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`pid`,`name`,`permission_url`,`description`,`method`) values (1,1,'ROLE_HOME','/','home','GET');
insert  into `sys_permission`(`id`,`pid`,`name`,`permission_url`,`description`,`method`) values (2,2,'ROLE_ABLE','/admin','ABle','POST');
insert  into `sys_permission`(`id`,`pid`,`name`,`permission_url`,`description`,`method`) values (3,3,'ROLE_USER_GET','/user/**','user','GET');
insert  into `sys_permission`(`id`,`pid`,`name`,`permission_url`,`description`,`method`) values (4,4,'ROLE_USER_POST','/user/**','user','POST');
insert  into `sys_permission`(`id`,`pid`,`name`,`permission_url`,`description`,`method`) values (5,5,'ROLE_USER_PUT','/user/**','user','PUT');
insert  into `sys_permission`(`id`,`pid`,`name`,`permission_url`,`description`,`method`) values (6,6,'ROLE_USER_ALL','/user/**','user','ALL');
insert  into `sys_permission`(`id`,`pid`,`name`,`permission_url`,`description`,`method`) values (7,7,'ROLE_CAMEL_GET','/camel/**','camel','POST');

/*Data for the table `sys_permission_role` */

insert  into `sys_permission_role`(`id`,`role_id`,`permission_id`) values (1,1,1);
insert  into `sys_permission_role`(`id`,`role_id`,`permission_id`) values (2,1,2);
insert  into `sys_permission_role`(`id`,`role_id`,`permission_id`) values (3,1,6);
insert  into `sys_permission_role`(`id`,`role_id`,`permission_id`) values (4,2,1);
insert  into `sys_permission_role`(`id`,`role_id`,`permission_id`) values (5,2,3);
insert  into `sys_permission_role`(`id`,`role_id`,`permission_id`) values (6,3,7);


/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`) values (1,'管理员');
insert  into `sys_role`(`id`,`name`) values (2,'普通用户');
insert  into `sys_role`(`id`,`name`) values (3,'采购');

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`cnname`,`re_password`,`history_password`,`email`,`telephone`,`mobile_phone`,`wechat_id`,`skill`,`department_id`,`login_count`) values (1,'admin','a1d83431229cbda302fb33f24276c446','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `sys_user`(`id`,`username`,`password`,`cnname`,`re_password`,`history_password`,`email`,`telephone`,`mobile_phone`,`wechat_id`,`skill`,`department_id`,`login_count`) values (2,'user','user',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `sys_user`(`id`,`username`,`password`,`cnname`,`re_password`,`history_password`,`email`,`telephone`,`mobile_phone`,`wechat_id`,`skill`,`department_id`,`login_count`) values (3,'caigou','4a66f5fdabd7d64ef3e118f65f231757',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (1,1,1);
insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (2,2,2);
insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (3,3,3);
