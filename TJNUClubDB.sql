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
  `desc` varchar(64) DEFAULT NULL COMMENT '接口描述',
  `is_deleted` bigint(20) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，时间戳-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;


INSERT INTO `api_meta_info` (`id`,`gmt_create`,`gmt_modified`,`api_name`,`class_name`,`method_name`,`param_type`,`params_meta`,`need_login`,`is_record`,`desc`,`is_deleted`) 
VALUES
(1,'2019-12-04 21:17:21','2019-12-11 10:26:57','/user/update','com.tjnu.club.api.UserInfoService','updateUserInfo','["com.tjnu.club.vo.UserInfoVO"]','["userInfoVO"]',1,0,'用户-信息修改',0),
(2,'2019-12-04 16:53:52','2019-12-11 10:26:55','/user/delete','com.tjnu.club.api.UserInfoService','deleteUserInfo','["java.lang.String"]','["userId"]',1,0,'用户-逻辑删除',0),
(3,'2019-12-04 21:14:56','2019-12-11 10:26:51','/user/login','com.tjnu.club.api.RegisterLoginService','login','["java.lang.String","java.lang.String"]','["nickNameOrEmail","password"]',0,0,'用户-登录',0),
(4,'2019-12-04 21:14:56','2019-12-11 10:26:50','/user/register','com.tjnu.club.api.RegisterLoginService','register','["com.tjnu.club.vo.UserInfoVO"]','["userInfoVO"]',0,0,'用户-注册',0),
(5,'2019-12-04 21:14:56','2019-12-11 10:26:48','/user/logout','com.tjnu.club.api.RegisterLoginService','logout','["java.lang.String"]','["token"]',1,0,'用户-登出',0),
(6,'2019-12-04 21:14:56','2019-12-11 10:26:45','/email/sendCode','com.tjnu.club.api.RegisterLoginService','emailVerify','["java.lang.String"]','["email"]',0,0,'验证码-发送邮箱验证码',0),
(7,'2019-12-07 14:11:51','2019-12-11 10:26:39','/user/get','com.tjnu.club.api.UserInfoService','getUserInfoByUserId','["java.lang.String"]','["userId"]',1,0,'用户-信息获取',0),
(8,'2019-12-10 16:31:33','2019-12-11 10:26:34','/category/save','com.tjnu.club.api.CategoryInfoService','saveCategoryInfo','["com.tjnu.club.vo.CategoryInfoVO"]','["categoryInfoVO"]',1,0,'版块-新增',0),
(9,'2019-12-10 16:31:40','2019-12-11 10:26:32','/category/update','com.tjnu.club.api.CategoryInfoService','updateCategoryInfo','["com.tjnu.club.vo.CategoryInfoVO"]','["categoryInfoVO"]',1,0,'版块-修改',0),
(10,'2019-12-10 16:31:45','2019-12-11 10:26:31','/category/delete','com.tjnu.club.api.CategoryInfoService','deleteCategoryInfo','["java.lang.String"]','["categoryId"]',1,0,'版块-删除',0),
(11,'2019-12-10 16:31:51','2019-12-11 10:26:27','/category/get','com.tjnu.club.api.CategoryInfoService','getCategoryInfoById','["java.lang.String"]','["categoryId"]',1,0,'版块-根据ID获取',0),
(12,'2019-12-10 16:31:55','2019-12-11 10:26:22','/category/list','com.tjnu.club.api.CategoryInfoService','listCategoryInfo','','',1,0,'版块-父版块列表',0),
(13,'2019-12-10 16:32:04','2019-12-11 10:26:17','/category/listChild','com.tjnu.club.api.CategoryInfoService','listChildCategoryInfoById','["java.lang.String"]','["categoryId"]',1,0,'版块-指定父版块的子版块列表',0),
(14,'2019-12-11 10:14:00','2019-12-11 10:26:06','/category/collected','com.tjnu.club.api.CategoryInfoService','categoryCollected','["java.lang.String","java.lang.String"]','["userId","categoryId"]',1,0,'版块-用户收藏',0),
(15,'2019-12-11 10:14:04','2019-12-11 10:26:01','/category/collected/canceled','com.tjnu.club.api.CategoryInfoService','categoryCollectedCancel','["java.lang.String","java.lang.String"]','["userId","categoryId"]',1,0,'版块-用户取消收藏',0),
(16,'2019-12-11 10:14:51','2019-12-11 10:25:56','/category/collected/List','com.tjnu.club.api.CategoryInfoService','listCategoryInfoCollected','["java.lang.String"]','["userId"]',1,0,'版块-用户收藏版块列表',0) ;




