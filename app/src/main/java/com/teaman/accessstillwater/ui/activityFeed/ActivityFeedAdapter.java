package com.teaman.accessstillwater.ui.activityFeed;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseMultiViewRecyclerAdapter;
import com.teaman.data.entities.Activity;

/**
 * Created by weava on 3/18/16.
 */
public class ActivityFeedAdapter extends BaseMultiViewRecyclerAdapter<Activity> {

    private static final int VIEW_TYPE_FRIEND = 0;
    private static final int VIEW_TYPE_REVIEW = 1;
    private static final int VIEW_TYPE_FAVORITE = 2;

    @IntDef({VIEW_TYPE_FRIEND, VIEW_TYPE_REVIEW, VIEW_TYPE_FAVORITE})
    public @interface ActivityFeedViewType {}

    @Override
    public RecyclerView.ViewHolder inflateViewHolder(View v, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_FAVORITE: return new FavoriteActivityViewHolder(v);
            case VIEW_TYPE_FRIEND: return new FriendActivityViewHolder(v);
            case VIEW_TYPE_REVIEW: return new ReviewActivityViewHolder(v);
            default: return new FriendActivityViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_FAVORITE:
                FavoriteActivityViewHolder favoriteHolder = (FavoriteActivityViewHolder) holder;
                favoriteHolder.bind(mElements.get(position));
                break;
            case VIEW_TYPE_FRIEND:
                FriendActivityViewHolder friendHolder = (FriendActivityViewHolder) holder;
                friendHolder.bind(mElements.get(position));
                break;
            case VIEW_TYPE_REVIEW:
                ReviewActivityViewHolder reviewHolder = (ReviewActivityViewHolder) holder;
                reviewHolder.bind(mElements.get(position));
                break;
        }
    }

    @Override
    public int getLayoutRes(int viewType) {
        switch (viewType) {
            case VIEW_TYPE_FAVORITE:
                return R.layout.card_favorite_activity_feed;
            case VIEW_TYPE_FRIEND:
                return R.layout.card_friend_activity_feed;
            case VIEW_TYPE_REVIEW:
                return R.layout.card_review_activity_feed;
            default:
                return R.layout.card_friend_activity_feed;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (mElements.get(position).getType()) {
            case "review":
                return VIEW_TYPE_REVIEW;
            case "favorite":
                return VIEW_TYPE_FAVORITE;
            case "friend":
                return VIEW_TYPE_FRIEND;
            default:
                return 0;
        }
    }
}
