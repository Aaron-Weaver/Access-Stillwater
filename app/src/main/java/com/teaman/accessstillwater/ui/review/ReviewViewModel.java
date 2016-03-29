package com.teaman.accessstillwater.ui.review;

import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.Review;
import com.teaman.data.entities.json.places.PlaceEntity;

/**
 * Created by weava on 3/28/16.
 */
public class ReviewViewModel {

    private Activity mActivity;
    private Review mReview;
    private Establishment mEstablishment;
    private PlaceEntity mPlaceEntity;

    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public PlaceEntity getPlaceEntity() {
        return mPlaceEntity;
    }

    public void setPlaceEntity(PlaceEntity placeEntity) {
        mPlaceEntity = placeEntity;
    }

    public Review getReview() {
        return mReview;
    }

    public void setReview(Review review) {
        mReview = review;
    }

    public Establishment getEstablishment() {
        return mEstablishment;
    }

    public void setEstablishment(Establishment establishment) {
        mEstablishment = establishment;
    }
}
