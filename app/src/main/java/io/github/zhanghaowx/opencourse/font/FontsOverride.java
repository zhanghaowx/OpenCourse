package io.github.zhanghaowx.opencourse.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Override Android's default system font with customs fonts
 */
public final class FontsOverride {
    public static String TAG = FontsOverride.class.getSimpleName();

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    /**
     * Replace default font typeface for <code>staticTypefaceFieldName</code> with <code>newTypeface</code>
     * @param staticTypefaceFieldName
     * @param newTypeface
     */
    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (Exception e) {
            Log.e(TAG, String.format("Replace default font for %s failed", staticTypefaceFieldName), e);
        }
    }

}
