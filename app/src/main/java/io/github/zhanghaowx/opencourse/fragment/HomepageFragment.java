package io.github.zhanghaowx.opencourse.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.Arrays;
import java.util.List;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.adapter.course.CourseListAdapter;
import io.github.zhanghaowx.opencourse.fragment.core.BaseFragment;

/**
 * fragment for homepage
 */
public class HomepageFragment extends BaseFragment {
    private static final String TAG = HomepageFragment.class.getSimpleName();

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
        mPagerSlidingTabStrip.setTextColor(res.getColor(R.color.theme_default_light));
        mPagerSlidingTabStrip.setDividerColor(res.getColor(R.color.theme_default_primary));
        mPagerSlidingTabStrip.setIndicatorColor(res.getColor(R.color.theme_default_primary_dark));
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
