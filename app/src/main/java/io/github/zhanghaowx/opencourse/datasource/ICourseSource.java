package io.github.zhanghaowx.opencourse.datasource;

import java.util.Collection;

import io.github.zhanghaowx.opencourse.datasource.utils.SearchCallback;
import io.github.zhanghaowx.opencourse.datasource.utils.SearchFilter;

/**
 * Interface of the data source that provides course information.
 * Course information is read-only and is not allowed to be edited.
 */
public interface ICourseSource {
    /**
     * Return a list of available courses
     * @return
     */
    public void getCoursesAsync(SearchCallback callback);
    /**
     * Return a list of available courses after filtering
     * @param filters
     * @return
     */
    public void getCoursesAsync(Collection<SearchFilter> filters, SearchCallback callback);
    /**
     * Get a course by id
     * @param id
     * @return
     */
    public void getCourseAsync(int id, SearchCallback callback);
}