package com.teaman.data.rest;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by weava on 3/13/16.
 */
public class PlacesClient
{
    public static final String API_BASE_URL = "https://maps.googleapis.com";
    public static final String API_ROUTE = "maps/api/place";

    public static PlacesApi initApi(Application application)
    {
        return new Retrofit.Builder()
                .baseUrl(buildEndpointURL())
                .client(NetworkClient.getOkClient(application))
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PlacesApi.class);

    }

    private static String buildEndpointURL()
    {
        return String.format("%s/%s/", API_BASE_URL, API_ROUTE);
    }
}
