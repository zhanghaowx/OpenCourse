package io.github.zhanghaowx.opencourse.model.course;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Base class of a course's instructor, inspired by coursera public api
 * https://tech.coursera.org/app-platform/catalog/
 * and Udacity developer API
 * https://s3.amazonaws.com/content.udacity-data.com/techdocs/UdacityCourseCatalogAPIDocumentation-v0.pdf
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

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public Collection<Course> getCourses() {
        return mCourses;
    }

    public void addCourse(Course course) {
        if(this.mCourses == null) {
            this.mCourses = new ArrayList<>();
        }

        this.mCourses.add(course);
    }
}
