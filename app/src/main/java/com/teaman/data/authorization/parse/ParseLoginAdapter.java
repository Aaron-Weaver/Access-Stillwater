package com.teaman.data.authorization.parse;

import android.util.Log;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.teaman.data.User;
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
 * @since 1/31/16
 */
public class ParseLoginAdapter implements LoginAdapter {

    @Override
    public boolean login(String username, String password) {
        try {
            ParseUser.logIn(username, password);
        } catch(ParseException ex) {
            return false;
        }

        return ParseUser.getCurrentUser() != null;
    }

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

    @Override
    public boolean isLoggedIn() {
        return ParseUser.getCurrentUser() != null && ParseUser.getCurrentUser().isAuthenticated();
    }

    @Override
    public void logOut() {
        if(ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public User getUser() {
        if(this.isLoggedIn()) {
            return new ParseUserAdapter(ParseUser.getCurrentUser());
        } else {
            return null;
        }
    }
}
