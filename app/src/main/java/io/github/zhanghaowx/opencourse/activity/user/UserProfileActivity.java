package io.github.zhanghaowx.opencourse.activity.user;

import android.support.v4.app.Fragment;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.core.BaseActivity;
import io.github.zhanghaowx.opencourse.fragment.user.UserProfileFragment;

/**
 * Profile page for currently logged in user
 */
public class UserProfileActivity extends BaseActivity {
    @Override
    protected Fragment getFragment() {
        return new UserProfileFragment();
    }

    @Override
    protected int getFragmentId() {
        return R.id.activity_default_content_container;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_default;
    }
}
