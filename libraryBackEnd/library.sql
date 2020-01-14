/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 14/01/2020 17:05:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ISBN` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `language` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `pubdate` date NULL DEFAULT NULL,
  `class_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pressmark` int(11) NULL DEFAULT NULL,
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('0b4280c4a6614431a85423b35dc6a4a3', '我在型月那些年', '李之卿呀', '起点中文网', '-', '穿越到这个呆毛众多，妹子众多，神秘犹存，还快要毁灭的世界！', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('2594540ab4954c64aa1e0c474816d5b2', '三国大领主的诸天', '云中梦剑', '起点中文网', '-', '重生三国，手下兵强马壮，正准备攻伐天下之时，诸天降临了。', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('370fc8cefe544cdabb648888f2c30766', '这个和尚强得过份', '黑桃十叁', '起点中文网', '-', '“阿弥陀佛，老衲只会种田，不会修炼，更没有修为境界。”', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('4db85934270348479ae0713446156e65', '送罪恶之徒回到属于他们的地狱', '东城令', '起点中文网', '-', '陆笙获得罚恶令，附身在衣锦还乡的士子身上。入驻提刑司，掌刑侦之职。', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('6f385b6b4ab84a12ba611f9035fe8ceb', 'NBA最成功的教练！', '临河羡鱼翁', '起点中文网', '-', '我是nba最成功的主教练？不，还有庄飞', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('731d3c8df5174a128e99385659767e80', '阴阳怪气的驱魔人', '庚辰辛巳', '起点中文网', '-', '我胸中有一口气，名为阴阳怪气。我手持一个骨灰盒，叫儒雅随盒。', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('833f1c75e44c4c6289bd5d9a4bf379b9', '宠物小精灵之庭树：宝可梦', '轻泉流响', '起点中文网', '-', '穿越到真实的神奇宝贝世界，自秋叶镇走出，在一步一步的探索中不断成长。', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('90750516ee454f0c9b402a6db48d2358', '生在唐人街', '陶良辰', '起点中文网', '-', '从唐人街到华尔街，看出生平凡的苏木，怎样拼出个大好未来！', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('9d53ec6086904315a0901b590a238dce', '数风流，论成败', '三戒大师', '起点中文网', '-', '励精图治挽天倾，哪怕身后骂名滚滚来。', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('acd009b9f9134bc68555d6c74bce3e7c', '从聊斋开始收容诸天', '稀碎玻璃渣', '起点中文网', '-', '以阴魂之躯，通天地幽冥；纳诸天阴物，拢万界香火！', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('de9f9b80b5b0485aa8004f8bfd67b99e', '军迷，爱刀爱枪爱冒险', '如水意', '起点中文网', '-', '且看在国际佣兵界闯下一片天地', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');
INSERT INTO `book` VALUES ('f8f51df92c174a3b96a3a9cec8c53014', '巨星从影视学院开始', '进击的咸鸭蛋', '起点中文网', '-', '穿越成帅哥，无脑过艺考，从影视学院开始，成为一名闪耀的巨星。', '中文', 0.00, '1969-12-31', 'f035cd6bb30244ae8ae0fdf913673b6e', 1, '1', '//qidian.gtimg.com/qd/images/common/default_book.5968b.png');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class_index` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('0e44e340bd41465ea5bebd7a270fbe27', '12', '建筑百科');
INSERT INTO `class` VALUES ('24bc012063c044d59d038d3ce061abfa', '4', '修仙');
INSERT INTO `class` VALUES ('363137b733424465a634afff9aa1f375', '7', '自然科学');
INSERT INTO `class` VALUES ('5eba381af7484a5ea08d8c565d8dcb02', '14', '戏曲');
INSERT INTO `class` VALUES ('65cde2bb53164927a72cc3ddf1d00bfc', '17', '音乐专辑');
INSERT INTO `class` VALUES ('7414d080d7244dc5a28b4d59be800b4d', '10', '动物百科');
INSERT INTO `class` VALUES ('8887c192c30547d49a210158f57b01be', '2', '穿越');
INSERT INTO `class` VALUES ('9be1e58b56724367b0978f96d467054b', '11', '植物百科');
INSERT INTO `class` VALUES ('a003f4500f9e4db28144e7c7a4f01928', '15', '棋谱');
INSERT INTO `class` VALUES ('a4327d35ea4047e0a4a043b68a5496eb', '16', '琴谱');
INSERT INTO `class` VALUES ('d671628af81544b6be8bb3e7f8dbc82b', '13', '天文百科');
INSERT INTO `class` VALUES ('d76082bada064b6c93749bb2e6b56d95', '3', '武侠');
INSERT INTO `class` VALUES ('df8c89add5ce4266ae5c87cef71e6644', '5', '历史');
INSERT INTO `class` VALUES ('e214b7e701924d1aa3e34c73487c3246', '8', '野史');
INSERT INTO `class` VALUES ('e54cf0cc9e644d0e8ae513061a3b1190', '9', '人物传记');
INSERT INTO `class` VALUES ('f035cd6bb30244ae8ae0fdf913673b6e', '1', '综合类');
INSERT INTO `class` VALUES ('f411d50e55844253b9ec7f36daa32060', '6', '社科');

