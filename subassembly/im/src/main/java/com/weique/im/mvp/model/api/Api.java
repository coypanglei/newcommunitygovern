package com.weique.im.mvp.model.api;


import com.weique.im.BuildConfig;

/**
 *
 */
public interface Api {
    String IM_DOMAIN_NAME = "IM";
    /**
     * 这里 添加 自己的url
     */
    String IM_DOMAIN = BuildConfig.IS_BUILD_MODULE
            ? me.jessyan.armscomponent.commonres.BuildConfig.SERVER_URL
            : me.jessyan.armscomponent.commonres.BuildConfig.SERVER_URL;
}
