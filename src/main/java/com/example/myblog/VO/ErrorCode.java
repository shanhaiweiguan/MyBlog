package com.example.myblog.VO;

public enum ErrorCode {

    USER_NOT_FIND(501, "用户不存在"),
    PWD_ERROR(502,"密码错误"),
    TOKEN_ERROR(511, "token不合法"),
    NO_PERMISSION(403, "无权限访问"),;

    private int code;
    private String msg;

    ErrorCode(int code, String msg){
        this.code =code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
