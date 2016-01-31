package com.teaman.data.parse;

import com.parse.ParseClassName;
import com.parse.ParseUser;

/**
 * <h1> UserEntity </h1>
 * <p>
 * Instance of a specific user's data as it would be retrieved from a Parse database.
 * </p>
 * <p>
 * This class relies on the Parse for Android API
 * See <a href="https://parse.com/docs/android">Parse Android API</a>
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 1/30/16
 */
@ParseClassName("_User")
public class UserEntity extends ParseUser{

    // Constants for Parse database keys.
    private final String USERNAME_KEY = "username";
    private final String EMAIL_KEY = "email";

    public UserEntity() { }

    @Override
    public String getObjectId() {
        return super.getObjectId();
    }

    @Override
    public String getUsername() {
        return getString(USERNAME_KEY);
    }

    @Override
    public void setUsername(String username) {
        this.put(USERNAME_KEY, username);
    }

    @Override
    public String getEmail() {
        return getString(EMAIL_KEY);
    }

    @Override
    public void setEmail(String email) {
        this.put(EMAIL_KEY, email);
    }
}
