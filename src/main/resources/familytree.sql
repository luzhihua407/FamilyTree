/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : familytree

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-06-25 09:18:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for basic_dict
-- ----------------------------
DROP TABLE IF EXISTS `basic_dict`;
CREATE TABLE `basic_dict` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `valid` int(1) DEFAULT NULL,
  `code` varchar(32) DEFAULT '',
  `dis` int(11) DEFAULT NULL,
  `name` varchar(32) DEFAULT '',
  `num_value` decimal(8,2) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT '',
  `value` varchar(32) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for basic_region
-- ----------------------------
DROP TABLE IF EXISTS `basic_region`;
CREATE TABLE `basic_region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creator` varchar(255) DEFAULT '',
  `create_time` datetime NOT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `edit_time` datetime NOT NULL,
  `orderno` double DEFAULT NULL,
  `valid` tinyint(1) NOT NULL,
  `version` int(11) NOT NULL,
  `area_code` varchar(20) DEFAULT '',
  `code` varchar(20) DEFAULT '',
  `full_name` varchar(100) DEFAULT '',
  `name` varchar(20) DEFAULT '',
  `post_code` varchar(20) DEFAULT '',
  `remark` varchar(250) DEFAULT '',
  `short_name` varchar(100) DEFAULT '',
  `status` int(11) DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `last_update_user` bigint(20) DEFAULT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12706 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bs_category
-- ----------------------------
DROP TABLE IF EXISTS `bs_category`;
CREATE TABLE `bs_category` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `valid` int(1) DEFAULT NULL,
  `name` varchar(32) DEFAULT '',
  `parent_id` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bs_category_content
-- ----------------------------
DROP TABLE IF EXISTS `bs_category_content`;
CREATE TABLE `bs_category_content` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `valid` int(1) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `content` text,
  `sub_title` varchar(64) DEFAULT '',
  `title` varchar(64) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bs_cemetery
-- ----------------------------
DROP TABLE IF EXISTS `bs_cemetery`;
CREATE TABLE `bs_cemetery` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `valid` int(1) DEFAULT NULL,
  `name` varchar(64) DEFAULT '',
  `address` varchar(128) DEFAULT NULL,
  `remark` varchar(255) DEFAULT '',
  `shape` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bs_children
-- ----------------------------
DROP TABLE IF EXISTS `bs_children`;
CREATE TABLE `bs_children` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `valid` int(1) DEFAULT NULL,
  `children_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bs_image_file
-- ----------------------------
DROP TABLE IF EXISTS `bs_image_file`;
CREATE TABLE `bs_image_file` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `valid` int(1) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL COMMENT '图片路径',
  `name` varchar(128) DEFAULT NULL COMMENT '图片名称',
  `brief` varchar(255) DEFAULT '' COMMENT '简述',
  `data` blob COMMENT 'base64数据',
  `width` int(5) unsigned DEFAULT NULL COMMENT '宽度',
  `heigth` int(5) unsigned DEFAULT NULL COMMENT '长度',
  `other_id` bigint(20) DEFAULT NULL COMMENT '其他主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for bs_partner
-- ----------------------------
DROP TABLE IF EXISTS `bs_partner`;
CREATE TABLE `bs_partner` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `valid` int(1) DEFAULT NULL,
  `husband_id` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT '',
  `wife_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bs_people
