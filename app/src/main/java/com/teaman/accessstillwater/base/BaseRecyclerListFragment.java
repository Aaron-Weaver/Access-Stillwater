package com.teaman.accessstillwater.base;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teaman.accessstillwater.R;

import butterknife.Bind;

/**
 * Created by weava on 3/14/16.
 */
public class BaseRecyclerListFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    protected GridLayoutManager mGridLayoutManager;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void initList(RecyclerView.Adapter adapter, int numCols) {
        mGridLayoutManager = new GridLayoutManager(mRecyclerView.getContext(), numCols);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.base_fragment_list;
    }
}
