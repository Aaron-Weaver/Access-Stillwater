package com.teaman.accessstillwater.ui.review;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.authorization.parse.ParseUserAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.Review;

import java.util.List;

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

    @Bind({R.id.review_star_1, R.id.review_star_2, R.id.review_star_3,
            R.id.review_star_4, R.id.review_star_5})
    protected List<ImageView> mReviewStars;

    @Bind(R.id.review_content_preview)
    protected TextView mReviewContentPreview;

    private Activity mActivity;
    private ItemCallback<Activity> mActivityItemCallback;
    private Context mContext;

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
            Establishment est =
                    mActivity.getEstablishment().fromParseObject(mActivity.getEstablishment());
            if(est.getName() != null) {
                mEstablishmentTitleText.setText(est.getName());
            }
        }

        if(mActivity.getReview() != null) {
            Review review = mActivity.getReview().fromParseObject(mActivity.getReview());

            if(review.getTitle() != null) {
                mReviewTitleText.setText(review.getTitle());
            }
            if(review.getContent() != null) {
                mReviewContentPreview.setText(review.getContent());
            }

            if(mReviewStars != null) {
                if (review.getTotalRating() >= 0) {
                    for (int i = 0; i < mReviewStars.size(); i++) {
                        if (i + 1 <= review.getTotalRating()) {
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
                    .placeholder(R.drawable.ic_account_circle_white_48dp)
                    .fit()
                    .into(mReviewImage);
        }
    }

    public void bind(Activity act) {
        mActivity = act;
        //Log.d("EstablishmentViewHolder", mActivity.getReview().getTitle());
        setView();
    }
}
