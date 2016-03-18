package com.teaman.accessstillwater.ui.activityFeed;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.base.BaseRecyclerListFragment;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.entities.Activity;

import java.util.List;

/**
 * Created by weava on 3/18/16.
 */
public class ActivityFeedFragment extends BaseRecyclerListFragment {

    public static final String ACTIVITY_FEED_TYPE = "feedType";

    private LoginAdapter mLoginAdapter;
    private ParseUser mCurrentUser;

    private ActivityFeedAdapter mActivityFeedAdapter;
    private int mActivityFeedViewType;

    public static ActivityFeedFragment newInstance() {
        Bundle args = new Bundle();
        ActivityFeedFragment fragment = new ActivityFeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();
        mCurrentUser = mLoginAdapter.getBaseUser();
        mActivityFeedAdapter = new ActivityFeedAdapter(getActivity());
        initList(mActivityFeedAdapter, 1);
        queryData();
    }

    private void queryData() {

        Activity.getQuery()
                .include("establishment")
                .include("toUser")
                .include("review")
                .whereEqualTo("fromUser", mCurrentUser)
                .orderByDescending("createdAt").findInBackground(new FindCallback<Activity>() {

            @Override
            public void done(List<Activity> objects, ParseException e) {
                Log.d("# Activites", String.valueOf(objects.size()));

                if(objects != null) {
                    if(objects.size() > 0) {
                        for(Activity act : objects) {
                            act = act.fromParseObject(act);
                            mActivityFeedAdapter.add(act);
                        }
                    }
                }
            }
        });
    }
}
