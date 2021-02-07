package com.wq.newcommunitygovern.mvp.model.entity.dynamic.interfaces;

import com.wq.newcommunitygovern.mvp.model.entity.dynamic.DynamicFormItemBean;

import java.util.List;

/**
 * @author GK
 * @description:
 * @date :2020/11/4 14:38
 */
public interface ItemFlags {

    /**
     * 将对象包装成Vo层 条目 对象
     *
     * @param routeCurrentFlag 路由当前界面
     * @return List
     */
    List<DynamicFormItemBean> packObjectToVoItem(String routeCurrentFlag);
}
