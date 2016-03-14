package com.teaman.data.authorization;

import com.google.android.gms.location.places.Place;

/**
 * Created by Dilancuan on 3/13/2016.
 */
public interface InformationAdapter {
    /**
     * Called when a user selects a place.
     * @param place  A user's selected place
     */

    void setPlace(Place place);

    Place getPlace();
}
