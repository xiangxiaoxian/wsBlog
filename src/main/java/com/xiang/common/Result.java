package com.xiang.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author XR
 * @version 1.0.0
 * @ClassName Result.java
 * @Description 结果集封装类
 * @createTime 2021年01月09日 13:12:00
 */
@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static Result success(int code,String msg,Object data){
        return new Result(code,msg,data);
    }

    public static Result error(int code,String msg){
        return new Result(code,msg,null);
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    public Result() {
    }
}
