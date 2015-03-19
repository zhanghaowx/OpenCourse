package io.github.zhanghaowx.opencourse.activity.core;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.login.LoginActivity;
import io.github.zhanghaowx.opencourse.fragment.core.NavigationDrawerFragment;
import io.github.zhanghaowx.opencourse.utils.SharedPreferenceNames;

/**
 * Creates an activity that supports navigation drawer menu
 */
public abstract class NavigationDrawerActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final int REQUEST_LOGIN = 0;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Returns the title on action bar
     *
     * @return
     */
    protected abstract int getActionBarTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar();
        setupNavigationMenu();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String userLoginSessionId = sp.getString(SharedPreferenceNames.PREF_USER_SESSION_ID, null);

        switch (position) {
            case 0:
                if (userLoginSessionId != null) {

                } else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, REQUEST_LOGIN);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN) {
            mNavigationDrawerFragment.requestUpdateMenu();
        }
    }

    private void setupToolbar() {
        getSupportActionBar().setTitle(getActionBarTitle());
    }

    private void setupNavigationMenu() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.activity_with_drawer_menu_navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.activity_with_drawer_menu_navigation_drawer,
                (DrawerLayout) findViewById(R.id.activity_with_drawer_menu_layout));
    }
}
