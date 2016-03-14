package com.teaman.data.authorization.parse;

import com.google.android.gms.location.places.Place;
import com.teaman.data.authorization.InformationAdapter;

/**
 * Created by Dilancuan on 3/13/2016.
 */
public class ParseInformationAdapter implements InformationAdapter {

    private Place mPlace;

    @Override
    public void setPlace(Place place) {
        mPlace = place;
    }

    @Override
    public Place getPlace() {
        return mPlace;
    }
}
