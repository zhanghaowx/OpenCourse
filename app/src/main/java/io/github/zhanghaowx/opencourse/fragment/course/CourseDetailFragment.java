package io.github.zhanghaowx.opencourse.fragment.course;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.core.BaseActivity;
import io.github.zhanghaowx.opencourse.fragment.core.BaseFragment;

public class CourseDetailFragment extends BaseFragment implements ObservableScrollViewCallbacks {
    private static final String TAG = CourseDetailFragment.class.getSimpleName();

    private View mImageView;
    private ObservableScrollView mScrollView;

    private int mParallaxImageHeight;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        changeActionBarTransparency(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScrollView = (ObservableScrollView) inflater.inflate(R.layout.fragment_course_details, container, false);
        mScrollView.setScrollViewCallbacks(this);

        mImageView = mScrollView.findViewById(R.id.course_detail_header_image);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.drawer_menu_width);

        return mScrollView;

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        float alpha = 1 - Math.max(0.0f, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
        alpha = Math.min(0.9f, alpha);

        // color of the toolbar changes when user scrolls
        changeActionBarTransparency(alpha);

        // parallax scrolling
        ViewHelper.setTranslationY(mImageView, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    private void changeActionBarTransparency(float alpha) {
        int baseColor = getResources().getColor(R.color.theme_default_primary);
        ColorDrawable backgroundDrawable =
                new ColorDrawable(ScrollUtils.getColorWithAlpha(alpha, baseColor));

        BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.getSupportActionBar().setBackgroundDrawable(backgroundDrawable);
    }
}
