package io.github.zhanghaowx.opencourse.fragment.course;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.core.BaseActivity;
import io.github.zhanghaowx.opencourse.datasource.parse.ParseCourseSource;
import io.github.zhanghaowx.opencourse.datasource.utils.SearchCallback;
import io.github.zhanghaowx.opencourse.fragment.core.BaseFragment;
import io.github.zhanghaowx.opencourse.model.course.Course;
import io.github.zhanghaowx.opencourse.model.course.Instructor;

public class CourseDetailFragment extends BaseFragment implements ObservableScrollViewCallbacks {
    public static final String EXTRA_COURSE_ID = "courseId";

    private static final String TAG = CourseDetailFragment.class.getSimpleName();
    private static final float MAX_TEXT_SCALE_DELTA = 0.1f;

    private Course mCourse;
    private CourseDetailViewHolder mViewHolder;
    private ObservableScrollView mScrollView;

    // Flexible Space (Header Image Space)
    private int mFlexibleSpaceShowFabOffset;
    private int mFlexibleSpaceImageHeight;

    // Floating Action Button (Favorite Button)
    private int mFabMargin;
    private boolean mFabIsShown;

    // Action Bar
    private int mActionBarSize;


    /**
     * Create by a course ID
     *
     * @param courseId
     * @return
     */
    public static CourseDetailFragment newInstance(String courseId) {
        Bundle args = new Bundle();
        args.putString(EXTRA_COURSE_ID, courseId);

        CourseDetailFragment fragment = new CourseDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Hide action bar
        BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.getSupportActionBar().hide();

        mActionBarSize = baseActivity.getSupportActionBar().getHeight();

        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.course_detail_header_image_height);
        mFlexibleSpaceShowFabOffset = getResources().getDimensionPixelOffset(R.dimen.course_detail_show_fab_offset);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScrollView = (ObservableScrollView) inflater.inflate(R.layout.fragment_course_details, container, false);
        mScrollView.setScrollViewCallbacks(this);

        String courseId = getArguments().getString(EXTRA_COURSE_ID);
        ParseCourseSource.getInstance().getCourseAsync(courseId, new SearchCallback<Course>() {
            @Override
            public void success(List<Course> courses) {
                mCourse = courses.get(0);
                mViewHolder = new CourseDetailViewHolder(getActivity(), mScrollView);

                Picasso.with(mViewHolder.mCourseImageView.getContext())
                        .load(mCourse.getBannerImage())
                        .error(R.drawable.placeholder_card_view)
                        .placeholder(R.drawable.placeholder_card_view)
                        .into(mViewHolder.mCourseImageView);
                mViewHolder.mCourseTitleView.setText(mCourse.getTitle());
                mViewHolder.mCourseSubtitleView.setText(mCourse.getSubtitle());

                setTextViewContent(mViewHolder.mCourseSummaryView, mCourse.getSummary());
                setTextViewContent(mViewHolder.mCourseFaqView, mCourse.getFaq());

                if (mCourse.getInstructors() != null &&
                        !mCourse.getInstructors().isEmpty()) {
                    Instructor mainInstructor = mCourse.getInstructors().get(0);

                    Picasso.with(mViewHolder.mInstructorImageView.getContext())
                            .load(mainInstructor.getPhoto())
                            .error(R.drawable.placeholder_user_profile_picture)
                            .placeholder(R.drawable.placeholder_user_profile_picture)
                            .into(mViewHolder.mInstructorImageView);
                    mViewHolder.mInstructorNameView.setText(mainInstructor.getFullName());
                } else {
                    Log.d(TAG, String.format("Instructor information is not found for course <%s>", mCourse.getTitle()));
                }
            }
        });

