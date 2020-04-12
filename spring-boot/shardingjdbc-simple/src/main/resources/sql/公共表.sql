-- create DATABASE user_db;

use user_db;
CREATE TABLE `t_dict` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `type` varchar(50) DEFAULT NULL COMMENT '字典类型',
  `code` varchar(50) DEFAULT NULL COMMENT '字典码',
  `value` varchar(50) DEFAULT NULL COMMENT '字典值',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- show create table t_dict;

use order_db_1;

CREATE TABLE `t_dict` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `type` varchar(50) DEFAULT NULL COMMENT '字典类型',
  `code` varchar(50) DEFAULT NULL COMMENT '字典码',
  `value` varchar(50) DEFAULT NULL COMMENT '字典值',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

use order_db_2;

CREATE TABLE `t_dict` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `type` varchar(50) DEFAULT NULL COMMENT '字典类型',
  `code` varchar(50) DEFAULT NULL COMMENT '字典码',
  `value` varchar(50) DEFAULT NULL COMMENT '字典值',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;