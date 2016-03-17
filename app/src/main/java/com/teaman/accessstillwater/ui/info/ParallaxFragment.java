package com.teaman.accessstillwater.ui.info;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;
import com.teaman.data.authorization.InformationAdapter;
import com.teaman.data.entities.json.places.PlaceEntity;
import com.teaman.data.rest.OkHttpHandler;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
        //Log.d("Photos", "Fetching photos for" + mPlace.getName());

        OkHttpClient client = new OkHttpClient();
        Request request;
        String API_KEY = "AIzaSyA9Zm_3nqNWTIyX8vbC0FpSmFLCH6z8z9A";

        Display display = this.getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //OkHttpHandler handler = new OkHttpHandler();
        ArrayList<OkHttpHandler> handlers = new ArrayList<>();
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        ArrayList<byte[]> rawImages = new ArrayList<>();

        for(int i = 0; i < mPlace.getPhotos().size() && i < 4; i++){
            try{
                handlers.add(new OkHttpHandler());
                //rawImage = handler.execute("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRdAAAAhmZmvHbK8wK6E6XK2881GnTh4FKZ5P1R4XD1aDIGZYLfG5xlU68wmLCQ_gUrEp7OBxPGFBJrFi0D3IDRZMgZeqOkjNNnqzGTeJeLN05xJAJoD4AVm3h65oQYi0cXRy5EEhAqciglkdWCEIdC2vZ_IoDNGhRrg0p55wmmo3SKk1lusJXzR9aRzw&key=AIzaSyA9Zm_3nqNWTIyX8vbC0FpSmFLCH6z8z9A").get();
                rawImages.add(handlers.get(i).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + mPlace.getPhotos().get(i).getPhotoReference() + "&key=" + API_KEY).get());

                if(rawImages.get(i) != null && rawImages.get(i).length > 0){
                    bitmaps.add(BitmapFactory.decodeByteArray(rawImages.get(i), 0, rawImages.get(i).length));
                    imageViews.get(i).setImageBitmap(Bitmap.createScaledBitmap(bitmaps.get(i),height, width ,false));
                }else{
                    Log.d("Image failed", "Is Null");
                }

            }catch(Exception e){
                Log.d("Image failed", e.getMessage());
            }

        }
        /*
        for(Photo photo : mPlace.getPhotos()){
            request = new Request.Builder()
                    //.url("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU&key=AIzaSyA9Zm_3nqNWTIyX8vbC0FpSmFLCH6z8z9A")
                    .url("https://maps.googleapis.com/maps/api/place/photo?maxwidth=" + width + "&photoreference=" + photo.getPhotoReference() + "&key=" + API_KEY)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("Photo failed", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
            });
        }
        */
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
