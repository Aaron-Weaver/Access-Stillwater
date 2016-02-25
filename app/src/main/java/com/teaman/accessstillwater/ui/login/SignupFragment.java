package com.teaman.accessstillwater.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;
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
 * @since 2/23/16
 */
public class SignupFragment extends BaseFragment implements Validator.ValidationListener {


    @Nullable
    @NotEmpty(messageResId = R.string.empty_field, sequence = 1)
    @Email(messageResId = R.string.email_not_valid, sequence = 2)
    @Order(1)
    @Bind(R.id.signup_email)
    protected EditText mEmailField;

    @Nullable
    @NotEmpty(messageResId = R.string.empty_field, sequence = 1)
    @Length(min = 6, max = 18, messageResId = R.string.username_invalid_length, sequence = 2)
    @Pattern(regex = "^[a-zA-Z0-9._-]{3,}$",
            messageResId = R.string.username_invalid_characters, sequence = 3)
    @Order(2)
    @Bind(R.id.signup_username)
    protected EditText mUsernameField;

    @Nullable
    @NotEmpty(messageResId = R.string.empty_field, sequence = 1)
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC,
            messageResId = R.string.password_invalid_characters, sequence = 2)
    @Length(max = 18, messageResId = R.string.password_exceeds_length, sequence = 3)
    @Order(3)
    @Bind(R.id.signup_password)
    protected EditText mPasswordField;

    @Nullable
    @ConfirmPassword(messageResId = R.string.password_unmatched, sequence = 1)
    @Order(4)
    @Bind(R.id.signup_confirm_password)
    protected EditText mConfirmPasswordField;

    @Nullable
    @Bind(R.id.confirm_signup_button)
    protected Button mConfirmSignUpButton;

    @Nullable
    @Bind(R.id.login_back_button)
    protected TextView mLoginBackButton;

    private SignupInterface mSignUpInterface;

    private Validator mValidator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSignUpInterface = (SignupInterface) this.getActivity();

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
        return R.layout.fragment_signup;
    }

    @OnClick(R.id.confirm_signup_button)
    public void signUpClicked() {
        mValidator.validate();
    }

    @OnClick(R.id.login_back_button)
    public void loginBackClicked() {
        this.mSignUpInterface.onLoginBack();
    }

    @Override
    public void onValidationSucceeded() {
        // Call activity to sign the user up.
        String email = mEmailField.getText().toString();
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();

        mSignUpInterface.onSignup(email, username, password);
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
