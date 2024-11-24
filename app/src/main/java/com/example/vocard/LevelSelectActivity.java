package com.example.vocard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LevelSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        // 홈 버튼
        Button homeBtn = (Button) findViewById(R.id.backMain);
        homeBtn.setOnClickListener(view -> {
            finish(); // 현재 액티비티 종료
        });

        // 레벨 1 버튼
        Button level1Btn = (Button) findViewById(R.id.level1);
        level1Btn.setOnClickListener(view -> {
            Intent intent = new Intent(LevelSelectActivity.this, WordStudyActivity.class);
            intent.putExtra("chapter", 1);
            startActivity(intent);
        });

        // 레벨 2 버튼
        Button level2Btn = (Button) findViewById(R.id.level2);
        level2Btn.setOnClickListener(view -> {
            Intent intent = new Intent(LevelSelectActivity.this, WordStudyActivity.class);
            intent.putExtra("chapter", 2); // 레벨 2 데이터 전달
            startActivity(intent);
        });

        // 레벨 3 버튼
        Button level3Btn = (Button) findViewById(R.id.level3);
        level3Btn.setOnClickListener(view -> {
            Intent intent = new Intent(LevelSelectActivity.this, WordStudyActivity.class);
            intent.putExtra("chapter", 3); // 레벨 3 데이터 전달
            startActivity(intent);
        });
    }
}
