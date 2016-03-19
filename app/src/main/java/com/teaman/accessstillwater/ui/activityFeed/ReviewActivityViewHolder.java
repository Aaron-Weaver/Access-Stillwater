package com.teaman.accessstillwater.ui.activityFeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;
import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.utils.StringUtils;
import com.teaman.data.User;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.Review;
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
public class ReviewActivityViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.from_user_image)
    protected CircleImageView mFromUserImageView;

    @Bind(R.id.date_text)
    protected TextView mDateText;

    @Bind(R.id.establishment_image)
    protected ImageView mEstablishmentImageView;

    @Bind(R.id.establishment_title)
    protected TextView mEstablishmentTitleView;

    @Bind(R.id.activity_review_title)
    protected TextView mActivityReviewTitle;

    @Bind(R.id.activity_auditory_review_rating)
    protected TextView mActivityAuditoryReviewRatingText;

    @Bind(R.id.activity_visual_review_rating)
    protected TextView mActivityVisualReviewRatingText;

    @Bind(R.id.activity_physical_review_rating)
    protected TextView mActivityPhysicalReviewRatingText;

    @Bind(R.id.activity_review_content)
    protected TextView mActivityReviewContent;

    @Bind(R.id.from_user_name)
    protected TextView mFromUserName;

    private Context mContext;
    private Activity mActivity;
    private ParseUser mFromUser;
    private Establishment mEstablishment;
    private Review mReview;

    private PlaceEntity mPlaceEntity;

    public ReviewActivityViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    private void setupViews() {
        if(mActivity.getEstablishment() != null) {
            Establishment est =
                    mActivity.getEstablishment().fromParseObject(mActivity.getEstablishment());
            if(mPlaceEntity.getName() != null) {
                mEstablishmentTitleView.setText(mPlaceEntity.getName());
            }

            if(mPlaceEntity.getPhotos().get(0).getPhotoReference() != null) {
                Picasso.with(mContext)
                        .load(StringUtils.MAPS_API_PHOTO_URL + mPlaceEntity.getPhotos().get(0).getPhotoReference())
                        .fit()
                        .into(mEstablishmentImageView);
            }
        }

        if(mActivity.getReview() != null) {
            Review review = mActivity.getReview().fromParseObject(mActivity.getReview());

            if(review.getTitle() != null) {
                mActivityReviewTitle.setText(review.getTitle());
            }
            if(review.getContent() != null) {
                mActivityReviewContent.setText(review.getContent());
            }

            if(review.getPhysicalRating() >= 0) {
                mActivityPhysicalReviewRatingText.setText(review.getPhysicalRating() + "/5");
            }
            if(review.getAuditoryRating() >= 0) {
                mActivityAuditoryReviewRatingText.setText(review.getAuditoryRating() + "/5");
            }
            if(review.getVisualRating() >= 0) {
                mActivityVisualReviewRatingText.setText(review.getVisualRating() + "/5");
            }
        }

        if(mActivity.getFromUser() != null) {
            ParseUser user = mActivity.getFromUser();

            mFromUserName.setText(user.getString(User.FIRST_NAME) + " " + user.getString(User.LAST_NAME));

            if(user.getParseFile("profilePicture") != null) {
                Picasso.with(mContext)
                        .load(user.getParseFile("profilePicture").getUrl())
                        .placeholder(R.drawable.ic_action_account_circle_blue)
                        .fit()
                        .into(mFromUserImageView);
            }


            if(mActivity.getCreatedAt() != null) {
                SimpleDateFormat format = new SimpleDateFormat("MMM d");
                mDateText.setText(format.format(mActivity.getCreatedAt()));
            }
        }


    }

    public void bind(Activity act) {
        mActivity = act;
        mEstablishment = mActivity.getEstablishment().fromParseObject(mActivity.getEstablishment());

        mFromUser = mActivity.getFromUser();
        mReview = mActivity.getReview().fromParseObject(mActivity.getReview());

        AccessStillwaterApp.getmInstance().getPlacesApi().getAllDetails(mEstablishment.getPlacesId()).enqueue(new Callback<Results<PlaceEntity>>() {
            @Override
            public void onResponse(Call<Results<PlaceEntity>> call, Response<Results<PlaceEntity>> response) {
                mPlaceEntity = response.body().getSingleResult();
                setupViews();
            }

            @Override
            public void onFailure(Call<Results<PlaceEntity>> call, Throwable t) {

            }
        });
    }
}
