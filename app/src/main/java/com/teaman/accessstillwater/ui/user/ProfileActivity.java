package com.teaman.accessstillwater.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teaman.accessstillwater.base.BaseActivity;

/**
 * Created by weava on 3/12/16.
 */
public class ProfileActivity extends BaseActivity
{
    private ProfileFragment mProfileFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.enableBackNav();

        mProfileFragment = ProfileFragment.newInstance();

        this.addFragmentToContainer(mProfileFragment, "profileFragment");
    }
}
