package com.teaman.accessstillwater.ui.review;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseDrawerActivity;
import com.teaman.accessstillwater.ui.establishment.EstablishmentActivity;
import com.teaman.accessstillwater.ui.establishment.EstablishmentListFragment;
import com.teaman.accessstillwater.utils.StringUtils;

/**
 * Created by weava on 3/16/16.
 */
public class ReviewListActivity extends BaseDrawerActivity {

    private ReviewListFragment mReviewListFragment;

    @ReviewListFragment.ReviewListType int mReviewListType;

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, EstablishmentActivity.class);
        return callingIntent;
    }

    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mReviewListType = getIntent().getIntExtra(StringUtils.ESTABLISHMENT_TYPE_EXTRA,
                EstablishmentListFragment.FRAGMENT_FAVORITE);

        if(mReviewListType == EstablishmentListFragment.FRAGMENT_FAVORITE) {
            setTitle(getString(R.string.activity_favorites));
        }

        mReviewListFragment = ReviewListFragment.newInstance(mReviewListType);

        this.addFragmentToContainer(mReviewListFragment, "establishmentFragment");
    }
}
