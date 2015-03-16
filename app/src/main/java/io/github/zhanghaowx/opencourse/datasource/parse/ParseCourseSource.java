package io.github.zhanghaowx.opencourse.datasource.parse;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.zhanghaowx.opencourse.datasource.ICourseSource;
import io.github.zhanghaowx.opencourse.datasource.utils.SearchCallback;
import io.github.zhanghaowx.opencourse.datasource.utils.SearchFilter;
import io.github.zhanghaowx.opencourse.model.course.Course;
import io.github.zhanghaowx.opencourse.model.course.Instructor;

/**
 * Course data provided from Parse platform.
 * See https://www.parse.com
 * Data are initialized by Udacity courses
 * https://www.udacity.com/public-api/v0/courses
 */
public class ParseCourseSource implements ICourseSource {
    private final static String TAG = ParseCourseSource.class.getSimpleName();
    private final static String PARSE_OBJECT_CLASS_NAME = "Course";
    private final static ParseCourseSource INSTANCE = new ParseCourseSource();

    private ParseCourseSource() {
        if (ParseCourseSource.INSTANCE != null) {
            throw new IllegalStateException("ParseCourseSource has already been instantiated");
        }
    }

    public static ICourseSource getInstance() {
        return ParseCourseSource.INSTANCE;
    }

    @Override
    public void getCoursesAsync(final SearchCallback callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(PARSE_OBJECT_CLASS_NAME);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> courseObjects, ParseException e) {
                if (e == null) {
                    Log.d(TAG, String.format("Retrieved information of %d courses", courseObjects.size()));

                    List<Course> courses = new ArrayList<Course>();
                    for (int i = 0; i < courseObjects.size(); i++) {
                        courses.add(getCourseFromParseObject(courseObjects.get(i)));
                    }
                    callback.success(courses);
                } else {
                    Log.e(TAG, "Error retrieving information for all available courses", e);
                }
            }
        });
    }

    @Override
    public void getCoursesAsync(Collection<SearchFilter> filters, SearchCallback callback) {

    }

    @Override
    public void getCourseAsync(int id, SearchCallback callback) {

    }

    private Course getCourseFromParseObject(ParseObject courseObject) {
        Course course = new Course(courseObject.getObjectId(), courseObject.getString("title"));
        course.setBannerImage(courseObject.getString("image"));
        course.setFaq(courseObject.getString("faq"));
        course.setShortSummary(courseObject.getString("short_summary"));
        course.setSubtitle(courseObject.getString("subtitle"));
        course.setSyllabus(courseObject.getString("syllabus"));

        try {
            String teaserVideoJsonString = courseObject.getString("teaser_video");
            if (teaserVideoJsonString != null && !teaserVideoJsonString.isEmpty()) {
                JSONObject teaserVideoJson = new JSONObject(teaserVideoJsonString);
                course.setTeaserVideo(teaserVideoJson.getString("youtube_url"));
            }
        } catch (Exception e) {
            Log.w(TAG, String.format("Error when parsing JSON string '%s', %s. Ignore teaser video for course %s",
                    courseObject.getString("teaser_video"), e.getMessage(), courseObject.getObjectId()));
        }

        try {
            JSONArray instructorsJsonArray = courseObject.getJSONArray("instructors");
            if (instructorsJsonArray != null) {
                for (int i = 0; i < instructorsJsonArray.length(); i++) {
                    JSONObject instructorJson = instructorsJsonArray.getJSONObject(i);
                    Instructor instructor = new Instructor(instructorJson.getString("name"), instructorJson.getString("name"));
                    instructor.setBio(instructorJson.getString("bio"));
                    instructor.setPhoto(instructorJson.getString("image"));

                    course.addInstructor(instructor);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, String.format("Error when parsing JSON string '%s', %s. Ignore instructors for course %s",
                    courseObject.getString("instructors"), e.getMessage(), courseObject.getObjectId()));
        }

        return course;
    }
}
