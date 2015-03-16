package io.github.zhanghaowx.opencourse.fragment.course;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
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

    private Course mCourse;
    private CourseDetailViewHolder mViewHolder;
    private ObservableScrollView mScrollView;

    private int mParallaxImageHeight;

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
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.card_view_height);

        changeActionBarTransparency(0);
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
        float alpha = 1 - Math.max(0.0f, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
        alpha = Math.min(1.0f, alpha);

        // color of the toolbar changes when user scrolls
        changeActionBarTransparency(alpha);

        // parallax scrolling
        ViewHelper.setTranslationY(mViewHolder.mCourseImageView, scrollY / 2);
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

    private void changeActionBarHeight(float height) {
        BaseActivity baseActivity = (BaseActivity) getActivity();
    }

    public static class CourseDetailViewHolder {
        private ImageView mCourseImageView;
        private TextView mCourseTitleView;
        private TextView mCourseSubtitleView;

        private ImageView mInstructorImageView;
        private TextView mInstructorNameView;

        private TextView mCourseSummaryView;
        private TextView mCourseFaqView;

        private Activity mActivity;

        public CourseDetailViewHolder(Activity activity, View itemView) {
            mActivity = activity;

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
