package io.github.zhanghaowx.opencourse.adapter.course;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import io.github.zhanghaowx.opencourse.fragment.course.CourseListFragment;

/**
 * Adapter that creates necessary view objects for CourseListFragment
 */
public class CourseListAdapter extends FragmentPagerAdapter {
    private List<String> mListTitleTabs;

    public CourseListAdapter(List<String> listTitleTabs, FragmentManager childFragmentManager) {
        super(childFragmentManager);
        this.mListTitleTabs = listTitleTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mListTitleTabs == null || mListTitleTabs.isEmpty()) {
            return "";
        }
        return mListTitleTabs.get(position);
    }

    @Override
    public int getCount() {
        if (mListTitleTabs == null || mListTitleTabs.isEmpty()) {
            return 0;
        }
        return mListTitleTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CourseListFragment.newInstance();
            case 1:
                return CourseListFragment.newInstance();
            case 2:
                return CourseListFragment.newInstance();
            default:
                return new Fragment();
        }
    }

}
