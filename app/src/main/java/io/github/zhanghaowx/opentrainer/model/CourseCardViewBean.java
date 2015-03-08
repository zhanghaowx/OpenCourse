package io.github.zhanghaowx.opentrainer.model;

public class CourseCardViewBean {
    private String mImageUrl;

    public CourseCardViewBean(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
