package com.teaman.data;

/**
 * <h1> User </h1>
 * <p>
 * Interface containing methods to return user data
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 1/30/16
 */
public interface User {

    /**
     * Retrieve username from User.
     *
     * @return  User's username
     */
    String getUsername();

    /**
     * Retrieve email from User.
     *
     * @return  User's email
     */
    String getEmail();

    /**
     * Retrieve display name of User.
     *
     * @return  User's display name
     */
    String getDisplayName();
}
