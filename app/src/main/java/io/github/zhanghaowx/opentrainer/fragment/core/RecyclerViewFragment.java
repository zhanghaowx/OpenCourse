package io.github.zhanghaowx.opentrainer.fragment.core;

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
import io.github.zhanghaowx.opentrainer.adapter.RecyclerViewCardsAdapter;
import io.github.zhanghaowx.opentrainer.model.CardViewBean;

/**
 * A fragment which uses recycler view + card view to display its content
 */
public class RecyclerViewFragment extends BaseFragment {
    private View mViewRecyclerCardsView;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mViewRecyclerCardsView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mRecyclerView = (RecyclerView) mViewRecyclerCardsView.findViewById(R.id.fragment_recycler_view_content_main);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new RecyclerViewCardsAdapter(getActivity(), createMockCardList()));

        mFloatingActionButton = (FloatingActionButton) mViewRecyclerCardsView.findViewById(R.id.fragment_recycler_view_float_action_button);
        mFloatingActionButton.attachToRecyclerView(mRecyclerView);

        return mViewRecyclerCardsView;
    }

    private List<CardViewBean> createMockCardList() {
        List<CardViewBean> cardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cardList.add(new CardViewBean("http://lorempixel.com/800/400/nightlife/" + i));
        }
        return cardList;
    }
}
