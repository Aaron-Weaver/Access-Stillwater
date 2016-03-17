package com.teaman.accessstillwater.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by weava on 3/16/16.
 */
public class ProfileFragment extends BaseFragment {

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();

        fragment.setArguments(args);
        return fragment;
    }

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
    protected int getLayoutResource() {
        return R.layout.fragment_profile;
    }
}
