package io.github.zhanghaowx.opencourse.adapter.course;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.course.CourseDetailActivity;
import io.github.zhanghaowx.opencourse.model.course.Course;
import io.github.zhanghaowx.opencourse.model.course.Instructor;

/**
 * Adapter to create view holder for RecyclerViewFragment
 */
public class CourseCardsAdapter extends RecyclerView.Adapter<CourseCardsAdapter.CourseCardViewHolder> {
    private static final String TAG = CourseCardsAdapter.class.getSimpleName();

    private List<Course> mListCourseCard;
    private Activity mActivity;

    public CourseCardsAdapter(Activity activity, List<Course> listCourseCard) {
        this.mListCourseCard = listCourseCard;
        this.mActivity = activity;
    }

    public void setListCourseCard(List<Course> listCourseCard) {
        mListCourseCard = listCourseCard;
    }

    @Override
    public CourseCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseCardViewHolder(mActivity, LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_course_list_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final CourseCardViewHolder holder, int position) {
        final Course courseCard = mListCourseCard.get(position);
        holder.itemView.setTag(courseCard);

        /**
         * Download and show images in card
         */
        holder.mCourseTitleView.setText(courseCard.getTitle());
        holder.mCourseShortSummaryView.setText(courseCard.getShortSummary());
        Picasso.with(holder.mCourseImageView.getContext())
                .load(courseCard.getBannerImage())
                .error(R.drawable.placeholder_card_view)
                .placeholder(R.drawable.placeholder_card_view)
                .into(holder.mCourseImageView);

        if (courseCard.getInstructors() != null &&
                !courseCard.getInstructors().isEmpty()) {
            Instructor mainInstructor = courseCard.getInstructors().get(0);

            holder.mInstructorNameView.setText(mainInstructor.getFullName());
            Picasso.with(holder.mInstructorImageView.getContext())
                    .load(mainInstructor.getPhoto())
                    .error(R.drawable.placeholder_user_profile_picture)
                    .placeholder(R.drawable.placeholder_user_profile_picture)
                    .into(holder.mInstructorImageView);
        } else {
            Log.d(TAG, String.format("Instructor information is not found for course at position #%d", position));
        }
    }

    @Override
    public int getItemCount() {
        return mListCourseCard.size();
    }

    /**
     * Holds a card view for a course
     */
    public static class CourseCardViewHolder extends RecyclerView.ViewHolder {
        // Parent for all other views
        private CardView mCardView;

        private ImageView mCourseImageView;
        private ImageView mInstructorImageView;
        private TextView mCourseTitleView;
        private TextView mCourseShortSummaryView;
        private TextView mInstructorNameView;

        private Activity mActivity;

        public CourseCardViewHolder(Activity activity, View itemView) {
            super(itemView);
            mActivity = activity;

            // Load view components
            mCardView = (CardView) itemView.findViewById(R.id.course_card_view);
            mCourseImageView = (ImageView) mCardView.findViewById(R.id.course_card_view_image);
            mCourseTitleView = (TextView) mCardView.findViewById(R.id.course_card_view_title);
            mCourseShortSummaryView = (TextView) mCardView.findViewById(R.id.course_card_view_short_summary);

            mInstructorImageView = (ImageView) mCardView.findViewById(R.id.course_card_view_instructor_thumbnail);
            mInstructorNameView = (TextView) mCardView.findViewById(R.id.course_card_view_instructor_name);

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity,
                                mCourseImageView, mCourseImageView.getTransitionName());
                        mActivity.startActivity(new Intent(mActivity, CourseDetailActivity.class),
                                options.toBundle());
                    } else {
                        mActivity.startActivity(new Intent(mActivity, CourseDetailActivity.class));
                    }
                }
            });
        }
    }
}
