package com.teaman.data.authorization.parse;

import android.util.Log;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.teaman.data.User;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.authorization.LoginCallback;

/**
 * <h1> ParseLoginAdapter </h1>
 * <p>
 * Implementation of {@link LoginAdapter} meant for use with Parse.
 * </p>
 * <p>
 * This class is intended to be used with Parse API.
 * @See <a href="https://parse.com/docs/android">Parse Android API</a>
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 1/31/16
 */
public class ParseLoginAdapter implements LoginAdapter {

    /**
     * Uses Parse's {@link ParseUser} login function to log a user into
     * the application.
     *
     * @param username  A user's specified name
     * @param password  A user's specified password
     * @return          Whether login attempt was succesful or not
     */
    @Override
    public boolean login(String username, String password) {
        try {
            ParseUser.logIn(username, password);
        } catch(ParseException ex) {
            return false;
        }

        return ParseUser.getCurrentUser() != null;
    }

    /**
     * Uses Parse's {@link ParseUser} logInInBackground function to log a user into
     * the application as a background process.
     *
     * @param callback  Callback implementation to handle status of async call
     * @param username  A user's specified name
     * @param password  A user's specified password
     */
    @Override
    public void loginAsync(final LoginCallback callback, String username, String password) {

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null) {
                    callback.loginSuccess();
                } else {
                    callback.loginFailure();
                    Log.e("ParseException", e.getMessage().toString());
                }
            }
        });
    }

    /**
     * Checks whether user is logged into the application using {@link ParseUser}
     *
     * @return  Current user's logged in status
     */
    @Override
    public boolean isLoggedIn() {
        return ParseUser.getCurrentUser() != null && ParseUser.getCurrentUser().isAuthenticated();
    }

    /**
     * Uses {@link ParseUser} to log a user out of the application.
     */
    @Override
    public void logOut() {
        if(ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    /**
     * If a user is currently logged in, this will retrieve that user's associated information.
     * Uses {@link ParseUser}.
     *
     * @return {@link User} that is currently logged in.
     */
    @Override
    public ParseUserAdapter getUser() {
        if(this.isLoggedIn()) {
            return new ParseUserAdapter(ParseUser.getCurrentUser());
        } else {
            return null;
        }
    }


}
