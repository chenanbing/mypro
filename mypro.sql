/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mypro

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-22 18:46:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `box_num` int(4) DEFAULT NULL COMMENT '箱号',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `brand` varchar(128) DEFAULT NULL COMMENT '品牌',
  `specs` varchar(128) DEFAULT NULL COMMENT '规格',
  `num` int(4) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '单价',
  `total_price` decimal(10,2) DEFAULT '0.00' COMMENT '总价',
  `net_weight` varchar(128) DEFAULT NULL COMMENT '净重',
  `gross_weight` varchar(128) DEFAULT NULL COMMENT '毛重',
  `recipient_name` varchar(128) DEFAULT NULL COMMENT '收件人',
  `recipient_phone` varchar(11) DEFAULT NULL COMMENT '收件人电话',
  `recipient_address` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `recipient_id` varchar(128) DEFAULT NULL COMMENT '身份证号',
  `order_num` varchar(128) DEFAULT NULL COMMENT '客户订单号',
  `status` smallint(4) DEFAULT '0' COMMENT '0未打印1已打印',
  `express_id` int(4) DEFAULT NULL COMMENT '快递公司id',
  `express_name` varchar(128) DEFAULT NULL COMMENT '快递公司',
  `express_num` varchar(128) DEFAULT NULL COMMENT '快递单号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('5', '1', '巧克力', 'KINDER CHOCOLATER', null, null, '0.00', '0.00', null, null, null, null, null, null, null, '0', null, null, null, '2018-05-22 00:55:26', null);
INSERT INTO `t_order` VALUES ('7', '2', '2风格复古', '2222', null, null, '0.00', '0.00', null, null, null, null, null, null, null, '0', null, null, null, '2018-05-22 00:57:05', null);
INSERT INTO `t_order` VALUES ('8', '3', 'DFSDF', 'RTRET', null, null, '0.00', '0.00', null, null, null, null, null, null, null, '0', null, null, null, '2018-05-22 01:04:04', null);
INSERT INTO `t_order` VALUES ('9', '4', '的地方复合弓地方', 'RTRET', null, null, '0.00', '0.00', null, null, null, null, null, null, null, '0', null, null, null, '2018-05-22 01:05:32', null);
INSERT INTO `t_order` VALUES ('10', '4', '的地方复合弓地方', 'RTRET', null, null, '0.00', '0.00', null, null, null, null, null, null, null, '0', null, null, null, '2018-05-22 01:08:45', null);
INSERT INTO `t_order` VALUES ('11', '1', '2', '3', null, null, '0.00', '0.00', null, null, null, null, null, null, null, '0', null, null, null, '2018-05-22 01:09:04', null);

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_test
-- ----------------------------
INSERT INTO `t_test` VALUES ('1', '姓名', '2018-05-22 00:09:26');
