package com.teaman.data.authorization.parse;

import com.parse.ParseUser;
import com.teaman.accessstillwater.R;
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
            displayName = getFirstName() + " " + getLastName().charAt(0) + ".";
        } else {
            displayName = getFirstName() + " " + getLastName();
        }
        return displayName;
    }

    @Override
    public String getFirstName() {
        return this.parseUser.getString(FIRST_NAME);
    }

    @Override
    public String getLastName() {
        return this.parseUser.getString(LAST_NAME);
    }

    @Override
    public String getUserAvatar() {
        if(this.parseUser.getParseFile(PROFILE_PICTURE) != null) {
            return this.parseUser.getParseFile(PROFILE_PICTURE).getUrl();
        } else {
            return  Integer.toString(R.drawable.ic_account_circle_white_48dp);
        }
    }

    @Override
    public boolean hasAuditoryDisability() {
        boolean auditoryDisability =
                this.parseUser.getBoolean(AUDITORY_DISABILITY);

        return auditoryDisability;
    }

    @Override
    public boolean hasVisualDisability() {
        boolean visualDisability =
                this.parseUser.getBoolean(VISUAL_DISABILITY);

        return visualDisability;
    }

    @Override
    public boolean hasPhysicalDisability() {
        boolean physicalDisability =
                this.parseUser.getBoolean(PHYSICAL_DISABILITY);

        return physicalDisability;
    }
}