DROP TABLE IF EXISTS `blog_info`;
CREATE TABLE `blog_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `blog_id` varchar(64) NOT NULL COMMENT '博客ID/帖子ID',
  `blog_name` varchar(128) NOT NULL COMMENT '博客名/帖子名',
  `blog_content` longblob NOT NULL COMMENT '帖子内容',
  `category_id` varchar(64) NOT NULL COMMENT '所属的版块ID',
  `top_blog` int(4) DEFAULT NULL COMMENT '是否是主贴。0-是，1-否-盖楼贴',
  `parent_blog_id` varchar(64) DEFAULT NULL COMMENT '父贴DI',
  `user_id` varchar(64) NOT NULL COMMENT '发帖人ID',
  `read_times` bigint(20) DEFAULT '0' COMMENT '帖子阅读量',
  `zan_times` bigint(20) DEFAULT '0' COMMENT '帖子点赞量',
  `is_deleted` bigint(20) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，时间戳-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;




DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `category_id` varchar(64) NOT NULL COMMENT '版块ID',
  `category_name` varchar(128) NOT NULL COMMENT '版块名称',
  `top_category` int(4) NOT NULL DEFAULT '0' COMMENT '是否是顶级版块-没有父版块。0-是，1-否',
  `parent_category_id` varchar(64) DEFAULT NULL COMMENT '父版块ID',
  `is_deleted` bigint(20) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，时间戳-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;




DROP TABLE IF EXISTS `user_blog_collected`;
CREATE TABLE `user_blog_collected` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` varchar(64) NOT NULL COMMENT '收录文章/帖子的用户ID',
  `blog_id` varchar(64) NOT NULL COMMENT '被收录的文章/帖子ID',
  `is_deleted` bigint(20) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，时间戳-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




DROP TABLE IF EXISTS `user_category_collected`;
CREATE TABLE `user_category_collected` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` varchar(64) NOT NULL COMMENT '收录版块的用户ID',
  `category_id` varchar(64) NOT NULL COMMENT '被收录的版块ID',
  `is_deleted` bigint(20) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，时间戳-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;




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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;


INSERT INTO `user_info` (`id`,`gmt_create`,`gmt_modified`,`user_id`,`user_img`,`user_name`,`nick_name`,`password`,`email`,`status`,`grade`,`major`,`college`,`university`,`last_login_time`,`login_times`,`description`,`is_deleted`) 
VALUES
(1,'2019-12-02 11:15:25','2019-12-11 14:34:22','1000',NULL,'崔大崔','daocaoren22222','admin','daocaoren22222@163.com',0,'大三','计算机科学与技术','计算机与信息工程学院','天津师范大学','2019-12-11 14:34:22',4,NULL,0),
(2,'2019-12-02 11:17:06','2019-12-11 15:42:06','1001',NULL,'韩小飞','daemon','admin','daocaoren@163.com',0,'大三','计算机科学与技术','计算机与信息工程学院','天津师范大学',NULL,0,NULL,0),
(3,'2019-12-05 11:22:58','2019-12-11 17:02:50','1002',NULL,'孙小莹','心多大世界多大','admin','1287024831@qq.com',0,'大三','计算机科学与技术','计算机与信息工程学院','天津师范大学','2019-12-05 11:23:13',1,'宇宙无敌美少女',0),
(4,'2019-12-07 13:49:13','2019-12-10 16:59:15','157569775326072',NULL,'罗小黑','小黑','customer','1287024833@qq.com',0,'大四','互联网工程','计算机与信息工程学院','天津师范大学','2019-12-10 16:59:15',10,'空间系',0) ;