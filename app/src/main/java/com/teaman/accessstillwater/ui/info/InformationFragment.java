package com.teaman.accessstillwater.ui.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;
import com.teaman.data.authorization.InformationAdapter;
import com.teaman.data.entities.json.places.PlaceEntity;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Alexander on 3/13/2016.
 */
public class InformationFragment extends BaseFragment implements OnMapReadyCallback {

    @Bind(R.id.placeAddress)
    TextView address;

    @Bind(R.id.placeType)
    TextView type;

    @Bind(R.id.placeWebsite)
    TextView website;

    @Bind(R.id.placePhone)
    TextView phone;

    @Bind(R.id.place_type_image)
    ImageView mImageView;

    @Bind(R.id.lite_map)
    MapView mMapView;

    private AccessStillwaterApp mApplication;
    private InformationAdapter mInformationAdapter;

    private PlaceEntity mPlace;

    GoogleMap mGoogleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setLocation();
    }

    public void setLocation(){
        try{
            LatLng loc = new LatLng(mPlace.getGeometry().getLocation().getLat(), mPlace.getGeometry().getLocation().getLng());
            mGoogleMap.addMarker(new MarkerOptions().position(loc));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, mGoogleMap.getCameraPosition().zoom));
        }catch(Exception e){
            Timber.d("Error parsing lat/lng");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, v);

        this.mApplication = AccessStillwaterApp.getmInstance();
        this.mInformationAdapter = this.mApplication.getInformationAdapter();

        mPlace = mInformationAdapter.getPlace();

        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.getMapAsync(this);
        }else{
            Log.d("Map Lite", "NULL");
        }

        String typeConcat = "";

        if(mPlace.getAddress() != null) address.setText(mPlace.getAddress());
        if(mPlace.getPhone() != null) phone.setText(mPlace.getPhone());
        if(mPlace.getWebsite() != null) website.setText(mPlace.getWebsite());
        if(!mPlace.getTypes().isEmpty()){
            for(int i = 0; i < mPlace.getTypes().size(); ++i){
                typeConcat = typeConcat + mPlace.getTypes().get(i);
                if(i != mPlace.getTypes().size() -1)typeConcat = typeConcat + ", ";
            }
            typeConcat = typeConcat.replace("_"," ");
            type.setText(typeConcat);
        }
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected int getLayoutResource(){return R.layout.fragment_information;}

}
