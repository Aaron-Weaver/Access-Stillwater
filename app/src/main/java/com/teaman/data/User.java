package com.teaman.data;

/**
 * <h1> User </h1>
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
public interface User {

    String getUsername();

    String getEmail();

    String getDisplayName();
}
