package com.alibaba.sdk.floating.bean;

import com.alibaba.sdk.floating.bean.interfaces.StringAndBoolean;

/**
 * @author GK
 * @description: 字符串和选中状态
 * @date :2020/12/4 16:54
 */
public class StringAndBooleanImpBean implements StringAndBoolean {

    private String name = "";
    private boolean checkedState = false;


    public StringAndBooleanImpBean(String name, boolean checkedState) {
        this.name = name;
        this.checkedState = checkedState;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getCheckState() {
        return checkedState;
    }

    @Override
    public void setCheckState(boolean checkState) {
        this.checkedState = checkedState;
    }

    public void setName(String name) {
        this.name = name;
    }
}
