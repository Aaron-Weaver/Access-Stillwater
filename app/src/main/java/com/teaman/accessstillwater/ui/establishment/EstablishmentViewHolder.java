package com.teaman.accessstillwater.ui.establishment;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.accessstillwater.utils.StringUtils;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.Review;
import com.teaman.data.entities.json.Results;
import com.teaman.data.entities.json.places.PlaceEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by weava on 3/14/16.
 */
public class EstablishmentViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.establishment_name)
    protected TextView mEstablishmentName;

    @Bind(R.id.establishment_picture)
    protected ImageView mEstablishmentImage;

    @Bind(R.id.linear_star_layout)
    protected LinearLayout mReviewStarsLayout;

    @Bind(R.id.rating_bar_establishment)
    protected AppCompatRatingBar mEstablishmentStarViews;

    private PlaceEntity mPlaceEntity;
    private Establishment mEstablishment;
    private ItemCallback<Establishment> mEstablishmentItemCallback;
    private Context mContext;

    private List<Review> mEstablishmentReviews;

    public EstablishmentViewHolder(View itemView, ItemCallback<Establishment> establishmentItemCallback, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
        mEstablishmentItemCallback = establishmentItemCallback;
    }

    private void setView() {
        if(mPlaceEntity.getName() != null) {
            mEstablishmentName.setText(mPlaceEntity.getName());
        }
        if(mPlaceEntity.getPhotos().get(0).getPhotoReference() != null) {
            Picasso.with(mContext)
                    .load(StringUtils.MAPS_API_PHOTO_URL + mPlaceEntity.getPhotos().get(0).getPhotoReference())
                    .fit()
                    .into(mEstablishmentImage);
        }

        int totalScore = mEstablishment.getTotalRatingWithReviews(mEstablishmentReviews);

        Log.d("Total Revies", "" + mEstablishmentReviews.size());
        Log.d("Total Score", "" + totalScore);

        if(totalScore >= 0) {
            mEstablishmentStarViews.setRating(totalScore);
        } else {
            mReviewStarsLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void bind(Establishment est) {
        mEstablishment = est;
        mEstablishmentReviews = new ArrayList<>();
        Log.d("EstablishmentViewHolder", mEstablishment.getPlacesId());

        AccessStillwaterApp.getmInstance().getPlacesApi().getAllDetails(mEstablishment.getPlacesId()).enqueue(new Callback<Results<PlaceEntity>>() {
            @Override
            public void onResponse(Call<Results<PlaceEntity>> call, Response<Results<PlaceEntity>> response) {
                mPlaceEntity = response.body().getSingleResult();

                Activity.getQuery()
                        .whereEqualTo("establishment", mEstablishment)
                        .whereEqualTo("type", Activity.TYPE_REVIEW)
                        .include("review")
                        .findInBackground(new FindCallback<Activity>() {
                            @Override
                            public void done(List<Activity> objects, ParseException e) {
                                if(objects != null) {
                                    if(objects.size() > 0) {
                                        for (Activity act : objects) {
                                            Review rev = act.getReview().fromParseObject(act.getReview());
                                            mEstablishmentReviews.add(rev);
                                        }
                                    }
                                }
                                setView();
                            }
                        });
            }

            @Override
            public void onFailure(Call<Results<PlaceEntity>> call, Throwable t) {

            }
        });
    }
}
