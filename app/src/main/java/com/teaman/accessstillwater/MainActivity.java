package com.teaman.accessstillwater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.teaman.data.parse.UserEntity;

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
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(getApplicationContext());
        ParseObject.registerSubclass(UserEntity.class);

        /*
         * For now, let this be an example of querying for specific objects
         * (In this case, UserEntity, subclass of ParseUser).
         *
         * TODO: Implement all Parse items within the data package
         */

        try {
            UserEntity entity = ParseQuery.getQuery(UserEntity.class).getFirst();
            if (entity != null) {
                //Log.i("Parse", "" + entity.getUsername().toString());
                System.out.println("PARSE:   " + entity.getObjectId() + "  |  " + entity.getUsername() + "  |  " + entity.getEmail());
            }
        } catch (Exception ex) {
            Log.d("ParseException", ex.getMessage() + ex.getStackTrace());
        }

        setContentView(R.layout.activity_main);
    }
}
