package io.github.zhanghaowx.opentrainer.activity.course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import io.github.zhanghaowx.opentrainer.R;
import io.github.zhanghaowx.opentrainer.activity.core.BaseActivity;

/**
 * Activity for detailed course information
 */
public class CourseDetailActivity extends BaseActivity implements ObservableScrollViewCallbacks {
    private View mImageView;
    private View mToolbarView;

    /**
     * Manages display of tool bar when user scrolls
     */
    private ObservableScrollView mScrollView;

    private int mParallaxImageHeight;


    @Override
    protected Fragment getFragment() {
        return null;
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }

    @Override
    protected int getLayout() {
        return R.layout.screen_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImageView = findViewById(R.id.image);
        mToolbarView = findViewById(R.id.toolbar);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.theme_default_primary)));

        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);

        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.drawer_menu_width);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        float alpha = 1 - Math.max(0.0f, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
        alpha = Math.min(0.9f, alpha);

        // color of the toolbar changes when user scrolls
        int baseColor = getResources().getColor(R.color.theme_default_primary);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));

        // parallax scrolling
        ViewHelper.setTranslationY(mImageView, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
