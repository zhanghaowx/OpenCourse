package io.github.zhanghaowx.opencourse.model.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.github.zhanghaowx.opencourse.utils.SharedPreferenceNames;

/**
 * Logged in user and related operations
 */
public class User {

    /**
     * Returns whether or not there is an user logged in
     * @param context
     * @return
     */
    public static boolean isLoggedIn(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String userSessionId = sp.getString(SharedPreferenceNames.PREF_USER_SESSION_ID, null);

        return userSessionId != null;
    }

    /**
     * Logs current user out
     * @param context
     */
    public static void logOut(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().remove(SharedPreferenceNames.PREF_USER_SESSION_ID).commit();

        //TODO: send request to server to invalidate session id
    }
}
