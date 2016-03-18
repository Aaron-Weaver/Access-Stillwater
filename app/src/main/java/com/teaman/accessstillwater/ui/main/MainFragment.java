package com.teaman.accessstillwater.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <h1> [Insert class name here] </h1>
 * <p>
 * [Insert class description here]
 * </p>
 * <p>
 * [Insert additional information here (links, code snippets, etc.)]
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 2/24/16
 */
public class MainFragment extends BaseFragment {

    private PlaceAutocompleteFragment mPlaceAutocompleteFragment;

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
        return R.layout.fragment_main;
    }

    @OnClick({R.id.physical_disability_filter, R.id.auditory_disability_filter, R.id.visual_disability_filter})
    public void onFilterClicked(View view) {
        switch (view.getId()) {
            case R.id.physical_disability_filter:
                Log.d("Filter Clicked", "Physical");
                break;
            case R.id.auditory_disability_filter:
                Log.d("Filter Clicked", "Audio");
                break;
            case R.id.visual_disability_filter:
                Log.d("Filter Clicked", "Visual");
                break;
        }
    }
}
