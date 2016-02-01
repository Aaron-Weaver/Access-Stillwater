package com.teaman.accessstillwater;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
}
