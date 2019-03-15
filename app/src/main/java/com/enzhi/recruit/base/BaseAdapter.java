package com.enzhi.recruit.base;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    protected LayoutInflater inflater;
    protected List<T> data;

    public BaseAdapter(Context context, List<T> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        if (data!=null && data.size()>0) {
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(T newData) {
        if (newData != null) {
            data.add(newData);
            notifyDataSetChanged();
        }
    }

    public void addAndNotNotify(T newData) {
        if (newData != null) {
            data.add(newData);
        }
    }

    public void addAndNotNotify(int position, T newData) {
        if (newData != null) {
            data.add(position, newData);
        }
    }

    public void addAll(List<T> newData) {
        if (newData != null) {
            data.addAll(newData);
            notifyDataSetChanged();
        }
    }

    public void clearAndAddAll(List<T> newData) {
        data.clear();
        if (newData != null) {
            data.addAll(newData);
        }
        notifyDataSetChanged();
    }

    public void setData(List<T> newData) {
        if (newData != null) {
            data = newData;
        }
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }

    public void clear() {
        if (data != null) {
            data.clear();
        }
    }
}
