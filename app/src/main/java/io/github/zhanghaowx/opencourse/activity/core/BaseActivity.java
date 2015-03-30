package io.github.zhanghaowx.opencourse.activity.core;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import io.github.zhanghaowx.opencourse.R;

/**
 * This is the base class for any activity that contains
 * one fragment and an action bar
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // because getWindow().requestFeature() needs to be called first
        // so requestFeature() needs to be put before super.onCreate()
        requestFeature();

        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(getFragmentId());

        if (fragment == null) {
            fragment = getFragment();
            if (fragment != null) {
                fm.beginTransaction()
                        .add(getFragmentId(), fragment)
                        .commit();
            }
        }

        setContentView(getLayoutId());

        setupTransition();
        setupActionBar();
    }

    private void requestFeature() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }

    /**
     * Setup exit transition of the activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupTransition() {
        TransitionInflater inflater = TransitionInflater.from(this);
        Transition enterTransition = inflater.inflateTransition(getEnterTransitionId());
        Transition exitTransition = inflater.inflateTransition(getExitTransition());

        getWindow().setEnterTransition(enterTransition);
        getWindow().setExitTransition(exitTransition);
    }

    /**
     * Setup action bar style
     */
    private void setupActionBar() {
        Toolbar customToolbar = (Toolbar) findViewById(getToolbarId());
        if (customToolbar != null) {
            setSupportActionBar(customToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Returns custom resource id for tool bar
     *
     * @return
     */
    protected int getToolbarId() {
        return 0; // No toolbar by default
    }

    /**
     * Get the ID for fragment when inflated from XML
     *
     * @return
     */
    protected abstract int getFragmentId();

    /**
     * Create a fragment for this activity.
     *
     * @return created fragment
     */
    protected abstract Fragment getFragment();

    /**
     * Returns the layout resource identifier for this activity
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * Returns resource id for the enter transition of this activity
     *
     * @return
     */
    protected int getEnterTransitionId() {
        return R.transition.default_transition;
    }

    /**
     * Returns resource id for the exit transition for this activity
     *
     * @return
     */
    protected int getExitTransition() {
        return R.transition.default_transition;
    }
}
