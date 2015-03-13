package io.github.zhanghaowx.opencourse;

import android.app.Application;

import io.github.zhanghaowx.opencourse.utils.FontsOverride;

/**
 * The OpenCourse application
 */
public final class OpenCourseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Override default font
        //FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/NotoSansCJKsc-Regular.otf");
    }
}
