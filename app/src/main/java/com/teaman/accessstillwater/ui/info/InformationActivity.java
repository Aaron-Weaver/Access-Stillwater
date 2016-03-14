package com.teaman.accessstillwater.ui.info;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.location.places.Place;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.base.BaseDrawerActivity;
import com.teaman.data.authorization.InformationAdapter;

/**
 * Created by Alexander Melton on 3/12/2016.
 */
public class InformationActivity extends BaseDrawerActivity {
    private Fragment mInformationFragment;
    private AccessStillwaterApp mApplication;
    private InformationAdapter mInformationAdapter;

    private ProgressDialog mInformationLoadingDialog;

    private Place mPlace;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mApplication = AccessStillwaterApp.getmInstance();
        this.mInformationAdapter = this.mApplication.getInformationAdapter();

        mPlace = mInformationAdapter.getPlace();

        getSupportActionBar().setTitle(mPlace.getName());

        /*
        mApplication.getPlacesApi().getPhotoForEstablishment(

                200,
                200
        );
        */
        
        mInformationFragment = new InformationFragment();
        super.addFragmentToContainer(mInformationFragment, "temp");
    }


    public static Intent getCallingIntent(Context context)
    {
        Intent callingIntent = new Intent(context, InformationActivity.class);
        return callingIntent;
    }

}
