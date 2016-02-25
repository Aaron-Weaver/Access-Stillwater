package com.teaman.accessstillwater.ui.login;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseActivity;
import com.teaman.data.authorization.SignupAdapter;
import com.teaman.data.authorization.SignupCallback;
import com.teaman.data.authorization.parse.ParseSignupAdapter;

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
 * @since 2/23/16
 */
public class SignupActivity extends BaseActivity implements SignupCallback, SignupInterface{

    private Fragment mSignUpFragment;

    private SignupAdapter mSignUpAdapter;

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, SignupActivity.class);
        return callingIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSignUpAdapter = new ParseSignupAdapter();
        mSignUpFragment = new SignupFragment();

        super.addFragmentToContainer(mSignUpFragment, getString(R.string.signup_fragment_tag));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_base_no_toolbar;
    }

    @Override
    public void signUpSuccess() {

    }

    @Override
    public void signUpFailure() {

    }

    @Override
    public void onSignup(String email, String username, String password) {

    }

    @Override
    public void onLoginBack() {
        this.finish();
    }
}
