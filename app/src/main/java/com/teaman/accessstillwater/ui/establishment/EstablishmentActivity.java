package com.teaman.accessstillwater.ui.establishment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseDrawerActivity;
import com.teaman.accessstillwater.utils.StringUtils;

/**
 * Created by weava on 3/12/16.
 */
public class EstablishmentActivity extends BaseDrawerActivity
{
    @EstablishmentListFragment.EstablishmentListType int mEstablishmentFragmentType;

    @EstablishmentListFragment.SearchListType int mSearchFilterType;

    private EstablishmentListFragment mEstablishmentListFragment;

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, EstablishmentActivity.class);
        return callingIntent;
    }

    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.enableBackNav();

        mEstablishmentFragmentType = getIntent().getIntExtra(StringUtils.ESTABLISHMENT_TYPE_EXTRA,
                EstablishmentListFragment.FRAGMENT_FAVORITE);

        mSearchFilterType = getIntent().getIntExtra(StringUtils.SEARCH_FILTER_EXTRA, 0);

        if(mEstablishmentFragmentType == EstablishmentListFragment.FRAGMENT_FAVORITE) {
            setTitle(getString(R.string.activity_user_favorites));
            mEstablishmentListFragment = EstablishmentListFragment.newInstance(mEstablishmentFragmentType);
        } else {
            switch (mSearchFilterType) {
                case EstablishmentListFragment.SEARCH_AUDITORY:
                    setTitle("Best Auditory");
                    break;
                case EstablishmentListFragment.SEARCH_PHYSICAL:
                    setTitle("Best Physical");
                    break;
                case EstablishmentListFragment.SEARCH_VISUAL:
                    setTitle("Best Visual");
            }
            mEstablishmentListFragment = EstablishmentListFragment.newInstanceForSearchFilter(mSearchFilterType);
        }

        this.addFragmentToContainer(mEstablishmentListFragment, "establishmentFragment");
    }
}
