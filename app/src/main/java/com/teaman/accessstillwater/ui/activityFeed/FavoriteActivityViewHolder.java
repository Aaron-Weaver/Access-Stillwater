package com.teaman.accessstillwater.ui.activityFeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.utils.StringUtils;
import com.teaman.data.authorization.parse.ParseUserAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.json.Results;
import com.teaman.data.entities.json.places.PlaceEntity;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by weava on 3/18/16.
 */
public class FavoriteActivityViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.from_user_image)
    protected CircleImageView mFromUserImage;

    @Bind(R.id.date_text)
    protected TextView mDateText;

    @Bind(R.id.establishment_image)
    protected ImageView mEstablishmentImageView;

    @Bind(R.id.establishment_title)
    protected TextView mEstablishmentTitle;

    private Context mContext;
    private Activity mActivity;
    private PlaceEntity mPlaceEntity;
    private Establishment mEstablishment;

    public FavoriteActivityViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    private void setupView() {
        if(mActivity.getEstablishment() != null) {
            Establishment est =
                    mActivity.getEstablishment().fromParseObject(mActivity.getEstablishment());
            if(mPlaceEntity.getName() != null) {
                mEstablishmentTitle.setText(mPlaceEntity.getName());
            }
            if(mPlaceEntity.getPhotos().get(0).getPhotoReference() != null) {
                Picasso.with(mContext)
                        .load(StringUtils.MAPS_API_PHOTO_URL + mPlaceEntity.getPhotos().get(0).getPhotoReference())
                        .fit()
                        .into(mEstablishmentImageView);
            }
        }

        if(mActivity.getCreatedAt() != null) {
            SimpleDateFormat format = new SimpleDateFormat("MMM d");
            mDateText.setText(format.format(mActivity.getCreatedAt()));
        }

        if(mActivity.getFromUser() != null) {
            ParseUserAdapter user = new ParseUserAdapter(mActivity.getFromUser());
            Picasso.with(mContext)
                    .load(user.getUserAvatar())
                    .placeholder(R.drawable.ic_action_account_circle_blue)
                    .fit()
                    .into(mFromUserImage);
        }
    }

    public void bind(Activity act) {
        mActivity = act;

        if(mActivity != null) {
            if(mActivity.getEstablishment() != null) {
                mEstablishment =
                        mActivity.getEstablishment().fromParseObject(mActivity.getEstablishment());

                AccessStillwaterApp.getmInstance().getPlacesApi().getAllDetails(mEstablishment.getPlacesId()).enqueue(new Callback<Results<PlaceEntity>>() {
                    @Override
                    public void onResponse(Call<Results<PlaceEntity>> call, Response<Results<PlaceEntity>> response) {
                        mPlaceEntity = response.body().getSingleResult();
                        setupView();
                    }

                    @Override
                    public void onFailure(Call<Results<PlaceEntity>> call, Throwable t) {

                    }
                });
            }
        }
    }
}
