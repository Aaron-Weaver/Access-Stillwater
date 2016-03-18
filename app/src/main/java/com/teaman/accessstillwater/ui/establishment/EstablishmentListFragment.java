package com.teaman.accessstillwater.ui.establishment;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.base.BaseRecyclerListFragment;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;

import java.util.List;

/**
 * Created by weava on 3/14/16.
 */
public class EstablishmentListFragment extends BaseRecyclerListFragment
        implements ItemCallback<Establishment> {

    public static final String ESTABLISHMENT_LIST_TYPE =
            "EstablishmentListType";

    public static final int FRAGMENT_SEARCH = 0;
    public static final int FRAGMENT_FAVORITE = 1;

    @IntDef({FRAGMENT_FAVORITE, FRAGMENT_SEARCH})
    public @interface EstablishmentListType {}

    private EstablishmentAdapter mEstablishmentAdapter;
    private int mEstablishmentListType;

    private ParseUser mCurrentUser;

    private LoginAdapter mLoginAdapter;

    public static EstablishmentListFragment newInstance(@EstablishmentListType int type) {
        Bundle args = new Bundle();
        EstablishmentListFragment fragment = new EstablishmentListFragment();
        args.putInt(ESTABLISHMENT_LIST_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();
        mCurrentUser = mLoginAdapter.getBaseUser();
        mEstablishmentAdapter = new EstablishmentAdapter(this, getActivity());
        mEstablishmentListType = getArguments().getInt(ESTABLISHMENT_LIST_TYPE);
        initList(mEstablishmentAdapter, 1);
        queryData();
    }

    @Override
    public void onCallback(Establishment item) {

    }

    public void queryData() {
        mEstablishmentAdapter.clear();

        if(mEstablishmentListType == FRAGMENT_FAVORITE) {
            Log.d("Establishments Results", "Establishments favoriteing");
            Activity.getQuery()
                .include("establishment")
                .whereEqualTo("fromUser", mCurrentUser)
                .whereEqualTo("type", Activity.TYPE_FAVORITE).findInBackground(new FindCallback<Activity>() {
                    @Override
                    public void done(List<Activity> objects, ParseException e) {
                        Log.d("Establishments Results", "Establishments returning");

                        if(objects != null) {
                            if (objects.size() > 0) {
                                for (Activity act : objects) {
                                    act = act.fromParseObject(act);
                                    Establishment est = act.getEstablishment().fromParseObject(act.getEstablishment());

                                    mEstablishmentAdapter.add(est);
                                }
                            }
                        }
                    }
                });
        } else if(mEstablishmentListType == FRAGMENT_SEARCH) {
            // Grab results from search, cross-reference with establishments.
        }
    }
}
