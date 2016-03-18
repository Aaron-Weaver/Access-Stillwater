package com.teaman.accessstillwater.ui.info;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.DeleteCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseActivity;
import com.teaman.accessstillwater.ui.CustomFragmentAdapter;
import com.teaman.accessstillwater.ui.ImageAdapterCallback;
import com.teaman.accessstillwater.ui.ImagePagerAdapter;
import com.teaman.accessstillwater.ui.animations.FlipAnimation;
import com.teaman.accessstillwater.ui.review.ReviewListFragment;
import com.teaman.data.authorization.InformationAdapter;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.json.places.Photo;
import com.teaman.data.entities.json.places.PlaceEntity;

import java.util.ArrayList;

import butterknife.Bind;
import timber.log.Timber;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Alexander Melton on 3/12/2016.
 */
public class InformationActivity extends BaseActivity implements ImageAdapterCallback, View.OnClickListener, ViewPager.OnPageChangeListener{
    private Fragment mInformationFragment;
    private Fragment mParallaxFragment;

    private AccessStillwaterApp mApplication;
    private InformationAdapter mInformationAdapter;

    private final Context mContext = this;

    private PlaceEntity mPlace;
    private final Context context = this;

    private Boolean isFavorite = false;

    @Bind(R.id.fab_info_fav)
    FloatingActionButton mFloatingActionButton;

    @Bind(R.id.fab_info_comment)
    FloatingActionButton mFloatingActionButtonComment;

    @Nullable
    @Bind(R.id.infoViewPager)
    protected ViewPager mInforPager;

    @Bind(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Bind(R.id.viewpager)
    protected ViewPager mPager;

    @Nullable
    @Bind(R.id.tabs)
    protected TabLayout mTabLayout;

    FlipAnimation flipAnimation;
    FlipAnimation flipAnimationReverse;

    private Establishment mEstablishment;
    private ParseUser mCurrentUser;
    private LoginAdapter mLoginAdapter;

    ArrayList<String> imageUrls;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        enableBackNav();
        this.mApplication = AccessStillwaterApp.getmInstance();
        this.mInformationAdapter = this.mApplication.getInformationAdapter();
        this.mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();
        this.mCurrentUser = mLoginAdapter.getBaseUser();

        mPlace = mInformationAdapter.getPlace();

        if (mPlace != null) {
            getSupportActionBar().setTitle(mPlace.getName());
        }

        if(mInforPager != null){
            CustomFragmentAdapter adapter = new CustomFragmentAdapter(getFragmentManager());

            InformationFragment informationFragment = new InformationFragment();
            ReviewListFragment reviewListFragment = ReviewListFragment.newInstance(ReviewListFragment.FRAGMENT_ESTABLISHMENT);

            adapter.addFragment(informationFragment, "Details");
            adapter.addFragment(reviewListFragment, "Reviews");

            mInforPager.setAdapter(adapter);
            if (mTabLayout != null) {
                mTabLayout.setupWithViewPager(mInforPager);
            }

        }

        determineFavoriteStatus();

        mFloatingActionButton.setOnClickListener(this);
        mFloatingActionButtonComment.setOnClickListener(this);

        flipAnimation = new FlipAnimation(mFloatingActionButton, mFloatingActionButtonComment);
        flipAnimationReverse = new FlipAnimation(mFloatingActionButtonComment, mFloatingActionButton);
        flipAnimationReverse.reverse();

        mInforPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //flipAnimation.swap();
        if(position == 1){
            mFloatingActionButton.startAnimation(flipAnimation);
            mFloatingActionButtonComment.startAnimation(flipAnimation);
        }else if(position == 0){
            mFloatingActionButtonComment.startAnimation(flipAnimationReverse);
            mFloatingActionButton.startAnimation(flipAnimationReverse);
        }

    }

    private void setFavoriteFabImage(int resource) {
        Picasso.with(context)
                .load(resource)
                .resize(48, 48)
                .centerInside()
                .into(mFloatingActionButton);
    }

    private void determineFavoriteStatus() {
        Establishment.getQuery()
                .whereEqualTo("placesId", mPlace.getPlaceId())
                .getFirstInBackground(new GetCallback<Establishment>() {
                    @Override
                    public void done(Establishment object, ParseException e) {
                        mEstablishment = object.fromParseObject(object);

                        Log.d("User", mLoginAdapter.getBaseUser().getString("username"));
                        Log.d("EstablishmentID", mEstablishment.getPlacesId());

                        Activity.getQuery()
                                .whereEqualTo("fromUser", mLoginAdapter.getBaseUser())
                                .whereEqualTo("type", Activity.TYPE_FAVORITE)
                                .whereEqualTo("establishment", mEstablishment)
                                .getFirstInBackground(new GetCallback<Activity>() {
                                    @Override
                                    public void done(Activity object, ParseException e) {
                                        if(e != null) {
                                            if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                                                setFavoriteFabImage(R.drawable.ic_favorites);
                                            }
                                        } else {
                                            setFavoriteFabImage(R.drawable.ic_action_favorite);
                                        }
                                    }
                                });
                    }
                });
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab_info_fav:
                clickFavoriteButton();
                break;
            case R.id.fab_info_comment:
                break;
        }

