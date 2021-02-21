package com.example.cinema_java;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //좋아요싫어요 변수
    private TextView tvThumbUpCount;
    private TextView tvThumbDownCount;
    private Button btnThumbUp;
    private Button btnThumbDown;
    private boolean likeState = false;
    private boolean unlikeState = false;
    private int likeCount = 15;
    private int unlikeCount = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //좋아요 버튼 처리
        tvThumbUpCount = (TextView) findViewById(R.id.tv_thumbupcount);
        btnThumbUp = (Button) findViewById(R.id.btn_thumbup);
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
        tvThumbDownCount = (TextView) findViewById(R.id.tv_thumbdowncount);
        btnThumbDown = (Button) findViewById(R.id.btn_thumbdown);
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
        ListView lvReview = (ListView) findViewById(R.id.lv_review);
        ReviewAdapter adapter = new ReviewAdapter(getApplicationContext());
        adapter.addItem(new ReviewItem(R.drawable.user1,"lsc", "19:00:00", 5, "재밌어요", "0"));
        adapter.addItem(new ReviewItem(R.drawable.user1,"asd155", "20:00:00", 4.5, "재미없어요", "20"));
        adapter.addItem(new ReviewItem(R.drawable.user1,"vkmd444", "15:00:00", 3, "재미있을까요?", "14"));
        adapter.addItem(new ReviewItem(R.drawable.user1,"lsc", "19:00:00", 5, "재밌어요", "0"));

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

        TextView tvWriteReview = (TextView)findViewById(R.id.tv_write_review);
        tvWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWriteReviewActivity();
            }
        });

    }

    private void showWriteReviewActivity(){

    }




    //좋아요 버튼 메소드
    public void incrThumbUpCount() {
        likeCount += 1;
        tvThumbUpCount.setText(String.valueOf(likeCount));
        btnThumbUp.setSelected(true);
    }

    public void decrThumbUpCount() {
        likeCount -= 1;
        tvThumbUpCount.setText(String.valueOf(likeCount));
        btnThumbUp.setSelected(false);
    }

    //싫어요 버튼 메소드
    public void incrThumbDownCount() {
        unlikeCount += 1;
        tvThumbDownCount.setText(String.valueOf(unlikeCount));
        btnThumbDown.setSelected(true);
    }

    public void decrThumbDownCount() {
        unlikeCount -= 1;
        tvThumbDownCount.setText(String.valueOf(unlikeCount));
        btnThumbDown.setSelected(false);
    }
}