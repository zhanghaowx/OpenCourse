package io.github.zhanghaowx.opencourse.model.course;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Base class of a course, inspired by coursera public api
 * https://tech.coursera.org/app-platform/catalog/
 * and Udacity developer API
 * https://s3.amazonaws.com/content.udacity-data.com/techdocs/UdacityCourseCatalogAPIDocumentation-v0.pdf
 */
public class Course extends BaseModel {
    /* REQUIRED */
    // The course name or title
    String mTitle;
    // The course subtitle
    String mSubtitle;
    // The categories of the course
    List<Category> mCategories;
    // The instructors of the course
    List<Instructor> mInstructors;

    /* OPTIONAL */
    // A course photo
    String mBannerImage;
    // HTML snippet containing the course syllabus
    String mSyllabus;
    // The YouTube video code. e.g. <https://www.youtube.com/watch?v=CCmTQW7OebM>
    String mTeaserVideo;
    // HTML snippet describing the course
    String mSummary;
    // A short description of the course
    String mShortSummary;
    // HTML snippet answering frequently asked questions
    String mFaq;

    /**
     * Create a course with an identifier and title
     * @param id
     * @param title
     */
    public Course(String id, String title) {
        super(id);
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void addCategory(Category category) {
        if(this.mCategories == null) {
            this.mCategories = new ArrayList<Category>();
        }
        this.mCategories.add(category);
    }

    public List<Instructor> getInstructors() {
        return mInstructors;
    }

    public void addInstructor(Instructor instructor) {
        if(this.mInstructors == null) {
            this.mInstructors = new ArrayList<Instructor>();
        }
        this.mInstructors.add(instructor);
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    public String getBannerImage() {
        return mBannerImage;
    }

    public void setBannerImage(String bannerImage) {
        mBannerImage = bannerImage;
    }

    public String getSyllabus() {
        return mSyllabus;
    }

    public void setSyllabus(String syllabus) {
        mSyllabus = syllabus;
    }

    public String getTeaserVideo() {
        return mTeaserVideo;
    }

    public void setTeaserVideo(String teaserVideo) {
        mTeaserVideo = teaserVideo;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getShortSummary() {
        return mShortSummary;
    }

    public void setShortSummary(String shortSummary) {
        mShortSummary = shortSummary;
    }

    public String getFaq() {
        return mFaq;
    }

    public void setFaq(String faq) {
        mFaq = faq;
    }
}
