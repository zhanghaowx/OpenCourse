package io.github.zhanghaowx.opencourse.activity.course;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import io.github.zhanghaowx.opencourse.TestOpenCourseRunner;

@Config(reportSdk = 18, emulateSdk = 18)
@RunWith(TestOpenCourseRunner.class)
public class CourseListActivityRoboTest {
    @Test
    public void testList() throws Exception {
        CourseListActivity courseListActivity = Robolectric
                .buildActivity(CourseListActivity.class)
                .create()
                .get();
        Assert.assertNotNull(courseListActivity);
    }
}