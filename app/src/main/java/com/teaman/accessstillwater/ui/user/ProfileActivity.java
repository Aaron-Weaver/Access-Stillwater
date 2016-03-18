package com.teaman.accessstillwater.ui.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
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

    @Bind(R.id.profile_view_picture)
    protected ImageView mProfilePicture;

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
                .fit()
                .placeholder(R.drawable.ic_account_circle_white_48dp)
                .into(mProfilePicture);

        mCollapsingToolbarLayout.setTitle(mLoginAdapter.getUser().getDisplayName(true));

        Drawable backgroundImageDraw = mProfileBackground.getDrawable();

        if (backgroundImageDraw instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) backgroundImageDraw;
            Bitmap bm = bitmapDrawable.getBitmap();
            Palette.from(bm).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {

                    if(palette.getDarkMutedSwatch() != null ) {
                        Palette.Swatch swatch = palette.getDarkMutedSwatch();

                        mCollapsingToolbarLayout.setExpandedTitleColor(swatch.getBodyTextColor());
                    }
                }
            });
        }

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.base_activity_collapsible_toolbar;
    }
}
