package com.teaman.accessstillwater.ui.main;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.base.BaseActivity;

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
 * @since 1/30/16
 */
public class MainActivity extends BaseActivity {

    private AccessStillwaterApp mApplication;

    private Fragment mLoginFragment;

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, MainActivity.class);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mApplication = AccessStillwaterApp.getmInstance();
        this.mLoginFragment = new MainFragment();

        super.addFragmentToContainer(mLoginFragment, "lol");
    }

//    @Override
//    protected int getLayoutResource() {
//        return R.layout.activity_main;
//    }
}
