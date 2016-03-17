package com.teaman.accessstillwater.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teaman.accessstillwater.base.BaseActivity;

/**
 * Created by weava on 3/12/16.
 */
public class ProfileActivity extends BaseActivity
{
    private ProfileFragment mProfileFragment;

    public static Intent getCallingIntent(Context context)
    {
        Intent callingIntent = new Intent(context, ProfileActivity.class);
        return callingIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.enableBackNav();

        mProfileFragment = ProfileFragment.newInstance();

        this.addFragmentToContainer(mProfileFragment, "profileFragment");
    }
}
