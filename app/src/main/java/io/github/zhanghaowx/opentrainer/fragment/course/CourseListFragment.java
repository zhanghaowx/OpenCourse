package io.github.zhanghaowx.opentrainer.fragment.course;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.Arrays;
import java.util.List;

import io.github.zhanghaowx.opentrainer.R;
import io.github.zhanghaowx.opentrainer.adapter.CourseListAdapter;
import io.github.zhanghaowx.opentrainer.fragment.core.BaseFragment;

/**
 * fragment for course list
 */
public class CourseListFragment extends BaseFragment {
    private static final String TAG = CourseListFragment.class.getSimpleName();
    private View mViewCourseList;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewCourseList = inflater.inflate(R.layout.fragment_default, container, false);

        mViewPager = (ViewPager) mViewCourseList.findViewById(R.id.fragment_home_view_pager);
        mViewPager.setAdapter(new CourseListAdapter(getToolbarMenuTitles(), getChildFragmentManager()));

        mPagerSlidingTabStrip = (PagerSlidingTabStrip) mViewCourseList.findViewById(R.id.fragment_home_pager_sliding_tab);
        Resources res = getActivity().getResources();
        mPagerSlidingTabStrip.setTextColor(res.getColor(R.color.theme_dialer_accent));
        mPagerSlidingTabStrip.setDividerColor(res.getColor(R.color.theme_dialer_primary));
        mPagerSlidingTabStrip.setIndicatorColor(res.getColor(R.color.theme_dialer_high));
        mPagerSlidingTabStrip.setViewPager(mViewPager);

        return mViewCourseList;
    }

    /**
     * Read head bar menu titles from resources file and create a list to hold them.
     *
     * @return
     */
    private List<String> getToolbarMenuTitles() {
        return Arrays.asList(getActivity().getResources().getStringArray(R.array.fragment_courseList_sections_tabs_title));
    }
}
