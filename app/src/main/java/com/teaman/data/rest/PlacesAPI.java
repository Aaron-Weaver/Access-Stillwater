package com.teaman.data.rest;

import com.teaman.data.entities.json.places.PlaceEntity;
import com.teaman.data.entities.json.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by weava on 3/13/16.
 */
public interface PlacesApi
{
    static final String API_KEY = "AIzaSyA9Zm_3nqNWTIyX8vbC0FpSmFLCH6z8z9A";

    @GET("nearbysearch/json?key=" + API_KEY)
    Call<Results<PlaceEntity>> getAllNearbyEstablishments(
            @Query("location") String latLong,
            @Query("radius") float radius
    );

    @GET("nearbysearch/json?key=" + API_KEY)
    Call<Results<PlaceEntity>> getAllNearbyEstablishmentsWithType(
            @Query("location") String latLong,
            @Query("radius") float radius,
            @Query("type") String type
    );

    @GET("nearbysearch/json?key=" + API_KEY)
    Call<Results<PlaceEntity>> getAllNearbyEstablishmentsWithTypeAndName(
            @Query("location") String latLong,
            @Query("radius") float radius,
            @Query("type") String type,
            @Query("name") String name
    );

    ///place/photo?photoreference=PHOTO_REFERENCE&sensor=false&maxheight=MAX_HEIGHT&maxwidth=MAX_WIDTH&key=YOUR_API_KEY
    @GET("photo?key=" + API_KEY)
    Call<Object> getPhotoForEstablishment(
            @Query("photoreference") String reference,
            @Query("maxheight") int height,
            @Query("maxwidth") int width
    );
}
