package com.teaman.accessstillwater.ui.review;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.authorization.parse.ParseUserAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.Review;
import com.teaman.data.entities.json.Results;
import com.teaman.data.entities.json.places.PlaceEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by weava on 3/16/16.
 */
public class ReviewViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.review_image)
    protected CircleImageView mReviewImage;

    @Bind(R.id.establishment_title_text)
    protected TextView mEstablishmentTitleText;

    @Bind(R.id.review_title_text)
    protected TextView mReviewTitleText;

    @Bind({R.id.review_star_1, R.id.review_star_2, R.id.review_star_3,
            R.id.review_star_4, R.id.review_star_5})
    protected List<ImageView> mReviewStars;

    @Bind(R.id.review_content_preview)
    protected TextView mReviewContentPreview;

    private Activity mActivity;
    private ItemCallback<Activity> mActivityItemCallback;
    private Context mContext;

    private PlaceEntity mPlaceEntity;
    private Review mReview;
    private Establishment mEstablishment;

    public ReviewViewHolder(View itemView, ItemCallback<Activity> activityItemCallback, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mActivityItemCallback = activityItemCallback;
        mContext = context;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private void setView() {
        if(mActivity.getEstablishment() != null) {
            if(mPlaceEntity.getName() != null) {
                mEstablishmentTitleText.setText(mPlaceEntity.getName());
            }
        }

        if(mActivity.getReview() != null) {

            if(mReview.getTitle() != null) {
                mReviewTitleText.setText(mReview.getTitle());
            }
            if(mReview.getContent() != null) {
                mReviewContentPreview.setText(mReview.getContent());
            }

            if(mReviewStars != null) {
                if (mReview.getTotalRating() >= 0) {
                    for (int i = 0; i < mReviewStars.size(); i++) {
                        if (i + 1 <= mReview.getTotalRating()) {
                            Picasso.with(mContext)
                                    .load(R.drawable.ic_star_full)
                                    .into(mReviewStars.get(i));
                        }
                    }
                }
            }
        }

        if(mActivity.getFromUser() != null) {
            ParseUserAdapter user = new ParseUserAdapter(mActivity.getFromUser());
            Picasso.with(mContext)
                    .load(user.getUserAvatar())
                    .placeholder(R.drawable.ic_action_account_circle_blue)
                    .fit()
                    .into(mReviewImage);
        }
    }

    public void bind(Activity act) {
        mActivity = act;
        mReview = mActivity.getReview().fromParseObject(mActivity.getReview());
        mEstablishment = mActivity.getEstablishment().fromParseObject(mActivity.getEstablishment());
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
        //Log.d("EstablishmentViewHolder", mActivity.getReview().getTitle());
    }
}
