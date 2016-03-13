package com.teaman.accessstillwater.ui.main;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseDrawerActivity;
import com.teaman.data.entities.json.places.PlaceEntity;
import com.teaman.data.entities.json.PlaceResults;
import com.teaman.data.entities.json.Results;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
 * @since 1/30/16
 */
public class MainActivity extends BaseDrawerActivity implements
        PlaceSelectionListener, OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks
{
    private final int REQUEST_CODE_FINE_LOCATION = 0;

    private AccessStillwaterApp mApplication;

    @Bind(R.id.place_autocomplete_container)
    protected FrameLayout mPlacesContainer;

    @Bind(R.id.maps_container)
    protected FrameLayout mMapsContainer;

    private PlaceAutocompleteFragment mPlaceAutocompleteFragment;
    private MapFragment mMapsFragment;

    private GoogleApiClient mApiClient;

    private Context mContext;

    private GoogleMap mGoogleMap;

    public static Intent getCallingIntent(Context context)
    {
        Intent callingIntent = new Intent(context, MainActivity.class);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mContext = this;
        this.mApplication = AccessStillwaterApp.getmInstance();

        mApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mApiClient.connect();


        mPlaceAutocompleteFragment = new PlaceAutocompleteFragment();
        mMapsFragment = new MapFragment();

        addFragmentToMainContainer(R.id.place_autocomplete_container, mPlaceAutocompleteFragment, "placesFrag");
        addFragmentToMainContainer(R.id.maps_container, mMapsFragment, "mapsFrag");

        mPlaceAutocompleteFragment.setOnPlaceSelectedListener(this);
        mMapsFragment.getMapAsync(this);

        String latLongStr = "-33.152, 42.123";

//        mApplication.getPlacesApi().getAllNearbyEstablishments(
//                latLongStr,
//                200000f
//        ).enqueue(new Callback<JsonObject>()
//        {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
//            {
//                if(response.body() != null) {
//                    Log.d("Places API Call", response.body().toString());
//                } else {
//                    Log.d("Places API NULL", response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t)
//            {
//                Log.d("Places API Call", t.getMessage() + " | " + t.getStackTrace());
//            }
//        });
    }

    private void setMapCurrentLocation() {

        PendingResult<PlaceLikelihoodBuffer> result =
                Places.PlaceDetectionApi.getCurrentPlace(mApiClient, null);

        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>()
        {
            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods)
            {
                PlaceLikelihood mostLikely = null;
                if (placeLikelihoods.getCount() <= 0)
                {
                    Toast.makeText(mContext, "No Place found", Toast.LENGTH_SHORT).show();
                }
                for (PlaceLikelihood place : placeLikelihoods)
                {
                    if(mostLikely != null)
                    {
                        if (place.getLikelihood() > mostLikely.getLikelihood())
                        {
                            mostLikely = place;
                        }
                    } else {
                        mostLikely = place;
                    }
                    Log.i("Place Activity", String.format("Place '%s' has likelihood: %g",
                            place.getPlace().getName(),
                            place.getLikelihood()));
                }
                if(mostLikely != null)
                {
                    CameraPosition camPos =
                            new CameraPosition(mostLikely.getPlace().getLatLng(), 18.0f, 0, 0);

                    mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));
                    mGoogleMap.setMyLocationEnabled(true);

                    String latLongStr = String.valueOf(mostLikely.getPlace().getLatLng().latitude);
                    latLongStr += ", " + String.valueOf(mostLikely.getPlace().getLatLng().longitude);

                    mApplication.getPlacesApi().getAllNearbyEstablishments(
                            latLongStr,
                            100000f
                    ).enqueue(new Callback<Results<PlaceEntity>>()
                    {
                        @Override
                        public void onResponse(Call<Results<PlaceEntity>> call, Response<Results<PlaceEntity>> response)
                        {
                            if(response.body() != null) {
                                for (PlaceEntity place : response.body().getResults()) {
                                    if(response.body() != null) {
                                        Log.d("Places API Call", place.getName());
                                    } else {
                                        Log.d("Places API NULL", response.message());
                                    }
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Results<PlaceEntity>> call, Throwable t)
                        {
                            Log.d("Places API Call", t.getMessage() + " | " + t.getStackTrace());
                        }
                    });
                }
                placeLikelihoods.release();
            }
        });
    }

    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_main;
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap = googleMap;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            int hasFineLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_FINE_LOCATION);
                return;
            }
        }
        setMapCurrentLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case REQUEST_CODE_FINE_LOCATION:
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setMapCurrentLocation();
                } else {
                    Toast.makeText(mContext, "FINE_LOCATION Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onPlaceSelected(Place place)
    {
        Log.d("Place fragment", place.getName().toString());
    }

    @Override
    public void onError(Status status)
    {
        Log.d("Place fragment", status.getStatusMessage());
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {

    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }

    private void addFragmentToMainContainer(@IdRes int view, Fragment fragment, String tag) {
        getFragmentManager().beginTransaction().add(view, fragment, tag).commit();
    }
}
