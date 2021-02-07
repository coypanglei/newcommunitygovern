package com.weique.commonres.base.arouter;

import android.os.Parcel;
import android.os.Parcelable;

import com.weique.commonres.constans.ConstantARouter;

import java.io.Serializable;

/**
 * @author GK
 * @description: 路由跳转时  传值对象  用于确定自己从哪里来  自己有什么 来干嘛  等行为
 * @date :2020/7/2 9:54
 */
public class BaseARouterByValueBean<T extends Serializable> implements Parcelable {
    /**
     * code 用于区分行为 默认是直接跳转
     */
    private int code = ConstantARouter.WhatTodoARouterEnum.ACCESS;

    /**
     * 这里是 路由 传递的值
     */
    private T data;

    /**
     * 上一个界面  需要event bus 回调时 用到
     */
    private String priorPage;

    public BaseARouterByValueBean() {
        super();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPriorPage() {
        return priorPage;
    }

    public void setPriorPage(String priorPage) {
        this.priorPage = priorPage;
    }

    protected BaseARouterByValueBean(Parcel in) {
        try {
            code = in.readInt();
            priorPage = in.readString();
            data = (T) in.readSerializable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final Creator<BaseARouterByValueBean> CREATOR = new Creator<BaseARouterByValueBean>() {
        @Override
        public BaseARouterByValueBean createFromParcel(Parcel in) {
            return new BaseARouterByValueBean(in);
        }

        @Override
        public BaseARouterByValueBean[] newArray(int size) {
            return new BaseARouterByValueBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        try {
            dest.writeInt(code);
            dest.writeString(priorPage);
            dest.writeSerializable(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
