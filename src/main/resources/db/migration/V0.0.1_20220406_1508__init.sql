create table mr_area
(
    area_id int auto_increment comment '区域ID主键',
    area_name nvarchar(20) not null comment '区域名称',
    area_explication nvarchar(200) null comment '区域说明文字',
    `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '删除标记：Y-已删除，N-未删除',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
    constraint mr_area_pk
        primary key (area_id)
)
    comment '区域表';


create unique index mr_area_area_name_uindex
    on mr_area (area_name);


INSERT INTO mr_area (area_id, area_name, area_explication) VALUES (1, '测试会议室', '测试会议室');

create table mr_config
(
    config_id int auto_increment,
    config_code varchar(20) null comment '配置代码',
    config_value nvarchar(50) null comment '配置值',
    config_remark nvarchar(200) null comment '配置说明',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
    constraint mr_config_pk
        primary key (config_id)
)
    comment '会议系统配置';

INSERT INTO mr_config (config_id, config_code, config_value, config_remark) VALUES (1, 'mettingBeginTime', '08:30', '会议室预约开始时间');
INSERT INTO mr_config (config_id, config_code, config_value, config_remark) VALUES (2, 'mettingEndTime', '18:30', '会议室预约结束时间');
INSERT INTO mr_config (config_id, config_code, config_value, config_remark) VALUES (3, 'advanceDayTime', '3', '最多能提前预约的天数');
INSERT INTO mr_config (config_id, config_code, config_value, config_remark) VALUES (4, 'intervalTime', '30', '间隔时间');



create table mr_reserve
(
    reserve_id int auto_increment comment '预约ID',
    reserve_user nvarchar(30) comment '预约人',
    reserve_user_name nvarchar(50) null comment '预约人名',
    reserve_todo nvarchar(300) null comment '预约会议室要干的事情',
    reserve_area_id int null comment '预约的会议室ID',
    reserve_time datetime comment '预约时间',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
    constraint mr_reserve_pk
        primary key (reserve_id)
)
    comment '预约表';

create table mr_reserve_day_time
(
    t_day_time varchar(10) null comment '预约的日期，如2021-08-26',
    t_timestamp varchar(5) null comment '当前时间点 如 08:30',
    t_reserve_before_id int null comment '当前时间戳前预约的ID',
    t_reserve_after_id int null comment '当前时间戳前预约的ID',
    t_area_id int null comment '所属会议室',
    constraint mr_reserve_day_time_pk
        unique (t_day_time, t_timestamp,t_area_id)
)
    comment '会议预约24小时预约表';



create table mr_user
(
    user_code int auto_increment comment '用户code',
    user_account varchar(30) not null comment '用户登录名',
    user_password varchar(36) null comment '用户加密密码',
    user_status int default 0 null comment '用户状态,默认0启动，1禁用,2删除',
    `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '删除标记：Y-已删除，N-未删除',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
    constraint mr_user_pk
        primary key (user_code)
)
    comment '用户表';

create unique index mr_user_user_account_uindex
    on mr_user (user_account);

INSERT INTO mr_user (user_account, user_password, user_status) VALUES ('root', 'e10adc3949ba59abbe56e057f20f883e', DEFAULT);

create table mr_role
(
    role_code int auto_increment comment '角色编码',
    role_name varchar(30) not null comment '角色名称',
    role_status int null DEFAULT 0 comment '角色状态 默认0启用， 1禁用，2删除',
    `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '删除标记：Y-已删除，N-未删除',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
    constraint mr_role_pk
        primary key (role_code)
)
    comment '角色表';

create unique index mr_role_role_name_uindex
    on mr_role (role_name);


INSERT INTO mr_role (role_code, role_name, role_status) VALUES (1, '管理员', null);


create table mr_auth
(
    auth_code int auto_increment comment '权限代码',
    auth_name varchar(50) not null comment '权限名称',
    auth_remark nvarchar(200) null comment '权限备注',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
    constraint mr_auth_pk
        primary key (auth_code)
);

create unique index mr_auth_auth_name_uindex
    on mr_auth (auth_name);

INSERT INTO mr_auth (auth_code, auth_name, auth_remark) VALUES (1, '后台管理员', '后台管理员，拥有访问后端页面的权限');


create table mr_role_auth
(
    role_code int not null comment '角色ID',
    auth_code int not null comment '权限ID',
    constraint mr_role_auth_pk
        unique (role_code, auth_code)
)
    comment '角色权限关联表';

INSERT INTO mr_role_auth (role_code, auth_code) VALUES (1, 1);


create table mr_role_user
(
    role_code int not null comment '角色code',
    user_code int not null comment '用户code',
    constraint mr_role_user_pk
        unique (role_code, user_code)
)
    comment '角色用户表';

INSERT INTO mr_role_user (role_code, user_code) VALUES (1, 1);


create table mr_user_info
(
    user_code int not null comment '用户编码',
    user_name nvarchar(20) null comment '用户姓名',
    user_email varchar(36) null comment '用户邮箱',
    user_phone varchar(11) null comment '用户手机号 11位',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
    constraint mr_user_info_mr_user_user_code_fk
        foreign key (user_code) references mr_user (user_code)
)
    comment '用户信息扩展表';

create unique index mr_user_info_user_code_uindex
    on mr_user_info (user_code);

INSERT INTO mr_user_info (user_code, user_name, user_email, user_phone,create_time) VALUES (1, '我是管理员', '3214444445@qq.com', '15172227621',now());


