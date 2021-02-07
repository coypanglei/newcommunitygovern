package com.weique.commonres.constans;

/**
 * 缓存 需要用的常量 key
 *
 * @author Administrator
 */
public interface ConstantACache {
    /**
     * 应用信息
     */
    public static final String APP_INFO = "app_info";
    /**
     * 登录成功返回的 用户信息(退出登录 只清理这个数据)
     */
    public static final String USER_INFO = "user_info";

    /**
     * 登录信息(用作缓存用户登录信息)
     */
    public static final String LOGIN_INFO = "login_info";

    /**
     * 是否同意了协议 政策
     */
    public static final String AGREE_WITH_THE_PROTOCOL = "AGREE_WITH_THE_PROTOCOL";

}
