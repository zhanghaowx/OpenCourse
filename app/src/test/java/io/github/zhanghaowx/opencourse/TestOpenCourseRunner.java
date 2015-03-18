package io.github.zhanghaowx.opencourse;

import android.app.Activity;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

/**
 * Extend RoboTestRunner to define custom properties
 */
public class TestOpenCourseRunner extends RobolectricTestRunner {

    private static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 18;

    public TestOpenCourseRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        final String manifestProperty = "../app/src/main/AndroidManifest.xml";
        final String resProperty = "../app/src/main/res";
        final String assetProperty = "../app/main/assets";
        return new AndroidManifest(Fs.fileFromPath(manifestProperty),
                Fs.fileFromPath(resProperty), Fs.fileFromPath(assetProperty)) {
            @Override
            public int getTargetSdkVersion() {
                return MAX_SDK_SUPPORTED_BY_ROBOLECTRIC;
            }

            @Override
            public String getThemeRef(Class<? extends Activity> activityClass) {
                return "@style/RoboAppTheme";
            }
        };
    }
}
