package com.teaman.accessstillwater.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
import com.teaman.accessstillwater.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 9/9/15.
 */
public class ImagePagerAdapter extends PagerAdapter implements View.OnClickListener{

    Context mContext;
    ArrayList<String> imageUrls;
    LayoutInflater mInflater;
    ImageAdapterCallback mImageAdapterCallback;
    ArrayList<PhotoView> mPhotoViews = new ArrayList<>();

    private Boolean isAttached = false;

    public ImagePagerAdapter(Context mContext, ArrayList<String> imageUrls) {
        this.mContext = mContext;
        this.imageUrls = imageUrls;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<PhotoView> getPhotoViews(){
        return mPhotoViews;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    public void setImageAdapterCallback(ImageAdapterCallback imageAdapterCallback) {
        mImageAdapterCallback = imageAdapterCallback;
    }

    public void attachPhotoView(){
        isAttached = true;
    }

    public void detachPhotoView(){
        isAttached = false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = mInflater.inflate(R.layout.page_image, container, false);
        PhotoView imageView = ButterKnife.findById(imageLayout, R.id.PhotoView);

        mPhotoViews.add(imageView);

        if(!isAttached){
            imageView.setZoomable(false);
        }else{
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setMaximumScale(100);
        }

        Picasso.with(mContext)
                .load(imageUrls.get(position))
                .centerInside()
                .fit()
                .into(imageView);

        imageLayout.setOnClickListener(this);

        container.addView(imageLayout);


        return imageLayout;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((RelativeLayout) object));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



    @Override
    public void onClick(View v) {
        if (mImageAdapterCallback != null) {
            mImageAdapterCallback.onImageCallback();
        }
    }
}
