/*
Navicat MySQL Data Transfer

Source Server         : MyJdbc
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : personal

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-06-04 13:43:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for business_inf
-- ----------------------------
DROP TABLE IF EXISTS `business_inf`;
CREATE TABLE `business_inf` (
  `id` int(11) NOT NULL,
  `mailmarketing` int(11) DEFAULT NULL,
  `allianceadvertising` int(11) DEFAULT NULL,
  `videoadvertising` int(11) DEFAULT NULL,
  `directaccess` int(11) DEFAULT NULL,
  `searchengine` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business_inf
-- ----------------------------
INSERT INTO `business_inf` VALUES ('1', '120', '220', '150', '320', '820');
INSERT INTO `business_inf` VALUES ('2', '132', '182', '232', '332', '932');
INSERT INTO `business_inf` VALUES ('3', '101', '191', '201', '301', '901');
INSERT INTO `business_inf` VALUES ('4', '134', '234', '154', '334', '934');
INSERT INTO `business_inf` VALUES ('5', '90', '290', '190', '390', '1290');
INSERT INTO `business_inf` VALUES ('6', '230', '330', '330', '330', '1330');
INSERT INTO `business_inf` VALUES ('7', '210', '310', '410', '320', '1340');

-- ----------------------------
-- Table structure for checkwork_inf
-- ----------------------------
DROP TABLE IF EXISTS `checkwork_inf`;
CREATE TABLE `checkwork_inf` (
  `ID` int(11) NOT NULL,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `EMP_ID` int(11) NOT NULL,
  `WORKINGDAYS` int(18) NOT NULL,
  `DAYSLEAVE` int(50) NOT NULL,
  `CREATEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DAYSOUT` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`,`EMP_ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  KEY `ID` (`ID`),
  KEY `checkwork_inf_ibfk_3` (`EMP_ID`),
  CONSTRAINT `che_1` FOREIGN KEY (`DEPT_ID`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `che_2` FOREIGN KEY (`JOB_ID`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `che_3` FOREIGN KEY (`EMP_ID`) REFERENCES `employee_inf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkwork_inf
-- ----------------------------
INSERT INTO `checkwork_inf` VALUES ('5', '3', '9', '5', '25', '2', '2019-05-08 10:38:11', '2');

-- ----------------------------
-- Table structure for code_inf
-- ----------------------------
DROP TABLE IF EXISTS `code_inf`;
CREATE TABLE `code_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_inf
-- ----------------------------
INSERT INTO `code_inf` VALUES ('1', 'KPHMYKTKT', '2019-03-29 14:25:14');
INSERT INTO `code_inf` VALUES ('2', 'PBB7KUYBT', '2019-03-29 16:47:29');
INSERT INTO `code_inf` VALUES ('3', '9I2AN4D3W', '2019-03-29 16:47:37');
INSERT INTO `code_inf` VALUES ('4', 'WLMGU4RZH', '2019-03-29 17:01:09');
INSERT INTO `code_inf` VALUES ('5', 'YQ2QEJ8WL', '2019-03-29 17:01:17');
INSERT INTO `code_inf` VALUES ('6', 'IMSQKMG6X', '2019-04-01 15:00:04');
INSERT INTO `code_inf` VALUES ('7', 'A7537ZTEJ', '2019-04-01 15:00:17');
INSERT INTO `code_inf` VALUES ('8', 'ZQ8VIGSF9', '2019-04-01 15:00:21');
INSERT INTO `code_inf` VALUES ('9', 'F8R7ANA6G', '2019-04-03 08:41:24');
INSERT INTO `code_inf` VALUES ('10', 'QNTGMEIXV', '2019-04-04 19:34:59');
INSERT INTO `code_inf` VALUES ('11', 'BYKZN687L', '2019-04-10 17:56:31');
INSERT INTO `code_inf` VALUES ('12', 'VDEY3V646', '2019-04-15 15:26:05');
INSERT INTO `code_inf` VALUES ('13', 'PK6K2AQNA', '2019-05-29 13:48:21');
INSERT INTO `code_inf` VALUES ('14', 'FIU8ZY2M7', '2019-05-29 13:48:29');
INSERT INTO `code_inf` VALUES ('15', 'V7P6G6WSB', '2019-05-29 13:48:31');

-- ----------------------------
-- Table structure for completion_inf
-- ----------------------------
DROP TABLE IF EXISTS `completion_inf`;
CREATE TABLE `completion_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of completion_inf
-- ----------------------------
INSERT INTO `completion_inf` VALUES ('1', '优秀');
INSERT INTO `completion_inf` VALUES ('2', '良好');
INSERT INTO `completion_inf` VALUES ('3', '不合格');

-- ----------------------------
-- Table structure for confidentialitycontract_inf
-- ----------------------------
DROP TABLE IF EXISTS `confidentialitycontract_inf`;
CREATE TABLE `confidentialitycontract_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of confidentialitycontract_inf
-- ----------------------------
INSERT INTO `confidentialitycontract_inf` VALUES ('1', '已签');
INSERT INTO `confidentialitycontract_inf` VALUES ('2', '未签');

-- ----------------------------
-- Table structure for contract_inf
-- ----------------------------
DROP TABLE IF EXISTS `contract_inf`;
CREATE TABLE `contract_inf` (
  `ID` int(11) NOT NULL,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `STATUS_ID` int(11) DEFAULT '0',
  `EMP_ID` int(11) NOT NULL,
  `TRAIN_CONTRACT` int(18) NOT NULL,
  `LABOR_CONTRACT` int(50) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CONFIDENTIALITY_CONTRACT` int(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  KEY `ID` (`ID`),
  KEY `checkwork_inf_ibfk_3` (`EMP_ID`),
  CONSTRAINT `con_1` FOREIGN KEY (`DEPT_ID`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `con_2` FOREIGN KEY (`JOB_ID`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `con_3` FOREIGN KEY (`EMP_ID`) REFERENCES `employee_inf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract_inf
-- ----------------------------
INSERT INTO `contract_inf` VALUES ('5', '3', '9', '0', '5', '2', '1', '2019-05-08 10:40:04', '2');

-- ----------------------------
-- Table structure for dept_inf
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES ('1', '技术部', '技术研发');
INSERT INTO `dept_inf` VALUES ('2', '运营部', '运营部');
INSERT INTO `dept_inf` VALUES ('3', '财务部', '财务分析与统计计划');
INSERT INTO `dept_inf` VALUES ('5', '总公办', '总公办');
INSERT INTO `dept_inf` VALUES ('6', '市场部', '市场部');
INSERT INTO `dept_inf` VALUES ('7', '教学部', '教学部');

-- ----------------------------
-- Table structure for document_inf
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `filename` varchar(300) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document_inf
-- ----------------------------
INSERT INTO `document_inf` VALUES ('1', '实习协议', '实习协议.doc', '实习协议文档信息', '2019-05-19 10:37:39', '1');

-- ----------------------------
-- Table structure for education_inf
-- ----------------------------
DROP TABLE IF EXISTS `education_inf`;
CREATE TABLE `education_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of education_inf
-- ----------------------------
INSERT INTO `education_inf` VALUES ('1', '大专');
INSERT INTO `education_inf` VALUES ('2', '本科');
INSERT INTO `education_inf` VALUES ('3', '硕士');
INSERT INTO `education_inf` VALUES ('4', '博士');

-- ----------------------------
-- Table structure for employee_inf
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf` (
  `id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `card_id` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `sex_id` int(11) DEFAULT NULL,
  `education_id` int(10) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_EMP_DEPT` (`dept_id`),
  KEY `FK_EMP_JOB` (`job_id`),
  CONSTRAINT `FK_EMP_DEPT` FOREIGN KEY (`dept_id`) REFERENCES `dept_inf` (`id`),
  CONSTRAINT `FK_EMP_JOB` FOREIGN KEY (`job_id`) REFERENCES `job_inf` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES ('1', '1', '7', '爱丽丝', '432801197711251052', '浙江省杭州市', '13902001111', '2', '3', '2019-05-09 19:45:59', '1');
INSERT INTO `employee_inf` VALUES ('3', '1', '3', '袭人', '620523199410103810', '江苏省南京市', '13902001111', '2', '3', '2019-05-11 13:19:32', '3');
INSERT INTO `employee_inf` VALUES ('5', '3', '9', '翎幺', '620523199810103810', '甘肃省兰州市', '13902001111', '1', '3', '2019-04-15 15:44:57', '5');

-- ----------------------------
-- Table structure for jobtype_inf
-- ----------------------------
DROP TABLE IF EXISTS `jobtype_inf`;
CREATE TABLE `jobtype_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jobtype_inf
-- ----------------------------
INSERT INTO `jobtype_inf` VALUES ('1', '人才项目');
INSERT INTO `jobtype_inf` VALUES ('2', '技术方向');
INSERT INTO `jobtype_inf` VALUES ('3', '设计方向');
INSERT INTO `jobtype_inf` VALUES ('4', '产品方向');
INSERT INTO `jobtype_inf` VALUES ('5', '客户服务');
INSERT INTO `jobtype_inf` VALUES ('6', '金融方向');
INSERT INTO `jobtype_inf` VALUES ('7', '市场与职能方向');

-- ----------------------------
-- Table structure for job_inf
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_inf
-- ----------------------------
INSERT INTO `job_inf` VALUES ('1', '职员', '职员');
INSERT INTO `job_inf` VALUES ('2', 'Java开发工程师', 'Java开发工程师');
INSERT INTO `job_inf` VALUES ('3', 'Java中级开发工程师', 'Java中级开发工作');
INSERT INTO `job_inf` VALUES ('6', '架构师', '架构师');
INSERT INTO `job_inf` VALUES ('7', '主管', '主管');
INSERT INTO `job_inf` VALUES ('9', '总经理', '负责项目总体工作');

-- ----------------------------
-- Table structure for laborcontract_inf
-- ----------------------------
DROP TABLE IF EXISTS `laborcontract_inf`;
CREATE TABLE `laborcontract_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of laborcontract_inf
-- ----------------------------
INSERT INTO `laborcontract_inf` VALUES ('1', '已签');
INSERT INTO `laborcontract_inf` VALUES ('2', '未签');

-- ----------------------------
-- Table structure for leavestatus_inf
-- ----------------------------
DROP TABLE IF EXISTS `leavestatus_inf`;
CREATE TABLE `leavestatus_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leavestatus_inf
-- ----------------------------
INSERT INTO `leavestatus_inf` VALUES ('1', '批准');
INSERT INTO `leavestatus_inf` VALUES ('2', '未批准');

-- ----------------------------
-- Table structure for leavetype_inf
-- ----------------------------
DROP TABLE IF EXISTS `leavetype_inf`;
CREATE TABLE `leavetype_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leavetype_inf
-- ----------------------------
INSERT INTO `leavetype_inf` VALUES ('1', '病假');
INSERT INTO `leavetype_inf` VALUES ('2', '事假');

-- ----------------------------
-- Table structure for leave_inf
-- ----------------------------
DROP TABLE IF EXISTS `leave_inf`;
CREATE TABLE `leave_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `startdata` varchar(255) DEFAULT NULL,
  `enddata` varchar(255) DEFAULT NULL,
  `leavedays` varchar(255) DEFAULT NULL,
  `leavetype` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `leavestatus` int(11) DEFAULT '2',
  `leavetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `lea_1` (`emp_id`),
  KEY `lea_2` (`dept_id`),
  KEY `lea_3` (`job_id`),
  CONSTRAINT `lea_1` FOREIGN KEY (`emp_id`) REFERENCES `employee_inf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lea_2` FOREIGN KEY (`dept_id`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lea_3` FOREIGN KEY (`job_id`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave_inf
-- ----------------------------
INSERT INTO `leave_inf` VALUES ('4', '5', '3', '9', '2019-05-07', '2019-05-09', '2', '1', '头疼感冒', '1', '2019-05-16 16:11:36');
INSERT INTO `leave_inf` VALUES ('12', '1', '1', '7', '2019-05-07', '2019-05-16', '9', '1', '感冒', '1', '2019-05-24 15:38:10');

-- ----------------------------
-- Table structure for notice_inf
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_notice` (`user_id`),
  CONSTRAINT `user_notice` FOREIGN KEY (`user_id`) REFERENCES `user_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice_inf
-- ----------------------------
INSERT INTO `notice_inf` VALUES ('1', '你好', '明天就是你自己的生日了', '2019-01-29 15:06:36', '1');
INSERT INTO `notice_inf` VALUES ('2', '下午开会', '技术部下午3.00开会,请各部门准时参加会议', '2019-01-29 15:13:24', '2');
INSERT INTO `notice_inf` VALUES ('3', '五一放假', '五一放假安排时间调整及时通知', '2019-02-22 19:06:23', '1');
INSERT INTO `notice_inf` VALUES ('4', '发布新消息', '在普通用户页面实时提示！！！', '2019-03-03 17:21:30', '1');
INSERT INTO `notice_inf` VALUES ('5', '年终考核结果公', '请各部门主管3.25号之前汇总上报部门考核情况！！！', '2019-03-03 20:10:27', '1');
INSERT INTO `notice_inf` VALUES ('6', '公告测试', '公告测试内容一，发送邮件', '2019-05-20 10:19:39', '1');
INSERT INTO `notice_inf` VALUES ('7', '开会', '开会开会开会开会开会', '2019-05-29 13:54:38', '1');

-- ----------------------------
-- Table structure for recruitmentstatus_inf
-- ----------------------------
DROP TABLE IF EXISTS `recruitmentstatus_inf`;
CREATE TABLE `recruitmentstatus_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruitmentstatus_inf
-- ----------------------------
INSERT INTO `recruitmentstatus_inf` VALUES ('1', '正在招聘');
INSERT INTO `recruitmentstatus_inf` VALUES ('2', '已结束');

-- ----------------------------
-- Table structure for recruitment_inf
-- ----------------------------
DROP TABLE IF EXISTS `recruitment_inf`;
CREATE TABLE `recruitment_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobtype` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `peoplenum` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `enddate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruitment_inf
-- ----------------------------
INSERT INTO `recruitment_inf` VALUES ('1', '1', '管培生', '1', '1', '2019-06-30');
INSERT INTO `recruitment_inf` VALUES ('2', '2', 'java初级工程师', '2', '2', '2019-5-30');
INSERT INTO `recruitment_inf` VALUES ('3', '3', 'UI设计工程师', '5', '1', '2019-6-30');
INSERT INTO `recruitment_inf` VALUES ('5', '5', '客服专员', '2', '1', '2019-6-30');
INSERT INTO `recruitment_inf` VALUES ('6', '6', '会计师', '1', '1', '2019-6-30');
INSERT INTO `recruitment_inf` VALUES ('7', '7', '行政专员', '2', '2', '2019-5-30');
INSERT INTO `recruitment_inf` VALUES ('8', '2', '算法工程师', '2', '1', '2019-6-30');
INSERT INTO `recruitment_inf` VALUES ('9', '2', 'C++开发工程师', '1', '1', '2019-06-30');
INSERT INTO `recruitment_inf` VALUES ('10', '2', 'Java专家', '1', '1', '2019-06-30');
INSERT INTO `recruitment_inf` VALUES ('11', '4', '产品经理', '1', '1', '2019-06-30');
INSERT INTO `recruitment_inf` VALUES ('12', '6', '高级会计师', '1', '1', '2019-06-30');
INSERT INTO `recruitment_inf` VALUES ('13', '5', '高级客服专员', '2', '1', '2019-06-30');

-- ----------------------------
-- Table structure for resume_inf
-- ----------------------------
DROP TABLE IF EXISTS `resume_inf`;
CREATE TABLE `resume_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobtype` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex_id` int(11) DEFAULT NULL,
  `education_id` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT '2',
  `filename` varchar(255) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resume_inf
-- ----------------------------
INSERT INTO `resume_inf` VALUES ('1', '设计方向', 'UI设计工程师', '翎幺', '13902001111@163.com', '1', '2', '13902001111', '2', '翎幺-苏州简历(2019年5月版) .pdf', '2019-06-03 20:27:12');
INSERT INTO `resume_inf` VALUES ('2', '技术方向', 'C++开发工程师', '翎幺', '13902001111@163.com', '1', '1', '13902001111', '1', '翎幺-苏州简历(2019年5月版) .pdf', '2019-06-04 08:55:09');
INSERT INTO `resume_inf` VALUES ('3', '市场与职能方向', '行政专员', '安安', '13902001111@163.com', '1', '2', '13902001111', '1', '翎幺-西安简历(2019年5月版) .pdf', '2019-06-04 09:41:18');
INSERT INTO `resume_inf` VALUES ('4', '产品方向', '产品研发工程师', '都受到', '13902001111@163.com', '2', '4', '13902001111', '2', '翎幺-简历(2019年6月版) .pdf', '2019-06-04 10:30:18');
INSERT INTO `resume_inf` VALUES ('5', '客户服务', '客服专员', '苟富贵', '13902001111@qq.com', '1', '3', '13902001111', '1', '翎幺-简历(2019年6月版) .pdf', '2019-06-04 10:30:54');

-- ----------------------------
-- Table structure for salary_inf
-- ----------------------------
DROP TABLE IF EXISTS `salary_inf`;
CREATE TABLE `salary_inf` (
  `ID` int(11) NOT NULL,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `EMP_ID` int(11) NOT NULL,
  `SALARY_STATION` int(18) NOT NULL,
  `SALARY_LEVEL` int(50) NOT NULL,
  `SENIORITY_PAY` int(50) DEFAULT NULL,
  `PERFORMANCE` int(16) DEFAULT NULL,
  `INDIVIDUAL_INCOME` float(11,0) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  KEY `ID` (`ID`),
  KEY `salary_inf_ibfk_3` (`EMP_ID`),
  CONSTRAINT `sal_1` FOREIGN KEY (`DEPT_ID`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sal_2` FOREIGN KEY (`JOB_ID`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sal_3` FOREIGN KEY (`EMP_ID`) REFERENCES `employee_inf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary_inf
-- ----------------------------
INSERT INTO `salary_inf` VALUES ('1', '1', '7', '1', '6050', '3', '620', '150', '690', '2019-04-19 09:12:08');
INSERT INTO `salary_inf` VALUES ('3', '1', '3', '3', '5000', '3', '450', '360', '500', '2019-04-19 09:12:53');
INSERT INTO `salary_inf` VALUES ('5', '3', '9', '5', '5500', '2', '450', '360', '540', '2019-04-19 09:13:21');

-- ----------------------------
-- Table structure for sex_inf
-- ----------------------------
DROP TABLE IF EXISTS `sex_inf`;
CREATE TABLE `sex_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sex_inf
-- ----------------------------
INSERT INTO `sex_inf` VALUES ('1', '男');
INSERT INTO `sex_inf` VALUES ('2', '女');

-- ----------------------------
-- Table structure for staticid_inf
-- ----------------------------
DROP TABLE IF EXISTS `staticid_inf`;
CREATE TABLE `staticid_inf` (
  `staticId` int(11) NOT NULL,
  PRIMARY KEY (`staticId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staticid_inf
-- ----------------------------
INSERT INTO `staticid_inf` VALUES ('9');

-- ----------------------------
-- Table structure for status_inf
-- ----------------------------
DROP TABLE IF EXISTS `status_inf`;
CREATE TABLE `status_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of status_inf
-- ----------------------------
INSERT INTO `status_inf` VALUES ('1', '已审核');
INSERT INTO `status_inf` VALUES ('2', '未审核');

-- ----------------------------
-- Table structure for traincontract_inf
-- ----------------------------
DROP TABLE IF EXISTS `traincontract_inf`;
CREATE TABLE `traincontract_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of traincontract_inf
-- ----------------------------
INSERT INTO `traincontract_inf` VALUES ('1', '已签');
INSERT INTO `traincontract_inf` VALUES ('2', '未签');

-- ----------------------------
-- Table structure for traindata_inf
-- ----------------------------
DROP TABLE IF EXISTS `traindata_inf`;
CREATE TABLE `traindata_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `filename` varchar(300) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of traindata_inf
-- ----------------------------
INSERT INTO `traindata_inf` VALUES ('1', '大圣归来', '0ec8cc80112a2d6.mp4', '大圣归来一', '2019-05-19 09:52:55', '1');
INSERT INTO `traindata_inf` VALUES ('2', '大圣归来', '0ec8cc80112a2d7.mp4', '大圣归来二', '2019-05-19 10:04:18', '1');
INSERT INTO `traindata_inf` VALUES ('3', '北京北京', '群星 - 北京欢迎你.mp4', '北京欢迎你', '2019-05-19 12:15:39', '1');

-- ----------------------------
-- Table structure for train_inf
-- ----------------------------
DROP TABLE IF EXISTS `train_inf`;
CREATE TABLE `train_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `startdata` varchar(255) DEFAULT NULL,
  `enddata` varchar(255) DEFAULT NULL,
  `totallength` int(11) DEFAULT NULL,
  `completion` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `traintime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `lea_1` (`emp_id`),
  KEY `lea_2` (`dept_id`),
  KEY `lea_3` (`job_id`),
  CONSTRAINT `train_inf_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee_inf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `train_inf_ibfk_2` FOREIGN KEY (`dept_id`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `train_inf_ibfk_3` FOREIGN KEY (`job_id`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train_inf
-- ----------------------------
INSERT INTO `train_inf` VALUES ('1', '5', '3', '9', 'java进阶', '2019-05-14', '2019-05-22', '8', '1', '86', '2019-05-24 15:37:48');

-- ----------------------------
-- Table structure for uservisit_inf
-- ----------------------------
DROP TABLE IF EXISTS `uservisit_inf`;
CREATE TABLE `uservisit_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_time` varchar(255) DEFAULT NULL,
  `exit_time` varchar(255) DEFAULT NULL,
  `visit_ip` varchar(255) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_from` varchar(255) NOT NULL,
  `browser` varchar(255) DEFAULT NULL,
  `system` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `loginname` varchar(255) DEFAULT NULL,
  `iphone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uservisit_inf
-- ----------------------------
INSERT INTO `uservisit_inf` VALUES ('1', '2019-02-12 09:13:56', '2019-02-12 20:10:21', '117.157.184.39', '北京 移动', '移动端', 'Chrome', 'Android', '57.0.2987.132', 'admin', 'HUAWEIFIG-AL10');
INSERT INTO `uservisit_inf` VALUES ('2', '2019-02-12 09:16:46', '2019-02-12 20:10:21', '117.157.180.39', '北京 移动', 'PC端', 'Firefox', 'Windows', '65.0', 'admin', 'Windows NT 10.0; Win64; x64');
INSERT INTO `uservisit_inf` VALUES ('3', '2019-02-12 09:17:19', '2019-02-12 20:10:21', '124.152.216.170', '北京 联通', '移动端', 'Mobile Safari', 'Android', '4.0', 'admin', 'HUAWEICLT-AL00');

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT '2',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(20) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  `che_id` int(11) DEFAULT NULL,
  `con_id` int(11) DEFAULT NULL,
  `sal_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES ('1', 'admin', '9f5964c2aec4dc3a56cfa77ad6cc3bbb', '', '1', '2019-04-15 15:35:40', '超级管理员', null, null, null, null);
INSERT INTO `user_inf` VALUES ('2', 'manager', '7304b1d41dbd735711f9e25db40b4f63', '', '1', '2019-04-15 15:35:43', '总经理', null, null, null, null);
INSERT INTO `user_inf` VALUES ('3', 'dsds', '7ac6e9b93d3e3ecc13968f6cc53a9093', '13902001111@163.com', '1', '2019-05-08 10:36:57', '测试1', '1', '1', '1', '1');
INSERT INTO `user_inf` VALUES ('4', 'dfre', '33374c88e3a7570c211c203b4795fc24', '13902001111@qq.com', '2', '2019-05-30 16:08:41', '翎幺', '3', '3', '3', '3');
INSERT INTO `user_inf` VALUES ('5', 'ahualy', '0246876c1c966a36923ceb77c1d82e3a', '13902001111@163.com', '1', '2019-05-18 09:04:08', '测试', '5', '5', '5', '5');
INSERT INTO `user_inf` VALUES ('6', 'Leetcode', '28621d88e9551ce4086aa466239ecb16', '13902001111@163.com', '1', '2019-04-17 08:40:33', '铭星', '8', '8', '8', '8');
INSERT INTO `user_inf` VALUES ('7', 'Think', '94596cc80b6cf1e974856d3715723d4a', '13902001111@qq.com', '1', '2019-05-21 08:22:57', '袭人', '9', '9', '9', '9');
