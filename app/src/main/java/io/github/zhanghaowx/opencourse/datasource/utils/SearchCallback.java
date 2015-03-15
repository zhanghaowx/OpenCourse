package io.github.zhanghaowx.opencourse.datasource.utils;

import java.util.List;

import io.github.zhanghaowx.opencourse.model.course.BaseModel;

/**
 * Callbacks will be called after a success search,
 */
public interface SearchCallback<T extends BaseModel> {
    void success(List<T> objects);
}
