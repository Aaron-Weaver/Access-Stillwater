package com.teaman.accessstillwater.ui.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseActivity;
import com.teaman.accessstillwater.ui.ImagePagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Dilancuan on 3/17/2016.
 */

public class PicturesActivity extends BaseActivity {
    ImagePagerAdapter mImagePagerAdapter;

    private ArrayList<String> imageUrls;


    @Bind(R.id.viewpager)
    protected ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackNav();

        setTitle("");

        imageUrls = getIntent().getExtras().getStringArrayList("pictures");
        int position = getIntent().getExtras().getInt("position");

        if(imageUrls != null){
            mImagePagerAdapter = new ImagePagerAdapter(this, imageUrls);
            mImagePagerAdapter.attachPhotoView();
            mViewPager.setAdapter(mImagePagerAdapter);
            mViewPager.setCurrentItem(position);
        }

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_pictures;
    }

}
