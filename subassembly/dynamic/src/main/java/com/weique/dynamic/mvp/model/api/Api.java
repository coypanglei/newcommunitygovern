package com.weique.dynamic.mvp.model.api;


import com.weique.dynamic.BuildConfig;

/**
 *
 */
public interface Api {
    String DYNAMIC_DOMAIN_NAME = "DYNAMIC";
    /**
     * 这里 添加 自己的url
     */
    String DYNAMIC_DOMAIN = BuildConfig.IS_BUILD_MODULE
            ? me.jessyan.armscomponent.commonres.BuildConfig.SERVER_URL
            : me.jessyan.armscomponent.commonres.BuildConfig.SERVER_URL;
}
