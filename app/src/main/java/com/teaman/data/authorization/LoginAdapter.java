package com.teaman.data.authorization;

import com.teaman.data.authorization.parse.ParseUserAdapter;

/**
 * <h1> LoginAdapter </h1>
 * <p>
 * Interface for user login operations
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 1/31/16
 */
public interface LoginAdapter {

    /**
     * Called when a user requests a login attempt.
     *
     * @param username  A user's specified name
     * @param password  A user's specified password
     * @return          Success or failure of login attempt
     */
    boolean login(String username, String password);

    /**
     * Called when a user requests a login attempt. Must occur on thread
     * outside of main thread.
     *
     * @param callback  Callback implementation to handle status of async call
     * @param username  A user's specified name
     * @param password  A user's specified password
     */
    void loginAsync(LoginCallback callback, String username, String password);

    /**
     * Called to check whether a user is logged in or not.
     *
     * @return  Checks if User is logged in to app
     */
    boolean isLoggedIn();

    /**
     * Called when a user desires to log out.
     */
    void logOut();

    /**
     * Called to get the User that is currently logged in.
     *
     * @return  Implementation of User interface
     */
    ParseUserAdapter getUser();
}
