package com.iot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("stat")
public class Stat {

    //视频id
    @TableId("videoId")
    private Long videoId;

    //点赞
    @TableField("`like`")
    private Integer like;

    //播放量
    private Integer view;

    //分享
    private Integer share;

    //评论数
    @TableField("comment_count")
    private Integer commentCount;

    //最后更新时间
    private Date updated;

    //点赞方法
    public void incrementLike() {
        this.like = (this.like == null ? 1 : this.like + 1);
        this.updated = new Date();
    }

    public void decrementLike() {
        this.like = (this.like == null || this.like <= 0 ? 0 : this.like - 1);
        this.updated = new Date();
    }

}
