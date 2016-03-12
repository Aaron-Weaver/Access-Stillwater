package com.teaman.accessstillwater;

import com.teaman.data.User;
import com.teaman.data.authorization.LoginAdapter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
 * @since 3/3/16
 */
public class ParseUserUnitTest {

    private static User mUser;
    private static LoginAdapter mLoginAdapter;

    @BeforeClass
    public static void setup() {
        mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();
        mLoginAdapter.logOut();
        mLoginAdapter.login("Weava", "testPass");
        mUser = mLoginAdapter.getUser();
    }

    @Test
    public void loginAdapterNull() {
        assertFalse(mLoginAdapter == null);
    }

    @Test
    public void userNull() {
        assertFalse(mUser == null);
    }

    @Test
    public void getUserFirstName() {
        String firstName = mUser.getFirstName();

        assertEquals(firstName, "Aaron");
    }

    @Test
    public void getUserLastName() {
        String lastName = mUser.getFirstName();

        assertEquals(lastName, "Weaver");
    }

    @Test
    public void getUserDisplayName() {
        String displayName = mUser.getDisplayName(false);

        assertEquals(displayName, "Aaron Weaver");
    }

    @Test
    public void getUserDisplayNameAbbr() {
        String displayName = mUser.getDisplayName(true);

        assertEquals(displayName, "Aaron W.");
    }

    @Test
    public void getUserEmail() {
        String email = mUser.getEmail();

        assertEquals(email, "weavakid@gmail.com");
    }

    @AfterClass
    public static void tearDown() {
        mLoginAdapter.logOut();
        mUser = null;
        mLoginAdapter = null;
    }
}
