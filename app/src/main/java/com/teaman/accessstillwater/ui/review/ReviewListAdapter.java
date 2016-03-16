package com.teaman.accessstillwater.ui.review;

import android.content.Context;
import android.view.View;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseRecyclerAdapter;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.entities.Activity;

/**
 * Created by weava on 3/16/16.
 */
public class ReviewListAdapter extends BaseRecyclerAdapter<Activity, ReviewViewHolder> {

    private ItemCallback<Activity> mActivityItemCallback;
    private Context mContext;

    public ReviewListAdapter(ItemCallback<Activity> activityItemCallback, Context context) {
        mActivityItemCallback = activityItemCallback;
        mContext = context;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_review;
    }

    @Override
    public ReviewViewHolder inflateViewHolder(View v) {
        return new ReviewViewHolder(v, mActivityItemCallback, mContext);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bind(mElements.get(position));
    }
}
