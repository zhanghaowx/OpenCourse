package io.github.zhanghaowx.opentrainer.activity.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import io.github.zhanghaowx.opentrainer.R;

/**
 * This is the base class for any activity that contains
 * one fragment
 */
public abstract class BaseActivity extends ActionBarActivity {
    /**
     * Create a fragment for this activity.
     *
     * @return created fragment
     */
    protected abstract Fragment getFragment();

    /**
     * Get the ID for above fragment
     * @return
     */
    protected abstract int getFragmentId();
    /**
     * Returns the layout resource identifier for this activity
     * @return
     */
    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(getFragmentId());

        if (fragment == null) {
            fragment = getFragment();
            if(fragment != null) {
                fm.beginTransaction()
                        .add(getFragmentId(), fragment)
                        .commit();
            }
        }
    }
}
