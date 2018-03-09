### 秒杀系统的开发

使用spring+springMVC+mybatis完成一个简易的秒杀系统。

#### 基本业务梳理

商家 <====> 库存 <===> 用户

* 对于用户

> 减库存 记录购买明细(事务) -----> 数据落地

功能：
* 秒杀接口暴露
* 执行秒杀
* 相关查询

#### DAO层设计编码
##### 数据库设计
```sql
create database seckill;

use seckill;

-- 秒杀库存表
create table seckill(
`seckill_id` bigint not null auto_increment comment '商品库存id',
`name` varchar(120) not null comment '商品名称',
`number` int not null comment '库存数量',
`start_time` timestamp not null comment '秒杀开启时间',
`end_time` timestamp not null comment '秒杀结束时间',
`create_time` timestamp not null  default CURRENT_TIMESTAMP comment '创建时间' ,
primary key(seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time) 
)engine=InnoDB auto_increment=1000 default charset=utf8 comment='秒杀库存表';

--初始化数据
insert into seckill(`name`, `number`, `start_time`, `end_time`)
values
  ('1000元秒杀iPhone10', 100, '2018-03-08 00:00:00', '2018-03-20 00:00:00'),
  ('1000元秒杀一加5', 100, '2018-03-08 00:00:00', '2018-03-20 00:00:00'),
  ('1000元秒杀macbook', 100, '2018-03-08 00:00:00', '2018-03-20 00:00:00'),
  ('1000元秒杀surface', 100, '2018-03-08 00:00:00', '2018-03-20 00:00:00');


-- 购买明细表
create table seckill_success(
 `seckill_id` bigint not null comment '秒杀商品id',
 `user_phone` bigint not null comment '用户电话',
 `state` tinyint not null default -1 comment '-1 无效 0成功 1 已付款',
 `create_time` timestamp not null DEFAULT  CURRENT_TIMESTAMP,
 primary key(seckill_id, user_phone),
 key idx_create_time(create_time) 
)engine=InnoDB default charset=utf8 comment '秒杀成功明细表';

alter table seckill_success rename to success_killed;
```
数据库 ------ 映射 ------ 对象 mybatis
* xml提供SQL （推荐 方便不需要写在java代码中）
* 注解提供SQL

整合spring 只写接口不写实现

别名org.seckill.entity.*    包扫描

更少的配置 mapper    配置扫描

DAO自动注入spring容器


#### Service层设计

#### Web设计
