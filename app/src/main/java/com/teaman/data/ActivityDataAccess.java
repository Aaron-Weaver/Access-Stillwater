package com.teaman.data;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;

import java.util.List;

/**
 * Created by weava on 3/28/16.
 */
public class ActivityDataAccess {

    public ActivityDataAccess() {}

    public List<Activity> getReviewActivityForUser(ParseUser user) {

        try {
            return Activity.getQuery()
                    .include("review")
                    .include("establishment")
                    .whereEqualTo("fromUser", user)
                    .whereEqualTo("type", Activity.TYPE_REVIEW)
                    .orderByDescending("createdAt")
                    .find();
        } catch (ParseException ex) {
            Log.d("Parse Activity", ex.getMessage());
            return null;
        }
    }

    public List<Activity> getReviewActivityForEstablishment(Establishment establishment) {
        try {
            return Activity.getQuery()
                    .include("review")
                    .include("establishment")
                    .whereEqualTo("establishment", establishment)
                    .whereEqualTo("type", Activity.TYPE_REVIEW)
                    .orderByDescending("createdAt")
                    .find();
        } catch (ParseException ex) {
            Log.d("Parse Activity", ex.getMessage());
            return null;
        }
    }
}
