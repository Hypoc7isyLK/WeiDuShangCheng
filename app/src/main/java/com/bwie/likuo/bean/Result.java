package com.bwie.likuo.bean;

/**
 * date:2018/12/28
 * author:李阔(淡意衬优柔)
 * function:
 */
public class Result<T> {
    private String message;
    private String status;
    private T result;
    private T orderList;

    public Result(String message, String status, T result, T orderList) {
        this.message = message;
        this.status = status;
        this.result = result;
        this.orderList = orderList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T getOrderList() {
        return orderList;
    }

    public void setOrderList(T orderList) {
        this.orderList = orderList;
    }
}
