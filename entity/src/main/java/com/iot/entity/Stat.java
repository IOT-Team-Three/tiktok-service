package com.iot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("stat")
public class Stat {

    //视频id
    @TableId(type = IdType.ASSIGN_ID)
    private Long video_id;

    //点赞
    private Integer like;

    //播放量
    private Integer view;

    //分享
    private Integer share;

    //评论数
    private Integer comment_count;

    //最后更新时间
    private Date updated;

}
