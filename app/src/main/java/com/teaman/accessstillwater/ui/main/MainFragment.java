package com.teaman.accessstillwater.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;
import com.teaman.accessstillwater.ui.establishment.EstablishmentListFragment;
import com.teaman.accessstillwater.ui.navigation.Navigator;

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

    @OnClick({R.id.auditory_disability_filter, R.id.visual_disability_filter, R.id.physical_disability_filter})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auditory_disability_filter:
                Navigator.getInstance().navigateToEstablishmentActivityWithFilter(getActivity(), EstablishmentListFragment.SEARCH_AUDITORY);
                break;
            case R.id.visual_disability_filter:
                Navigator.getInstance().navigateToEstablishmentActivityWithFilter(getActivity(), EstablishmentListFragment.SEARCH_VISUAL);
                break;
            case R.id.physical_disability_filter:
                Navigator.getInstance().navigateToEstablishmentActivityWithFilter(getActivity(), EstablishmentListFragment.SEARCH_PHYSICAL);
                break;
        }
    }
}
