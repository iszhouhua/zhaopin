/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : 127.0.0.1:3306
Source Database       : zhaopin

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-08-06 19:59:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(10) NOT NULL,
  `province` varchar(50) DEFAULT NULL COMMENT '省名称',
  `city` varchar(50) NOT NULL COMMENT '城市名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` char(32) NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '公司名',
  `nature` varchar(50) DEFAULT NULL COMMENT '公司性质',
  `scale` varchar(100) DEFAULT NULL COMMENT '公司规模',
  `url` varchar(500) DEFAULT NULL COMMENT '公司网址',
  `industry` varchar(100) DEFAULT NULL COMMENT '公司行业',
  `address` varchar(500) DEFAULT NULL COMMENT '公司地址',
  `description` varchar(10000) DEFAULT NULL COMMENT '公司介绍',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` char(32) NOT NULL COMMENT '主键',
  `city` varchar(20) NOT NULL COMMENT '工作城市',
  `name` varchar(100) NOT NULL COMMENT '职位名称',
  `url` varchar(500) NOT NULL COMMENT '职位链接',
  `min_price` decimal(10,2) unsigned DEFAULT NULL COMMENT '最低工资',
  `max_price` decimal(10,2) unsigned DEFAULT NULL COMMENT '最高工资',
  `welfare` varchar(500) DEFAULT NULL COMMENT '福利待遇',
  `education` varchar(50) DEFAULT NULL COMMENT '最低学历',
  `experience` varchar(50) DEFAULT NULL COMMENT '工作经验',
  `nature` varchar(10) DEFAULT NULL COMMENT '工作性质',
  `demand` int(5) unsigned DEFAULT '0' COMMENT '招聘人数',
  `type` varchar(200) DEFAULT NULL COMMENT '工作类型',
  `address` varchar(1000) DEFAULT NULL COMMENT '工作地址',
  `description` varchar(10000) DEFAULT NULL COMMENT '工作描述',
  `company_id` char(32) DEFAULT NULL COMMENT '公司ID',
  `release_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
