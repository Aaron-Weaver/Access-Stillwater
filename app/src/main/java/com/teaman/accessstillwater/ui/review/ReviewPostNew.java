package com.teaman.accessstillwater.ui.review;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseActivity;
import com.teaman.data.authorization.InformationAdapter;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.Review;
import com.teaman.data.entities.json.places.PlaceEntity;

import butterknife.Bind;

/**
 * Created by Dilancuan on 3/18/2016.
 */
public class ReviewPostNew extends BaseActivity {

    @Bind(R.id.auditory_rating)
    AppCompatRatingBar auditoryRating;

    @Bind(R.id.physical_rating)
    AppCompatRatingBar physicalRating;

    @Bind(R.id.visual_rating)
    AppCompatRatingBar visualRating;

    @Bind(R.id.review_body_text)
    EditText body;

    @Bind(R.id.review_title_text)
    EditText title;

    @Bind(R.id.submit_review)
    FloatingActionButton fab;

    private AccessStillwaterApp mApplication;
    private LoginAdapter mLoginAdapter;
    private InformationAdapter mInformationAdapter;

    private Establishment mEstablishment;
    private Review mReview;

    PlaceEntity mPlace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackNav();

        this.mApplication = AccessStillwaterApp.getmInstance();
        this.mLoginAdapter = mApplication.getLoginAdapter();
        this.mInformationAdapter = mApplication.getInformationAdapter();



        mPlace = mInformationAdapter.getPlace();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Title:", title.getText().toString());
                Log.d("Body:", body.getText().toString());
                Log.d("Visual:", visualRating.getRating() + "");
                Log.d("Auditory:", auditoryRating.getRating() + "");
                Log.d("Physical:", physicalRating.getRating() + "");

                mReview = new Review();
                mReview.setContent(body.getText().toString());
                mReview.setTitle(title.getText().toString());
                mReview.setAuditoryRating((int) auditoryRating.getRating());
                mReview.setPhysicalRating((int) physicalRating.getRating());
                mReview.toParseObject(mReview);
                mReview.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Log.d("Error in parse review", e.getMessage());
                        } else {
                            Log.d("No Error on Parse", "");
                        }
                    }
                });

                Establishment.getQuery().whereEqualTo("placesId", mPlace.getPlaceId()).getFirstInBackground(new GetCallback<Establishment>() {
                    @Override
                    public void done(Establishment object, ParseException e) {
                        if (object != null) {
                            mEstablishment = object.fromParseObject(object);

                            Activity act = new Activity();
                            act.setFromUser(mLoginAdapter.getBaseUser());
                            act.setEstablishment(mEstablishment);
                            act.setType(Activity.TYPE_REVIEW);
                            act.setReview(mReview);
                            act.toParseObject(act).saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e != null) {
                                        Log.d("Error in parse review", e.getMessage());
                                    } else {
                                        Log.d("No Error on act Review", "");
                                    }
                                    endYourself();
                                }
                            });
                        }
                    }
                });

                //act.setFromUser();
            }

        });

    }

    private void endYourself(){
        this.finish();
    }



    @Override
    protected int getLayoutResource(){return R.layout.activity_post_new_review;}


}
