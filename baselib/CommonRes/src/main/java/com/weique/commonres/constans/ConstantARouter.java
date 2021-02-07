package com.weique.commonres.constans;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Administrator
 */
public interface ConstantARouter {
    /**
     * ARouter 接参数的key
     */
    String AROUTER_BRAN = "AROUTER_BRAN";

    /**
     * 路由跳转进入 行为判断
     */
    @IntDef({WhatTodoARouterEnum.ACCESS, WhatTodoARouterEnum.GET_RESOURCE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WhatTodoARouterEnum {
        /**
         * 普通进入 正常跳转
         */
        int ACCESS = 0;

        /**
         * 获取资源 其它界面来选择资源
         */
        int GET_RESOURCE = 1;
    }
}
