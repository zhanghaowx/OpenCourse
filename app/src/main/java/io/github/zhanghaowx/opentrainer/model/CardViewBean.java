package io.github.zhanghaowx.opentrainer.model;

public class CardViewBean {
    private String mImageUrl;

    public CardViewBean(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
