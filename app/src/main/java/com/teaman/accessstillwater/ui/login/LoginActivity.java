package com.teaman.accessstillwater.ui.login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseActivity;
import com.teaman.accessstillwater.ui.navigation.Navigator;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.authorization.LoginCallback;

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
 * @since 2/1/16
 */
public class LoginActivity extends BaseActivity implements LoginCallback{

    private Fragment mLoginFragment;

    private LoginAdapter mLoginAdapter;
    private AccessStillwaterApp mApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mApplication = AccessStillwaterApp.getmInstance();
        this.mLoginAdapter = this.mApplication.getLoginAdapter();

        mLoginFragment = new LoginFragment();
        super.addFragmentToContainer(mLoginFragment, getString(R.string.login_fragment_tag));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(this.mLoginAdapter.isLoggedIn()) {
            Log.d("Login Activity", "user is already logged in");
            Navigator.getInstance().navigateToMainActivity(this.getApplicationContext());
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        Log.d("Login Activity", "login was successful");
        Navigator.getInstance().navigateToMainActivity(this.getApplicationContext());
    }

    @Override
    public void loginFailure() {
        Log.d("Login Activity", "login has failed");
        // TODO: Handle login failure
    }
}
