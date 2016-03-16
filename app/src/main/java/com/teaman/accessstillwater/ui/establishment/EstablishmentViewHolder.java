package com.teaman.accessstillwater.ui.establishment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.entities.Establishment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        if(mEstablishment.getName() != null) {
            mEstablishmentName.setText(mEstablishment.getName());
        }
        if(mEstablishment.getBusinessImage() != null) {
            Picasso.with(mContext)
                    .load(mEstablishment.getBusinessImage().getUrl())
                    .fit()
                    .into(mEstablishmentImage);
        }

        if(mEstablishment.getTotalRating() > 0) {
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
        Log.d("EstablishmentViewHolder", mEstablishment.getName());

        setView();
    }
}
