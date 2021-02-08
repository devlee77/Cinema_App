package com.example.cinema_java;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

//아이템뷰
public class ReviewItemView extends ConstraintLayout {

    private ImageView ivUser;
    private TextView tvReviewId;
    private TextView tvReviewTime;
    private RatingBar ratingbarReview;
    private TextView tvReviewComment;
    private TextView tvRecommendCount;

    public ReviewItemView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ReviewItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    //초기화 부분
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_review, this, true);

        ivUser = (ImageView) findViewById(R.id.iv_user);
        tvReviewId = (TextView) findViewById(R.id.tv_review_id);
        tvReviewTime = (TextView) findViewById(R.id.tv_review_time);
        ratingbarReview = (RatingBar) findViewById(R.id.ratingbar_review);
        tvReviewComment = (TextView) findViewById(R.id.tv_review_comment);
        tvRecommendCount = (TextView) findViewById(R.id.tv_recommend_count);
    }


    //setter
    public void setIvUser(ImageView ivUser) {
        this.ivUser = ivUser;
    }

    public void setTvReviewId(TextView tvReviewId) {
        this.tvReviewId = tvReviewId;
    }

    public void setTvReviewTime(TextView tvReviewTime) {
        this.tvReviewTime = tvReviewTime;
    }

    public void setRatingbarReview(RatingBar ratingbarReview) {
        this.ratingbarReview = ratingbarReview;
    }

    public void setTvReviewComment(TextView tvReviewComment) {
        this.tvReviewComment = tvReviewComment;
    }

    public void setTvRecommendCount(TextView tvRecommendCount) {
        this.tvRecommendCount = tvRecommendCount;
    }
}