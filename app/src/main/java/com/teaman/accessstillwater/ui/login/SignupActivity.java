package com.teaman.accessstillwater.ui.login;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseActivity;
import com.teaman.accessstillwater.ui.navigation.Navigator;
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

    private MaterialDialog mSignUpLoadingDialog;

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
        return R.layout.base_activity_no_toolbar;
    }

    @Override
    public void signUpSuccess() {
        this.mSignUpLoadingDialog.dismiss();

        Toast.makeText(this,
                getString(R.string.signup_successful),
                Toast.LENGTH_LONG).show();

        Navigator.getInstance().navigateToMainActivity(this);
    }

    @Override
    public void signUpFailure() {
        this.mSignUpLoadingDialog.dismiss();

        Toast.makeText(this,
                getString(R.string.signup_failure),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignup(String email, String firstName, String lastName, String password) {

        this.showLoadingDialog();

        this.mSignUpAdapter.signUpAsync(this, email, firstName, lastName, password);
    }

    @Override
    public void onLoginBack() {
        this.finish();
    }

    private void showLoadingDialog() {

        this.mSignUpLoadingDialog = new MaterialDialog.Builder(this)
                .content(getString(R.string.load_signup))
                .progress(true, 0)
                .backgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .widgetColor(ContextCompat.getColor(this, R.color.white))
                .contentColor(ContextCompat.getColor(this, R.color.white))
                .show();
    }
}
