package com.teaman.accessstillwater.ui.review;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.view.View;

import com.parse.ParseUser;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.base.BaseRecyclerListFragment;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.accessstillwater.ui.establishment.EstablishmentListFragment;
import com.teaman.accessstillwater.view.DividerItemDecoration;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.entities.Review;

/**
 * Created by weava on 3/16/16.
 */
public class ReviewListFragment extends BaseRecyclerListFragment implements ItemCallback<Review> {

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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        initList(mReviewListAdapter, 1);
        queryData();
    }

    @Override
    public void onCallback(Review item) {

    }

    public void queryData() {
        mReviewListAdapter.clear();

        if(mReviewListType == FRAGMENT_USER) {

        } else if(mReviewListType == FRAGMENT_ESTABLISHMENT) {

        }
    }
}
