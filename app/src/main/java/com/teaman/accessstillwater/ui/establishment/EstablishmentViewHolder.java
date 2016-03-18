package com.teaman.accessstillwater.ui.establishment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.accessstillwater.utils.StringUtils;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.json.Results;
import com.teaman.data.entities.json.places.PlaceEntity;

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

    @Bind({R.id.establishment_review_star_1, R.id.establishment_review_star_2,
            R.id.establishment_review_star_3, R.id.establishment_review_star_4,
            R.id.establishment_review_star_5})
    protected List<ImageView> mEstablishmentStarViews;

    private PlaceEntity mPlaceEntity;
    private Establishment mEstablishment;
    private ItemCallback<Establishment> mEstablishmentItemCallback;
    private Context mContext;

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

        Log.d("View Holder", mEstablishmentName + " | " + mEstablishment.getTotalRating() + " | "
                + mEstablishment.getAuditoryRating() + "," + mEstablishment.getVisualRating() + "," + mEstablishment.getPhysicalRating());

        if(mEstablishment.getTotalRating() >= 0) {
            for (int i = 0; i < mEstablishmentStarViews.size(); i++) {
                if (i + 1 <= mEstablishment.getTotalRating()) {
                    Picasso.with(mContext)
                            .load(R.drawable.ic_star_full)
                            .into(mEstablishmentStarViews.get(i));
                }
            }
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
        Log.d("EstablishmentViewHolder", mEstablishment.getPlacesId());

        AccessStillwaterApp.getmInstance().getPlacesApi().getAllDetails(mEstablishment.getPlacesId()).enqueue(new Callback<Results<PlaceEntity>>() {
            @Override
            public void onResponse(Call<Results<PlaceEntity>> call, Response<Results<PlaceEntity>> response) {
                mPlaceEntity = response.body().getSingleResult();
                setView();
            }

            @Override
            public void onFailure(Call<Results<PlaceEntity>> call, Throwable t) {

            }
        });
    }
}
