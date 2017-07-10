/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2017-07-10 23:52:17
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `movie`
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `star` varchar(255) DEFAULT NULL,
  `summary` varchar(1024) DEFAULT NULL,
  `releasetime` timestamp NOT NULL DEFAULT '2012-01-02 00:00:00',
  `price` decimal(10,0) DEFAULT NULL,
  `photourl` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO movie VALUES ('9', '城市猎人', '124', '等等', '多大的', '2017-07-27 00:00:00', '23', 'http://img1.imgtn.bdimg.com/it/u=1155839859,1689821038&fm=26&gp=0.jpg');
INSERT INTO movie VALUES ('10', '22', '22', '22', '22', '2017-07-08 00:00:00', '22', 'http://img1.imgtn.bdimg.com/it/u=1155839859,1689821038&fm=26&gp=0.jpg');

-- ----------------------------
-- Table structure for `schedule`
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `movieid` int(10) DEFAULT NULL,
  `hallname` varchar(255) DEFAULT NULL,
  `moviename` varchar(255) DEFAULT NULL,
  `starttime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `endtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO schedule VALUES ('7', '9', '6号', '城市猎人', '2017-07-02 00:00:00', '2017-07-09 00:00:00');
INSERT INTO schedule VALUES ('8', '10', '3', '22', '2017-07-08 00:00:00', '2017-07-09 00:00:00');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('11', 'hzl', '1234', '1031734796@qq.com', '1.jpeg');
INSERT INTO user VALUES ('12', 'alvin', '123', '1031d7@qq.com', 'a962220f47d645ccaf529644a7636c74.jpeg');
INSERT INTO user VALUES ('14', '1', '1', '103171@qq.com', 'f648af8dd0d348c19c6bc63cf2261345.jpeg');

-- ----------------------------
-- Table structure for `userorder`
-- ----------------------------
DROP TABLE IF EXISTS `userorder`;
CREATE TABLE `userorder` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) DEFAULT NULL,
  `movieid` int(10) DEFAULT NULL,
  `scheduleid` int(10) DEFAULT NULL,
  `seat` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userorder
-- ----------------------------
INSERT INTO userorder VALUES ('5', '11', '9', '7', '12pai3zuo', '23');
INSERT INTO userorder VALUES ('6', '11', '9', '7', '8pai8zuo', '23');
INSERT INTO userorder VALUES ('7', '11', '9', '7', '13pai5zuo', '23');
