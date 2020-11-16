-- set names utf8;


-- 创建一个名叫“ibs_audit”的数据库
-- CREATE SCHEMA ibs_audit;

-- 切换数据库
--  use ibs_audit;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for ed_proj_info
-- ----------------------------
DROP TABLE IF EXISTS `ed_proj_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
 
CREATE TABLE `ed_proj_info` (
  `proj_id` int(11) NOT NULL AUTO_INCREMENT,
  `proj_name` varchar(50) NOT NULL,
  `proj_desc` varchar(1000) NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `manager` varchar(50) NOT NULL,
  `auditee` varchar(50) NOT NULL,
  `auditedManager` varchar(50) NOT NULL,
  `supervisor` varchar(50) NOT NULL,
  `office` varchar(50) NOT NULL,
  `officeManager` varchar(50) NOT NULL,
  `creater` varchar(50) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`proj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
-- ----------------------------
-- Records of ed_proj_info
-- ----------------------------
INSERT INTO `ed_proj_info` VALUES (1, '20201102审计项目','这是项目描述1.......','2020-11-02 12:00:00','2020-11-20 13:00:00', 'hxm', '无限公司', '李四', '王五', '事务所', '张三', 'admin','2020-11-02 00:00:00');
INSERT INTO `ed_proj_info` VALUES (2, '20201105审计项目','这是项目描述2.......','2020-11-05 12:00:00','2020-11-20 13:00:00', 'hxm2', '无限公司2', '李四2', '王五2', '事务所2', '张三2', 'admin','2020-11-05 00:00:00');

