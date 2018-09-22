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
DAO层：接口设计 + SQL编写

service接口设计 站在使用者的角度设计接口 参数越简单越好

使用注解控制事务：
* 明确事务方法编程风格
* 保证事务方法执行时间尽可能的短，不要穿插其他的网络操作
* 不是所有的方法需要事务

#### Web设计

什么是restful？一种优雅的URL的表达方式；资源的状态或者状态转移

* get ---> 查询操作
* post --> 添加/修改操作
* put ---> 修改操作 
* delete ---> 删除操作

url设计`/模块/资源/{标识}/集合1` `/user/{uid}/friends`

秒杀API设计：
* GET /seckill/list  秒杀列表
* GET /seckill/{id}/detail 秒杀商品详情
* GET /seckill/time/now  系统时间 
* POST /seckill/{id}/exposer 暴露秒杀地址
* GET /seckill/{id}/{md5}/execution 执行秒杀

#### 秒杀优化
* 将sql语句进行调换，避免SQL执行串行化
* 使用redis对秒杀商品进行缓存，提升秒杀接口暴露的性能