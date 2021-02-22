DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_code` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `status` tinyint(3) NOT NULL COMMENT '状态',
  `dept_id` int(11) NOT NULL COMMENT '所属部门',
  `user_group_id` int(11) NOT NULL COMMENT '所属用户组',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `operator_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_extension` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `frozen_recovery_date` datetime DEFAULT NULL COMMENT '解冻恢复时间',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table t_user add status tinyint(3) NOT NULL DEFAULT '1' after operator_id;
alter table t_user drop COLUMN phone;
alter table t_user add phone varchar(20) NOT NULL DEFAULT '0' after password;
alter table t_user add email varchar(100) NOT NULL DEFAULT '' after phone;
alter table t_user add dept_id int(11) NOT NULL DEFAULT '0' after email;
alter table t_user add user_group_id int(11) NOT NULL DEFAULT '0' after dept_id;

INSERT INTO `t_user` VALUES 
(1,'admin','admin','$2a$10$JHz4wZFnISYHtF4Z2ksIVORtbFNcXhhCKlEgNpEyuAY5d2RjKn2m.',18899998888,'srb.123@163.com',1,1,1,0,1,'2020-09-08 23:55:13','2020-09-08 23:55:13'),
(2,'stocker','stocker','$2a$10$x9ZurZSjjBG5KR.h9BVTPuTN8.8eiPMnfbBSqJt3PNwNuagRhFjuu',18899996666,'srb.456@163.com',1,1,1,0,1,'2020-09-08 23:56:08','2020-09-08 23:56:08');


DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_code` varchar(50) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `sort` int NOT NULL DEFAULT '0', 
  `operator_id` bigint NOT NULL,
  `is_del` int NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `t_role` VALUES (1,'ADMIN','ADMIN',1,0,'2020-09-06 10:01:18','2020-09-06 10:03:06');

DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_code` varchar(20) DEFAULT NULL,
  `permission_name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `permission_type` int NOT NULL DEFAULT '0',
  `sort` int NOT NULL DEFAULT '0',
  `level` int NOT NULL DEFAULT '0',
  `pid` int NOT NULL,
  `operator_id` bigint NOT NULL,
  `is_del` int NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

