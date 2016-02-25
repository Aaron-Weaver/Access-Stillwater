package com.teaman.data.authorization;

import com.parse.ParseException;

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
public interface SignupAdapter {

    boolean signUp(String email, String username, String password) throws ParseException;

    void signUpAsync(SignupCallback callback, String email, String username, String password);
}
