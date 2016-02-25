package com.teaman.accessstillwater;

import android.app.Application;
import android.support.v7.appcompat.BuildConfig;

import com.parse.Parse;
import com.squareup.leakcanary.LeakCanary;
import com.teaman.data.User;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.authorization.parse.ParseLoginAdapter;

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
 * android:name = .AccessStillwaterApp
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
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Parse.enableLocalDatastore(this);

        // Put all Object Registrations here.


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

    /**
     * Singleton for accessing {@link User} object.
     *
     * @return  Shared instance of a User object
     */
    public User getUser() {
        if(this.user == null) {
            this.user = this.loginAdapter.getUser();
        }

        return this.user;
    }
}
