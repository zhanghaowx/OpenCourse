package io.github.zhanghaowx.opentrainer.activity.course;

import android.support.v4.app.Fragment;

import io.github.zhanghaowx.opentrainer.R;
import io.github.zhanghaowx.opentrainer.activity.core.NavigationDrawerActivity;
import io.github.zhanghaowx.opentrainer.fragment.course.CourseListFragment;

/**
 * For course list activity
 */
public class CourseListActivity extends NavigationDrawerActivity {

    @Override
    protected Fragment getFragment() {
        return new CourseListFragment();
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

    }
}
