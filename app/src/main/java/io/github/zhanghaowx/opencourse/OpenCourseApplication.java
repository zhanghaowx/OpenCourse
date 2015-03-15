package io.github.zhanghaowx.opencourse;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * The OpenCourse application
 */
public final class OpenCourseApplication extends Application {

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
        Parse.initialize(this, "dIfjPyWxb27y8bOsUyRrQBDeTQOatdLtuiR0pgvT", "3xXJCl8Uqg1LvtnvI6Ky3qpXxUH6RZCYJ0THvHob");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
