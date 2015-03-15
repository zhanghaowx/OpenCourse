package io.github.zhanghaowx.opencourse.fragment.course;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.adapter.course.CourseCardsAdapter;
import io.github.zhanghaowx.opencourse.datasource.parse.ParseCourseSource;
import io.github.zhanghaowx.opencourse.datasource.utils.SearchCallback;
import io.github.zhanghaowx.opencourse.fragment.core.BaseFragment;
import io.github.zhanghaowx.opencourse.model.course.BaseModel;
import io.github.zhanghaowx.opencourse.model.course.Course;

/**
 * A fragment which uses recycler view + card view to display its content
 */
public class CourseListFragment extends BaseFragment {
    private static final String TAG = CourseListFragment.class.getSimpleName();

    private View mViewRecyclerCardsView;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;

    public static CourseListFragment newInstance() {
        return new CourseListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mViewRecyclerCardsView = inflater.inflate(R.layout.fragment_course_list, container, false);

        mRecyclerView = (RecyclerView) mViewRecyclerCardsView.findViewById(R.id.fragment_recycler_view_content_main);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        final CourseCardsAdapter courseCardsAdapter = new CourseCardsAdapter(getActivity(), new ArrayList<Course>());
        ParseCourseSource.getInstance().getCoursesAsync(new SearchCallback<Course>() {
            @Override
            public void success(List<Course> courses) {
                courseCardsAdapter.setListCourseCard(courses);
                courseCardsAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView.setAdapter(courseCardsAdapter);

        mFloatingActionButton = (FloatingActionButton) mViewRecyclerCardsView.findViewById(R.id.fragment_recycler_view_float_action_button);
        mFloatingActionButton.attachToRecyclerView(mRecyclerView);

        return mViewRecyclerCardsView;
    }
}
