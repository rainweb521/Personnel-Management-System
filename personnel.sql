/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost
 Source Database       : personnel

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : utf-8

 Date: 04/26/2019 16:22:02 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dept_inf`
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dept_inf`
-- ----------------------------
BEGIN;
INSERT INTO `dept_inf` VALUES ('1', '技术部', '技术部'), ('2', '运营部', '运营部'), ('3', '财务部', '财务部'), ('5', '总公办', '总公办'), ('6', '市场部', '市场部'), ('7', '教学部', '教学部'), ('28', '123', '123'), ('29', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `document_inf`
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `filename` varchar(300) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `document_inf`
-- ----------------------------
BEGIN;
INSERT INTO `document_inf` VALUES ('1', '2,2', '01.jpg', '11', null, null), ('2', '2,2', '01.jpg', null, null, null), ('3', '4', '2.jpg', '4', null, null), ('4', '1', '1.jpg', '1', null, null), ('5', '2', '1.jpg', '2', null, null), ('6', '2', '1.jpg', '2', null, null), ('7', '3', '5.jpg', '3', null, null), ('8', '2', '5.jpg', '2', null, null), ('9', '额外热污染', 'tqe.sql', '热舞任务二r', null, '1');
COMMIT;

-- ----------------------------
--  Table structure for `employee_inf`
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `card_id` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `post_code` varchar(50) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `qq_num` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `party` varchar(10) DEFAULT NULL,
  `birthday` char(20) DEFAULT NULL,
  `race` varchar(100) DEFAULT NULL,
  `education` varchar(10) DEFAULT NULL,
  `speciality` varchar(20) DEFAULT NULL,
  `hobby` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `employee_inf`
-- ----------------------------
BEGIN;
INSERT INTO `employee_inf` VALUES ('1', '1', '8', '爱丽丝', '4328011988', '广州天河', '510000', '020-77777777', '13902001111', '36750066', '251425887@qq.com', '0', '党员', '1980-01-01 00:00:00', '满', '本科', '美声', '唱歌', '四大天王', '2016-03-14 11:35:18', null), ('2', '3', '1', '杰克', '22623', '43234', '42427424', '42242', '4247242', '42424', '251425887@qq.com', '2', null, null, null, '1', null, null, null, '2016-03-14 11:35:18', null), ('3', '1', '2', 'bb', '432801197711251038', '广州', '510000', '020-99999999', '13907351532', '36750064', '36750064@qq.com', '1', '党员', '1977-11-25 00:00:00', '汉', '本科', '计算机', '爬山', '无', '2016-07-14 09:54:52', null), ('4', null, null, '1', '2', '7', null, null, '6', null, '5', '3', null, null, null, '4', null, null, null, null, null), ('5', '1', '1', '1', '1', '1', null, null, '1', null, '1', '1', null, null, null, '1', null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `job_inf`
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `job_inf`
-- ----------------------------
BEGIN;
INSERT INTO `job_inf` VALUES ('1', '职员1', '职员'), ('2', 'Java开发工程师', 'Java开发工程师'), ('3', 'Java中级开发工程师', 'Java中级开发工程师'), ('4', 'Java高级开发工程师', 'Java高级开发工程师'), ('6', '架构师', '架构师'), ('7', '主管', '主管'), ('8', '经理', '经理'), ('9', '总经理', '总经理'), ('10', '2', '2'), ('11', '1', '2,3,4,5,6,7');
COMMIT;

-- ----------------------------
--  Table structure for `notice_inf`
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `create_date` char(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `notice_inf`
-- ----------------------------
BEGIN;
INSERT INTO `notice_inf` VALUES ('1', '1112', '132323', null, null), ('2', '444', '4444', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `user_inf`
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createdate` char(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_inf`
-- ----------------------------
BEGIN;
INSERT INTO `user_inf` VALUES ('1', 'admin', '123456', '2', '2016-03-12 09:34:28', '超级管理员1'), ('2', 'root', '123', null, null, 'root'), ('3', '哈哈', '123', null, null, '哈哈'), ('4', 'rain', '123456', null, null, 'rain');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
