package com.teaman.accessstillwater.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
 * @since 2/22/16
 */
public class LoginFragment extends BaseFragment {

    @Nullable
    @Bind(R.id.login_username)
    protected EditText mUsernameField;

    @Nullable
    @Bind(R.id.login_password)
    protected EditText mPasswordField;

    @Nullable
    @Bind(R.id.login_button)
    protected Button mLoginButton;

    @Nullable
    @Bind(R.id.signup_button)
    protected Button mSignupButton;

    private LoginInterface mLoginInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginInterface = (LoginInterface) this.getActivity();
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
        return R.layout.fragment_login;
    }

    @OnClick(R.id.login_button)
    public void loginClicked() {
        //Log.d("Login Fragment", "Login button clicked");

        if(StringUtils.isNullOrEmpty(mUsernameField.getText().toString())) {
            Log.d("Login Fragment", "Username field is empty");
            Toast.makeText(this.getActivity(),
                    getString(R.string.empty_username_field),
                   Toast.LENGTH_LONG).show();

            return;
        }
        if(StringUtils.isNullOrEmpty(mPasswordField.getText().toString())) {
            Toast.makeText(this.getActivity(),
                    getString(R.string.empty_password_field),
                    Toast.LENGTH_LONG).show();

            return;
        }

        mLoginInterface.onLogin(mUsernameField.getText().toString(),
                    mPasswordField.getText().toString());
    }

    @OnClick(R.id.signup_button)
    public void signupClicked() {
        Log.d("Login Fragment", "Sign up button clicked");
        mLoginInterface.onSignup();
    }
}
