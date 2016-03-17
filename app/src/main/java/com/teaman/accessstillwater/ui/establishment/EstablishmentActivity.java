package com.teaman.accessstillwater.ui.establishment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseActivity;
import com.teaman.accessstillwater.utils.StringUtils;

/**
 * Created by weava on 3/12/16.
 */
public class EstablishmentActivity extends BaseActivity
{
    @EstablishmentListFragment.EstablishmentListType int mEstablishmentFragmentType;

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

        if(mEstablishmentFragmentType == EstablishmentListFragment.FRAGMENT_FAVORITE) {
            setTitle(getString(R.string.activity_user_favorites));
        }

        mEstablishmentListFragment = EstablishmentListFragment.newInstance(mEstablishmentFragmentType);

        this.addFragmentToContainer(mEstablishmentListFragment, "establishmentFragment");
    }
}
