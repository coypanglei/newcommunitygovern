package com.weique.commonres.utils.commonutils;

import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ObjectUtils;
import com.lihang.ShadowLayout;
import com.weique.commonres.base.commonbean.CommonStyle;
import com.weique.commonres.base.commonbean.EditStyleBean;
import com.weique.commonres.base.commonbean.ShadowLayoutStyleBean;

import timber.log.Timber;

public class ViewUtils {

    /**
      通用 改变布局的参数
     */
    public static void setCommonStyle(CommonStyle styleBean, View view) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        if(ObjectUtils.isNotEmpty(styleBean.getBottomMargin())) {
            params.bottomMargin = styleBean.getBottomMargin();
        }
        if(ObjectUtils.isNotEmpty(styleBean.getRightMargin())) {
            params.rightMargin = styleBean.getRightMargin();
        }
        if(ObjectUtils.isNotEmpty(styleBean.getLeftMargin())) {
            params.leftMargin = styleBean.getLeftMargin();
        }
        if(ObjectUtils.isNotEmpty(styleBean.getTopMargin())) {
            params.topMargin = styleBean.getTopMargin();
        }
        if(ObjectUtils.isNotEmpty(styleBean.getHeight())&&0!=styleBean.getHeight()) {
            params.height = styleBean.getHeight();
        }


        if(ObjectUtils.isNotEmpty(styleBean.getHeight())&&0!=styleBean.getWidth()) {
            params.width = styleBean.getWidth();
        }
        view.setLayoutParams(params);
    }

    /**
     *
     * @param styleBean ShadowLayout 圆角 阴影等
     * @param view
     */
    public static void setCommonStyle(CommonStyle styleBean, ShadowLayout view) {
        Timber.e(styleBean.toString());
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if(ObjectUtils.isNotEmpty(styleBean.getBottomMargin())) {
            params.bottomMargin = styleBean.getBottomMargin();
        }
        if(ObjectUtils.isNotEmpty(styleBean.getRightMargin())) {
            params.rightMargin = styleBean.getRightMargin();
        }
        if(ObjectUtils.isNotEmpty(styleBean.getLeftMargin())) {
            params.leftMargin = styleBean.getLeftMargin();
        }
        if(ObjectUtils.isNotEmpty(styleBean.getTopMargin())) {
            params.topMargin = styleBean.getTopMargin();
        }
        if(ObjectUtils.isNotEmpty(styleBean.getHeight())&&0!=styleBean.getHeight()) {
            params.height = styleBean.getHeight();
        }

        if(ObjectUtils.isNotEmpty(styleBean.getHeight())&&0!=styleBean.getWidth()) {
            params.width = styleBean.getWidth();
        }
        view.setLayoutParams(params);
    }


    /**
     * {@link EditStyleBean}
     * @param styleBean edit 的一些通用属性
     */
    public static void setCommonStyle(EditStyleBean styleBean, EditText edit) {
        switch (styleBean.getInputType()) {
            case "number":
                edit.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case "phone":
                edit.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case "id_number":
                edit.setKeyListener(DigitsKeyListener.getInstance("0123456789xyzXYZ"));
                InputFilter[] filters = {new InputFilter.LengthFilter(18)};
                edit.setFilters(filters);
                break;
            default:
                break;
        }
    }




    /**
     * {@link ShadowLayoutStyleBean}
     * @param styleBean ShadowLayout的一些通用属性
     */
    public static void setShadowLayoutStyle(ShadowLayoutStyleBean styleBean, ShadowLayout shadowLayout) {

        if (ObjectUtils.isNotEmpty(styleBean.getLayoutBackground())) {
            shadowLayout.setLayoutBackground(Color.parseColor(styleBean.getLayoutBackground()));
        }
        if (ObjectUtils.isNotEmpty(styleBean.getLayoutBackgroundTrue())) {

            shadowLayout.setLayoutBackgroundTrue(Color.parseColor(styleBean.getLayoutBackgroundTrue()));
        }
        if (ObjectUtils.isNotEmpty(styleBean.getCornerRadius())) {
            shadowLayout.setCornerRadius(styleBean.getCornerRadius());
        }
    }
}
