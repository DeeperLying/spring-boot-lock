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