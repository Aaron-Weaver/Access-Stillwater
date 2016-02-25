package com.teaman.accessstillwater.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;

import java.util.List;

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
public class LoginFragment extends BaseFragment implements Validator.ValidationListener {

    @Nullable
    @NotEmpty(messageResId = R.string.empty_field)
    @Order(1)
    @Bind(R.id.login_username)
    protected EditText mUsernameField;

    @Nullable
    @NotEmpty(messageResId = R.string.empty_field)
    @Order(2)
    @Bind(R.id.login_password)
    protected EditText mPasswordField;

    @Nullable
    @Bind(R.id.login_button)
    protected Button mLoginButton;

    @Nullable
    @Bind(R.id.signup_button)
    protected TextView mSignupButton;

    private LoginInterface mLoginInterface;

    private Validator mValidator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginInterface = (LoginInterface) this.getActivity();

        mValidator = new Validator(this);
        mValidator.setValidationMode(Validator.Mode.IMMEDIATE);
        mValidator.setValidationListener(this);
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
        mValidator.validate();
    }

    @OnClick(R.id.signup_button)
    public void signupClicked() {
        Log.d("Login Fragment", "Sign up button clicked");
        mLoginInterface.onSignup();
    }

    @Override
    public void onValidationSucceeded() {
        mLoginInterface.onLogin(mUsernameField.getText().toString(),
                mPasswordField.getText().toString());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this.getActivity());

            if(view instanceof EditText) {
                ((EditText) view).setError(message);
                view.requestFocus();
            } else {
                Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
