package com.teaman.accessstillwater.ui.activityFeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teaman.data.authorization.parse.ParseUserAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;

import butterknife.ButterKnife;

/**
 * Created by weava on 3/18/16.
 */
public class FavoriteActivityViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private Activity mActivity;
    private Establishment mEstablishment;
    private ParseUserAdapter mFromUser;

    public FavoriteActivityViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    public void bind(Activity act) {
        mActivity = act;
    }
}
