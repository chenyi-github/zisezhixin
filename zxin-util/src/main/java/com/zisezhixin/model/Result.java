package com.zisezhixin.model;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class Result {
    private boolean success;
    private String code;
    private String msg;
    private Object data;
    private String para1;
    private String para2;

    public Result() {
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, String msg, boolean success) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public String toString() {
        return String.format("Code: %s, Message: %s", getCode(), getMsg());
    }
}