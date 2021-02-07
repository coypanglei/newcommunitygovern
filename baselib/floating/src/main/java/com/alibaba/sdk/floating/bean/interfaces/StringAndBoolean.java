package com.alibaba.sdk.floating.bean.interfaces;

/**
 * @author GK
 * @description: 字符串和选中状态
 * @date :2020/12/4 16:50
 */
public interface StringAndBoolean {

    /**
     * 获取名称
     *
     * @return String
     */
    String getName();

    /**
     * 获取选中状态
     *
     * @return boolean
     */
    boolean getCheckState();

    void setCheckState(boolean checkState);
}
