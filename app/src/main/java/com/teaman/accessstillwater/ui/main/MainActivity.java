package com.teaman.accessstillwater.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseDrawerActivity;
import com.teaman.accessstillwater.ui.activityFeed.ActivityFeedFragment;
import com.teaman.accessstillwater.ui.navigation.Navigator;
import com.teaman.data.entities.json.Results;
import com.teaman.data.entities.json.places.Photo;
import com.teaman.data.entities.json.places.PlaceEntity;

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
        PlaceSelectionListener
{
    private final int REQUEST_CODE_FINE_LOCATION = 0;

    private AccessStillwaterApp mApplication;

    @Bind(R.id.place_autocomplete_container)
    protected FrameLayout mPlacesContainer;

    @Bind(R.id.activity_feed_container)
    protected FrameLayout mActivityFeedContainer;

    private MainFragment mMainFragment;

    private PlaceAutocompleteFragment mPlaceAutocompleteFragment;
    private ActivityFeedFragment mActivityFeedFragment;

    private GoogleApiClient mApiClient;

    private final Context mContext = this;

    public static Intent getCallingIntent(Context context)
    {
        Intent callingIntent = new Intent(context, MainActivity.class);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //mContext = this;
        this.mApplication = AccessStillwaterApp.getmInstance();

        mApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mApiClient.connect();

        mPlaceAutocompleteFragment = new PlaceAutocompleteFragment();
        mActivityFeedFragment = ActivityFeedFragment.newInstance();

        getFragmentManager().beginTransaction().add(R.id.place_autocomplete_container,
                mPlaceAutocompleteFragment, "Autocomplete Frag").commit();

        mPlaceAutocompleteFragment.setOnPlaceSelectedListener(this);
        this.setTitle(getString(R.string.activity_home));

        mMainFragment = new MainFragment();
        addFragmentToContainer(mMainFragment, "Main Frag");

        getFragmentManager().beginTransaction().add(R.id.activity_feed_container,
                mActivityFeedFragment, "Activity Feed Frag").commit();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            int hasFineLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_FINE_LOCATION);
                return;
            }
            else {
                //getUserCurrentLocation();
            }
        } else {
            //getUserCurrentLocation();
        }
    }


    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_main;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case REQUEST_CODE_FINE_LOCATION:
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //getUserCurrentLocation();
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
        Log.d("Place ID", place.getId());



        mApplication.getPlacesApi().getAllDetails(
                place.getId()
        ).enqueue(new Callback<Results<PlaceEntity>>() {
            @Override
            public void onResponse(Call<Results<PlaceEntity>> call, Response<Results<PlaceEntity>> response) {
                if (response.body() != null) {

                    for (Photo photo : response.body().getSingleResult().getPhotos()) {
                        Log.d("PHOTO REFERENCE:", photo.getPhotoReference());
                    }
                    Log.d("DETAILS:", response.body().getSingleResult().getName());

                    mApplication.getInformationAdapter().setPlace(response.body().getSingleResult());
                    Navigator.getInstance().navigateToInformationActivity(mContext);

                } else {
                    Log.d("DETAILS", "EMPTY");
                }

            }

            @Override
            public void onFailure(Call<Results<PlaceEntity>> call, Throwable t) {
                Log.d("Places API Call", t.getMessage() + " | " + t.getStackTrace());
            }
        });

    }

    @Override
    public void onError(Status status)
    {
        Log.d("Place fragment", status.getStatusMessage());

    }
}
