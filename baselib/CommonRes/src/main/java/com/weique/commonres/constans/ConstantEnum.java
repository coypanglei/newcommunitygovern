package com.weique.commonres.constans;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ConstantEnum {

    /**
     * 获取动态表单enum
     */
    @IntDef({PartyMultipleChoiceViewStatus.DEFAULE, PartyMultipleChoiceViewStatus.CHECKED,
            PartyMultipleChoiceViewStatus.CORRECT, PartyMultipleChoiceViewStatus.ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PartyMultipleChoiceViewStatus {
        /**
         * 未开始选择时状态
         */
        int DEFAULE = 0;
        /**
         * 选中的答案
         */
        int CHECKED = 1;
        /**
         * 正确答案
         */
        int CORRECT = 2;
        /**
         * 选错状态
         */
        int ERROR = 3;
    }

    /**
     * 请求方式
     */
    @IntDef({RequestWayEnum.GET, RequestWayEnum.POST_FORM,
            RequestWayEnum.POST_BODY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestWayEnum {
        /**
         * GET
         */
        int GET = 0;
        /**
         * POST_FORM
         */
        int POST_FORM = 1;
        /**
         * POST_BODY
         */
        int POST_BODY = 2;
    }


}
