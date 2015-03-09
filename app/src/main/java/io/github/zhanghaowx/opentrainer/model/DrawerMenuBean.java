package io.github.zhanghaowx.opentrainer.model;

public class DrawerMenuBean {
    private int mIconResourceId;
    private String mTitle;

    public DrawerMenuBean(String title, int iconResourceId) {
        this.mTitle = title;
        this.mIconResourceId = iconResourceId;
    }

    public int getIconResourceId() {
        return mIconResourceId;
    }
    public void setIconResourceId(int iconResourceId) {
        mIconResourceId = iconResourceId;
    }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        this.mTitle = title;
    }
}