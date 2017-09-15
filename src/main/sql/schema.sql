-- 数据库初始化脚本

-- 创建数据库
CREATE DataBase seckill;
-- 使用数据库
USE seckill;
--创建秒杀库存表
create table seckill(
`seckill_id` bigint Not null auto_increment Comment '商品库存id',
`name` VARCHAR(120) not null Comment '商品名称',
`number` int not null comment '库存数量',
`start_time` TIMESTAMP not null comment '秒杀开始时间',
`end_time` TIMESTAMP not null comment '秒杀结束时间',
`create_time` TIMESTAMP not null default CURRENT_TIMESTAMP Comment '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)Engine=innoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

--初始化数据
INSERT into
  seckill (name,number,start_time,end_time)
VALUES
  ('1000元秒杀iphone7p',100,'2017-09-04 00:00:00','2017-09-05 00:00:00'),
  ('500元秒杀ipad',200,'2017-09-04 00:00:00','2017-09-05 00:00:00'),
  ('300元秒杀小米MIX2',300,'2017-09-04 00:00:00','2017-09-05 00:00:00'),
  ('1000元秒杀小米6s',400,'2017-09-04 00:00:00','2017-09-05 00:00:00');

--秒杀成功明细表
--用户登陆认证相关的信息
create table success_killed (
`seckill_id` bigint not null comment '秒杀商品id',
`user_phone` bigint not null comment '用户手机号',
`state` tinyint not null DEFAULT -1 comment '状态标识：-1：无效 0：成功 1：已付款 2：已发货 3:已收货',
`create_time` TIMESTAMP not null comment '创建时间',
PRIMARY KEY(seckill_id,user_phone), /*联合主键：防止用户重复秒杀*/
KEY idx_create_time(create_time)
)Engine=innoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

--连接数据库控制台
mysql -uroot -p123456

--以下都是打比方
--手写DDL的原因
--记录每次上线的DDL修改
--上线V1.1
ALTER TABLE  seckill
DROP INDEX idx_create_time,
ADD INDEX idx_c_s(start_time,create_time);

--上线V1.2
--DDL
