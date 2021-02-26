package com.example.cinema_java;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class WriteReviewActivity extends AppCompatActivity {

    private RatingBar rbReviewRating;
    private EditText etReviewContent;
    private Button btnReviewSave;
    private Button btnReviewCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        findId();

        //저장 버튼
        btnReviewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check_null = etReviewContent.getText().toString();
                if (check_null.equals("")) {
                    Toast.makeText(getApplicationContext(), "내용을 입력해주세요", Toast.LENGTH_LONG).show();
                } else {
                    passReview();
                }
            }
        });

        //취소 버튼
        btnReviewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(getApplicationContext(), "취소되었습니다", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Toast.makeText(getApplicationContext(), "취소되었습니다", Toast.LENGTH_LONG).show();
    }

    //저장버튼 메소드
    private void passReview() {
        Intent intent = new Intent();
        intent.putExtra("rating", rbReviewRating.getRating());
        intent.putExtra("content", etReviewContent.getText().toString());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void findId() {
        rbReviewRating = findViewById(R.id.rb_review_rating);
        etReviewContent = findViewById(R.id.et_review_content);
        btnReviewSave = findViewById(R.id.btn_review_save);
        btnReviewCancel = findViewById(R.id.btn_review_cancel);
    }
}