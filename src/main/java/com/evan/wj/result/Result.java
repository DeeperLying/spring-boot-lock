package com.evan.wj.result;

public class Result {
    private int code;
    private String errorMsg;
    private Object data;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
