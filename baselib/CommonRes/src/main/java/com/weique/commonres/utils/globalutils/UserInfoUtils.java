package com.weique.commonres.utils.globalutils;

import android.content.Context;
import android.os.Build;

import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.Gson;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.core.RouterHub;
import com.weique.commonres.utils.BaseSingleton;
import com.weique.commonres.utils.commonutils.ACache;
import com.weique.commonres.utils.commonutils.ARouterUtils;
import com.weique.commonservice.zongzhi.bean.UserInfoBean;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GK
 * @description: 用户信息管理
 * @date :2020/6/8 14:13
 */
public class UserInfoUtils extends BaseSingleton {

    /**
     * 用户信息缓存KEY
     */
    private static final String USER_INFO = "USER_INFO";

    /**
     * 实例化
     */
    public static UserInfoUtils getInstance() {
        return getSingleton(UserInfoUtils.class);
    }

    /**
     * 存储用户信息
     *
     * @param userInfoBean userInfoBean
     */
    public void saveUserInfo(UserInfoBean userInfoBean) {
        try {
            if (userInfoBean == null) {
                return;
            }
            Constants.userinfo = userInfoBean;
            ACache aCache = ACache.getUserInfo();
            //登录成功刷新我的页面余额
            aCache.put(USER_INFO, new Gson().toJson(userInfoBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询
     *
     * @return GlobalUserInfoBean
     */
    public UserInfoBean getUserInfo() {
        if (Constants.userinfo != null) {
            return Constants.userinfo;
        }
        Constants.userinfo = null;
        try {
            ACache aCache = ACache.getUserInfo();
            //登录成功刷新我的页面余额
            String userInfo = aCache.getAsString(USER_INFO);
            Constants.userinfo = new Gson().fromJson(userInfo, UserInfoBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constants.userinfo;
    }


    /**
     * 退出登录以后删除用户信息
     */
    public void loginOut() {
        Constants.userinfo = null;
        ACache aCache = ACache.getUserInfo();
        aCache.clear();
    }

    /**
     * 历史搜索key
     */
    public static final String HISTORY_SEARCH_KEY = "HISTORY_SEARCH_KEY";

    public static List<String> getSearchKeywordList() {
        List<String> list = new ArrayList<>();
        try {
            ACache aCache = ACache.getPartySearchRecord();
            JSONArray asJSONArray = aCache.getAsJSONArray(HISTORY_SEARCH_KEY);
            if (asJSONArray == null) {
                aCache.put(HISTORY_SEARCH_KEY, new JSONArray());
            }
            if (asJSONArray != null) {
                //去除相同的 字
                for (int i = asJSONArray.length() - 1; i >= 0; i--) {
                    list.add((String) asJSONArray.get(i));
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 添加一个搜索关键字
     *
     * @param keyword keyword
     */
    public static void addSearchKeyword(String keyword) {
        try {
            if (ObjectUtils.isEmpty(keyword)) {
                return;
            }
            ACache aCache = ACache.getPartySearchRecord();
            JSONArray hitoryArray = aCache.getAsJSONArray(HISTORY_SEARCH_KEY);
            if (hitoryArray == null) {
                aCache.put(HISTORY_SEARCH_KEY, new JSONArray());
            } else {
                if (hitoryArray.length() > 10) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        hitoryArray.remove(hitoryArray.length() - 1);
                    }
                }
                //去除相同的 字
                for (int i = 0; i < hitoryArray.length(); i++) {
                    if (keyword.equals(hitoryArray.get(i))) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            hitoryArray.remove(i);
                        }
                        break;
                    }
                }
                hitoryArray.put(keyword);
            }
            aCache.put(HISTORY_SEARCH_KEY, hitoryArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cleanSearchKeyword() {
        try {
            ACache aCache = ACache.getPartySearchRecord();
            aCache.remove(HISTORY_SEARCH_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
