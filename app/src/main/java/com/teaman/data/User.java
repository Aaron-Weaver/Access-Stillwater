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

    // String constants for columns within the user db.
    static final String FIRST_NAME = "firstName";
    static final String  LAST_NAME = "lastName";

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
    String getDisplayName(boolean lastNameOnlyInitial);

    /**
     * Retrieve the first name of a given User.
     *
     * @return  User's first name
     */
    String getFirstName();

    /**
     * Retrieve the last name of a given User.
     *
     * @return  User's last name
     */
    String getLastNmae();
}
