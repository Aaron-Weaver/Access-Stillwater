package com.teaman.accessstillwater.ui.main;

import android.os.Bundle;
import android.util.Log;

import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.base.BaseActivity;
import com.teaman.data.authorization.LoginAdapter;

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

    private LoginAdapter mLoginAdapter;
    private AccessStillwaterApp mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        this.mApplication = AccessStillwaterApp.getmInstance();

        Log.d("Main Activity", "About to call getLoginAdapter");

        this.mLoginAdapter = this.mApplication.getLoginAdapter();
//        mLoginAdapter.login("Weava", "testPass");
    }
}
