package io.github.zhanghaowx.opencourse.fragment.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.thedazzler.droidicon.IconicFontDrawable;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.core.BaseActivity;

/**
 * Base class for all fragments which saves a reference to the
 * application context during its lifecycle.
 * It also has a lot helper methods which may be useful for
 * derived classes.
 */
public class BaseFragment extends Fragment {
    private final static int DEFAULT_ICON_SIZE = 48;
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

    /**
     * Create a drawable icon, icon name includes FontAwesome, Iconic Font, Entypo Pictograms
     *
     * @param iconName
     * @return
     */
    protected IconicFontDrawable createIcon(String iconName) {
        return createIcon(iconName, DEFAULT_ICON_SIZE);
    }

    /**
     * Create a drawable icon with given size
     *
     * @param iconName
     * @param iconSize
     * @return
     */
    protected IconicFontDrawable createIcon(String iconName, int iconSize) {
        IconicFontDrawable icon = new IconicFontDrawable(getActivity().getApplicationContext());
        icon.setIcon(iconName);
        icon.setBounds(0, 0, iconSize, iconSize);
        icon.setIconColor(getResources().getColor(R.color.white));

        return icon;
    }

    /**
     * Show hide action bar with custom animations
     *
     * @param isVisible
     */
    protected void showActionBar(boolean isVisible) {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        final ActionBar actionBar = baseActivity.getSupportActionBar();
        if (isVisible) {
            if (!actionBar.isShowing()) {
                baseActivity.getToolbar()
                        .animate()
                        .translationY(0)
                        .withStartAction(new Runnable() {
                            @Override
                            public void run() {
                                actionBar.show();
                            }
                        });
            }
        } else {
            if (actionBar.isShowing()) {
                baseActivity.getToolbar()
                        .animate()
                        .translationY(-baseActivity.getToolbar().getBottom())
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                actionBar.hide();
                            }
                        });
            }
        }
    }

    /**
     * Change the transparency of activity's action bar
     *
     * @param alpha
     */
    protected void setActionBarTransparency(float alpha) {
        int baseColor = getResources().getColor(R.color.theme_default_primary);
        ColorDrawable backgroundDrawable =
                new ColorDrawable(ScrollUtils.getColorWithAlpha(alpha, baseColor));

        BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.getSupportActionBar().setBackgroundDrawable(backgroundDrawable);
    }

}
