package com.teaman.domain;

/**
 * <h1> User </h1>
 * <p>
 * User entity within the domain layer.
 * </p>
 * <p>
 * May be mapped from {@link com.teaman.data.parse.UserEntity}
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 1/30/16
 */
public class User {

    private String userId;

    private String username;

    private String email;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
