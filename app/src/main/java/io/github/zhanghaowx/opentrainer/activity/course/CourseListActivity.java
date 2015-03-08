package io.github.zhanghaowx.opentrainer.activity.course;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.github.ksoichiro.android.observablescrollview.ScrollState;

import io.github.zhanghaowx.opentrainer.R;
import io.github.zhanghaowx.opentrainer.activity.core.NavigationDrawerActivity;
import io.github.zhanghaowx.opentrainer.fragment.course.CourseListHomeFragment;

/**
 * For course list activity
 */
public class CourseListActivity extends NavigationDrawerActivity {

    @Override
    protected Fragment getFragment() {
        return new CourseListHomeFragment();
    }

    @Override
    protected int getFragmentId() {
        return R.id.screen_default_container;
    }

    @Override
    protected int getLayout() {
        return R.layout.screen_default;
    }

    @Override
    protected int getActionBarTitle() {
        return R.string.courselist_actionbar_title;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // create different fragment for different selections
    }
}
