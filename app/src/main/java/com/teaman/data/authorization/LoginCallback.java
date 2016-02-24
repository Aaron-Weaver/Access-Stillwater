package com.teaman.data.authorization;

/**
 * <h1> LoginCallback </h1>
 * <p>
 * Interface for asynchronous login callback methods.
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 1/31/16
 */

public interface LoginCallback {

    /**
     * Called on a successful async login attempt.
     */
    void loginSuccess();

    /**
     * Called on a failed login attempt.
     */
    void loginFailure();

    // TODO: Add login error for unintended errors like network difficulties.
}
