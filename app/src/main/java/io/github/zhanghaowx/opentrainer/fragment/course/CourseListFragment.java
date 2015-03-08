package io.github.zhanghaowx.opentrainer.fragment.course;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.github.zhanghaowx.opentrainer.R;
import io.github.zhanghaowx.opentrainer.adapter.CourseCardsAdapter;
import io.github.zhanghaowx.opentrainer.fragment.core.BaseFragment;
import io.github.zhanghaowx.opentrainer.model.CourseCardViewBean;

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

        mViewRecyclerCardsView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mRecyclerView = (RecyclerView) mViewRecyclerCardsView.findViewById(R.id.fragment_recycler_view_content_main);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new CourseCardsAdapter(getActivity(), createMockCardList()));

        mFloatingActionButton = (FloatingActionButton) mViewRecyclerCardsView.findViewById(R.id.fragment_recycler_view_float_action_button);
        mFloatingActionButton.attachToRecyclerView(mRecyclerView);

        return mViewRecyclerCardsView;
    }

    private List<CourseCardViewBean> createMockCardList() {
        List<CourseCardViewBean> cardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cardList.add(new CourseCardViewBean("http://lorempixel.com/800/400/nightlife/" + i));
        }
        return cardList;
    }
}
