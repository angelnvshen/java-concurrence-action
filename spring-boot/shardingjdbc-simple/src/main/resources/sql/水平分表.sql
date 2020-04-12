create DATABASE order_db;
use order_db;

CREATE TABLE `t_order_1` (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `status` tinyint(2) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_order_2` (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `status` tinyint(2) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;