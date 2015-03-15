package io.github.zhanghaowx.opencourse.model.course;

public class BaseModel {
    private String mId;

    public BaseModel(String id) {
        this.mId = id;
    }

    public String getId() {
        return mId;
    }
}
