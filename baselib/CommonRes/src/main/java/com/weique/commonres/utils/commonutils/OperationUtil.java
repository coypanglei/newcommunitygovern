package com.weique.commonres.utils.commonutils;

/**
 * @author GK
 * @description: 用户 操作控制
 * @date :2020/7/2 14:53
 */
public class OperationUtil {
    private static long lastClickTime = 0;
    private static int spaceTime = 500;

    public static boolean isFastClick() {

        long currentTime = System.currentTimeMillis();
        boolean isAllowClick;

        if (currentTime - lastClickTime > spaceTime) {

            isAllowClick = false;

        } else {
            isAllowClick = true;

        }

        lastClickTime = currentTime;
        return isAllowClick;
    }
}
