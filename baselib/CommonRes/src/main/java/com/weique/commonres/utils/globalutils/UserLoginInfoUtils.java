package com.weique.commonres.utils.globalutils;

import android.content.Context;

import com.google.gson.Gson;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.http.Api;
import com.weique.commonres.utils.BaseSingleton;
import com.weique.commonres.utils.commonutils.ACache;
import com.weique.commonres.utils.commonutils.StringUtil;
import com.weique.commonservice.zongzhi.bean.LoginInfoBean;

import me.jessyan.armscomponent.commonres.BuildConfig;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * @author GK
 * @description: 用户登录信息存CRUD
 * @date :2020/6/8 14:13
 */
public class UserLoginInfoUtils extends BaseSingleton {

    /**
     * 登录信息key
     */
    private static String LOGIN_NAME_PASSWORD = "LOGIN_NAME_PASSWORD";
    /**
     * 是否记住登录信息
     */
    private static String LOGIN_NAME_PASSWORD_REMEMBER = "LOGIN_NAME_PASSWORD_REMEMBER";
    /**
     * 最后一次登录时间
     */
    private static String LAST_LOAIN_TIME_KEY = "LAST_LOAIN_TIME_KEY";
    /**
     * 登录全局url
     */
    private static String LOGIN_GLOBAL_URL = "LOGIN_GLOBAL_URL";


    /**
     * 实例化
     */
    public static UserLoginInfoUtils getInstance() {
        return getSingleton(UserLoginInfoUtils.class);
    }

    /**
     * 1.存用户账号密码信息
     * 2.记录登录时间 - 用于本地判断  30秒内连续登录要 验证
     *
     * @param loginInfoBean loginInfoBean
     */
    public void saveLoginInfo(LoginInfoBean loginInfoBean) {
        try {
            ACache.getLoginInfo().put(LOGIN_NAME_PASSWORD, new Gson().toJson(loginInfoBean));
            ACache.getLoginInfo().put(LAST_LOAIN_TIME_KEY, String.valueOf(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户账号密码信息
     *
     * @return LoginInfoBean
     */
    public LoginInfoBean getLoginInfo() {
        return new Gson().fromJson(ACache.getLoginInfo().getAsString(LOGIN_NAME_PASSWORD), LoginInfoBean.class);
    }

    /**
     * 清除用户本地账号密码信息
     *
     * @param context context
     */
    public void clearLoginInfo(Context context) {
        ACache.getLoginInfo().remove(LOGIN_NAME_PASSWORD);
    }

    /**
     * 是否记住密码
     *
     * @param remember
     */
    public void setRememberLoginInfo(boolean remember) {
        ACache.getLoginInfo().put(LOGIN_NAME_PASSWORD_REMEMBER, remember);
    }

    /**
     * 获取是否记住密码
     *
     * @return boolean
     */
    public boolean getRememberLoginInfo() {
        boolean remembered = false;
        try {
            Object asObject = ACache.getLoginInfo().getAsObject(LOGIN_NAME_PASSWORD_REMEMBER);
            if (asObject != null) {
                remembered = (boolean) asObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return remembered;
    }

    /**
     * 获取最后一次登录时间
     *
     * @return long
     */
    private long getLastLoginTime() {
        String asString = ACache.getLoginInfo().getAsString(LAST_LOAIN_TIME_KEY);
        if (StringUtil.isNullString(asString)) {
            asString = "0";
        }
        return Long.parseLong(asString);
    }

    /**
     * 判断是否 要验证码
     *
     * @return boolean
     */
    public boolean whetherVerify() {
        boolean showVerify;
        long last = getLastLoginTime();
        long newTime = System.currentTimeMillis();
        /**
         * 最小验证间隔 30秒以内 连续登录需要验证码
         */
        long MINI_VERIFY_INTERVAL = 30 * 1000;
        if (newTime - last < MINI_VERIFY_INTERVAL) {
            showVerify = true;
        } else {
            showVerify = false;
        }
        return showVerify;
    }

    /**
     * 设置全局 url
     *
     * @param url url
     */
    public void setGlobalUrl(String url) {
        if (BuildConfig.DEBUG) {
            if (StringUtil.isNotNullString(url) && url.startsWith(Constants.HTTP)) {
                RetrofitUrlManager.getInstance().setGlobalDomain(url);
                ACache.getLoginInfo().put(LOGIN_GLOBAL_URL, url);
            } else {
                ArmsUtils.makeText("url有误");
            }
        } else {
            ArmsUtils.makeText("release 环境不允许修改 url");
        }
    }

    /**
     * 获取全局 url
     */
    public String getGlobalUrl() {
        String globalUrl = "";
        if (BuildConfig.DEBUG) {
            globalUrl = ACache.getLoginInfo().getAsString(LOGIN_GLOBAL_URL);
            if (StringUtil.isNullString(globalUrl) || !globalUrl.startsWith(Constants.HTTP)) {
                globalUrl = Api.APP_DOMAIN;
            }
        } else {
            ArmsUtils.makeText("release 为什么要这样获取全局url");
        }
        return globalUrl;
    }

}
