package com.teaman.accessstillwater.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.teaman.accessstillwater.R;

import butterknife.ButterKnife;

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
 * @since 1/31/16
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this); // Bind the views of this Activity

//        if(mToolbar != null) {
//            // Sets the SupportActionBar
//            setSupportActionBar(mToolbar);
//        }
//
//        if(mFragmentManager == null) {
//            mFragmentManager = getFragmentManager(); // Gets a reference to the FragmentManager
//        }
    }

    @LayoutRes
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    /**
     * Below are the Android Activity Lifecycle management methods.
     * These do not have any custom code, I just leave them here for easy access
     * when overriding methods in child classes.
     */

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
