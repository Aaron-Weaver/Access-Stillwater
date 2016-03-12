package com.teaman.data.authorization.parse;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.teaman.data.User;
import com.teaman.data.authorization.SignupAdapter;
import com.teaman.data.authorization.SignupCallback;

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
public class ParseSignupAdapter implements SignupAdapter {

    @Override
    public boolean signUp(String email, String firstName, String lastName, String password) throws ParseException{
        ParseUser user = new ParseUser();
        user.setEmail(email);
        user.put(User.FIRST_NAME, firstName);
        user.put(User.LAST_NAME, lastName);
        user.setPassword(password);
        user.setUsername(email);

        user.signUp();
        return false;
    }

    @Override
    public void signUpAsync(final SignupCallback callback, String email, String firstName, String lastName, String password) {
        ParseUser user = new ParseUser();
        user.put(User.FIRST_NAME, firstName);
        user.put(User.LAST_NAME, lastName);
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    callback.signUpSuccess();
                } else {
                    Log.d("Parse Signup Exception",
                            e.getMessage().toString() + " | " + e.getStackTrace().toString());
                    callback.signUpFailure();
                }
            }
        });
    }
}
