package com.teaman.accessstillwater.ui.info;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;
import com.teaman.data.authorization.InformationAdapter;
import com.teaman.data.entities.json.places.PlaceEntity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dilancuan on 3/16/2016.
 */
public class ParallaxFragment extends BaseFragment {

    private AccessStillwaterApp mApplication;
    private InformationAdapter mInformationAdapter;

    private final Context context = this.getActivity();

    private PlaceEntity mPlace;

    ArrayList<ImageView> imageViews = new ArrayList<>();

    @Nullable
    @Bind(R.id.image1)
    protected ImageView image1;

    @Nullable
    @Bind(R.id.image2)
    protected ImageView image2;

    @Nullable
    @Bind(R.id.image3)
    protected ImageView image3;

    @Nullable
    @Bind(R.id.image4)
    protected ImageView image4;

    @Nullable
    @Bind(R.id.image5)
    protected ImageView image5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mApplication = AccessStillwaterApp.getmInstance();
        this.mInformationAdapter = this.mApplication.getInformationAdapter();

        mPlace = mInformationAdapter.getPlace();
    }

    private void loadImages(){
        String API_KEY = "AIzaSyA9Zm_3nqNWTIyX8vbC0FpSmFLCH6z8z9A";

        Display display = this.getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);


        for(int i = 0; i < mPlace.getPhotos().size() && i < 4; i++){
            Picasso.with(context).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=1600&photoreference=" + mPlace.getPhotos().get(i).getPhotoReference() + "&key=" + API_KEY).fit().centerCrop().into(imageViews.get(i));
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        imageViews.add(image1);
        imageViews.add(image2);
        imageViews.add(image3);
        imageViews.add(image4);
        imageViews.add(image5);

        loadImages();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected int getLayoutResource(){return R.layout.fragment_parallax;}
}
