package com.tiktok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("stat")
public class stat {

    //视频id
    @TableId
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
