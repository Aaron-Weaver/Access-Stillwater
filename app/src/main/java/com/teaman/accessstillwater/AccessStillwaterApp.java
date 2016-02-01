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
public class AccessStillwaterApp extends Application {

    private static AccessStillwaterApp mInstance;
    public static AccessStillwaterApp getmInstance() {
        return mInstance;
    }

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
        //ParseObject.registerSubclass(User.class);

        Parse.initialize(this);

        mInstance = this;
    }

    public LoginAdapter getLoginAdapter() {
        if(this.loginAdapter == null) {
            this.loginAdapter = new ParseLoginAdapter();
        }

        return this.loginAdapter;
    }

    public User getUser() {
        if(this.user == null) {
            this.user = this.loginAdapter.getUser();
        }

        return this.user;
    }
}
