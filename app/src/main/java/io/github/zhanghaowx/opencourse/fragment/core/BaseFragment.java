package io.github.zhanghaowx.opencourse.fragment.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.thedazzler.droidicon.IconicFontDrawable;

import io.github.zhanghaowx.opencourse.R;

/**
 * Base class for all fragments which saves a reference to the
 * application context during its lifecycle.
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
     * @param iconName
     * @return
     */
    protected IconicFontDrawable createIcon(String iconName) {
        return createIcon(iconName, DEFAULT_ICON_SIZE);
    }

    /**
     * Create a drawable icon with given size
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

}
