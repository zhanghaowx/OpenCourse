package io.github.zhanghaowx.opencourse;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * The OpenCourse application
 */
public class OpenCourseApplication extends Application {
    public static final String PARSE_APPLICATION_ID = "dIfjPyWxb27y8bOsUyRrQBDeTQOatdLtuiR0pgvT";
    public static final String PARSE_CLIENT_KEY = "3xXJCl8Uqg1LvtnvI6Ky3qpXxUH6RZCYJ0THvHob";

    @Override
    public void onCreate() {
        super.onCreate();

        startParseService();

    }

    private void setDefaultFont() {
        // Override default font
        //FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/.ttf");
    }

    private void startParseService() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);
    }
}
