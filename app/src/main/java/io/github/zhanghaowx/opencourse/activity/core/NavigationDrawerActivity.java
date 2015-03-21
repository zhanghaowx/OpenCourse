package io.github.zhanghaowx.opencourse.activity.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.login.LoginActivity;
import io.github.zhanghaowx.opencourse.activity.user.UserProfileActivity;
import io.github.zhanghaowx.opencourse.fragment.core.NavigationDrawerFragment;
import io.github.zhanghaowx.opencourse.model.user.User;

/**
 * Creates an activity that supports navigation drawer menu
 */
public abstract class NavigationDrawerActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final int REQUEST_LOGIN = 0;
    private static final int REQUEST_LOGOUT = 1;

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
        boolean isUserLoggedIn = User.isLoggedIn(this);

        switch (position) {
            case 0:
                if (isUserLoggedIn) {
                    Intent intent = new Intent(this, UserProfileActivity.class);
                    startActivityForResult(intent, REQUEST_LOGOUT);
                } else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, REQUEST_LOGIN);
                }
                break;
            default:
                Toast.makeText(this, "In Developing ... ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN || requestCode == REQUEST_LOGOUT) {
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
