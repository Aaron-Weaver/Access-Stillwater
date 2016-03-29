package com.teaman.accessstillwater.ui.review;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseUser;
import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.User;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.Review;
import com.teaman.data.entities.json.places.PlaceEntity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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

    @Bind(R.id.review_rating_bar)
    protected AppCompatRatingBar mRatingBar;

    @Bind(R.id.review_content_preview)
    protected TextView mReviewContentPreview;

    @Bind(R.id.from_user_name)
    protected TextView mFromUserName;

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

            mRatingBar.setRating(mReview.getTotalRating());
        }

        if(mActivity.getFromUser() != null) {
            ParseUser user = mActivity.getFromUser();

            mFromUserName.setText(user.getString(User.FIRST_NAME) + " " + user.getString(User.LAST_NAME));

            if(user.getParseFile("profilePicture") != null) {
                Picasso.with(mContext)
                        .load(user.getParseFile("profilePicture").getUrl())
                        .placeholder(R.drawable.ic_action_account_circle_blue)
                        .fit()
                        .into(mReviewImage);
            }
            else {
                Picasso.with(mContext)
                        .load(R.drawable.ic_action_account_circle_blue)
                        .fit()
                        .into(mReviewImage);
            }
        }
    }

    public void bind(ReviewViewModel viewModel) {
        mActivity = viewModel.getActivity();
        mReview = viewModel.getReview();
        mEstablishment = viewModel.getEstablishment();
        mPlaceEntity = viewModel.getPlaceEntity();
        setView();
        //Log.d("EstablishmentViewHolder", mActivity.getReview().getTitle());
    }
}
