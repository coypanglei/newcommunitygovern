package com.alibaba.sdk.floating.utils;

import com.alibaba.sdk.floating.bean.StringAndBooleanImpBean;
import com.alibaba.sdk.floating.bean.interfaces.StringAndBoolean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GK
 * @description:
 * @date :2020/12/7 13:55
 */
public class FloatingBeanUtils {

    /**
     * 字符串转成 {@link com.alibaba.sdk.floating.popup.OptionPopup}可以使用的 list
     *
     * @param list        数据
     * @param defaultZero 是否默认选中第一个
     * @return
     */
    public List<StringAndBooleanImpBean> stringsToSB(List<String> list, boolean defaultZero) {
        List<StringAndBooleanImpBean> beans = new ArrayList<>();
        for (String s : list) {
            if (defaultZero) {
                defaultZero = false;
                beans.add(new StringAndBooleanImpBean(s, true));
            } else {
                beans.add(new StringAndBooleanImpBean(s, false));
            }
        }
        return beans;
    }

    /**
     * @param list
     * @param defaultZero
     * @return
     */
    public <T extends StringAndBoolean> List<String> sbToStrings(List<T> list, boolean defaultZero) {
        List<String> beans = new ArrayList<>();
        for (T s : list) {
            if (defaultZero) {
                beans.add(s.getName());
            }
        }
        return beans;
    }
}