-- ----------------------------
-- Table structure for loan_logs
-- ----------------------------
DROP TABLE IF EXISTS `loan_logs`;
CREATE TABLE `loan_logs`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志id',
  `book_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍id',
  `reader_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '读者id',
  `loan_date` date NOT NULL COMMENT '借出日期',
  `return_date` date NULL DEFAULT NULL COMMENT '归还日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of loan_logs
-- ----------------------------
INSERT INTO `loan_logs` VALUES ('077aadbcce364c15b7b289a8e99c8b67', 'acd009b9f9134bc68555d6c74bce3e7c', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('2cf5b55f93a34174827e5027828e2d5d', '833f1c75e44c4c6289bd5d9a4bf379b9', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('470c68ddbe2c44dd9d78555fac2178bf', '0b4280c4a6614431a85423b35dc6a4a3', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('4b3cb16950a04638b62ed4df9b268e9f', '731d3c8df5174a128e99385659767e80', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('529b4d7000d74b2495fced322940ef02', 'f8f51df92c174a3b96a3a9cec8c53014', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('82f3e659ca02476ea5ebb6b1c334d400', '4db85934270348479ae0713446156e65', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('ab8e60eca4464296a6758c469340200b', '2594540ab4954c64aa1e0c474816d5b2', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('b51e34de0e7b446eb0135411b1f13f99', '9d53ec6086904315a0901b590a238dce', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('c3a02fc934184fc583fe533a424ed348', '90750516ee454f0c9b402a6db48d2358', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('d79b03ffb603439f98dfa196897a2cdc', 'de9f9b80b5b0485aa8004f8bfd67b99e', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('eddc9804dc274d29841d4bebe28c2823', '370fc8cefe544cdabb648888f2c30766', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');
INSERT INTO `loan_logs` VALUES ('f0bf05f7b2f7479992caa8338cdfe106', '6f385b6b4ab84a12ba611f9035fe8ceb', '8047e126d8f44f0cbd1aefe289cafc6c', '2020-01-14', '2020-01-14');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `is_admin` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '管理员标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0515f866b7c24d84a94728a33cc35655', 'reader3', '123456', '读者3', '0', '2030-11-30', '杭州', '87654321', '0');
INSERT INTO `user` VALUES ('3106ca8e33c341b98b2649f31ebbf97d', 'reader1', '123456', '读者1', '0', '2010-11-30', '北京', '87654321', '0');
INSERT INTO `user` VALUES ('65efdf19da1b4b97b13500711720b964', 'wizardj', '321', '小盐的bf', '1', '1992-12-31', '武汉啦', '123456789', '0');
INSERT INTO `user` VALUES ('8047e126d8f44f0cbd1aefe289cafc6c', 'admin', '123', '小盐小盐小盐', '0', '1969-12-31', '武汉孝感', '12345678', '1');
INSERT INTO `user` VALUES ('8e138e5eafac43c485ea46799ab80021', 'reader2', '123456', '读者2', '0', '2020-11-30', '深圳', '87654321', '0');
INSERT INTO `user` VALUES ('ff86ae6be5cc4232bade8fe42ce42bdf', 'dragonborn', '123456', '龙傲天', '0', '2000-11-30', '上海', '87654321', '0');

SET FOREIGN_KEY_CHECKS = 1;
