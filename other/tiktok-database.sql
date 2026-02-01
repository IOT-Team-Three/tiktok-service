create table user
(
    `user-id`            int auto_increment comment '用户id'
        primary key,
    `user-name`          varchar(15)                                 not null comment '用户名',
    `user-password`      varchar(50) default (md5(_utf8mb4'123456')) not null comment '用户密码',
    `user-usability`     tinyint(1)  default 1                       not null comment '用户是否可用',
    `user-time-register` datetime    default CURRENT_TIMESTAMP       null comment '用户创建时间'
)
    comment '用户库';

create definer = root@localhost trigger set_user_name_default
    before insert
    on user
    for each row
begin
BEGIN
    IF NEW.`user-name` IS NULL THEN
        SET NEW.`user-name` = CONCAT(
                '用户',
                LEFT(
                        REPLACE(FORMAT(UNIX_TIMESTAMP(NOW(6)) * 1000000,0),',',''),
                         10
                )
                              );
END IF;
END;


