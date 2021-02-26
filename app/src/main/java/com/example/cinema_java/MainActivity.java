package com.example.cinema_java;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //액티비티 상수지정
    private static final int WRITE_REVIEW = 101;
    private static final int ALL_REVIEW = 102;

    //리스트뷰 어댑터
    private ReviewAdapter adapter;
    private ListView lvReview;

    //좋아요싫어요 변수
    private TextView tvThumbUpCount;
    private TextView tvThumbDownCount;
    private Button btnThumbUp;
    private Button btnThumbDown;
    private boolean likeState = false;
    private boolean unlikeState = false;
    private int likeCount = 15;
    private int unlikeCount = 1;

    //작성하기, 모두보기 변수
    private TextView tvWriteReview;
    private Button btnShowReviews;

    @SuppressLint({"ClickableViewAccessibility", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findId();

        //좋아요 버튼 처리
        btnThumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!likeState) {
                    if (unlikeState) {
                        decrThumbDownCount();
                        unlikeState = !unlikeState;
                    }
                    incrThumbUpCount();
                } else decrThumbUpCount();
                likeState = !likeState;
            }
        });

        //싫어요 버튼 처리
        btnThumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!unlikeState) {
                    if (likeState) {
                        decrThumbUpCount();
                        likeState = !likeState;
                    }
                    incrThumbDownCount();
                } else decrThumbDownCount();
                unlikeState = !unlikeState;
            }
        });

        //리스트뷰처리
        adapter = new ReviewAdapter(getApplicationContext());
        adapter.addItem(new ReviewItem(R.drawable.user1, "lsc", "19:00:00", 5, "재밌어요", "0"));
        adapter.addItem(new ReviewItem(R.drawable.user1, "asd155", "20:00:00", 4.5, "재미없어요", "20"));
        adapter.addItem(new ReviewItem(R.drawable.user1, "vkmd444", "15:00:00", 3, "재미있을까요?", "14"));
        adapter.addItem(new ReviewItem(R.drawable.user1, "lsc", "19:00:00", 5, "재밌어요", "0"));
        lvReview.setAdapter(adapter);

        //리스트뷰 중복스크롤
        lvReview.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                lvReview.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        //작성하기 눌렀을때
        tvWriteReview.setOnClickListener(view -> showWriteReviewActivity());

        //모두보기 눌렀을때
        btnShowReviews.setOnClickListener(view -> showAllReviewActivity());
    }

    //작성하기 메소드
    private void showWriteReviewActivity() {
        Intent intent_writeReview = new Intent(getApplicationContext(), WriteReviewActivity.class);

        startActivityForResult(intent_writeReview, WRITE_REVIEW);
    }

    //모두보기 메소드
    private void showAllReviewActivity() {
        Intent intent_allReview = new Intent(getApplicationContext(), AllReviewActivity.class);

        intent_allReview.putExtra("list", adapter.getItems());
        startActivityForResult(intent_allReview, ALL_REVIEW);
    }

    //다른 액티비티에서 값받기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == WRITE_REVIEW) {
            if (data != null) {
                float rating = data.getFloatExtra("rating", 0.0f);
                String content = data.getStringExtra("content");

                adapter.addItem(new ReviewItem(R.drawable.user1, "user", "10:00:00", rating, content, "0"));
                lvReview.setAdapter(adapter);
            }
        }
        if (requestCode == ALL_REVIEW) {
            if (data != null) {
                adapter.setItems((ArrayList<ReviewItem>) data.getSerializableExtra("list"));
                lvReview.setAdapter(adapter);
            }
        }
    }

    //좋아요 버튼 메소드
    private void incrThumbUpCount() {
        likeCount += 1;
        tvThumbUpCount.setText(String.valueOf(likeCount));
        btnThumbUp.setSelected(true);
    }

    private void decrThumbUpCount() {
        likeCount -= 1;
        tvThumbUpCount.setText(String.valueOf(likeCount));
        btnThumbUp.setSelected(false);
    }

    //싫어요 버튼 메소드
    private void incrThumbDownCount() {
        unlikeCount += 1;
        tvThumbDownCount.setText(String.valueOf(unlikeCount));
        btnThumbDown.setSelected(true);
    }

    private void decrThumbDownCount() {
        unlikeCount -= 1;
        tvThumbDownCount.setText(String.valueOf(unlikeCount));
        btnThumbDown.setSelected(false);
    }

    private void findId() {
        tvThumbUpCount = findViewById(R.id.tv_thumbupcount);
        btnThumbUp = findViewById(R.id.btn_thumbup);
        tvThumbDownCount = findViewById(R.id.tv_thumbdowncount);
        btnThumbDown = findViewById(R.id.btn_thumbdown);
        lvReview = findViewById(R.id.lv_review);
        tvWriteReview = findViewById(R.id.tv_write_review);
        btnShowReviews = findViewById(R.id.btn_show_reviews);
    }
}