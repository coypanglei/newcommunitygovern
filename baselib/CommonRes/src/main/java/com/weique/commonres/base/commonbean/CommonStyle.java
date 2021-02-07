package com.weique.commonres.base.commonbean;

/**
 * @author Administrator
 */
public class CommonStyle {
    /**
     * 下边距
     */
    private int bottomMargin;
    /**
     * 右边距
     */
    private int rightMargin;
    /**
     * 左边距
     */
    private int leftMargin;
    /**
     * 上边距
     */
    private int topMargin;
    /**
     * 高
     */
    private int height;
    /**
     * 宽
     */
    private int width;

    /**
     * 背景 支持图片 资源  color  可拓展 暂时未加
     * <p>
     * color   drawable   url
     */

    private String backgroundType = "color";
    /**
     * 背景
     */
    private String background;



    public int getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    public int getRightMargin() {
        return rightMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getBackgroundType() {
        return backgroundType;
    }

    public void setBackgroundType(String backgroundType) {
        this.backgroundType = backgroundType;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }


    @Override
    public String toString() {
        return "CommonStyle{" +
                "bottomMargin=" + bottomMargin +
                ", rightMargin=" + rightMargin +
                ", leftMargin=" + leftMargin +
                ", topMargin=" + topMargin +
                ", height=" + height +
                ", width=" + width +
                ", backgroundType='" + backgroundType + '\'' +
                ", background='" + background + '\'' +
                '}';
    }
}
