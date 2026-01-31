//用户表
create table user.user
(
    username varchar(50)  not null comment '账号',
    userid   bigint       not null comment 'id'
        primary key,
    password varchar(255) not null comment '密码',
    avatar   varchar(255) not null comment '头像地址',
    constraint user_pk
        unique (password)
);

//关注
create table user.follow
(
    follower_id bigint    not null comment '关注者',
    followed_id bigint    not null comment '被关注者id',
    time       timestamp not null comment '关注时间',
    id         bigint    not null
        primary key,
    constraint follow_user_userid_fk
        foreign key (follower_id) references user.user (userid),
    constraint follow_user_userid_fk_2
        foreign key (followed_id) references user.user (userid)
)
    comment '关注表';

create index follow_followed_id_index
    on user.follow (followed_id);

create index follow_follower_id_index
    on user.follow (follower_id);


//视频
create table user.video
(
    user_id   bigint       not null comment '作者id',
    title     varchar(200) not null comment '视频标题',
    video_id  bigint       not null comment '视频id'
        primary key,
    video_url varchar(500) not null comment '视频地址',
    cover_url varchar(500) not null comment '封面图地址',
    duration  int          not null comment '时长',
    status    tinyint      not null comment '状态:1.审核中,2.公开,3.私密,4.下架',
    `create`  timestamp    not null comment '发布时间',
    updated   timestamp    not null comment '更新时间',
    text      varchar(500) null comment '视频文案',
    constraint video_user_userid_fk
        foreign key (user_id) references user.user (userid)
);

//视频互动
create table user.stat
(
    `like`   int       null comment '点赞',
    view     int       null comment '播放量',
    video_id bigint    not null
        primary key,
    share    int       null comment '分享数',
    updated  timestamp null comment '更新时间',
    constraint stat_video_video_id_fk
        foreign key (video_id) references user.video (video_id)
);

//评论
create table user.comment
(
    id            bigint    not null comment '评论id'
        primary key,
    user_id       bigint    not null comment 'ping',
    video_id      bigint    not null,
    content       text      not null comment '评论内容',
    comment_time timestamp not null comment '评论时间',
    reply_id      bigint    not null comment '评论回复的id',
    constraint comment_comment_id_fk
        foreign key (reply_id) references user.comment (id),
    constraint comment_user_userid_fk
        foreign key (user_id) references user.user (userid),
    constraint comment_video_video_id_fk
        foreign key (video_id) references user.video (video_id)
);

create index comment_comment_time_index
    on user.comment (comment_time);



