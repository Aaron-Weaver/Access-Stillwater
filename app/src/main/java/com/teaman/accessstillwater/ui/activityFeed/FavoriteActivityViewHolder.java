package com.teaman.accessstillwater.ui.activityFeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.utils.StringUtils;
import com.teaman.data.authorization.parse.ParseUserAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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

    public FavoriteActivityViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    private void setupView() {
        if(mActivity.getEstablishment() != null) {
            Establishment est =
                    mActivity.getEstablishment().fromParseObject(mActivity.getEstablishment());
            if(est.getPlaceEntity().getName() != null) {
                mEstablishmentTitle.setText(est.getPlaceEntity().getName());
            }
            if(est.getPlaceEntity().getPhotos().get(0).getPhotoReference() != null) {
                Picasso.with(mContext)
                        .load(StringUtils.MAPS_API_PHOTO_URL + est.getPlaceEntity().getPhotos().get(0).getPhotoReference())
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
        setupView();
    }
}