-- ----------------------------
DROP TABLE IF EXISTS `bs_people`;
CREATE TABLE `bs_people` (
  `id` bigint(20) NOT NULL,
  `nickname` varchar(32) DEFAULT '',
  `full_name` varchar(16) DEFAULT '',
  `pinyin` varchar(16) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `is_married` int(1) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `education` varchar(32) DEFAULT '',
  `has_child` int(1) DEFAULT NULL,
  `phone_number` varchar(32) DEFAULT '',
  `job` varchar(32) DEFAULT '',
  `avatar` varchar(64) DEFAULT '',
  `company` varchar(32) DEFAULT '',
  `death` date DEFAULT NULL,
  `generations` int(2) DEFAULT NULL,
  `height` varchar(16) DEFAULT '',
  `weight` varchar(16) DEFAULT '',
  `brief` varchar(64) DEFAULT '',
  `remark` varchar(255) DEFAULT '',
  `valid` int(1) DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `partner_id` bigint(20) DEFAULT NULL,
  `people_branch` bigint(20) unsigned DEFAULT NULL COMMENT '几房',
  `heir` varchar(16) DEFAULT NULL COMMENT '入嗣',
  `prod_team` bigint(20) unsigned DEFAULT NULL COMMENT '生产队',
  `village_code` varchar(16) NOT NULL COMMENT '乡村编码',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bs_village
-- ----------------------------
DROP TABLE IF EXISTS `bs_village`;
CREATE TABLE `bs_village` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `valid` int(1) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL COMMENT '村编号',
  `name` varchar(128) DEFAULT NULL COMMENT '村名',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `longitude` double unsigned DEFAULT NULL COMMENT '经度',
  `latitude` double unsigned DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(256) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(64) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(64) NOT NULL,
  `user_name` varchar(64) DEFAULT NULL,
  `client_id` varchar(64) DEFAULT NULL,
  `authentication` mediumblob,
  `refresh_token` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(64) DEFAULT NULL,
  `clientId` varchar(64) DEFAULT NULL,
  `scope` varchar(64) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(64) NOT NULL,
  `resource_ids` varchar(64) DEFAULT NULL,
  `client_secret` varchar(64) DEFAULT NULL,
  `scope` varchar(64) DEFAULT NULL,
  `authorized_grant_types` varchar(64) DEFAULT NULL,
  `web_server_redirect_uri` varchar(64) DEFAULT NULL,
  `authorities` varchar(64) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(64) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(64) NOT NULL,
  `user_name` varchar(64) DEFAULT NULL,
  `client_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(64) DEFAULT NULL,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(64) DEFAULT NULL,
  `token` mediumblob,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT NULL,
  `valid` int(1) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `id_path` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_menu_right
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_right`;
CREATE TABLE `sys_menu_right` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT NULL,
  `valid` int(1) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT NULL,
  `valid` int(1) DEFAULT NULL,
  `admin` int(1) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT NULL,
  `valid` int(1) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `own` int(1) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_menu_right
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_right`;
CREATE TABLE `sys_role_menu_right` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT NULL,
  `valid` int(1) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `menu_right_id` bigint(20) DEFAULT NULL,
  `role_menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT NULL,
  `valid` int(1) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `own` int(1) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_menu_right
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu_right`;
CREATE TABLE `sys_user_menu_right` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT NULL,
  `valid` int(1) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `menu_right_id` bigint(20) DEFAULT NULL,
  `user_menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT NULL,
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT NULL,
  `valid` int(1) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for spring_session
-- ----------------------------
DROP TABLE IF EXISTS `spring_session`;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`) USING BTREE,
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`) USING BTREE,
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for spring_session_attributes
-- ----------------------------
DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `spring_session_attributes_ibfk_1` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(16) DEFAULT '',
  `edit_time` datetime DEFAULT NULL,
  `editor` varchar(16) DEFAULT '',
  `valid` tinyint(1) DEFAULT NULL,
  `address` varchar(32) DEFAULT '',
  `age` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `district` int(11) DEFAULT NULL,
  `email` varchar(32) DEFAULT '',
  `enabled` int(1) DEFAULT NULL,
  `first_login` int(1) DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `mobile` varchar(16) DEFAULT '',
  `password` varchar(64) DEFAULT '',
  `province` int(11) DEFAULT NULL,
  `real_name` varchar(32) DEFAULT '',
  `register_time` datetime NOT NULL,
  `type` int(11) DEFAULT NULL,
  `username` varchar(32) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for verification_token
-- ----------------------------
DROP TABLE IF EXISTS `verification_token`;
CREATE TABLE `verification_token` (
  `id` bigint(20) DEFAULT NULL,
  `token` varchar(64) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
