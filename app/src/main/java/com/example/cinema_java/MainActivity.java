package com.example.cinema_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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