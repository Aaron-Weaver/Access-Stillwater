package com.teaman.data.authorization;

import com.teaman.data.User;

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
public interface LoginAdapter {

    boolean login(String username, String password);

    void loginAsync(LoginCallback callback, String email, String password);

    boolean isLoggedIn();

    void logOut();

    User getUser();
}
