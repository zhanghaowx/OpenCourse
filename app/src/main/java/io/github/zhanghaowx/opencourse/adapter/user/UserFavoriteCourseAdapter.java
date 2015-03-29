package io.github.zhanghaowx.opencourse.adapter.user;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.model.course.Course;

public class UserFavoriteCourseAdapter extends ArrayAdapter<Course> {

    private static final String TAG = UserFavoriteCourseAdapter.class.getSimpleName();

    public UserFavoriteCourseAdapter(Context context, int resource, ArrayList<Course> courses) {
        super(context, resource, courses);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CourseViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_user_favorite_course_item, null);

            viewHolder = new CourseViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (CourseViewHolder) convertView.getTag();
        }

        Course course = getItem(position);
        viewHolder.mCourseTitleView.setText(course.getTitle());
        viewHolder.mCourseShortSummaryView.setText(course.getShortSummary());

        Picasso.with(viewHolder.mCourseImageView.getContext())
                .load(course.getBannerImage())
                .fit()
                .centerCrop()
                .error(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(viewHolder.mCourseImageView);

        if (course.getInstructors() != null) {
            String instructors = "";
            for (int i = 0; i < course.getInstructors().size(); i++) {
                instructors += instructors.isEmpty() ? "" : ", ";
                instructors += course.getInstructors().get(i).getFullName();
            }
            viewHolder.mCourseInstructorsView.setText(instructors);

        } else {
            Log.d(TAG, String.format("Instructor information is not found for course at position #%d", position));
        }

        return convertView;
    }

    private class CourseViewHolder {
        ImageView mCourseImageView;
        TextView mCourseTitleView;
        TextView mCourseInstructorsView;
        TextView mCourseShortSummaryView;

        public CourseViewHolder(View rootView) {
            mCourseImageView = (ImageView) rootView.findViewById(R.id.user_favorite_course_thumbnail_image);
            mCourseTitleView = (TextView) rootView.findViewById(R.id.user_favorite_course_title);
            mCourseInstructorsView = (TextView) rootView.findViewById(R.id.user_favorite_course_instructors_name);
            mCourseShortSummaryView = (TextView) rootView.findViewById(R.id.user_favorite_course_short_summary);
        }
    }
}
