package com.teaman.accessstillwater;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.parse.Parse;
import com.parse.ParseUser;
import com.teaman.data.parse.UserEntity;

import org.junit.Before;
import org.junit.runner.RunWith;

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
 * @since 1/30/16
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ParseQueryTest extends AndroidTestCase {

    @Before
    @Override protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void parse_isQuerying() throws Exception{
        Parse.initialize(getContext());

        UserEntity userEntity = (UserEntity) ParseUser.getQuery()
                .find().get(0);

        assert (userEntity != null);
    }
}
