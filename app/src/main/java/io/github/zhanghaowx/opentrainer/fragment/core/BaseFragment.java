package io.github.zhanghaowx.opentrainer.fragment.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Base class for all fragments which saves a reference to the
 * application context during its lifecycle.
 */
public class BaseFragment extends Fragment {
    protected Context mContext;

    public BaseFragment() {
        super();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity.getApplicationContext();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mContext = null;
    }

}
