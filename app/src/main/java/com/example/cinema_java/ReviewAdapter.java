package com.example.cinema_java;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();
    Context context;

    public ReviewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ReviewItem item){
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ReviewItemView itemView = null;
        if (view == null) {
            itemView = new ReviewItemView(context);
        } else {
            itemView = (ReviewItemView) view;
        }

        ReviewItem item = items.get(i);
        itemView.setIvUser(item.getUserImage());
        itemView.setTvReviewId(item.getId());
        itemView.setRatingbarReview(item.getRate());
        itemView.setTvReviewTime(item.getTime());
        itemView.setTvRecommendCount(item.getRecommend());
        itemView.setTvReviewComment(item.getComment());

        return itemView;
    }
}
