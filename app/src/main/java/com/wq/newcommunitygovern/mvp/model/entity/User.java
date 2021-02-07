package com.wq.newcommunitygovern.mvp.model.entity;

import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.wq.newcommunitygovern.mvp.model.entity.dynamic.DynamicFormItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GK
 * @description:
 * @date :2020/11/3 15:49
 */
public class User implements com.wq.newcommunitygovern.mvp.model.entity.dynamic.interfaces.ItemFlags {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public List<DynamicFormItemBean> packObjectToVoItem(String routerCurrentFlag) {
        List<DynamicFormItemBean> bean = new ArrayList<>();
        bean.add(new DynamicFormItemBean<>(DynamicFormEnum.ItemFlagEnum.TEXT_VIEW_ONE,
                "姓名", "请出入姓名", "name", DynamicFormEnum.Behavior.ADD));
        bean.add(new DynamicFormItemBean<>(DynamicFormEnum.ItemFlagEnum.TEXT_VIEW_ONE,
                "年龄", "请出入年龄", "age", DynamicFormEnum.Behavior.ADD));
        return bean;
    }
}
