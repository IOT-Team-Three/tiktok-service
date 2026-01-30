package com.tiktok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class user {
    //id
    @TableId(type = IdType.ASSIGN_ID)
    private Long userid;

    //账号名
    private String username;

    //密码
    private String password;

    //头像地址
    private String avatar;
}
