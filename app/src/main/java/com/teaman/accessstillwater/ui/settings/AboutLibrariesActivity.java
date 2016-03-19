package com.teaman.accessstillwater.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.ui.LibsFragment;
import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseActivity;

/**
 * Created by weava on 3/18/16.
 */
public class AboutLibrariesActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, AboutLibrariesActivity.class);
        return callingIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackNav();

        LibsFragment fragment = new LibsBuilder().fragment();
        addFragmentToContainer(fragment, "LibsFragment");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.base_activity;
    }
}
