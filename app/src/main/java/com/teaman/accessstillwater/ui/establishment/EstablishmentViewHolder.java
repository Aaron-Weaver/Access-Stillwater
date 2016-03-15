package com.teaman.accessstillwater.ui.establishment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.entities.Establishment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by weava on 3/14/16.
 */
public class EstablishmentViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.establishment_name)
    protected TextView mEstablishmentName;

    @Bind(R.id.establishment_picture)
    protected CircleImageView mEstablishmentImage;

    private Establishment mEstablishment;
    private ItemCallback<Establishment> mEstablishmentItemCallback;
    private Context mContext;

    public EstablishmentViewHolder(View itemView, ItemCallback<Establishment> establishmentItemCallback, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mEstablishmentItemCallback = establishmentItemCallback;
        mContext = context;
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
