package io.github.zhanghaowx.opencourse;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

/**
 * Extend RoboTestRunner to define custom properties
 */
public class TestOpenCourseRunner extends RobolectricTestRunner {

    public TestOpenCourseRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

}
