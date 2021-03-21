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
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

insert into t_supplier(id, supplier_code,supplier_name,pic_url,status,sort,create_by) values(999, 'SUP0000001','BINS.COM','www.baidu.com',1,1,1);

DROP TABLE IF EXISTS `t_brand`;
CREATE TABLE `t_brand` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `brand_name` varchar(50) NOT NULL COMMENT '品牌名称',
  `brand_desc` varchar(100) NOT NULL COMMENT '品牌描述',
  `pic_url` varchar(20) NOT NULL COMMENT '图片',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `sort` bigint NOT NULL COMMENT '排序',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint NOT NULL COMMENT '创建人',
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

insert into t_goods (id, goods_code, goods_name, brand_id, category_id, goods_brief, goods_price,pic_url,keywords,is_on_sale, create_by) values(1000, 'GN000001','JAVA', 1000,1000,'JAVA book', 100,
'www.baidu.com','java',1,1);