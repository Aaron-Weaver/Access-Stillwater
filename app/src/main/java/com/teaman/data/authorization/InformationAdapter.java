package com.teaman.data.authorization;

import com.teaman.data.entities.json.places.PlaceEntity;

/**
 * Created by Dilancuan on 3/13/2016.
 */
public interface InformationAdapter {
    /**
     * Called when a user selects a place.
     * @param place  A user's selected place
     */

    void setPlace(PlaceEntity place);

    PlaceEntity getPlace();
}
