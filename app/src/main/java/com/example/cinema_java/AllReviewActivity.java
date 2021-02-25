package com.example.cinema_java;

import androidx.annotation.Nullable;
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

    private static final int WRITE_REVIEW = 101;

    private ReviewAdapter adapter;
    private TextView tvWriteReview;
    private RatingBar ratingBar;
    private TextView tvAvgRating;
    private TextView tvCntParticipant;
    private ListView lvReview;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_review);

        findId();

        //어댑터 받고 리스트뷰에 넣기
        adapter = new ReviewAdapter(getApplicationContext());
        adapter.setItems((ArrayList<ReviewItem>) getIntent().getSerializableExtra("list"));
        lvReview.setAdapter(adapter);

        //레이팅바, 평점, 참여인원만들기
        tvAvgRating.setText(String.format("%.1f", adapter.ratingAvg() * 2));
        ratingBar.setRating(adapter.ratingAvg());
        tvCntParticipant.setText(Integer.toString(adapter.getCount()));

        //작성하기 버튼누르기
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

    //작성하기 액티비티에서 값받기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == WRITE_REVIEW){
            if(data != null){
                float rating = data.getFloatExtra("rating", 0.0f);
                String content = data.getStringExtra("content");

                adapter.addItem(new ReviewItem(R.drawable.user1, "user", "10:00:00", rating, content, "0"));
                lvReview.setAdapter(adapter);
            }
        }
    }

    private void findId() {
        tvWriteReview = findViewById(R.id.tv_write_review);
        ratingBar = findViewById(R.id.ratingbar);
        tvAvgRating = findViewById(R.id.tv_avg_rating);
        lvReview = findViewById(R.id.lv_review);
        tvCntParticipant = findViewById(R.id.tv_cnt_participant);
    }
}