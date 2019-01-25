package com.confucius.common.bean;

public class JsonResult {

    // 响应业务状态
    private Integer errno;

    // 响应消息
    private String errmsg;

    // 响应中的数据
    private Object data;


    public Integer getErrno() {
        return errno;
    }



    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    public static JsonResult build(Integer errno, String errmsg, Object data) {
        return new JsonResult(errno, errmsg, data);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult ok() {
        return new JsonResult(null);
    }


    public JsonResult() {

    }

    public static JsonResult build(Integer errno, String errmsg) {
        return new JsonResult(errno, errmsg, null);
    }

    public JsonResult(Integer errno, String errmsg, Object data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.data = data;
    }

    public JsonResult(Object data) {
        this.errno = 0;
        this.errmsg = "SUCCESS";
        this.data = data;
    }

    public Boolean isOK() {
        return this.errno == 200;
    }
}