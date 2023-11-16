package com.demo.springboot.common;

import java.io.Serializable;

/**
 * 统一返回接口
 * @param <T>
 */
public class ReturnJson<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;

    private String errorMsg;

    private String fullMsg;

    private T data;

    private String uri;

    public ReturnJson() {
        this.status = 200;
    }

    public ReturnJson(int status, String errorMsg) {
        this.status = status;
        this.errorMsg = errorMsg;
    }

    public ReturnJson(int status, String errorMsg, T data) {
        this.status = status;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public ReturnJson(int status, String errorMsg, T data, String uri) {
        this.status = status;
        this.errorMsg = errorMsg;
        this.data = data;
        this.uri = uri;
    }

    public ReturnJson(int status, String errorMsg, T data, String uri, String fullMsg) {
        this.status = status;
        this.errorMsg = errorMsg;
        this.data = data;
        this.uri = uri;
        this.fullMsg = fullMsg;
    }

    public ReturnJson(T data) {
        this.status = 200;
        this.data = data;
    }

    public static ReturnJson error(String errorMsg) {
        ReturnJson j = new ReturnJson(500, errorMsg);
        return j;
    }

    public static ReturnJson error(String errorMsg, Object data) {
        ReturnJson j = new ReturnJson(500, errorMsg, data);
        return j;
    }

    public static ReturnJson error(String errorMsg, Object data, String uri) {
        ReturnJson j = new ReturnJson(500, errorMsg, data, uri);
        return j;
    }

    public static ReturnJson error(String errorMsg, Object data, String uri, String fullMsg) {
        ReturnJson j = new ReturnJson(500, errorMsg, data, uri, fullMsg);
        return j;
    }

    public static ReturnJson error(int status, String errorMsg) {
        ReturnJson j = new ReturnJson(status, errorMsg);
        return j;
    }

    public static ReturnJson error(int status, String errorMsg, Object data) {
        ReturnJson j = new ReturnJson(status, errorMsg, data);
        return j;
    }

    public static ReturnJson error(int status, String errorMsg, Object data, String uri) {
        ReturnJson j = new ReturnJson(status, errorMsg, data, uri);
        return j;
    }

    public static <T> ReturnJson<T> ok(T data) {
        ReturnJson<T> j = new ReturnJson(200, data);
        return j;
    }


    public static ReturnJson ok() {
        ReturnJson j = new ReturnJson(200, (String) null);
        return j;
    }

    public ReturnJson(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return this.status;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public String getFullMsg() {
        return this.fullMsg;
    }

    public T getData() {
        return this.data;
    }

    public String getUri() {
        return this.uri;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setFullMsg(String fullMsg) {
        this.fullMsg = fullMsg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ReturnJson;
    }



    public ReturnJson(int status, String errorMsg, String fullMsg, T data, String uri) {
        this.status = status;
        this.errorMsg = errorMsg;
        this.fullMsg = fullMsg;
        this.data = data;
        this.uri = uri;
    }
}
