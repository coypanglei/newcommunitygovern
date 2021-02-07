package com.weique.commonres.base.commonbean;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author GK
 * @description:
 * @date :2020/9/14 9:25
 */
public class DynamicFormEnum {

    /**
     * 布局标志  的枚举
     * !!!这里每添加一个 要在 {com.wq.newcommunitygovern.mvp.ui.adapter.provider.ProviderFactory}
     * 中添加一个  对应的 <？extends BaseItemProvider>
     */
    @IntDef({ItemFlagEnum.TEXT_VIEW_ONE,
            ItemFlagEnum.TEXT_VIEW_SINGLE_SELECT,
            ItemFlagEnum.SELECT_LATLNG,
            ItemFlagEnum.IMAGE_LIST,
            ItemFlagEnum.LOGIN_PASSWORD
            , ItemFlagEnum.EDIT_ITEM_TOP
            , ItemFlagEnum.NULL_VIEW
            , ItemFlagEnum.SETTING_ITEM_CLICK
            , ItemFlagEnum.PERSONAL_INFORMATION,ItemFlagEnum.EDIT_ITEM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemFlagEnum {
        /**
         * 单行文本显示
         */
        int TEXT_VIEW_ONE = 0;
        /**
         * 带选项的
         */
        int TEXT_VIEW_SINGLE_SELECT = 1;
        /**
         * 布局样式3
         */
        int IMAGE_LIST = 2;
        /**
         * 选择定位
         */
        int SELECT_LATLNG = 3;

        /**
         * 登录页密码
         */
        int LOGIN_PASSWORD = 4;

        /**
         * EDIT_ITEM 编辑框 提示在上面
         */
        int EDIT_ITEM_TOP = 5;

        /**
         * 图片 - 文字 -文字 跳转图标  下划线
         * 仿钉钉
         */
        int SETTING_ITEM_CLICK = 6;


        /**
         * 文字 -文字 跳转图标  下划线
         * 仿钉钉
         */
        int SETTING_ITEM_CLICK_TEXT = 9;

        /**
         * 空view 用来填补布局
         */
        int NULL_VIEW = 7;

        /***
         *  头像 详细信息   个人信息
         */
        int PERSONAL_INFORMATION = 8;

        /**
         * 编辑 选项 通过style 来区分 样式
         */
        int EDIT_ITEM = 10;
    }


    /**
     * 行为 针对列表 和详情
     */
    @IntDef({Behavior.DETAIL,
            Behavior.ADD,
            Behavior.EDIT,
            Behavior.LIST
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Behavior {
        /**
         * 看详情
         */
        int DETAIL = 0;
        /**
         * 新增
         */
        int ADD = 1;
        /**
         * 编辑
         */
        int EDIT = 2;

        /**
         * 列表
         */
        int LIST = 3;

    }


    /**
     * 权限 针对详情
     * 现有逻辑是
     * CommonWhatToDoEnum - DETAIL - >显示 DELETE_AND_ALERT
     * CommonWhatToDoEnum - ADD - >显示 CHECK
     * CommonWhatToDoEnum - EDIT - >显示 CHECK
     */
    @IntDef({CommonPermission.CHECK,
            CommonPermission.ALERT,
            CommonPermission.DELETE,
            CommonPermission.DELETE_AND_ALERT
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface CommonPermission {
        /**
         * 查看
         */
        int CHECK = 0;
        /**
         * 修改
         */
        int ALERT = 1;
        /**
         * 删除
         */
        int DELETE = 2;
        /**
         * 删除 和 修改
         */
        int DELETE_AND_ALERT = 3;

    }
}
