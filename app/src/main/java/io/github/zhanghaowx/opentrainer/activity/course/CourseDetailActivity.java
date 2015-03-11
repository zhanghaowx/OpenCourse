package io.github.zhanghaowx.opentrainer.activity.course;

import android.support.v4.app.Fragment;

import io.github.zhanghaowx.opentrainer.R;
import io.github.zhanghaowx.opentrainer.activity.core.BaseActivity;
import io.github.zhanghaowx.opentrainer.fragment.course.CourseDetailFragment;

/**
 * Activity for detailed course information
 */
public class CourseDetailActivity extends BaseActivity {
    @Override
    protected Fragment getFragment() {
        return new CourseDetailFragment();
    }

    @Override
    protected int getFragmentId() {
        return R.id.activity_default_content_container;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_default;
    }

    @Override
    protected int getToolbar() {
        return R.id.activity_default_toolbar;
    }

    @Override
    protected int getEnterTransition() {
        return R.transition.default_transition;
    }

    @Override
    protected int getExitTransition() {
        return R.transition.default_transition;
    }
}
