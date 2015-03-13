package io.github.zhanghaowx.opencourse.activity.core;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.fragment.core.NavigationDrawerFragment;

/**
 * Creates an activity that supports navigation drawer menu
 */
public abstract class NavigationDrawerActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Returns the title on action bar
     * @return
     */
    protected abstract int getActionBarTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar();
        setupNavigationMenu();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.activity_with_drawer_menu_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getActionBarTitle());
    }

    private void setupNavigationMenu() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.activity_with_drawer_menu_navigation_drawer);
        mNavigationDrawerFragment.setUp(
                R.id.activity_with_drawer_menu_navigation_drawer,
                (DrawerLayout) findViewById(R.id.activity_with_drawer_menu_layout));
    }
}
