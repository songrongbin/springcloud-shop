DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_code` varchar(20) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `sort` int NOT NULL DEFAULT '0',
  `is_del` int NOT NULL DEFAULT '0' COMMENT '1 已删除',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_by` bigint NOT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `t_role` VALUES (1,'ADMIN','ADMIN',1,0,1,1,'2020-09-06 10:01:18','2020-09-06 10:03:06');

DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_code` varchar(20) DEFAULT NULL,
  `permission_name` varchar(100) NOT NULL,
  `url` varchar(50) DEFAULT NULL,
  `permission_type` int NOT NULL DEFAULT '0',
  `level` int NOT NULL DEFAULT '0',
  `pid` int NOT NULL,
  `sort` int NOT NULL DEFAULT '0',
  `is_del` int NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_by` bigint NOT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

INSERT INTO `t_permission` VALUES (1,'sys:user:view','用户查看','',0,0,0,0,1,0,1,1,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(2,'sys:user:add','用户添加','',0,0,0,0,1,0,1,1,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(3,'sys:user:edit','用户编辑','',0,0,0,0,1,0,1,1,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(4,'sys:user:delete','用户删除','',0,0,0,0,1,0,1,1,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(5,'sys:role:view','角色查看','',0,0,0,0,1,0,1,1,'2020-09-07 15:31:01','2020-09-07 15:31:01'),(6,'sys:role:edit','角色编辑',NULL,1,1,1,1,0,0,1,1,'2020-09-09 00:18:29','2020-09-09 00:18:29');

DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_code` varchar(20) NOT NULL COMMENT '部门编号',
  `dept_name` varchar(20) NOT NULL COMMENT '部门名称',
  `pid` int NOT NULL DEFAULT '0',
  `sort` int NOT NULL DEFAULT '0',
  `is_del` int NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_by` bigint NOT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `t_dept` VALUES (1,'admin','admin11',0,2,0,1,1,'2021-03-09 00:26:38','2021-03-25 00:00:11'),(2,'sale','销售部1',1,3,1,1,1,'2021-03-24 23:37:00','2021-03-24 23:59:02'),(3,'deployment','开发部',1,10,0,1,1,'2021-03-25 00:01:15','2021-03-25 00:01:15');

DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分组id',
  `group_code` varchar(20) NOT NULL COMMENT '分组编码',
  `group_name` varchar(50) NOT NULL COMMENT '分组名称',
  `sort` int NOT NULL DEFAULT '0',
  `is_del` int NOT NULL DEFAULT '0' COMMENT '1 已删除',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_by` bigint NOT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_code` varchar(20) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL DEFAULT '0',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `status` tinyint NOT NULL COMMENT '状态',
  `dept_id` int NOT NULL COMMENT '所属部门',
  `user_group_id` int NOT NULL COMMENT '所属用户组',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_by` bigint NOT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

INSERT INTO `t_user` VALUES (1,'admin','admin','$2a$10$JHz4wZFnISYHtF4Z2ksIVORtbFNcXhhCKlEgNpEyuAY5d2RjKn2m.','18899998888','srb.123@163.com',1,1,1,0,1,1,'2020-09-08 23:55:13','2020-09-08 23:55:13'),(2,'stocker','stocker','$2a$10$x9ZurZSjjBG5KR.h9BVTPuTN8.8eiPMnfbBSqJt3PNwNuagRhFjuu','18899996666','srb.456@163.com',1,1,1,0,1,1,'2020-09-08 23:56:08','2020-09-08 23:56:08');

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

DROP TABLE IF EXISTS `t_user_address`;
CREATE TABLE `t_user_address` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '地址名称',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `province_id` bigint NOT NULL COMMENT '省id',
  `city_id` bigint NOT NULL COMMENT '城市id',
  `area_id` bigint NOT NULL COMMENT '区域id',
  `address` varchar(255) NOT NULL COMMENT '详细地址',
  `mobile` varchar(20) NOT NULL COMMENT '电话',
  `is_default` tinyint(1) NOT NULL COMMENT '是否默认',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8; 

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_code` varchar(20) NOT NULL COMMENT '订单编码',
  `order_amount` int NOT NULL COMMENT '订单金额',
  `coupon_amount` int NOT NULL COMMENT '优惠券金额',
  `discount_amount` int NOT NULL COMMENT '折扣金额',
  `shopping_amount` int NOT NULL COMMENT '运费金额',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `supplier_id` bigint NOT NULL COMMENT '供应商id',
  `pay_date` datetime COMMENT '支付日期',
  `confirm_date` datetime COMMENT '确认日期',
  `delivery_date` datetime COMMENT '确认日期',
  `pic_url` varchar(50) COMMENT '图片',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_by` bigint NOT NULL DEFAULT 0 COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

INSERT INTO `t_order` VALUES (1000,'SN10000',100,10,10,5,1,999,NULL,NULL,NULL,'www.baidu.com',0,0,1,'2021-03-21 04:09:14','2021-03-21 04:09:14');
insert into t_order (order_code, order_amount,coupon_amount,discount_amount, shopping_amount, user_id, supplier_id, pic_url, create_by) values('SN10000',100, 10, 10, 5, 1, 999,'www.baidu.com', 1);

DROP TABLE IF EXISTS `t_order_goods`;
CREATE TABLE `t_order_goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `order_code` varchar(20) NOT NULL COMMENT '订单编码',
  `goods_id` bigint NOT NULL COMMENT '商品id',
  `goods_code` varchar(20) NOT NULL COMMENT '商品编码',
  `goods_name` varchar(100) NOT NULL COMMENT '商品名称',
  `goods_price` int NOT NULL DEFAULT 0 COMMENT '商品价格',
  `goods_number` int NOT NULL DEFAULT 0 COMMENT '商品数量',
  `goods_amount` int NOT NULL DEFAULT 0 COMMENT '商品价格',
  `coupon_amount` int NOT NULL DEFAULT 0 COMMENT '优惠券金额',
  `discount_amount` int NOT NULL DEFAULT 0 COMMENT '折扣金额',
  `shopping_amount` int NOT NULL DEFAULT 0 COMMENT '运费金额',
  `pic_url` varchar(50) NOT NULL COMMENT '商品图片',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_by` bigint NOT NULL DEFAULT 0 COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

INSERT INTO `t_order_goods` VALUES (1000,1000,'SN10000',1000,'GN000001','java',100,1,100,10,5,1,'www.baidu.com',1,0,1,'2021-03-21 07:53:59','2021-03-21 07:53:59');
insert into t_order_goods (order_id, order_code, goods_id, goods_code, goods_name, goods_price, goods_number, goods_amount,coupon_amount,discount_amount, shopping_amount, pic_url, status, create_by) 
values(1000, 'SN10000',1000, 'GN000001', 'java', 100, 1, 100, 10, 5, 1, 'www.baidu.com', 1,1);

DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `supplier_code` varchar(20) NOT NULL COMMENT '供应商编码',
  `supplier_name` varchar(50) NOT NULL COMMENT '供应商名称',
  `pic_url` varchar(50) NOT NULL COMMENT '图片',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `sort` int NOT NULL COMMENT '排序',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_by` bigint NOT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

--INSERT INTO `t_supplier` VALUES (999,'SUP0000001','BINS.COM','www.baidu.com',1,1,0,1,1,'2021-03-21 04:08:57','2021-03-21 04:08:57');
insert into t_supplier(id, supplier_code,supplier_name,pic_url,status,sort,create_by,update_by) values(999, 'SUP0000001','BINS.COM','www.baidu.com',1,1,1,1);

DROP TABLE IF EXISTS `t_brand`;
CREATE TABLE `t_brand` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `brand_name` varchar(50) NOT NULL COMMENT '品牌名称',
  `brand_desc` varchar(200) NOT NULL COMMENT '品牌描述',
  `pic_url` varchar(50) NOT NULL COMMENT '图片',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_by` bigint NOT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_name` varchar(50) NOT NULL COMMENT '品类名称',
  `category_desc` varchar(100) NOT NULL COMMENT '品类描述',
  `pid` bigint NOT NULL COMMENT '父id',
  `icon_url` varchar(20) NOT NULL COMMENT '图标',
  `pic_url` varchar(20) NOT NULL COMMENT '图片',
  `level` tinyint(1) NOT NULL COMMENT '层级',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `sort` bigint NOT NULL COMMENT '排序',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8; 
    
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `order_id` bigint NOT NULL DEFAULT 0 COMMENT '订单id',
  `order_goods_id` bigint NOT NULL DEFAULT 0 COMMENT '订单商品id',
  `goods_id` bigint NOT NULL DEFAULT 0 COMMENT '商品id',
  `pic_urls` varchar(255) NOT NULL COMMENT '评论图片',
  `star` tinyint(1) NOT NULL COMMENT '标星',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT ' 0 1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8; 

DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_name` varchar(100) NOT NULL COMMENT '优惠券名称',
  `coupon_desc` varchar(255) NOT NULL COMMENT '优惠券描述',
  `coupon_type` tinyint(3) NOT NULL COMMENT '优惠券类型',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_keyword`;
CREATE TABLE `t_keyword` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `keyword` varchar(100) NOT NULL COMMENT '关键字',
  `url` varchar(100) NOT NULL COMMENT 'url',
  `is_hot` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否热门',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8; 

    
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_code` varchar(20) NOT NULL COMMENT '商品编码',
  `goods_name` varchar(100) NOT NULL COMMENT '商品名称',
  `brand_id` bigint NOT NULL COMMENT '品牌id',
  `category_id` bigint NOT NULL COMMENT '品类id',
  `goods_brief` varchar(255) NOT NULL COMMENT '商品简介',
  `goods_price` int NOT NULL COMMENT '商品价格',
  `pic_url` varchar(20) NOT NULL COMMENT '图片',
  `keywords` varchar(100) NOT NULL COMMENT '关键字',
  `is_on_sale` tinyint(1) NOT NULL COMMENT '是否上架',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8; 

insert into t_goods (id, goods_code, goods_name, brand_id, category_id, goods_brief, goods_price,pic_url,keywords,is_on_sale, create_by) 
values(1000, 'GN000001','JAVA', 1000,1000,'JAVA book', 100, 'www.baidu.com','java',1,1);