-- ----------------------------
-- Table structure for certificate
-- ----------------------------
DROP TABLE IF EXISTS `ed_cert_info`;
CREATE TABLE `ed_cert_info`  (
  `cert_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `manager` varchar(50) NOT NULL,
  `creater` varchar(50) NOT NULL,
  `executor` varchar(50) NOT NULL,
  `executorPhone` varchar(50) NOT NULL,
  `createTime` datetime NOT NULL,
  `deadline` varchar(50) NOT NULL,
  `status` int(11) NOT NULL,
  `picture` longblob NOT NULL,
  `picHash` varchar(255) NOT NULL,
  `transfer` int(11) NOT NULL,
  `cert_desc` varchar(255),
  `auditor` varchar(50)NOT NULL,
  PRIMARY KEY (`cert_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ed_cert_info
-- ----------------------------
INSERT INTO `ed_cert_info` VALUES (1, 'xxx文件存证需求单.', '李四', '王五', '张三', '188141801222', '2020-10-05 12:00:00', '2020-10-20 12:00:00', 1,'xxxx','987',1,'详细描述 xxx','韩梅梅');


-- ----------------------------
-- Table structure for ed_conf_info
-- ----------------------------
DROP TABLE IF EXISTS `ed_conf_info`;
CREATE TABLE `ed_conf_info`  (
  `conf_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `conf_desc` varchar(255),
  `summary` varchar(255),
  `auditor` varchar(50)NOT NULL,
  `creater` varchar(50) NOT NULL,
  `audited` varchar(50) NOT NULL,
  `createTime` datetime NOT NULL,
  `deadline` datetime NOT NULL,
  `status` int(11) NOT NULL,
  `picture` longblob NOT NULL,
  `picHash` varchar(255) NOT NULL,
  `reviewer` varchar(50) NOT NULL,
  `auditTime` datetime NOT NULL,
  `reviewTime` datetime NOT NULL,
  `manager` varchar(50) NOT NULL,
  `managerLeader` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  PRIMARY KEY (`conf_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ed_conf_info
-- ----------------------------
 INSERT INTO `ed_conf_info` VALUES (1, 'xxx确认单.', 'xxxx 概述', 'xxx 描述', '张三', '李思', 'client', '2020-10-20 12:00:00', '2020-10-20 12:00:00', 1,'xxxx','987','波子','2020-10-20 12:00:00','2020-10-20 12:00:00',' 美美','韩梅梅','研发部门');


-- ----------------------------
-- Table structure for ed_hash_info
-- ----------------------------
DROP TABLE IF EXISTS `ed_hash_info`;
CREATE TABLE `ed_hash_info`  (
  `hash_id` int(11) NOT NULL AUTO_INCREMENT,
  `hash_name` varchar(150) NOT NULL,
  `hash_value` varchar(255),
  `hash_type` varchar(255),
  `title` varchar(150)NOT NULL,
  `creater` varchar(50) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`hash_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ed_hash_info
-- ----------------------------
INSERT INTO `ed_hash_info` VALUES (1,'xxx文件.', '987', 'xxxx项目', '审计确认单', 'admin', '2020-10-20 12:00:00');


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
 `user_id` int(11) NOT NULL AUTO_INCREMENT,
 `user_name` varchar(150) NOT NULL,
 `user_phone` varchar(255),
 `user_email` varchar(255),
 `user_password` varchar(255),
 PRIMARY KEY (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1,'admin', '188141803333', '111@qq.com','123456');


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_code` varchar(64) NOT NULL COMMENT '菜单编码',
  `parent_code` varchar(64) NOT NULL COMMENT '父级编号',
  `tree_sort` decimal(10,0) NOT NULL COMMENT '本级排序号（升序）',
  `tree_leaf` char(1) NOT NULL COMMENT '是否最末级',
  `tree_level` decimal(4,0) NOT NULL COMMENT '层次级别',
  `tree_names` varchar(1000) NOT NULL COMMENT '全节点名',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `menu_type` char(1) NOT NULL COMMENT '菜单类型（1菜单 2权限 3开发）',
  `menu_href` varchar(1000) DEFAULT NULL COMMENT '链接',
  `menu_target` varchar(20) DEFAULT NULL COMMENT '目标',
  `menu_icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `permission` varchar(1000) DEFAULT NULL COMMENT '权限标识',
  `weight` decimal(4,0) DEFAULT NULL COMMENT '菜单权重',
  `is_show` char(1) NOT NULL COMMENT '是否显示（1显示 0隐藏）',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  `created` varchar(30) NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `last_updated` varchar(30) DEFAULT NULL COMMENT '修改人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '修改时间',
  `ATTRIBUTE1` varchar(100) DEFAULT NULL,
  `ATTRIBUTE2` varchar(100) DEFAULT NULL,
  `ATTRIBUTE3` varchar(100) DEFAULT NULL,
  `ATTRIBUTE4` varchar(100) DEFAULT NULL,
  `ATTRIBUTE5` varchar(100) DEFAULT NULL,
  `is_delete` varchar(10) NOT NULL DEFAULT 'NORMAL' COMMENT '逻辑删除标志，NORMAL/DELETE',
  PRIMARY KEY (`menu_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
 INSERT INTO `sys_menu` VALUES ('1027170929387582963', '0', 1, '0', 1, '审计存证栏目', '审计存证栏目', '1', '/list', '', 'desktop', '', 3, '1', '22', '', '2019-01-10 10:00:00', 'admin', '2019-06-25 10:12:58', NULL, NULL, NULL, NULL, NULL, 'NORMAL');
 INSERT INTO `sys_menu` VALUES ('1027028251421568275', '0', 2, '0', 1, '哈希值对比', '哈希值对比', '1', '/file', '', 'book', '', 3, '1', '22', '', '2019-01-10 10:00:00', 'admin', '2019-06-25 10:12:58', NULL, NULL, NULL, NULL, NULL, 'NORMAL');
 INSERT INTO `sys_menu` VALUES ('1027029038633737604', '1027028251421568275', 1, '0', 1, '哈希值对比/文件上链', '文件上链', '1', '/file/lock', '', 'file-text-o', NULL, NULL, '1', '', 'admin', '2019-03-20 03:19:59', 'admin', '2019-04-15 10:04:31', NULL, NULL, NULL, NULL, NULL, 'NORMAL');



-- ----------------------------
-- Table structure for sys_verification_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_verification_code`;
CREATE TABLE `sys_verification_code` (
 `captcha_id` varchar(100) NOT NULL COMMENT '验证码id',
 `captcha` varchar(100) NOT NULL COMMENT '验证码',
 `expireTime` datetime NOT NULL COMMENT '过期时间',
 `updateTime` datetime NOT NULL COMMENT '更新时间',
 PRIMARY KEY (`captcha_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_verification_code
-- ----------------------------
BEGIN;
INSERT INTO `sys_verification_code` VALUES ('00d2c04fd72c432cbb56370f9f450b30', 'mem6', '2020-09-18 17:52:51', '2020-09-18 17:47:51');
INSERT INTO `sys_verification_code` VALUES ('07dbaa7685774275918ca7487f8e56d0', 'nd53', '2020-09-16 16:15:34', '2020-09-16 16:10:34');
INSERT INTO `sys_verification_code` VALUES ('1fdc46b5e0c9495abd1435275b1d7e80', 'mynm', '2020-10-09 09:04:17', '2020-10-09 08:59:17');
INSERT INTO `sys_verification_code` VALUES ('2292689b535e46ba8df0ebd0cc87355b', 'wn3b', '2020-09-24 20:25:09', '2020-09-24 20:20:09');
INSERT INTO `sys_verification_code` VALUES ('2bc3fbd56ab84e93a42723ca3ee84efa', '6mxn', '2020-10-09 09:01:23', '2020-10-09 08:56:23');
INSERT INTO `sys_verification_code` VALUES ('35186208a58743d182ddb6a01cc0c074', '43cx', '2020-10-30 11:16:30', '2020-10-30 11:11:30');
INSERT INTO `sys_verification_code` VALUES ('4a1d1810dfea44848a3f8fd9f4ce9213', 'dy3d', '2020-10-09 17:44:21', '2020-10-09 17:39:21');
INSERT INTO `sys_verification_code` VALUES ('504945e190e84c4188a1fafa5a7c6e9d', 'wg6y', '2020-10-27 15:10:20', '2020-10-27 15:05:20');
INSERT INTO `sys_verification_code` VALUES ('51ac046386dd466190e2df58e55819b0', 'n4a7', '2020-09-24 09:40:39', '2020-09-24 09:35:39');
INSERT INTO `sys_verification_code` VALUES ('577fd22a33bc47a382bd983325194ea0', 'ddec', '2020-11-02 16:58:38', '2020-11-02 16:53:38');
INSERT INTO `sys_verification_code` VALUES ('59d9022ee6af4d00aca10486bdc36a50', '3ag6', '2020-09-25 12:51:41', '2020-09-25 12:46:41');
INSERT INTO `sys_verification_code` VALUES ('5e512894f43c4b59b522db0393328b78', '32bc', '2020-09-25 13:30:46', '2020-09-25 13:25:46');
INSERT INTO `sys_verification_code` VALUES ('5f31b99d5be749ed8ffcd09aebe5c147', 'ydf2', '2020-09-14 14:04:43', '2020-09-14 13:59:43');
INSERT INTO `sys_verification_code` VALUES ('658840b462f344c98cf36c58697a1310', 'xw7e', '2020-09-21 10:06:52', '2020-09-21 10:01:52');
INSERT INTO `sys_verification_code` VALUES ('699a588bee11403ba89e04ad150a29de', 'n2yw', '2020-09-21 14:47:29', '2020-09-21 14:42:29');
INSERT INTO `sys_verification_code` VALUES ('79b8a24c1e4a40588775094e4e476346', '566w', '2020-09-13 10:09:15', '2020-09-13 10:04:15');
INSERT INTO `sys_verification_code` VALUES ('7ffa54f6fddf49cc88ed1bccd6072c46', '4aaa', '2020-09-21 14:47:54', '2020-09-21 14:42:54');
INSERT INTO `sys_verification_code` VALUES ('84eb7805afde4b71a28b9a578c8e8d34', 'dx2b', '2020-09-16 14:22:30', '2020-09-16 14:17:30');
INSERT INTO `sys_verification_code` VALUES ('85280ebf28d84b1c92695b505de650c4', '78gm', '2020-10-28 18:10:23', '2020-10-28 18:05:23');
INSERT INTO `sys_verification_code` VALUES ('87c17a06385d4f20baca253c8576b8df', 'wne6', '2020-09-21 13:19:24', '2020-09-21 13:14:24');
INSERT INTO `sys_verification_code` VALUES ('b14bfd6acb754aaa811341c810b81b61', 'b8dy', '2020-10-27 15:08:41', '2020-10-27 15:03:41');
INSERT INTO `sys_verification_code` VALUES ('bcd6b8db4be34c11a471e4528e8890aa', 'pf4b', '2020-10-09 08:58:20', '2020-10-09 08:53:20');
INSERT INTO `sys_verification_code` VALUES ('bd952d3b8c45429faf846822918a9f60', 'wden', '2020-09-17 10:40:57', '2020-09-17 10:35:57');
INSERT INTO `sys_verification_code` VALUES ('c4c51ff3d9fb4b1f988a7a909a0024f6', 'n84w', '2020-09-24 09:40:45', '2020-09-24 09:35:45');
INSERT INTO `sys_verification_code` VALUES ('ceec1952b3864daa90abea5f5379a357', 'bwge', '2020-09-21 10:47:09', '2020-09-21 10:42:09');
INSERT INTO `sys_verification_code` VALUES ('df5ce82653e64411ab21a0fa09f1d1d5', '37xn', '2020-09-17 10:44:15', '2020-09-17 10:39:15');
INSERT INTO `sys_verification_code` VALUES ('e0aa759ef8ec40ca8dd0117df9f6e636', 'p2an', '2020-11-02 17:42:31', '2020-11-02 17:37:31');
INSERT INTO `sys_verification_code` VALUES ('ef1d20df08e14caeabd6c56079d7ca45', '3f5m', '2020-10-30 15:30:39', '2020-10-30 15:25:39');
COMMIT;



-- ----------------------------
-- Table structure for sys_para_errcode
-- ----------------------------
DROP TABLE IF EXISTS `sys_para_errcode`;
CREATE TABLE `sys_para_errcode` (
 `ERRCODE` varchar(10) NOT NULL COMMENT '错误代码',
 `ERRMSG` varchar(128) DEFAULT NULL COMMENT '错误信息',
 `OUTCODE` varchar(10) DEFAULT NULL COMMENT '外部代码',
 `OUTMSG` varchar(128) DEFAULT NULL COMMENT '外部错误信息',
 `SYSTEMCODE` varchar(4) NOT NULL COMMENT '系统编号',
 `ISCONVERT` char(1) DEFAULT NULL COMMENT '是否需要转换, 0:不需要, 1:需要',
 `ISVALID` char(1) DEFAULT NULL COMMENT '是否有效, 0:无效, 1:有效',
 PRIMARY KEY (`ERRCODE`,`SYSTEMCODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_para_errcode
-- ----------------------------
BEGIN;
INSERT INTO `sys_para_errcode` VALUES ('1001', '获取验证码失败', '1001', '获取验证码失败', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1002', '请求参数异常', '1002', '请求参数有误', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1003', '验证码失效', '1003', '验证码失效', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1004', '验证码输入有误', '1004', '验证码输入有误', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1005', '用户锁定', '1005', '用户锁定', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1006', '用户不存在', '1006', '用户不存在', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1007', '账号密码错误', '1007', '账号密码错误', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1008', '保存失败', '1008', '保存失败', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1012', '角色代码已存在', '1012', '角色代码已存在', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1013', '角色代码不存在', '1013', '角色代码不存在', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1014', '菜单代码已存在，不能新增', '1014', '菜单代码已存在，不能新增', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1015', '待修改的菜单不存在', '1015', '待修改的菜单不存在', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1016', '父级代码不存在', '1016', '父级代码不存在', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1017', '用户编码已存在', '1017', '用户编码已存在', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1018', '该字典数据包含未停用的子字典', '1018', '该字典数据包含未停用的子字典', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1019', '两次输入的新密码不一致', '1019', '两次输入的新密码不一致', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1020', '用户账号已存在', '1020', '用户账号已存在', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1021', '输入的旧密码错误', '1021', '输入的旧密码错误', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1035', '密码必须同时包含字母和数字！', '1035', '密码必须同时包含字母和数字！', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1036', '密码解密错误！', '1036', '密码解密错误！', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1037', '新旧密码一致不需要修改', '1037', '新旧密码一致不需要修改', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1038', '用户已登录！', '1038', '用户已登录！', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1039', '该用户在其他设备登录，请重新登录！', '1039', '该用户在其他设备登录，请重新登录！', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1040', '用户账号不存在！', '1040', '用户账号不存在！', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1041', '获取缓存数据有误！', '1041', '获取缓存数据有误！', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1042', '删除失败', '1042', '删除失败', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1043', '删除成功', '1043', '删除成功', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1044', '保存成功', '1044', '保存成功', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('1100', '项目名称已存在', '1100', '项目名称已存在', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('200', '成功', '200', '成功', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('401', '未登录', '401', '未登录', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('403', '权限不足,禁止访问', '403', '权限不足,禁止访问', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('404', '页面未发现', '404', '页面未发现', 'ZT', '1', '1');
INSERT INTO `sys_para_errcode` VALUES ('405', '请重新登录！', '405', '请重新登录！', 'ZT', '1', '1');
COMMIT;


ALTER TABLE `ed_cert_info`
ADD COLUMN `proj_Id` int(11) NULL COMMENT '项目ID';
ALTER TABLE `ed_cert_info`
ADD COLUMN `node` int(11) NULL COMMENT '节点';

ALTER TABLE `ed_conf_info`
ADD COLUMN `proj_Id` int(11) NULL COMMENT '项目ID';
ALTER TABLE `ed_conf_info`
ADD COLUMN `node` int(11) NULL COMMENT '节点';
ALTER TABLE `ed_conf_info`
ADD COLUMN `executor` varchar(50) NULL COMMENT '执行人';


-- ----------------------------
-- 审批记录表
-- ----------------------------
DROP TABLE IF EXISTS `ed_approval_record`;

CREATE TABLE `ed_approval_record` (
      `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
      `processId` int(11) COMMENT '审批流程ID',
      `executor` varchar(50) COMMENT '执行人',
      `stayExecutor` varchar(50) COMMENT '待执行人',
      `status` int(4) COMMENT '审核状态 0:发起人 1:同意 2:不同意 3:扭转 4:流程结束',
      `approvalOpinion` varchar(500) COMMENT '审核意见',
      `attachment` int(11) COMMENT '附件',
      `approvalDate` datetime COMMENT '审核时间',
      `category` varchar(50) COMMENT '类别:存证信息或者确认单',
      `creater` varchar(50) COMMENT '创建者',
      `createTime` datetime COMMENT '创建时间',
      `updates` varchar(50) COMMENT '更新者',
      `updateTime` datetime COMMENT '更新时间',
      PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- ----------------------------
-- 审批流程节点
-- ----------------------------

DROP TABLE IF EXISTS `ed_approval_node`;

CREATE TABLE `ed_approval_node` (
      `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
      `processId` int(11) COMMENT '审批流程ID',
      `upNode` varchar(50) COMMENT '上个节点',
      `nextNode` varchar(50) COMMENT '下个节点',
      `node` int(4) COMMENT '节点',
      `category` varchar(50) COMMENT '类别:存证信息或者确认单',
      PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- ----------------------------
-- Function structure for getMenuChild
-- ----------------------------
 DROP FUNCTION IF EXISTS `getMenuChild`;
 delimiter ;;
 CREATE FUNCTION `getMenuChild`(`rootId` varchar(1000))
   RETURNS varchar(1000) CHARSET utf8
 BEGIN

    DECLARE sTemp VARCHAR(1000);
    DECLARE sTempChild VARCHAR(1000);

    SET sTemp='$';
   SET sTempChild=CAST(rootId AS CHAR);
    WHILE sTempChild is not null DO
            set sTemp = CONCAT(sTemp, ',', sTempChild);
            SELECT GROUP_CONCAT(menu_code) INTO sTempChild from sys_menu where FIND_IN_SET(parent_code, sTempChild) > 0;
        END WHILE;
   RETURN sTemp;
 END
 ;;
 delimiter ;

 SET FOREIGN_KEY_CHECKS = 1;