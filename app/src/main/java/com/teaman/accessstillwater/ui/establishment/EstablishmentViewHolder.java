package com.teaman.accessstillwater.ui.establishment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.entities.Establishment;

import butterknife.ButterKnife;

/**
 * Created by weava on 3/14/16.
 */
public class EstablishmentViewHolder extends RecyclerView.ViewHolder {

    private Establishment mEstablishment;
    private ItemCallback<Establishment> mEstablishmentItemCallback;

    public EstablishmentViewHolder(View itemView, ItemCallback<Establishment> establishmentItemCallback) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mEstablishmentItemCallback = establishmentItemCallback;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void bind(Establishment est) {
        mEstablishment = est;
    }
}
