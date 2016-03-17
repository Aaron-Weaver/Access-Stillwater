package com.teaman.accessstillwater.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseActivity;
import com.teaman.data.authorization.LoginAdapter;

import butterknife.Bind;

/**
 * Created by weava on 3/12/16.
 */
public class ProfileActivity extends BaseActivity
{
    private final String IMAGE_TRANS = "imageTrans";

    private ProfileFragment mProfileFragment;

    @Bind(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Bind(R.id.profile_app_bar)
    protected AppBarLayout mAppBarLayout;

    @Bind(R.id.parallax_background)
    protected ImageView mProfileBackground;

    private LoginAdapter mLoginAdapter;

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

        mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();

        mProfileFragment = ProfileFragment.newInstance();

        this.addFragmentToContainer(mProfileFragment, "profileFragment");

        ViewCompat.setTransitionName(mAppBarLayout, IMAGE_TRANS);

        Picasso.with(this)
                .load(mLoginAdapter.getUser().getUserAvatar())
                .resize(300, 300)
                .centerCrop()
                .placeholder(R.drawable.ic_nav_drawer_background)
                .into(mProfileBackground);

        mCollapsingToolbarLayout.setTitle(mLoginAdapter.getUser().getDisplayName(false));
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));

        mCollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        mCollapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.base_activity_collapsible_toolbar;
    }
}
