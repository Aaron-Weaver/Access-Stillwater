package com.teaman.accessstillwater.ui.info;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.base.BaseDrawerActivity;
import com.teaman.data.authorization.InformationAdapter;
import com.teaman.data.entities.json.places.PlaceEntity;

/**
 * Created by Alexander Melton on 3/12/2016.
 */
public class InformationActivity extends BaseDrawerActivity {
    private Fragment mInformationFragment;
    private Fragment mParallaxFragment;

    private AccessStillwaterApp mApplication;
    private InformationAdapter mInformationAdapter;

    private PlaceEntity mPlace;

    private final Context context = this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mApplication = AccessStillwaterApp.getmInstance();
        this.mInformationAdapter = this.mApplication.getInformationAdapter();

        mPlace = mInformationAdapter.getPlace();

        if(mPlace != null){
            getSupportActionBar().setTitle(mPlace.getName());
        }

        mInformationFragment = new InformationFragment();
        mParallaxFragment = new ParallaxFragment();
        super.overlayFragmentToContainer(mParallaxFragment, "Parallax");
        super.overlayFragmentToContainer(mInformationFragment, "Info");
    }


    public static Intent getCallingIntent(Context context)
    {
        Intent callingIntent = new Intent(context, InformationActivity.class);
        return callingIntent;
    }

}
