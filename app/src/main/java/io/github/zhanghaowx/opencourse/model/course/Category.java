package io.github.zhanghaowx.opencourse.model.course;

/**
 * Base class of a course's category, inspired by Coursera public api
 * https://tech.coursera.org/app-platform/catalog/
 * and Udacity developer API
 * https://s3.amazonaws.com/content.udacity-data.com/techdocs/UdacityCourseCatalogAPIDocumentation-v0.pdf
 */
public class Category extends BaseModel {

    // The category's name
    String mName;
    // The category's description
    String mDescription;

    public Category(String id, String name, String description) {
        super(id);
        this.mName = name;
        this.mDescription = description;
    }
}
