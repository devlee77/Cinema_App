package com.example.cinema_java;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.Serializable;
import java.util.ArrayList;

//리뷰리스트뷰 어댑터
public class ReviewAdapter extends BaseAdapter implements Serializable {
    private ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();
    Context context;

    public ReviewAdapter(Context context) {
        this.context = context;
    }

    //아이템 리스트에 추가
    public void addItem(ReviewItem item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public ArrayList<ReviewItem> setItems(ArrayList<ReviewItem> arrayList) {
        return items = arrayList;
    }

    public ArrayList<ReviewItem> getItems() {
        return items;
    }

    public float ratingAvg() {
        float avg = 0;
        for (int i = 0; i < items.size(); i++) {
            avg += items.get(i).getRate();
        }
        avg /= items.size();
        return avg;
    }

    //뷰처리
    //뷰재활용
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ReviewItemView itemView = null;
        if (view == null) {
            itemView = new ReviewItemView(context);
        } else {
            itemView = (ReviewItemView) view;
        }

        ReviewItem item = items.get(i);
        itemView.setData(item);

        return itemView;
    }
}
