package com.tiktok.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("follow")
public class follow {

    private Long follwed_id;
    private Long follower_id;

    //时间,插入时自动设置当前时间
    @TableField(fill = FieldFill.INSERT)
    private Date time;

    //主键,自增
    @TableId(type = IdType.AUTO)
    private String id;

}
