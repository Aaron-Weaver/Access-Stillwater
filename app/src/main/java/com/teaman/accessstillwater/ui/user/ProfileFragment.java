package com.teaman.accessstillwater.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;
import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.AccessStillwaterApp;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseFragment;
import com.teaman.data.User;
import com.teaman.data.authorization.LoginAdapter;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by weava on 3/16/16.
 */
public class ProfileFragment extends BaseFragment {

    @Bind(R.id.profile_name)
    protected TextView mProfileName;

    @Bind(R.id.profile_email)
    protected TextView mProfileEmail;

    @Bind(R.id.physical_check_mark)
    protected ImageView mPhysicalDisabilityCheck;

    @Bind(R.id.auditory_check_mark)
    protected ImageView mAuditoryDisabilityCheck;

    @Bind(R.id.visual_check_mark)
    protected ImageView mVisualDisabilityCheck;

    private LoginAdapter mLoginAdapter;
    private ParseUser mUser;
    private ParseUser mBaseUser;

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

        mLoginAdapter = AccessStillwaterApp.getmInstance().getLoginAdapter();

        mUser = mLoginAdapter.getUser();
        mProfileName.setText(mUser.getString(User.FIRST_NAME) + mUser.getString(User.LAST_NAME));


        mProfileEmail.setText(mLoginAdapter.getBaseUser().getString("email"));

        confirmDisabilityCheck(mUser.getBoolean("physicalDisability"), mPhysicalDisabilityCheck);
        confirmDisabilityCheck(mUser.getBoolean("auditoryDisability"), mAuditoryDisabilityCheck);
        confirmDisabilityCheck(mUser.getBoolean("visualDisability"), mVisualDisabilityCheck);

        return v;
    }

    private void confirmDisabilityCheck(boolean hasDisability, ImageView v) {
        if(hasDisability) {
            Picasso.with(getActivity())
                    .load(R.drawable.ic_check_green)
                    .fit()
                    .into(v);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_profile;
    }
}
