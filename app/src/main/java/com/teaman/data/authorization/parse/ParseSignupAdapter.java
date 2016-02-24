package com.teaman.data.authorization.parse;

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
    public boolean signUp(String email, String username, String password) {
        return false;
    }

    @Override
    public void signUpAsync(SignupCallback callback, String email, String username, String password) {

    }
}
