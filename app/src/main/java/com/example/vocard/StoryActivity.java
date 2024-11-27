package com.example.vocard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StoryActivity extends AppCompatActivity {

    private int chapter; // 전달받은 챕터 정보

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // 전달된 챕터 데이터 가져오기
        chapter = getIntent().getIntExtra("chapter", -1);

    }

}
