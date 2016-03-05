package com.teaman.accessstillwater.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.data.User;
import com.teaman.data.authorization.LoginAdapter;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <h1> [Insert class name here] </h1>
 * <p>
 * [Insert class description here]
 * </p>
 * <p>
 * [Insert additional information here (links, code snippets, etc.)]
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 2/25/16
 */
public abstract class BaseDrawerActivity extends BaseActivity {

    @Bind(R.id.nav_drawer_layout)
    protected DrawerLayout mDrawerLayout;

    @Bind(R.id.nav_view)
    protected NavigationView mNavMenu;

    @Nullable
    @Bind(R.id.header_profile_image)
    protected CircleImageView mProfileImage;

    @Nullable
    @Bind(R.id.header_profile_name)
    protected TextView mProfileName;

    private User mCurrentUser;
    private LoginAdapter mLoginAdapter;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(mLoginAdapter.isLoggedIn()) {
            mCurrentUser = mLoginAdapter.getUser();
        }

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

//                boolean opening = slideOffset > last;
//                boolean closing = slideOffset < last;
//
//                if (opening) {
//
//                } else if (closing) {
//
//                }

            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.base_activity_drawer;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout != null) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
