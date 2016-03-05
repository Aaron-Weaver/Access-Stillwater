package com.teaman.data.authorization.parse;

import com.parse.ParseUser;
import com.teaman.data.User;

/**
 * <h1> ParseUserAdapter </h1>
 * <p>
 * Implementation of {@link User}. Handles User data from a {@link ParseUser}
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 1/31/16
 */
public class ParseUserAdapter implements User {

    private ParseUser parseUser;

    public ParseUserAdapter(ParseUser parseUser) {
        this.parseUser = parseUser;
    }

    /**
     * @return  {@link ParseUser}'s username
     */
    @Override
    public String getUsername() {
        return this.parseUser.getUsername();
    }

    /**
     * @return  {@link ParseUser}'s email
     */
    @Override
    public String getEmail() {
        return this.parseUser.getEmail();
    }

    /**
     * @return  {@link ParseUser}'s displayName
     */
//    @Override
//    public String getDisplayName() {
//        String displayName;
//
//        if(this.getUsername() != null) {
//            displayName = this.getUsername();
//        }
//        else {
//            displayName = this.getEmail();
//        }
//
//        return displayName;
//    }
    @Override
    public String getDisplayName(boolean lastNameOnlyInitial) {
        String displayName;

        if (lastNameOnlyInitial) {
            displayName = getFirstName() + " " + getLastNmae().charAt(0) + ".";
        } else {
            displayName = getFirstName() + " " + getLastNmae();
        }
        return displayName;
    }

    @Override
    public String getFirstName() {
        return this.parseUser.getString(FIRST_NAME);
    }

    @Override
    public String getLastNmae() {
        return this.parseUser.getString(LAST_NAME);
    }
}
