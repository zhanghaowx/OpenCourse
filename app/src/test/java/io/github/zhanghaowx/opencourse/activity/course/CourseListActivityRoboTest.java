package io.github.zhanghaowx.opencourse.activity.course;

import android.app.Activity;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import io.github.zhanghaowx.opencourse.TestOpenCourseRunner;

@Ignore
@RunWith(TestOpenCourseRunner.class)
public class CourseListActivityRoboTest {
    @Test
    public void testList() throws Exception {
        Activity courseListActivity = Robolectric
                .buildActivity(CourseListActivity.class)
                .create()
                .get();
        Assert.assertNotNull(courseListActivity);
    }
}