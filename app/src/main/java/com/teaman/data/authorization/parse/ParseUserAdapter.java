package com.teaman.data.authorization.parse;

import com.parse.ParseUser;
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
public class ParseUserAdapter implements User {

    private ParseUser parseUser;

    public ParseUserAdapter(ParseUser parseUser) {
        this.parseUser = parseUser;
    }

    @Override
    public String getUsername() {
        return this.parseUser.getUsername();
    }

    @Override
    public String getEmail() {
        return this.parseUser.getEmail();
    }

    @Override
    public String getDisplayName() {
        String displayName;

        if(this.getUsername() != null) {
            displayName = this.getUsername();
        }
        else {
            displayName = this.getEmail();
        }

        return displayName;
    }
}
