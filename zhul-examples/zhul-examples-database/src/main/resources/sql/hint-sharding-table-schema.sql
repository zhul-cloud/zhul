drop schema if exists my_order;
create schema my_order default character set utf8mb4 collate utf8mb4_general_ci;
use
my_order;

-- ----------------------------
-- Table structure for t_order_D023
-- ----------------------------
DROP TABLE IF EXISTS `t_order_D023`;
CREATE TABLE `t_order_D023`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `order_code`     int(11) NOT NULL DEFAULT 0 COMMENT '订单编码',
    `warehouse_code` varchar(12) NOT NULL DEFAULT '' COMMENT '仓库编码',
    `create_time`    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`      varchar(32) NOT NULL DEFAULT 'sys' COMMENT '创建者',
    `update_time`    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`      varchar(32) NOT NULL DEFAULT 'sys' COMMENT '更新者',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT ='订单表';

-- ----------------------------
-- Table structure for t_order_D021
-- ----------------------------
DROP TABLE IF EXISTS `t_order_D021`;
CREATE TABLE `t_order_D021`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `order_code`     int(11) NOT NULL DEFAULT 0 COMMENT '订单编码',
    `warehouse_code` varchar(12) NOT NULL DEFAULT '' COMMENT '仓库编码',
    `create_time`    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`      varchar(32) NOT NULL DEFAULT 'sys' COMMENT '创建者',
    `update_time`    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`      varchar(32) NOT NULL DEFAULT 'sys' COMMENT '更新者',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT ='订单表';