//        if(!isFavorite){
//            mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccentDark)));
//            Log.d("FAB", "Fav'd!");
//            isFavorite = true;
//        }else{
//            mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
//            Log.d("FAB", "unFav'd");
//            isFavorite = false;
//        }
    }

    private void clickFavoriteButton() {
        Log.d("Favorite Clicked", "fav icon clicked");
        mFloatingActionButton.setClickable(false);

        Activity.getQuery()
                .whereEqualTo("fromUser", mLoginAdapter.getBaseUser())
                .whereEqualTo("type", Activity.TYPE_FAVORITE)
                .whereEqualTo("establishment", mEstablishment)
                .getFirstInBackground(new GetCallback<Activity>() {
                    @Override
                    public void done(Activity object, ParseException e) {
                        if(e != null) {
                            if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                                Activity act = new Activity();
                                act.setFromUser(mLoginAdapter.getBaseUser());
                                act.setEstablishment(mEstablishment);
                                act.setType(Activity.TYPE_FAVORITE);
                                act.toParseObject(act).saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        mFloatingActionButton.setClickable(true);
                                        setFavoriteFabImage(R.drawable.ic_action_favorite);
                                    }
                                });
                            }
                        } else {
                            object.deleteInBackground(new DeleteCallback() {
                                @Override
                                public void done(ParseException e) {
                                    mFloatingActionButton.setClickable(true);
                                    setFavoriteFabImage(R.drawable.ic_favorites);
                                }
                            });
                        }
                    }
                });
    }

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, InformationActivity.class);
        return callingIntent;
    }

    @Override
    public void onImageCallback() {
        // Get the url of the clicked image and do whatever you want
        Timber.d("Image URL: " + imageUrls.get(mPager.getCurrentItem()));

        Intent i = new Intent(this, PicturesActivity.class);
        i.putExtra("position", mPager.getCurrentItem());
        i.putStringArrayListExtra("pictures", imageUrls);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mPager,
                    this.getString(R.string.profile_image_transition));
            this.startActivity(i, options.toBundle());
        } else {
            this.startActivity(i);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        String API_KEY = "AIzaSyA9Zm_3nqNWTIyX8vbC0FpSmFLCH6z8z9A";

        //Picasso.with(context).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=1600&photoreference=" + mPlace.getPhotos().get(0).getPhotoReference() + "&key=" + API_KEY).fit().centerCrop().into(imageView);

        if (mPager != null) {

            imageUrls = new ArrayList<>();
            for (Photo photo : mPlace.getPhotos()) {
                imageUrls.add("https://maps.googleapis.com/maps/api/place/photo?maxwidth=1600&photoreference=" + photo.getPhotoReference() + "&key=" + API_KEY);
            }

            final ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(this, imageUrls);
            imagePagerAdapter.setImageAdapterCallback(this);
            mPager.setAdapter(imagePagerAdapter);


            mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    PhotoView photoView = (PhotoView) mPager.findViewById(R.id.PhotoView);
                    Drawable backgroundImageDraw = photoView.getDrawable();

                    if (backgroundImageDraw instanceof BitmapDrawable) {
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) backgroundImageDraw;
                        Bitmap bm = bitmapDrawable.getBitmap();
                        if(bm == null) Log.d("Swatch", "NULL");
                        Palette.from(bm).generate(new Palette.PaletteAsyncListener() {
                                                      @Override
                                                      public void onGenerated(Palette palette) {
                                                          Palette.Swatch swatch = null;
                                                          if(swatch == null)swatch = palette.getDarkMutedSwatch();
                                                          if(swatch == null)swatch = palette.getMutedSwatch();
                                                          if(swatch == null)swatch = palette.getLightMutedSwatch();
                                                          if(swatch == null)swatch = palette.getSwatches().get(0);

                                                          if (swatch != null) {
                                                              mCollapsingToolbarLayout.setExpandedTitleColor(swatch.getBodyTextColor());
                                                          }else{
                                                              mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
                                                          }
                                                      }
                                                  }
                        );

                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_information;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
