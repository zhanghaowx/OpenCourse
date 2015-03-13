package io.github.zhanghaowx.opencourse.activity.course;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.core.BaseActivity;
import io.github.zhanghaowx.opencourse.fragment.course.CourseDetailFragment;

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go back
                Intent intent = new Intent(this, CourseListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
