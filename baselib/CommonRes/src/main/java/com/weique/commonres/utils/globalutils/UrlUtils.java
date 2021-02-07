package com.weique.commonres.utils.globalutils;

import com.weique.commonres.constans.Constants;
import com.weique.commonres.http.Api;

/**
 * @author GK
 * @description:
 * @date :2020/7/28 14:10
 */
public class UrlUtils {
    public static String setGlobelUrl(String path) {
        if (path.startsWith(Constants.HTTP)) {
            return path;
        }
        return Api.APP_DOMAIN + path;
    }
}
