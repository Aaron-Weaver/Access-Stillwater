package com.teaman.accessstillwater.ui.establishment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.entities.Establishment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by weava on 3/14/16.
 */
public class EstablishmentViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.establishment_name)
    protected TextView mEstablishmentName;

    private Establishment mEstablishment;
    private ItemCallback<Establishment> mEstablishmentItemCallback;

    public EstablishmentViewHolder(View itemView, ItemCallback<Establishment> establishmentItemCallback) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mEstablishmentItemCallback = establishmentItemCallback;
        //mEstablishment = mEstablishment.fromParseObject(mEstablishment);
    }

    private void setView() {
        mEstablishmentName.setText(mEstablishment.getName());
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
