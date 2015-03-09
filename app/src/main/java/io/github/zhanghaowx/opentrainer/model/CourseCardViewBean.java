package io.github.zhanghaowx.opentrainer.model;

public class CourseCardViewBean {
    private String mTitle;
    private String mImageUrl;

    public CourseCardViewBean(String title, String imageUrl) {
        this.mTitle = title;
        this.mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
