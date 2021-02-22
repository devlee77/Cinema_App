package com.example.cinema_java;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.io.Serializable;

public class AllReviewActivity extends AppCompatActivity {

    private final int WRITE_REVIEW = 101;

    private ReviewAdapter adapter;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_review);

        //어댑터 받고 리스트뷰에 넣기
        adapter = new ReviewAdapter(getApplicationContext());
        adapter.setItems((ArrayList<ReviewItem>) getIntent().getSerializableExtra("list"));
        ListView lvReview = (ListView) findViewById(R.id.lv_review);
        lvReview.setAdapter(adapter);

        //레이팅바, 평점만들기
        TextView tvAvgRating = (TextView) findViewById(R.id.tv_avg_rating);
        tvAvgRating.setText(String.format("%.1f", adapter.ratingAvg() * 2));
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        ratingBar.setRating(adapter.ratingAvg());

        //작성하기 버튼누르기
        TextView tvWriteReview = (TextView) findViewById(R.id.tv_write_review);
        tvWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWriteReviewActivity();
            }
        });
    }

    //뒤로가기 버튼 누름
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("list", adapter.getItems());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    //작성하기 버튼 메소드
    private void showWriteReviewActivity() {
        Intent intent_writeReview = new Intent(getApplicationContext(), WriteReviewActivity.class);

        startActivityForResult(intent_writeReview, WRITE_REVIEW);
    }
}