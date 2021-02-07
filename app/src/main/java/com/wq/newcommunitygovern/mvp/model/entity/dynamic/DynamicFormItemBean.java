package com.wq.newcommunitygovern.mvp.model.entity.dynamic;

import com.weique.commonres.base.commonbean.DynamicFormEnum;

/**
 * @author GK
 * @description: 动态表单item bean
 * @date :2020/9/9 17:57
 */
public class DynamicFormItemBean<T> {


    public DynamicFormItemBean() {
    }


    /**
     * 做什么 新增 还是查看  还是修改
     */
    private int behavior;

    /**
     * item 标志 区分 是哪个 item
     */
    private int itemFlag;

    /**
     * 需要显示 值
     */
    private T displayValue;

    /**
     * 默认显示 值
     */
    private T defaultValue;

    /**
     * 必须的
     */
    private boolean require = false;

    /**
     * 这里指向 接口获取对象的 key
     */
    private String submitKey;

    /**
     * 条目的中文名称
     */
    private String itemChineseName;


    /**
     * 路由 当前 页面
     */
    private String routeCurrentFlag;
    /**
     * 路由 目标 页面
     */
    private String routeTargetFlag;

    /**
     * @param itemFlag        itemFlag  item 标志 用来判断是哪个 item  可以对比文档
     * @param defaultValue    defaultValue 默认显示的值
     * @param submitKey       submitKey  接口要的map key
     * @param itemChineseName itemName  item的中文 名称 eg ： 姓名   年龄  等
     */
    public DynamicFormItemBean(@DynamicFormEnum.ItemFlagEnum int itemFlag, String itemChineseName,
                               T defaultValue, String submitKey, @DynamicFormEnum.Behavior int behavior) {
        this.itemFlag = itemFlag;
        this.defaultValue = defaultValue;
        this.submitKey = submitKey;
        this.itemChineseName = itemChineseName;
        this.behavior = behavior;
    }

    /**
     * @param itemFlag         itemFlag
     * @param itemChineseName  itemChineseName
     * @param defaultValue     defaultValue
     * @param submitKey        submitKey
     * @param behavior         behavior
     * @param routeCurrentFlag routeCurrentFlag
     * @param routeTargetFlag  routeTargetFlag
     */
    public DynamicFormItemBean(@DynamicFormEnum.ItemFlagEnum int itemFlag, String itemChineseName,
                               T defaultValue, String submitKey, @DynamicFormEnum.Behavior int behavior,
                               String routeCurrentFlag, String routeTargetFlag) {
        this.itemFlag = itemFlag;
        this.defaultValue = defaultValue;
        this.submitKey = submitKey;
        this.itemChineseName = itemChineseName;
        this.behavior = behavior;
        this.routeCurrentFlag = routeCurrentFlag;
        this.routeTargetFlag = routeTargetFlag;
    }

    public int getItemFlag() {
        return itemFlag;
    }

    public void setItemFlag(@DynamicFormEnum.ItemFlagEnum int itemFlag) {
        this.itemFlag = itemFlag;
    }

    public T getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(T displayValue) {
        this.displayValue = displayValue;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isRequire() {
        return require;
    }

    public void setRequire(boolean require) {
        this.require = require;
    }

    public int getBehavior() {
        return behavior;
    }

    public void setBehavior(@DynamicFormEnum.Behavior int behavior) {
        this.behavior = behavior;
    }

    public String getSubmitKey() {
        return submitKey;
    }

    public void setSubmitKey(String submitKey) {
        this.submitKey = submitKey;
    }

    public String getItemChineseName() {
        return itemChineseName;
    }

    public void setItemChineseName(String itemChineseName) {
        this.itemChineseName = itemChineseName;
    }

    public String getRouteCurrentFlag() {
        return routeCurrentFlag;
    }

    public void setRouteCurrentFlag(String routeCurrentFlag) {
        this.routeCurrentFlag = routeCurrentFlag;
    }

    public String getRouteTargetFlag() {
        return routeTargetFlag;
    }

    public void setRouteTargetFlag(String routeTargetFlag) {
        this.routeTargetFlag = routeTargetFlag;
    }
}
