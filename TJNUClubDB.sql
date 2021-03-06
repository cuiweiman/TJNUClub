CREATE DATABASE IF NOT EXISTS `tjnu_club`  DEFAULT CHARACTER SET utf8mb4;

USE `tjnu_club`;

DROP TABLE IF EXISTS `api_meta_info`;
CREATE TABLE `api_meta_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `api_name` varchar(256) NOT NULL COMMENT '接口访问路径',
  `class_name` varchar(256) NOT NULL COMMENT '服务接口的类名',
  `method_name` varchar(256) NOT NULL COMMENT '方法名',
  `param_type` varchar(2048) NOT NULL COMMENT '参数类型',
  `params_meta` varchar(2048) NOT NULL COMMENT '参数信息',
  `need_login` int(4) NOT NULL DEFAULT '1' COMMENT '是否验证token，0-不需要，1-需要',
  `is_record` int(4) NOT NULL DEFAULT '0' COMMENT '是否记录接口日志，0-不需要，1-需要',
  `is_deleted` bigint(20) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，时间戳-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
insert into `api_meta_info` (`id`, `gmt_create`, `gmt_modified`, `api_name`, `class_name`, `method_name`, `param_type`, `params_meta`, `need_login`, `is_record`, `is_deleted`) values('1','2019-12-04 21:17:21','2019-12-04 21:18:50','/user/update','com.tjnu.club.api.UserInfoService','updateUserInfo','["com.tjnu.club.vo.UserInfoVO"]','["userInfoVO"]','1','0','0');
insert into `api_meta_info` (`id`, `gmt_create`, `gmt_modified`, `api_name`, `class_name`, `method_name`, `param_type`, `params_meta`, `need_login`, `is_record`, `is_deleted`) values('2','2019-12-04 16:53:52','2019-12-04 21:18:47','/user/delete','com.tjnu.club.service.UserInfoService','deleteUserInfo','["java.lang.String"]','["userId"]','1','0','0');
insert into `api_meta_info` (`id`, `gmt_create`, `gmt_modified`, `api_name`, `class_name`, `method_name`, `param_type`, `params_meta`, `need_login`, `is_record`, `is_deleted`) values('3','2019-12-04 21:14:56','2019-12-04 21:18:57','/user/login','com.tjnu.club.api.RegisterLoginService','login','["java.lang.String","java.lang.String"]','["nickNameOrEmail","password"]','0','0','0');
insert into `api_meta_info` (`id`, `gmt_create`, `gmt_modified`, `api_name`, `class_name`, `method_name`, `param_type`, `params_meta`, `need_login`, `is_record`, `is_deleted`) values('4','2019-12-04 21:14:56','2019-12-04 21:19:07','/user/register','com.tjnu.club.api.RegisterLoginService','register','["com.tjnu.club.vo.UserInfoVO"]','["userInfoVO"]','0','0','0');
insert into `api_meta_info` (`id`, `gmt_create`, `gmt_modified`, `api_name`, `class_name`, `method_name`, `param_type`, `params_meta`, `need_login`, `is_record`, `is_deleted`) values('5','2019-12-04 21:14:56','2019-12-04 21:19:05','/user/logout','com.tjnu.club.api.RegisterLoginService','logout','["java.lang.String"]','["token"]','1','0','0');
insert into `api_meta_info` (`id`, `gmt_create`, `gmt_modified`, `api_name`, `class_name`, `method_name`, `param_type`, `params_meta`, `need_login`, `is_record`, `is_deleted`) values('6','2019-12-04 21:14:56','2019-12-04 21:19:11','/email/sendCode','com.tjnu.club.api.RegisterLoginService','emailVerify','["java.lang.String"]','["email"]','0','0','0');



DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户唯一标识',
  `user_img` varchar(128) DEFAULT NULL COMMENT '用户头像',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户真实姓名',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `status` int(4) DEFAULT NULL COMMENT '账户状态：0正常，1封号',
  `grade` varchar(64) DEFAULT NULL COMMENT '当前年级',
  `major` varchar(64) DEFAULT NULL COMMENT '所学专业/主修',
  `college` varchar(64) DEFAULT NULL COMMENT '所在学院',
  `university` varchar(64) DEFAULT NULL COMMENT '所在大学名称',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `login_times` bigint(20) DEFAULT '0' COMMENT '登陆次数',
  `description` varchar(128) DEFAULT NULL COMMENT '说明/个人介绍',
  `is_deleted` bigint(64) DEFAULT '0' COMMENT '逻辑删除。0-正常，时间戳-被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
insert  into `user_info`(`id`,`gmt_create`,`gmt_modified`,`user_id`,`user_img`,`user_name`,`nick_name`,`password`,`email`,`status`,`grade`,`major`,`college`,`university`,`last_login_time`,`login_times`,`description`,`is_deleted`) values (1,'2019-12-02 11:15:25','2019-12-04 16:55:36','1000',NULL,'崔为满','daocaoren22222','admin','daocaoren22222@163.com',0,'大三','计算机科学与技术','计算机与信息工程学院','天津师范大学','2019-12-04 16:55:36',2,NULL,0),(2,'2019-12-02 11:17:06','2019-12-04 17:20:38','1001',NULL,'韩霄飞','fei','admin','daocaoren@163.com',0,'大三','计算机科学与技术','计算机与信息工程学院','天津师范大学',NULL,0,NULL,0);




