package com.teaman.accessstillwater.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weava on 3/18/16.
 */
public abstract class BaseMultiViewRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> mElements;

    public BaseMultiViewRecyclerAdapter() {
        mElements = new ArrayList<>();
    }

    public BaseMultiViewRecyclerAdapter(List<T> elements) {
        mElements = elements;
    }

    public void add(T element) {
        mElements.add(element);
        notifyItemInserted(getItemCount() - 1);
    }

    public void add(List<T> elements) {
        mElements.addAll(elements);
        notifyDataSetChanged();
    }

    public void remove(T element) {
        mElements.remove(element);
        notifyItemRemoved(mElements.indexOf(element));
    }

    public void remove(int position) {
        mElements.remove(position);
        notifyItemRemoved(position);
    }

    public void swapData(List<T> elements) {
        mElements.clear();
        add(elements);
        notifyDataSetChanged();
    }

    public void clear() {
        mElements.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(viewType), parent, false);
        return inflateViewHolder(view, viewType);
    }

    public abstract @LayoutRes int getLayoutRes(int viewType);

    public abstract RecyclerView.ViewHolder inflateViewHolder(View v, int viewType);

    @Override
    public int getItemCount() {
        return mElements.size();
    }
}
