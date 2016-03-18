package com.teaman.accessstillwater;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.teaman.data.User;
import com.teaman.data.authorization.InformationAdapter;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.authorization.parse.ParseEstablishmentAdapter;
import com.teaman.data.authorization.parse.ParseInformationAdapter;
import com.teaman.data.authorization.parse.ParseLoginAdapter;
import com.teaman.data.authorization.parse.ParseUserAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.Review;
import com.teaman.data.rest.PlacesApi;
import com.teaman.data.rest.PlacesClient;

import timber.log.Timber;

/**
 * <h1> AccessStillwaterApp </h1>
 * <p>
 * Custom Application extension for global access to necessary utilities.
 * Will be called on an application's startup, as long as it is set
 * as android:name class.
 * </p>
 *
 * <p>
 * @see android.app.Application
 * <pre>
 * <code>
 *     In AndroidManifest.xml
 *     android:name = .AccessStillwaterApp
 * </code>
 * </pre>
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 1/31/16
 */
public class AccessStillwaterApp extends Application {

    private static AccessStillwaterApp mInstance;

    private LoginAdapter loginAdapter = new ParseLoginAdapter();
    private InformationAdapter informationAdapter = new ParseInformationAdapter();
    private ParseEstablishmentAdapter mEstablishmentAdapter = new ParseEstablishmentAdapter();

    private ParseUserAdapter user;

    private PlacesApi mPlacesApi;

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);

        Timber.plant(new Timber.DebugTree());

        Stetho.initializeWithDefaults(this);

        Parse.enableLocalDatastore(this);

        // Put all Object Registrations here.
        ParseObject.registerSubclass(Activity.class);
        ParseObject.registerSubclass(Establishment.class);
        ParseObject.registerSubclass(Review.class);
        ParseUser.registerSubclass(ParseUserAdapter.class);

        Parse.initialize(this);

        //ParseUser.enableRevocableSessionInBackground();

        mInstance = this;
    }

    /**
     * Singleton for accessing {@link AccessStillwaterApp} object.
     *
     * @return  Shared instance of an AccessStillwaterApp object
     */
    public static AccessStillwaterApp getmInstance() {
        return mInstance;
    }

    /**
     * Singleton for accessing {@link LoginAdapter} object.
     *
     * @return  Shared instance of a LoginAdapter object
     */
    public LoginAdapter getLoginAdapter() {
        if(this.loginAdapter == null) {
            this.loginAdapter = new ParseLoginAdapter();
        }

        return this.loginAdapter;
    }

    public InformationAdapter getInformationAdapter(){
        if(this.informationAdapter == null){
            this.informationAdapter = new ParseInformationAdapter();
        }

        return this.informationAdapter;
    }

    public ParseEstablishmentAdapter getEstablishmentAdapter() {
        if(this.mEstablishmentAdapter == null) {
            this.mEstablishmentAdapter = new ParseEstablishmentAdapter();
        }
        return this.mEstablishmentAdapter;
    }

    /**
     * Singleton for accessing {@link User} object.
     *
     * @return  Shared instance of a User object
     */
    public ParseUserAdapter getUser() {
        if(this.user == null) {
            this.user = this.loginAdapter.getUser();
        }

        return this.user;
    }

    public PlacesApi getPlacesApi() {
        if(mPlacesApi == null) {
            mPlacesApi = PlacesClient.initApi(this);
        }

        return mPlacesApi;
    }
}
