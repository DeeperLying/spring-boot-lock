$$$
CREATE TABLE IF NOT EXISTS `user_info`
(
    `id`               int unsigned NOT NULL auto_increment COMMENT '主键ID',
    `bbsex`            int NOT NULL DEFAULT 0 COMMENT '测试更新字段是否成功',
    `third_account_id` varchar(128) NOT NULL DEFAULT '' COMMENT '第三方用户ID',
    `user_name`        varchar(64)  NOT NULL DEFAULT '' COMMENT '用户名',
    `password`         varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
    `login_type`       tinyint      NOT NULL DEFAULT '0' COMMENT '登录方式: 0-微信登录，1-账号密码登录',
    `deleted`          tinyint      NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY                `key_third_account_id` (`third_account_id`),
    KEY                `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='用户登录表';
$$$
 SELECT table_name FROM information_schema.TABLES WHERE table_name ='user2TestTable'; $$$

CREATE TABLE IF NOT EXISTS `we_chat_user_info`(
   id INT NOT NULL auto_increment COMMENT '主键',
   openid CHAR(255) NOT NULL UNIQUE COMMENT 'openid用户唯一标识',
   nickname VARCHAR(600) NOT NULL DEFAULT'' COMMENT '用户名字',
   sex INT NOT NULL DEFAULT 0 COMMENT '性别',
   province CHAR(100) COMMENT '省份',
   city CHAR(100) COMMENT '城市',
   country CHAR(100) COMMENT '国家',
   headimgurl VARCHAR(600) COMMENT '头像',
   privilege VARCHAR(600) COMMENT '特权信息',
   unionid CHAR(255) NOT NULL DEFAULT '' COMMENT '公众号id',
   create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`id`),
   KEY `unionid` (`unionid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='用户登录表';$$$

CREATE TABLE IF NOT EXISTS `goods_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_title` varchar(600) NOT NULL COMMENT '商品标题',
  `goods_image` varchar(600) NOT NULL DEFAULT '' COMMENT '商品图片',
  `sex` int(11) NOT NULL DEFAULT '0' COMMENT '性别',
  `size` char(100) DEFAULT NULL COMMENT '大小',
  `goods_desc` char(100) DEFAULT NULL COMMENT '商品详情',
  `sale` int(11) DEFAULT NULL COMMENT '销售',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `goods_title` (`goods_title`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';$$$

CREATE TABLE IF NOT EXISTS `order_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) NOT NULL COMMENT '对应的商品ID',
  `out_trade_no` CHAR(100) NOT NULL COMMENT '订单号',
  `goods_title` varchar(600) NOT NULL COMMENT '商品标题',
  `goods_image` varchar(600) NOT NULL DEFAULT '' COMMENT '商品图片',
  `sex` int(11) NOT NULL DEFAULT '0' COMMENT '性别',
  `size` char(100) DEFAULT NULL COMMENT '大小',
  `goods_desc` char(100) DEFAULT NULL COMMENT '商品详情',
  `sale` int(11) DEFAULT NULL COMMENT '销售',
  `order_status` CHAR(255) NOT NULL DEFAULT 'WAIT_BUYER_PAY' COMMENT '订单状态，与支付宝状态保持一致',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `out_trade_no` (`out_trade_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';$$$

CREATE TABLE IF NOT EXISTS  `user` (
    `id` int(12) NOT NULL AUTO_INCREMENT,
    `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `username` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Email',
    `phone` varchar(20) DEFAULT NULL COMMENT '手机',
    `sex` int(11) NOT NULL DEFAULT '0' COMMENT '性别',
    `role` int(11) NOT NULL DEFAULT '0' COMMENT '角色',
    `headimgurl` varchar(600) DEFAULT NULL COMMENT '头像',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='通过邮箱和手机注册的用户表';$$$