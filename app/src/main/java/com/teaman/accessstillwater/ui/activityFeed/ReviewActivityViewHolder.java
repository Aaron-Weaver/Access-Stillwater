package com.teaman.accessstillwater.ui.activityFeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teaman.data.authorization.parse.ParseUserAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.Review;

import butterknife.ButterKnife;

/**
 * Created by weava on 3/18/16.
 */
public class ReviewActivityViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private Activity mActivity;
    private ParseUserAdapter mFromUser;
    private Establishment mEstablishment;
    private Review mReview;

    public ReviewActivityViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    public void bind(Activity act) {
        mActivity = act;
    }
}
