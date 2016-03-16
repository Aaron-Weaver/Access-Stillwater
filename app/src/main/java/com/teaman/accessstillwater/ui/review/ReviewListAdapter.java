package com.teaman.accessstillwater.ui.review;

import android.content.Context;
import android.view.View;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseRecyclerAdapter;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.entities.Review;

/**
 * Created by weava on 3/16/16.
 */
public class ReviewListAdapter extends BaseRecyclerAdapter<Review, ReviewViewHolder> {

    private ItemCallback<Review> mReviewItemCallback;
    private Context mContext;

    public ReviewListAdapter(ItemCallback<Review> establishmentItemCallback, Context context) {
        this.mReviewItemCallback = establishmentItemCallback;
        mContext = context;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_review;
    }

    @Override
    public ReviewViewHolder inflateViewHolder(View v) {
        return null;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {

    }
}
