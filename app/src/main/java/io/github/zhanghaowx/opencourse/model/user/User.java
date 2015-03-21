package io.github.zhanghaowx.opencourse.model.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.github.zhanghaowx.opencourse.utils.SharedPreferenceNames;

/**
 * Logged in user
 */
public class User {

    /**
     * Returns whether or not there is an user logged in
     * @param context
     * @return
     */
    public static boolean IsLoggedIn(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String userSessionId = sp.getString(SharedPreferenceNames.PREF_USER_SESSION_ID, null);

        return userSessionId != null;
    }
}
