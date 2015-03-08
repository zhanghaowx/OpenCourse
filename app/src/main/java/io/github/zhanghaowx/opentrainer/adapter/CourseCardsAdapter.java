package io.github.zhanghaowx.opentrainer.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.zhanghaowx.opentrainer.R;
import io.github.zhanghaowx.opentrainer.activity.course.CourseDetailActivity;
import io.github.zhanghaowx.opentrainer.model.CourseCardViewBean;

/**
 * Adapter to create view holder for RecyclerViewFragment
 */
public class CourseCardsAdapter extends RecyclerView.Adapter<CourseCardsAdapter.ViewHolder> {
    private static final String TAG = CourseCardsAdapter.class.getSimpleName();

    private final List<CourseCardViewBean> mListItemsCard;
    private Activity mActivity;

    public CourseCardsAdapter(Activity activity, List<CourseCardViewBean> listItemsCard) {
        this.mListItemsCard = listItemsCard;
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mActivity, LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_view_comp, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CourseCardViewBean itemCardView = mListItemsCard.get(position);
        holder.itemView.setTag(itemCardView);
        Log.d(TAG, "Load Image into ViewHolder " + holder.mImageView);

        Picasso.with(holder.mImageView.getContext())
                .load(itemCardView.getImageUrl())
                .error(R.drawable.placeholder_card_view)
                .placeholder(R.drawable.placeholder_card_view)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mListItemsCard.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        private Activity mActivity;

        public ViewHolder(Activity activity , View itemView) {
            super(itemView);
            mActivity = activity;
            mImageView = (ImageView) itemView.findViewById(R.id.material_com_card_view_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.startActivity(new Intent(mActivity, CourseDetailActivity.class));
                }
            });
        }

    }
}
