package com.weique.commonres.base.eventbean;

import com.weique.commonres.constans.ConstantEventBus;

/**
 * @author GK
 * @description: 事件回调 通用
 * @date :2020/6/2 13:58
 */
public class CommonEventCallbackBean<T> {
    private int code;
    private String message;
    private T data;

    public CommonEventCallbackBean(T data) {
        this.data = data;
    }

    /**
     * @param code    code
     * @param message message
     * @param data    data
     */
    public CommonEventCallbackBean(@ConstantEventBus.CommonEventCallback int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonEventCallbackBean(@ConstantEventBus.CommonEventCallback int code, T data) {
        this.code = code;
        this.data = data;
    }

    public CommonEventCallbackBean(@ConstantEventBus.CommonEventCallback int code) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
