package com.teaman.accessstillwater.ui.review;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseDrawerActivity;
import com.teaman.accessstillwater.utils.StringUtils;

/**
 * Created by weava on 3/16/16.
 */
public class ReviewListActivity extends BaseDrawerActivity {

    private ReviewListFragment mReviewListFragment;

    @ReviewListFragment.ReviewListType int mReviewListType;

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, ReviewListActivity.class);
        return callingIntent;
    }

    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.enableBackNav();
        mReviewListType = getIntent().getIntExtra(StringUtils.REVIEW_TYPE_EXTRA,
                ReviewListFragment.FRAGMENT_USER);

        if(mReviewListType == ReviewListFragment.FRAGMENT_USER) {
            setTitle(getString(R.string.activity_user_reviews));
        }

        mReviewListFragment = ReviewListFragment.newInstance(mReviewListType);


        this.addFragmentToContainer(mReviewListFragment, "establishmentFragment");
    }
}
