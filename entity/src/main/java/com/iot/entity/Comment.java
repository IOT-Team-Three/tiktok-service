package com.iot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comment")

public class Comment {

    //评论id
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    //视频号
    private Long video_id;

    //评论者id
    private Long user_id;

    //评论回复id
    @TableField(fill = FieldFill.INSERT)
    private Long reply_id;

    //评论内容
    private String content;

    //评论时间
    private Date comment_time;




}
