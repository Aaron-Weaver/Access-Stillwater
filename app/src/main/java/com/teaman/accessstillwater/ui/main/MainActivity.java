package com.teaman.accessstillwater.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseDrawerActivity;

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
public class MainActivity extends BaseDrawerActivity {

    private AccessStillwaterApp mApplication;


    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, MainActivity.class);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mApplication = AccessStillwaterApp.getmInstance();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.base_activity_drawer;
    }
}
