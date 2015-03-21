package io.github.zhanghaowx.opencourse.activity.login;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.core.BaseActivity;
import io.github.zhanghaowx.opencourse.fragment.login.LoginEmailFragment;

/**
 * A login screen that offers login via email/password.
 */
public class LoginEmailActivity extends BaseActivity {

    @Override
    protected Fragment getFragment() {
        return new LoginEmailFragment();
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



