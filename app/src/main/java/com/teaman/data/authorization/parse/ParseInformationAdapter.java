package com.teaman.data.authorization.parse;

import com.teaman.data.authorization.InformationAdapter;
import com.teaman.data.entities.json.places.PlaceEntity;

/**
 * Created by Dilancuan on 3/13/2016.
 */
public class ParseInformationAdapter implements InformationAdapter {

    private PlaceEntity mPlace;

    @Override
    public void setPlace(PlaceEntity place) {
        mPlace = place;
    }

    @Override
    public PlaceEntity getPlace() {
        return mPlace;
    }
}
