-- 主库 --
create DATABASE store_db;
use store_db;

CREATE TABLE `region` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `region_code` varchar(50) DEFAULT NULL COMMENT '地理区域编码',
  `region_name` varchar(100) DEFAULT NULL COMMENT '地理区域名称',
  `level` tinyint(1) DEFAULT NULL COMMENT '级别（省，区，县）',
  `parent_region_code` varchar(50) DEFAULT NULL COMMENT '上级的区域编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `store_db`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (1, '110-000', '北京', 0, NULL);
INSERT INTO `store_db`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (2, '410-000', '河南', 0, NULL);
INSERT INTO `store_db`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (3, '110-100', '北京市', 1, NULL);
INSERT INTO `store_db`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (4, '410-100', '郑州市', 2, NULL);

CREATE TABLE `store_info` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `store_name` varchar(100) DEFAULT NULL COMMENT '店铺名称',
  `reputation` tinyint(1) DEFAULT NULL COMMENT '信誉',
  `region_code` varchar(50) DEFAULT NULL COMMENT '店铺所在地',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `store_db`.`store_info`(`id`, `store_name`, `reputation`, `region_code`) VALUES (1, '大融城商场', 4, '110-100');
INSERT INTO `store_db`.`store_info`(`id`, `store_name`, `reputation`, `region_code`) VALUES (2, '宝乐汇商场', 5, '410-100');


create DATABASE product_db_1;
use product_db_1;

CREATE TABLE `region` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `region_code` varchar(50) DEFAULT NULL COMMENT '地理区域编码',
  `region_name` varchar(100) DEFAULT NULL COMMENT '地理区域名称',
  `level` tinyint(1) DEFAULT NULL COMMENT '级别（省，区，县）',
  `parent_region_code` varchar(50) DEFAULT NULL COMMENT '上级的区域编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `product_db_1`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (1, '110-000', '北京', 0, NULL);
INSERT INTO `product_db_1`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (2, '410-000', '河南', 0, NULL);
INSERT INTO `product_db_1`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (3, '110-100', '北京市', 1, NULL);
INSERT INTO `product_db_1`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (4, '410-100', '郑州市', 2, NULL);


CREATE TABLE `product_info_1` (
  `product_info_id` bigint(20) NOT NULL COMMENT 'id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `spec` varchar(50) DEFAULT NULL COMMENT '规格',
  `region_code` varchar(50) DEFAULT NULL COMMENT '区域码',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `image_url` varchar(100) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`product_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product_info_2` (
  `product_info_id` bigint(20) NOT NULL COMMENT 'id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `spec` varchar(50) DEFAULT NULL COMMENT '规格',
  `region_code` varchar(50) DEFAULT NULL COMMENT '区域码',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `image_url` varchar(100) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`product_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product_descript_1` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `product_info_id` bigint(20) DEFAULT NULL COMMENT '所属商品ID',
  `description` longtext COMMENT '商品描述',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product_descript_2` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `product_info_id` bigint(20) DEFAULT NULL COMMENT '所属商品ID',
  `description` longtext COMMENT '商品描述',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----
create DATABASE product_db_2;
use product_db_2;

CREATE TABLE `region` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `region_code` varchar(50) DEFAULT NULL COMMENT '地理区域编码',
  `region_name` varchar(100) DEFAULT NULL COMMENT '地理区域名称',
  `level` tinyint(1) DEFAULT NULL COMMENT '级别（省，区，县）',
  `parent_region_code` varchar(50) DEFAULT NULL COMMENT '上级的区域编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `product_db_2`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (1, '110-000', '北京', 0, NULL);
INSERT INTO `product_db_2`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (2, '410-000', '河南', 0, NULL);
INSERT INTO `product_db_2`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (3, '110-100', '北京市', 1, NULL);
INSERT INTO `product_db_2`.`region`(`id`, `region_code`, `region_name`, `level`, `parent_region_code`) VALUES (4, '410-100', '郑州市', 2, NULL);

CREATE TABLE `product_info_1` (
  `product_info_id` bigint(20) NOT NULL COMMENT 'id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `spec` varchar(50) DEFAULT NULL COMMENT '规格',
  `region_code` varchar(50) DEFAULT NULL COMMENT '区域码',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `image_url` varchar(100) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`product_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product_info_2` (
  `product_info_id` bigint(20) NOT NULL COMMENT 'id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `spec` varchar(50) DEFAULT NULL COMMENT '规格',
  `region_code` varchar(50) DEFAULT NULL COMMENT '区域码',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `image_url` varchar(100) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`product_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product_descript_1` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `product_info_id` bigint(20) DEFAULT NULL COMMENT '所属商品ID',
  `description` longtext COMMENT '商品描述',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product_descript_2` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `product_info_id` bigint(20) DEFAULT NULL COMMENT '所属商品ID',
  `description` longtext COMMENT '商品描述',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;