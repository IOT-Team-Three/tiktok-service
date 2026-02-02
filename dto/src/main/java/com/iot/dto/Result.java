package com.iot.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private int code;
    private T data;
    private String msg;
    
    public Result() {
    }
    
    public Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Result<T> success(T data) { return new Result<T>(200, data, "操作成功");}
    public static <T> Result<T> success(T data, String msg) {return new Result<T>(200,data,msg);}
    public static <T> Result<T> success() {return success(null);}

    public static <T> Result<T> error(int code, String msg) {return new Result<T>(code,null,msg);}
    public static <T> Result<T> error(int code, String msg, T data) {return new Result<T>(code,data,msg);}
    public static <T> Result<T> error(String msg) {return error(500,msg);}
    public static <T> Result<T> error() {return error(500,"操作失败");}
}