        return mScrollView;

    }

    /**
     * Set the content of a text view. When the content is empty, hide
     * the text view and its parent.
     *
     * @param view
     * @param content
     */
    private void setTextViewContent(TextView view, String content) {
        if (content != null && !content.isEmpty()) {
            view.setText(content);
        } else {
            try {
                View parent = (View) view.getParent();
                parent.setVisibility(View.GONE);
            } catch (ClassCastException e) {
                Log.e(TAG, "Error when hiding a view with no content", e);
            }
        }
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        translateOverlayAndImage(scrollY);
        showHideFabButton(scrollY);
    }

    private void translateOverlayAndImage(int scrollY) {
        // Translate overlay and image

        ViewHelper.setTranslationY(mViewHolder.mCourseImageOverlayView, scrollY / 4);
        ViewHelper.setTranslationY(mViewHolder.mCourseImageView, scrollY / 4);

        // Change alpha of overlay && toolbar
        float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        float alpha = ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1);

        ViewHelper.setAlpha(mViewHolder.mCourseImageOverlayView, alpha);
    }

    private void scaleTitleText(int scrollY) {
        float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        float scale = 1 - MAX_TEXT_SCALE_DELTA + ScrollUtils.getFloat(1 - (float) scrollY / flexibleRange, 0, 1) * MAX_TEXT_SCALE_DELTA;
        ViewHelper.setPivotX(mViewHolder.mCourseTitleView, 0);
        ViewHelper.setPivotY(mViewHolder.mCourseTitleView, 0);
        ViewHelper.setScaleX(mViewHolder.mCourseTitleView, scale);
        ViewHelper.setScaleY(mViewHolder.mCourseTitleView, scale);
    }

    private void changeActionBarTransparency(float alpha) {
        int baseColor = getResources().getColor(R.color.theme_default_primary);
        ColorDrawable backgroundDrawable =
                new ColorDrawable(ScrollUtils.getColorWithAlpha(alpha, baseColor));

        BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.getSupportActionBar().setBackgroundDrawable(backgroundDrawable);
    }

    /**
     * Show/Hide Favorite Button
     * @param scrollY
     */
    private void showHideFabButton(int scrollY) {
        int maxFabTranslationY = mFlexibleSpaceImageHeight - mViewHolder.mFab.getHeight() / 2;
        float fabTranslationY = ScrollUtils.getFloat(
                -scrollY + mFlexibleSpaceImageHeight - mViewHolder.mFab.getHeight() / 2,
                mActionBarSize - mViewHolder.mFab.getHeight() / 2,
                maxFabTranslationY);

        if (fabTranslationY < mFlexibleSpaceShowFabOffset) {
            hideFab();
        } else {
            showFab();
        }
    }

    private void showFab() {
        if (!mFabIsShown) {
            ViewPropertyAnimator.animate(mViewHolder.mFab).cancel();
            ViewPropertyAnimator.animate(mViewHolder.mFab).scaleX(1).scaleY(1).setDuration(200).start();
            mFabIsShown = true;
        }
    }

    private void hideFab() {
        if (mFabIsShown) {
            ViewPropertyAnimator.animate(mViewHolder.mFab).cancel();
            ViewPropertyAnimator.animate(mViewHolder.mFab).scaleX(0).scaleY(0).setDuration(200).start();
            mFabIsShown = false;
        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    /**
     * Holder class to manage all views belong to course details page
     */
    public static class CourseDetailViewHolder {

        private View mCourseImageOverlayView;
        private ImageView mCourseImageView;
        private TextView mCourseTitleView;
        private TextView mCourseSubtitleView;

        private ImageView mInstructorImageView;
        private TextView mInstructorNameView;

        private TextView mCourseSummaryView;
        private TextView mCourseFaqView;

        private View mToolbarBackground;
        private ImageButton mFab;

        private Activity mActivity;

        public CourseDetailViewHolder(Activity activity, View itemView) {
            mActivity = activity;

            mFab = (ImageButton) itemView.findViewById(R.id.course_detail_favorite_button);

            mCourseImageOverlayView = itemView.findViewById(R.id.course_detail_header_image_overlay);
            mCourseImageView = (ImageView) itemView.findViewById(R.id.course_detail_header_image);
            mCourseTitleView = (TextView) itemView.findViewById(R.id.course_detail_title);
            mCourseSubtitleView = (TextView) itemView.findViewById(R.id.course_detail_subtitle);

            mInstructorImageView = (ImageView) itemView.findViewById(R.id.course_detail_instructor_profile_image);
            mInstructorNameView = (TextView) itemView.findViewById(R.id.course_detail_instructor_name);

            mCourseSummaryView = (TextView) itemView.findViewById(R.id.course_detail_course_summary);
            mCourseFaqView = (TextView) itemView.findViewById(R.id.course_detail_course_faq);
        }
    }
}
