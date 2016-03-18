package com.teaman.accessstillwater.ui.review;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.base.BaseRecyclerListFragment;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.entities.Activity;

import java.util.List;

/**
 * Created by weava on 3/16/16.
 */
public class ReviewListFragment extends BaseRecyclerListFragment implements ItemCallback<Activity> {

    public static final String REVIEW_LIST_TYPE =
            "ReviewListType";

    public static final int FRAGMENT_USER = 0;
    public static final int FRAGMENT_ESTABLISHMENT = 1;

    @IntDef({FRAGMENT_USER, FRAGMENT_ESTABLISHMENT})
    public @interface ReviewListType {}

    private ParseUser mCurrentUser;
    private LoginAdapter mLoginAdapter;
    private ReviewListAdapter mReviewListAdapter;
    private int mReviewListType;

    public static ReviewListFragment newInstance(@ReviewListType int type) {
        Bundle args = new Bundle();
        ReviewListFragment fragment = new ReviewListFragment();
        args.putInt(REVIEW_LIST_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();
        mCurrentUser = mLoginAdapter.getBaseUser();
        mReviewListAdapter = new ReviewListAdapter(this, getActivity());
        mReviewListType = getArguments().getInt(REVIEW_LIST_TYPE);
        initList(mReviewListAdapter, 1);
        queryData();
    }

    @Override
    public void onCallback(Activity item) {

    }

    public void queryData() {
        mReviewListAdapter.clear();

        if(mReviewListType == FRAGMENT_USER) {
            Activity.getQuery()
                    .include("review")
                    .include("establishment")
                    .whereEqualTo("fromUser", mLoginAdapter.getBaseUser())
                    .whereEqualTo("type", Activity.TYPE_REVIEW).findInBackground(new FindCallback<Activity>() {
                @Override
                public void done(List<Activity> objects, ParseException e) {
                    if(objects != null) {
                        if(objects.size() > 0) {
                            for(Activity act : objects) {
                                act = act.fromParseObject(act);
                                Log.d("Review List", act.getType());

                                mReviewListAdapter.add(act);
                            }
                        }
                    }
                }
            });

        } else if(mReviewListType == FRAGMENT_ESTABLISHMENT) {

        }
    }
}
