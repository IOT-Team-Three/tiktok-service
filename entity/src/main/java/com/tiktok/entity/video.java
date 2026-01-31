package com.tiktok.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("video")
public class video {
    //视频号
    private Long video_id;

    //标题
    private String tittle;

    //文案
    private String text;

    //作者id
    private Long user_id;

    //视频地址
    private String video_url;

    //封面地址
    private String cover_url;

    //视频状态
    private  Integer status;

    //上传时间
    @TableField(fill = FieldFill.INSERT)
    private Date create;

    //最后更新时间
    @TableField(fill = FieldFill.INSERT)
    private Date updated;

    //时长
    private Integer duration;


}
