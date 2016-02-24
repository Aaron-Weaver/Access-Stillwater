package com.teaman.accessstillwater.ui.navigation;

import android.content.Context;
import android.content.Intent;

import com.teaman.accessstillwater.ui.login.SignupActivity;

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
            //move to mainActivity
        }
    }

    public void navigateToSignupActivity(Context context) {
        if(context != null) {
            //move to signupActivity
            Intent moveToIntent = SignupActivity.getCallingIntent(context);
            context.startActivity(moveToIntent);
        }
    }
}
