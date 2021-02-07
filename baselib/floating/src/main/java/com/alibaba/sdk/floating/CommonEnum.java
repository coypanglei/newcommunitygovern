package com.alibaba.sdk.floating;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author GK
 * @description:
 * @date :2020/12/7 11:49
 */
public class CommonEnum {
    /**
     * 单选还是多选
     */
    @IntDef({ChoiceModelEnum.SOLE, ChoiceModelEnum.MULTI})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ChoiceModelEnum {
        /**
         * 单选
         */
        int SOLE = 0;
        /**
         * 多选
         */
        int MULTI = 1;
    }

    /**
     * 布局方式
     */
    @IntDef({LayoutModelEnum.ERECT, LayoutModelEnum.FLOW,
            LayoutModelEnum.AUTO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LayoutModelEnum {
        /**
         * 竖着的
         */
        int ERECT = 0;
        /**
         * 流式
         */
        int FLOW = 1;
        /**
         * 自动 大于阈值 {@link #AUTO_VPT} 用FLOW 小于用ERECT
         */
        int AUTO = 2;
    }
}
