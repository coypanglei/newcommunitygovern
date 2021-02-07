package com.weique.commonres.utils.commonutils;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.weique.commonres.base.arouter.BaseARouterByValueBean;
import com.weique.commonres.constans.ConstantARouter;

import java.io.Serializable;

/**
 * @author GK
 */
public class ARouterUtils {

    private ARouterUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }


    /**
     * @param context context
     * @param path    path
     * @param bean    bean
     * @param <T>     T extends Serializable
     */
    public static <T extends Serializable> void navigation(Context context, String path, BaseARouterByValueBean<T> bean) {
        try {
            ARouter.getInstance().build(path)
                    .withParcelable(ConstantARouter.AROUTER_BRAN, bean)
                    .navigation(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
