package com.weique.commonservice.zongzhi.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.weique.commonservice.zongzhi.bean.UserInfoBean;

/**
 * @author GK
 * @description: 综治模块对外报出接口
 * @date :2020/6/28 14:50
 */
public interface UserInfoService extends IProvider {
    UserInfoBean getUserInfo();
}
