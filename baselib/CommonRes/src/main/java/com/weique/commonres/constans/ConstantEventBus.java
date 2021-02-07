package com.weique.commonres.constans;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author GK
 * @description: event bus 行为 字段
 * @date :2020/6/2 13:34
 */
public interface ConstantEventBus {

    /**
     * 通用事件回调
     */
    @IntDef({CommonEventCallback.UPDATE_ALL,
            CommonEventCallback.UPDATE_ITEM,
            CommonEventCallback.ADD_ONE_ITEM,
            CommonEventCallback.DELETE_ITEM,
            CommonEventCallback.DELETE_ALL,
            CommonEventCallback.SKIP_PAGE,
            CommonEventCallback.LOG_OUT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CommonEventCallback {
        /**
         * 更新整个list
         */
        int UPDATE_ALL = 0;
        /**
         * 更新某个item
         */
        int UPDATE_ITEM = 1;
        /**
         * 新增了一个item
         */
        int ADD_ONE_ITEM = 2;
        /**
         * 删除item
         */
        int DELETE_ITEM = 3;
        /**
         * 删除所有
         */
        int DELETE_ALL = 4;
        /**
         * 跳转界面
         */
        int SKIP_PAGE = 5;
        /**
         * 退出登录
         */
        int LOG_OUT = 6;
    }

    /**
     * 其它事件回调 添加枚举时 请标明清楚应用的地方 和 作用
     */
    @IntDef({})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OtherEventCallback {
    }
}
