package io.github.zhanghaowx.opentrainer;

import android.app.Application;

import io.github.zhanghaowx.opentrainer.utils.FontsOverride;

/**
 * The OpenTrainer application
 */
public final class OpenTrainerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Override default font
        //FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/NotoSansCJKsc-Regular.otf");
    }
}
