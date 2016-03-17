package com.teaman.accessstillwater.ui.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Alexander on 3/13/2016.
 */
public class InformationFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected int getLayoutResource(){return R.layout.fragment_information;}

}
