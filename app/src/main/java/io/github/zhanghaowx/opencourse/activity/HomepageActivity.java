package io.github.zhanghaowx.opencourse.activity;

import android.support.v4.app.Fragment;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.core.NavigationDrawerActivity;
import io.github.zhanghaowx.opencourse.fragment.course.CourseListFragment;

/**
 * For course list activity
 */
public class HomepageActivity extends NavigationDrawerActivity {

    @Override
    protected Fragment getFragment() {
        return new CourseListFragment();
    }

    @Override
    protected int getFragmentId() {
        return R.id.activity_with_drawer_menu_content_container;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_with_drawer_menu;
    }

    @Override
    protected int getEnterTransitionId() {
        return R.transition.default_transition;
    }

    @Override
    protected int getExitTransition() {
        return R.transition.default_transition;
    }

    @Override
    protected int getActionBarTitle() {
        return R.string.home_actionbar_title;
    }
}
