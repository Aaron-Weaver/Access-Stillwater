package com.teaman.accessstillwater.ui.login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
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
public class LoginActivity extends BaseActivity implements LoginCallback, LoginInterface{

    private Fragment mLoginFragment;

    private LoginAdapter mLoginAdapter;
    private AccessStillwaterApp mApplication;

    private MaterialDialog mLoginLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mApplication = AccessStillwaterApp.getmInstance();
        this.mLoginAdapter = this.mApplication.getLoginAdapter();

        mLoginFragment = new LoginFragment();
        super.addFragmentToContainer(mLoginFragment, getString(R.string.login_fragment_tag));

        //this.beforeLogin();
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
        return R.layout.activity_base_no_toolbar;
    }

    @Override
    public void loginSuccess() {
        Log.d("Login Activity",
                "login for user: " + mLoginAdapter.getUser().getUsername() + " was successful");

        this.mLoginLoadingDialog.dismiss();


        //Navigator.getInstance().navigateToMainActivity(this.getApplicationContext());
    }

    @Override
    public void loginFailure() {
        Log.d("Login Activity", "login has failed");

        this.mLoginLoadingDialog.dismiss();

        Toast.makeText(this,
                getString(R.string.invalid_credentials),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLogin(String username, String password) {
        Log.d("Login Activity", "Login clicked and sent to activity");

        // Bring up loading dialog
        this.beforeLogin();

        // Login success/failure will close the loading dialog
        mLoginAdapter.loginAsync(this, username, password);

    }

    @Override
    public void onSignup() {
        Log.d("Login Activity", "Sign up clicked and sent to activity");
        Navigator.getInstance().navigateToSignupActivity(this);
    }

    private void beforeLogin() {
        // TODO: Show a cool little loading dialog
//        this.mLoginLoadingDialog = new ProgressDialog(this, R.style.loading_theme);
//        this.mLoginLoadingDialog.setMessage(getString(R.string.load_login));
//        this.mLoginLoadingDialog.show();

        this.mLoginLoadingDialog = new MaterialDialog.Builder(this)
                .content(getString(R.string.load_login))
                .progress(true, 0)
                .backgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .widgetColor(ContextCompat.getColor(this, R.color.white))
                .contentColor(ContextCompat.getColor(this, R.color.white))
                .show();
    }
}
