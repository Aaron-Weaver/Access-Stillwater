package com.teaman.accessstillwater.ui.navigation;

import android.content.Context;
import android.content.Intent;

import com.teaman.accessstillwater.ui.establishment.EstablishmentActivity;
import com.teaman.accessstillwater.ui.establishment.EstablishmentListFragment;
import com.teaman.accessstillwater.ui.login.LoginActivity;
import com.teaman.accessstillwater.ui.login.SignupActivity;
import com.teaman.accessstillwater.ui.main.MainActivity;
import com.teaman.accessstillwater.utils.StringUtils;

/**
 * <h1> Navigator </h1>
 * <p>
 * Intent and navigation handling singleton for entirety of application
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 2/22/16
 */
public class Navigator {

    public static Navigator mInstance;

    private Navigator() {

    }

    public static Navigator getInstance() {
        if(mInstance == null) {
            mInstance = new Navigator();
        }
        return mInstance;
    }

    public void navigateToMainActivity(Context context) {
        if(context != null) {
            Intent moveToIntent = MainActivity.getCallingIntent(context);
            context.startActivity(moveToIntent);
        }
    }

    public void navigateToSignupActivity(Context context) {
        if(context != null) {
            //move to signupActivity
            Intent moveToIntent = SignupActivity.getCallingIntent(context);
            context.startActivity(moveToIntent);
        }
    }

    public void navigateToLoginActivity(Context context) {
        if(context != null) {
            Intent moveToIntent = LoginActivity.getCallingIntent(context);
            context.startActivity(moveToIntent);
        }
    }

    public void navigateToEstablishmentActivity(Context context, @EstablishmentListFragment.EstablishmentListType int type) {
        if(context != null) {
            Intent moveToIntent = EstablishmentActivity.getCallingIntent(context);
            moveToIntent.putExtra(StringUtils.ESTABLISHMENT_TYPE_EXTRA, type);
            context.startActivity(moveToIntent);
        }
    }
}
