package com.iot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comment")
public class Comment {

    //评论id
    @TableId(type = IdType.AUTO)
    private Long id;

    //视频号
    private Long videoId;

    //评论者id
    private Long userId;

    //评论回复id
    @TableField(fill = FieldFill.INSERT)
    private Long replyId;

    //评论内容
    private String content;

    //评论时间
    private Date commentTime;




}
