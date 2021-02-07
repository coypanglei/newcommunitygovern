package com.weique.commonres.base.commonbean;

/**
 *  圆角 阴影  波浪点击  颜色变化
 * @author Administrator
 */
public class ShadowLayoutStyleBean extends CommonStyle {

    //    <!-- 1、关于阴影 -->
    //        <!--阴影：是否要显示阴影，可能有只想用shape和selector功能，不想要阴影-->
    //        <attr format="boolean" name="hl_shadowHidden"/>
    //        <!--阴影：阴影颜色-->
    //        <attr format="color" name="hl_shadowColor"/>
    //        <!--阴影：阴影的扩散范围(也可以理解为扩散程度)-->
    //        <attr format="dimension" name="hl_shadowLimit"/>
    //        <!--控件区域是否对称，默认是对称。不对称的话，那么控件区域随着阴影区域走-->
    //        <attr format="boolean" name="hl_shadowSymmetry"/>
    //        <!--阴影：x轴的偏移量-->
    //        <attr format="dimension" name="hl_shadowOffsetX"/>
    //        <!--阴影：y轴的偏移量-->
    //        <attr format="dimension" name="hl_shadowOffsetY"/>
    //        <!--阴影：左边是否隐藏阴影-->
    //        <attr format="boolean" name="hl_shadowHiddenLeft"/>
    //        <!--阴影：右边是否隐藏阴影-->
    //        <attr format="boolean" name="hl_shadowHiddenRight"/>
    //        <!--阴影：上边是否隐藏阴影-->
    //        <attr format="boolean" name="hl_shadowHiddenTop"/>
    //        <!--阴影：下面是否隐藏阴影-->
    //        <attr format="boolean" name="hl_shadowHiddenBottom"/>
    //
    //
    //        <!-- 2、关于圆角 -->
    //        <!--圆角：统一大小，其中包括了阴影，shape、背景图、stroke边框圆角-->
    //        <attr format="dimension" name="hl_cornerRadius"/>
    //        <!--圆角：左上圆角。设置后会忽略hl_cornerRadius的值-->
    //        <attr format="dimension" name="hl_cornerRadius_leftTop"/>
    //        <!--圆角：右上圆角。同上-->
    //        <attr format="dimension" name="hl_cornerRadius_rightTop"/>
    //        <!--圆角：左下圆角。同上-->
    //        <attr format="dimension" name="hl_cornerRadius_leftBottom"/>
    //        <!--圆角：右下圆角。同上-->
    //        <attr format="dimension" name="hl_cornerRadius_rightBottom"/>

    private boolean shadowHidden;

    public boolean isShadowHidden() {
        return shadowHidden;
    }

    public void setShadowHidden(boolean shadowHidden) {
        this.shadowHidden = shadowHidden;
    }

    private int cornerRadius;

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    private String LayoutBackground;

    private String LayoutBackgroundTrue;

    public String getLayoutBackground() {
        return LayoutBackground;
    }

    public void setLayoutBackground(String layoutBackground) {
        LayoutBackground = layoutBackground;
    }

    public String getLayoutBackgroundTrue() {
        return LayoutBackgroundTrue;
    }

    public void setLayoutBackgroundTrue(String layoutBackgroundTrue) {
        LayoutBackgroundTrue = layoutBackgroundTrue;
    }

    /**
     *  字体大小
     */
    private int textSize;


    /**
     *  字体颜色
     */
    private String textColor;

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
}
