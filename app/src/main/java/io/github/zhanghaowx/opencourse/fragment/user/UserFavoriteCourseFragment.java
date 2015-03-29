package io.github.zhanghaowx.opencourse.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.adapter.user.UserFavoriteCourseAdapter;
import io.github.zhanghaowx.opencourse.datasource.parse.ParseCourseSource;
import io.github.zhanghaowx.opencourse.datasource.utils.SearchCallback;
import io.github.zhanghaowx.opencourse.fragment.core.BaseFragment;
import io.github.zhanghaowx.opencourse.model.course.Course;

/**
 * A list of user favorite courses
 */
public class UserFavoriteCourseFragment extends BaseFragment {

    private static final String TAG = UserFavoriteCourseFragment.class.getSimpleName();

    private ListView mUserFavoriteCourseListView;

    public static UserFavoriteCourseFragment newInstance() {
        return new UserFavoriteCourseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_user_favorite_course_list, container, false);

        mUserFavoriteCourseListView = (ListView) rootView.findViewById(R.id.user_favorite_course_list);
        final UserFavoriteCourseAdapter adapter = new UserFavoriteCourseAdapter(
                getActivity().getApplicationContext(),
                R.layout.fragment_user_favorite_course_item,
                new ArrayList<Course>());
        mUserFavoriteCourseListView.setAdapter(adapter);

        ParseCourseSource.getInstance().getCoursesAsync(new SearchCallback<Course>() {
            @Override
            public void success(List<Course> courses) {
                adapter.addAll(courses);
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }
}
