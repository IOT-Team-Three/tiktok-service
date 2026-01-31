package iot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class Result<T> implements Serializable {
    private int code;
    private T data;
    private String msg;

    public static <T> Result<T> success(T data) { return new Result<>(200, null, "操作成功");}
    public static <T> Result<T> success(T data, String msg) {return new Result<>(200,data,msg);}
    public static <T> Result<T> success() {return success(null);}

    public static <T> Result<T> error(int code, String msg) {return new Result<>(code,null,msg);}
    public static <T> Result<T> error(int code, String msg, T data) {return new Result<>(code,data,msg);}
    public static <T> Result<T> error(String msg) {return error(500,msg);}
    public static <T> Result<T> error() {return error(500,null);}
}
