package io.github.zhanghaowx.opencourse.model.core;

public class DrawerMenu {
    private int mIconResourceId;
    private String mTitle;

    public DrawerMenu(String title, int iconResourceId) {
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