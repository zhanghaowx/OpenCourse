package io.github.zhanghaowx.opencourse.model.course;

import java.util.Collection;

/**
 * Base class of a course's instructor, inspired by coursera public api
 * https://tech.coursera.org/app-platform/catalog/
 */
public class Instructor extends BaseModel {
    // Photo URL
    String mPhoto;
    // Instructor's name
    String mFullName;
    // Instructor's title
    String mTitle;
    // 1-2 paragraph summary about the instructor
    String mBio;
    // The courses an instructor teaches
    Collection<Course> mCourses;

    public Instructor(String id, String fullName) {
        super(id);

        this.mFullName = fullName;
    }
}
