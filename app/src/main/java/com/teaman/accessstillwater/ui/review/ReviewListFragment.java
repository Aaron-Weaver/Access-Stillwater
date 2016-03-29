package com.teaman.accessstillwater.ui.review;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.view.View;

import com.parse.ParseUser;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.base.BaseRecyclerListFragment;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.ActivityDataAccess;
import com.teaman.data.authorization.LoginAdapter;
import com.teaman.data.entities.Activity;
import com.teaman.data.entities.Establishment;
import com.teaman.data.entities.json.Results;
import com.teaman.data.entities.json.places.PlaceEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by weava on 3/16/16.
 */
public class ReviewListFragment extends BaseRecyclerListFragment implements ItemCallback<Activity> {

    public static final String REVIEW_LIST_TYPE =
            "ReviewListType";

    public static final int FRAGMENT_USER = 0;
    public static final int FRAGMENT_ESTABLISHMENT = 1;

    @IntDef({FRAGMENT_USER, FRAGMENT_ESTABLISHMENT})
    public @interface ReviewListType {}

    private ParseUser mCurrentUser;
    private LoginAdapter mLoginAdapter;
    private ReviewListAdapter mReviewListAdapter;
    private Establishment mEstablishment;
    private int mReviewListType;

    public static ReviewListFragment newInstance(@ReviewListType int type) {
        Bundle args = new Bundle();
        ReviewListFragment fragment = new ReviewListFragment();
        args.putInt(REVIEW_LIST_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    public static ReviewListFragment newInstanceForEstablishment(Establishment est) {
        Bundle args = new Bundle();
        ReviewListFragment fragment = new ReviewListFragment();
        args.putInt(REVIEW_LIST_TYPE, FRAGMENT_ESTABLISHMENT);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();
        mCurrentUser = mLoginAdapter.getBaseUser();
        mReviewListAdapter = new ReviewListAdapter(this, getActivity());
        mReviewListType = getArguments().getInt(REVIEW_LIST_TYPE);

        if(mReviewListType == FRAGMENT_ESTABLISHMENT) {
            mEstablishment = AccessStillwaterApp.getmInstance().getEstablishmentAdapter().getEstablishment();
        }

        initList(mReviewListAdapter, 1);
        queryData();
    }

    @Override
    public void onCallback(Activity item) {

    }

    public void queryData() {
        mReviewListAdapter.clear();
        ActivityDataAccess activityDataAccess = new ActivityDataAccess();

        if(mReviewListType == FRAGMENT_USER) {
            List<Activity> activityList = activityDataAccess.getReviewActivityForUser(mCurrentUser);

            if (activityList != null) {
                for (final Activity activity : activityList) {
                    final ReviewViewModel model = new ReviewViewModel();
                    model.setActivity(activity);
                    model.setEstablishment(
                            activity.getEstablishment().fromParseObject(activity.getEstablishment()));
                    model.setReview(activity.getReview().fromParseObject(activity.getReview()));

                    AccessStillwaterApp.getmInstance().getPlacesApi().getAllDetails(
                            model.getEstablishment().getPlacesId()).enqueue(new Callback<Results<PlaceEntity>>() {
                        @Override
                        public void onResponse(Call<Results<PlaceEntity>> call,
                                               Response<Results<PlaceEntity>> response) {
                            model.setPlaceEntity(response.body().getSingleResult());
                            mReviewListAdapter.add(model);
                        }

                        @Override
                        public void onFailure(Call<Results<PlaceEntity>> call, Throwable t) {
                            mReviewListAdapter.add(model);
                        }
                    });
                }
            }

        } else if(mReviewListType == FRAGMENT_ESTABLISHMENT) {
            List<Activity> activityList = activityDataAccess.getReviewActivityForEstablishment(mEstablishment);

            if (activityList != null) {
                for (final Activity activity : activityList) {
                    final ReviewViewModel model = new ReviewViewModel();
                    model.setActivity(activity);
                    model.setEstablishment(
                            activity.getEstablishment().fromParseObject(activity.getEstablishment()));
                    model.setReview(activity.getReview().fromParseObject(activity.getReview()));

                    AccessStillwaterApp.getmInstance().getPlacesApi().getAllDetails(
                            model.getEstablishment().getPlacesId()).enqueue(new Callback<Results<PlaceEntity>>() {
                        @Override
                        public void onResponse(Call<Results<PlaceEntity>> call,
                                               Response<Results<PlaceEntity>> response) {
                            model.setPlaceEntity(response.body().getSingleResult());
                            mReviewListAdapter.add(model);

                        }

                        @Override
                        public void onFailure(Call<Results<PlaceEntity>> call, Throwable t) {
                            mReviewListAdapter.add(model);
                        }
                    });
                }
            }
        }
    }
}
