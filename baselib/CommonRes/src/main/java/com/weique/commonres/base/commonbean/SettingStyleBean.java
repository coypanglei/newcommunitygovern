package com.weique.commonres.base.commonbean;

public class SettingStyleBean extends CommonStyle{

    /**
     * 图片
     */
    private String img;

    /**
     *  点击跳转的title
     */
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *   是否显示下划线
     */
    private boolean visibleView;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isVisibleView() {
        return visibleView;
    }

    public void setVisibleView(boolean visibleView) {
        this.visibleView = visibleView;
    }
}
