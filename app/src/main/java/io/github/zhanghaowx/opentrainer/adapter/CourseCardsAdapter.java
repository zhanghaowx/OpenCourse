package io.github.zhanghaowx.opentrainer.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.zhanghaowx.opentrainer.R;
import io.github.zhanghaowx.opentrainer.activity.course.CourseDetailActivity;
import io.github.zhanghaowx.opentrainer.model.CourseCardViewBean;

/**
 * Adapter to create view holder for RecyclerViewFragment
 */
public class CourseCardsAdapter extends RecyclerView.Adapter<CourseCardsAdapter.CourseCardViewHolder> {
    private static final String TAG = CourseCardsAdapter.class.getSimpleName();

    private final List<CourseCardViewBean> mListItemsCard;
    private Activity mActivity;

    public CourseCardsAdapter(Activity activity, List<CourseCardViewBean> listItemsCard) {
        this.mListItemsCard = listItemsCard;
        this.mActivity = activity;
    }

    @Override
    public CourseCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseCardViewHolder(mActivity, LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_course_list_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final CourseCardViewHolder holder, int position) {
        final CourseCardViewBean courseCard = mListItemsCard.get(position);
        holder.itemView.setTag(courseCard);

        /**
         * Download and show images in card
         */
        holder.mTitleView.setText(courseCard.getTitle());
        Picasso.with(holder.mImageView.getContext())
                .load(courseCard.getImageUrl())
                .error(R.drawable.placeholder_card_view)
                .placeholder(R.drawable.placeholder_card_view)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mListItemsCard.size();
    }

    /**
     * Holds a card view for a course
     */
    public static class CourseCardViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private TextView mTitleView;
        private ImageView mImageView;
        private Activity mActivity;

        public CourseCardViewHolder(Activity activity, View itemView) {
            super(itemView);
            mActivity = activity;
            mCardView = (CardView) itemView.findViewById(R.id.course_card_view);

            // Load view components
            mTitleView = (TextView) mCardView.findViewById(R.id.course_card_view_title);
            mImageView = (ImageView) mCardView.findViewById(R.id.course_card_view_image);

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.startActivity(new Intent(mActivity, CourseDetailActivity.class));
                }
            });
        }
    }
}
