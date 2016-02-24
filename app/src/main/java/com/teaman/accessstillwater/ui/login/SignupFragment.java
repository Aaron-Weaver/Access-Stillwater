package com.teaman.accessstillwater.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;
import com.teaman.accessstillwater.utils.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
public class SignupFragment extends BaseFragment {

    @Nullable
    @Bind(R.id.signup_email)
    protected EditText mEmailField;

    @Nullable
    @Bind(R.id.signup_username)
    protected EditText mUsernameField;

    @Nullable
    @Bind(R.id.signup_password)
    protected EditText mPasswordField;

    @Nullable
    @Bind(R.id.signup_confirm_password)
    protected EditText mConfirmPasswordField;

    @Nullable
    @Bind(R.id.confirm_signup_button)
    protected Button mConfirmSignUpButton;

    @Nullable
    @Bind(R.id.login_back_button)
    protected TextView mLoginBackButton;

    private SignupInterface mSignUpInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSignUpInterface = (SignupInterface) this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_signup;
    }

    @OnClick(R.id.confirm_signup_button)
    public void signUpClicked() {
        String email = mEmailField.getText().toString();
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();
        String confirmPasword = mConfirmPasswordField.getText().toString();

        // TODO: Add error checking for signup

        if(StringUtils.isNullOrEmpty(email)) {

        }
        if(StringUtils.isNullOrEmpty(username)) {

        }
        if(StringUtils.isNullOrEmpty(password)) {

        }
        if(StringUtils.isNullOrEmpty(confirmPasword)) {

        }

        this.mSignUpInterface.onSignup(email, username, password);
    }

    @OnClick(R.id.login_back_button)
    public void loginBackClicked() {
        this.mSignUpInterface.onLoginBack();
    }
}