INSERT INTO `t_permission` VALUES (1,'sys:user:view','用户查看','',0,0,0,0,1,0,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(2,'sys:user:add','用户添加','',0,0,0,0,1,0,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(3,'sys:user:edit','用户编辑','',0,0,0,0,1,0,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(4,'sys:user:delete','用户删除','',0,0,0,0,1,0,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(5,'sys:role:view','角色查看','',0,0,0,0,1,0,'2020-09-07 15:31:01','2020-09-07 15:31:01'),(6,'sys:role:edit','角色编辑',NULL,1,1,1,1,0,0,'2020-09-09 00:18:29','2020-09-09 00:18:29');

DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `t_user_role` VALUES (1,1,1);

DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `t_role_permission` VALUES (1,1,1);

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `jobId` varchar(25) NOT NULL COMMENT '工号',
  `cardId` varchar(18) NOT NULL COMMENT '身份证号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `sex` int(11) NOT NULL COMMENT '性别',
  `part` int(11) NOT NULL COMMENT '部门',
  `ggroup` int(11) NOT NULL COMMENT '分组',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `addr` varchar(20) DEFAULT NULL COMMENT '地址',
  `kind` int(11) NOT NULL COMMENT '用户类型',
  `joinTime` datetime NOT NULL,
  `password` varchar(32) NOT NULL COMMENT '密码',
  `status` int(11) NOT NULL COMMENT '用户状态',
  `errorTimes` int(11) NOT NULL,
  `post` varchar(255) DEFAULT NULL COMMENT '职务',
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_info` VALUES ('0000', '0000', '系统', '0', '1', '1', null, null, null, '0', '2000-01-01 00:00:00', '0', '2', '0', null);
INSERT INTO `user_info` VALUES ('1000', '211103', '初始管理员', '0', '1', '2', null, null, null, '0', '2000-01-01 00:00:00', '628fee503e90dc1d6c257a4ab6378cac', '1', '0', null);


DROP TABLE IF EXISTS `user_frozen`;
CREATE TABLE `user_frozen` (
  `jobId` varchar(255) NOT NULL COMMENT '冻结用户的jobId',
  `recoveryDateLong` bigint(20) NOT NULL COMMENT '解冻时间',
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `part`;
CREATE TABLE `part` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `createPerson` varchar(20) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of part
-- ----------------------------
INSERT INTO `part` VALUES ('1', 'part1', 'part1', '2017-10-16 09:37:13');
INSERT INTO `part` VALUES ('2', '初始部门', '初始化', '2017-10-16 09:37:13');

DROP TABLE IF EXISTS `user_kind`;
CREATE TABLE `user_kind` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(20) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_kind
-- ----------------------------
INSERT INTO `user_kind` VALUES ('0', '网站管理员');
INSERT INTO `user_kind` VALUES ('1', '部门管理员');
INSERT INTO `user_kind` VALUES ('2', '小组管理员');
INSERT INTO `user_kind` VALUES ('3', '成员');

DROP TABLE IF EXISTS `ggroup`;
CREATE TABLE `ggroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分组id',
  `partId` int(11) NOT NULL COMMENT '部门id',
  `name` varchar(20) NOT NULL COMMENT '分组名称',
  `createPerson` varchar(20) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `partId` (`partId`),
  CONSTRAINT `ggroup_ibfk_1` FOREIGN KEY (`partId`) REFERENCES `part` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

INSERT INTO `ggroup` VALUES ('1', '1', '暂无小组', '系统', '2017-10-16 09:38:19');
INSERT INTO `ggroup` VALUES ('2', '1', '初始小组', '初始化', '2017-10-20 22:42:21');

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `title` varchar(25) NOT NULL COMMENT '标题',
  `kind` int(11) NOT NULL COMMENT '分类',
  `sendPerson` varchar(25) NOT NULL COMMENT '发送人',
  `sendDate` datetime NOT NULL COMMENT '发送时间',
  `content` text NOT NULL COMMENT '内容',
  `acceptPerson` varchar(25) DEFAULT NULL COMMENT '接受者',
  `acceptPart` int(11) DEFAULT NULL COMMENT '接受部门',
  `acceptGroup` int(11) DEFAULT NULL COMMENT '接受分组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `message_kind`;
CREATE TABLE `message_kind` (
  `id` int(11) NOT NULL COMMENT '类型ID',
  `name` varchar(25) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `message_kind` VALUES ('1', '系统消息');
INSERT INTO `message_kind` VALUES ('2', '公司公告');
INSERT INTO `message_kind` VALUES ('3', '公司消息');
INSERT INTO `message_kind` VALUES ('4', '部门公告');
INSERT INTO `message_kind` VALUES ('5', '部门消息');
INSERT INTO `message_kind` VALUES ('6', '小组消息');
INSERT INTO `message_kind` VALUES ('7', '小组公告');
INSERT INTO `message_kind` VALUES ('8', '个人消息');

DROP TABLE IF EXISTS `model_option`;
CREATE TABLE `model_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `modelId` int(11) NOT NULL COMMENT '流程模板ID',
  `name` varchar(25) NOT NULL COMMENT '填选项的名称',
  `oorder` int(11) NOT NULL COMMENT '填选项的顺序，从1开始',
  `must` int(11) NOT NULL COMMENT '是否必填',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `model_procedure`;
CREATE TABLE `model_procedure` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) NOT NULL COMMENT '流程名',
  `introduction` text NOT NULL COMMENT '简介',
  `illustrate` text NOT NULL COMMENT '说明',
  `remark` text COMMENT '备注',
  `title` varchar(25) NOT NULL COMMENT '表格题头',
  `projectName` varchar(25) NOT NULL COMMENT '项目名称',
  `enclosure` int(11) NOT NULL,
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `createPerson` varchar(20) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `model_shen`;
CREATE TABLE `model_shen` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `modelId` int(11) NOT NULL COMMENT '流程模板ID',
  `oorder` int(11) NOT NULL COMMENT '过程序号',
  `name` varchar(25) NOT NULL COMMENT '过程标题',
  `part` int(11) NOT NULL COMMENT '审批部门',
  `ggroup` int(11) NOT NULL COMMENT '审批分组',
  `person` varchar(25) NOT NULL COMMENT '审批人',
  PRIMARY KEY (`id`,`modelId`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `procedure_option`;
CREATE TABLE `procedure_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '小项ID',
  `procedureId` int(11) NOT NULL COMMENT '流程编号',
  `title` varchar(25) NOT NULL COMMENT '小项名称',
  `content` varchar(25) DEFAULT NULL COMMENT '小项内容',
  `oorder` int(11) NOT NULL COMMENT '小项顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `procedure_shen`;
CREATE TABLE `procedure_shen` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流程审批ID',
  `procedureId` int(11) NOT NULL COMMENT '流程编号',
  `name` varchar(255) NOT NULL,
  `userGroup` int(11) NOT NULL COMMENT '审批者所在小组',
  `userPart` int(11) NOT NULL COMMENT '审批者所在部门',
  `userJobId` varchar(25) NOT NULL COMMENT '审批者ID',
  `userName` varchar(25) NOT NULL COMMENT '审批者姓名',
  `content` varchar(25) DEFAULT NULL COMMENT '审批内容',
  `time` datetime DEFAULT NULL COMMENT '审批时间',
  `oorder` int(11) NOT NULL COMMENT '审批顺序',
  `pass` int(11) DEFAULT NULL COMMENT '是否通过',
  `work` int(11) NOT NULL COMMENT '是否已经审批了',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `procedure_submit`;
CREATE TABLE `procedure_submit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流程编号/ID',
  `name` varchar(25) NOT NULL COMMENT '流程名称',
  `projectName` varchar(25) NOT NULL COMMENT '项目名称',
  `projectNameTitle` varchar(25) NOT NULL COMMENT '项目名称标题',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `createPerson` varchar(25) NOT NULL COMMENT '创建人ID',
  `status` int(11) NOT NULL COMMENT '状态',
  `partName` varchar(25) NOT NULL COMMENT '创建人现所属部门',
  `groupName` varchar(25) NOT NULL COMMENT '创建人现所属小组',
  `showFileName` varchar(255) DEFAULT NULL COMMENT '上传的文件名',
  `fileName` varchar(255) DEFAULT NULL COMMENT '服务器上的文件名',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `remind`;
CREATE TABLE `remind` (
  `id` int(11) NOT NULL COMMENT '消息id/流程id',
  `remindId` varchar(20) NOT NULL COMMENT '接受者id',
  `isMsg` int(11) NOT NULL COMMENT '是不是消息',
  PRIMARY KEY (`id`,`remindId`,`isMsg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `file_depot`;
CREATE TABLE `file_depot` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `showFileName` varchar(255) NOT NULL COMMENT '上传的文件名',
  `realFileName` varchar(255) NOT NULL COMMENT '服务器中文件名',
  `updateDate` datetime NOT NULL COMMENT '修改日期',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `source` int(11) NOT NULL COMMENT '文件分组大类',
  `ggroup` int(11) DEFAULT NULL COMMENT '具体小组',
  `part` int(11) DEFAULT NULL COMMENT '具体部门',
  `createPerson` varchar(25) NOT NULL COMMENT '文件上传者工号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_code` varchar(20) NOT NULL COMMENT '部门编号',
  `dept_name` varchar(20) NOT NULL COMMENT '部门名称',
  `pid` int NOT NULL DEFAULT '0',
  `sort` int NOT NULL DEFAULT '0',  
  `is_del` int NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `title` varchar(25) NOT NULL COMMENT '标题',
  `message_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型',
  `send_by` int(11) NOT NULL DEFAULT '0' COMMENT '发送人',
  `send_status` tinyint(3) DEFAULT '0' COMMENT '发送状态',
  `send_date` datetime COMMENT '发送时间',
  `content` text NOT NULL COMMENT '内容',
  `accept_by` tinyint(3) DEFAULT '0' COMMENT '接收状态',
  `accept_status` tinyint(3) DEFAULT '0' COMMENT '接收者',
  `accept_date` datetime DEFAULT NULL COMMENT '接收时间',
  `accept_dept_id` int(11) DEFAULT NULL COMMENT '接收部门',
  `accept_group_id` int(11) DEFAULT NULL COMMENT '接收分组',
  `is_del` tinyint(3) NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_by` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `file_name` varchar(255) NOT NULL COMMENT '上传的文件名',
  `real_file_name` varchar(255) NOT NULL COMMENT '服务器中文件名',
  `file_size` int(11) NOT NULL COMMENT '文件大小',
  `source` int(11) NOT NULL COMMENT '文件分组大类',
  `dept_id` int(11) DEFAULT 0 COMMENT '具体部门',
  `is_del` tinyint(3) NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分组id',
  `group_name` varchar(50) NOT NULL COMMENT '分组名称',
  `is_del` int NOT NULL DEFAULT '0' COMMENT '1 已删除',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_procedure_model`;
CREATE TABLE `t_procedure_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `procedure_name` varchar(50) NOT NULL COMMENT '流程名',
  `introduction` text NOT NULL COMMENT '简介',
  `illustrate` text NOT NULL COMMENT '说明',
  `remark` text COMMENT '备注',
  `project_title` varchar(50) NOT NULL COMMENT '项目标题',
  `project_name` varchar(50) NOT NULL COMMENT '项目名称',
  `enclosure` int(11) NOT NULL,
  `is_del` int NOT NULL DEFAULT '0' COMMENT '1 已删除',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_procedure_model_option`;
CREATE TABLE `t_procedure_model_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `model_id` int(11) NOT NULL COMMENT '流程模板ID',
  `option_name` varchar(25) NOT NULL COMMENT '任务名称',
  `sort` int(11) NOT NULL COMMENT '序号',
  `is_mandatory` int NOT NULL COMMENT '是否必填',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_procedure_model_task`;
CREATE TABLE `t_procedure_model_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `model_id` int(11) NOT NULL COMMENT '流程模板ID',
  `sort` int(11) NOT NULL COMMENT '过程序号',
  `task_name` varchar(25) NOT NULL COMMENT '过程标题',
  `dept_id` int(11) NOT NULL COMMENT '审批部门',
  `user_group_id` int(11) NOT NULL COMMENT '审批用户组',
  `operator_id` varchar(25) NOT NULL COMMENT '审批人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_procedure_option`;
CREATE TABLE `t_procedure_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `procedure_id` int(11) NOT NULL COMMENT '流程ID',
  `option_name` varchar(25) NOT NULL COMMENT '选项名称',
  `option_content` varchar(25) DEFAULT NULL COMMENT '选项内容',
  `sort` int(11) NOT NULL COMMENT '选项顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_procedure_process`;
CREATE TABLE `t_procedure_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流程节点ID',
  `procedure_id` int(11) NOT NULL COMMENT '流程ID',
  `process_name` varchar(255) NOT NULL COMMENT '流程节点名称',
  `user_group_id` int(11) NOT NULL COMMENT '审批者所在小组',
  `dept_id` int(11) NOT NULL COMMENT '审批者所在部门',
  `approve_id` int(11) NOT NULL COMMENT '审批者ID',
  `approve_name` varchar(25) NOT NULL COMMENT '审批者姓名',
  `content` varchar(25) DEFAULT NULL COMMENT '审批内容',
  `approve_date` datetime DEFAULT NULL COMMENT '审批时间',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '审批顺序',
  `is_pass` int DEFAULT 0 COMMENT '是否通过',
  `work` int(11) NOT NULL DEFAULT 0 COMMENT '是否已经审批了',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_procedure`;
CREATE TABLE `t_procedure` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流程编号/ID',
  `procedure_name` varchar(25) NOT NULL COMMENT '流程名称',
  `project_name` varchar(25) NOT NULL COMMENT '项目名称',
  `project_title` varchar(25) NOT NULL COMMENT '项目名称标题',
  `status` int(11) NOT NULL COMMENT '状态',
  `dept_id` int(11) NOT NULL COMMENT '所属部门',
  `user_group_id` int(11) NOT NULL COMMENT '所属用户组',
  `file_name` varchar(255) DEFAULT '' COMMENT '上传的文件名',
  `real_file_name` varchar(255) DEFAULT '' COMMENT '服务器上的文件名',
  `remark` text COMMENT '备注',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
