package com.example.vocard;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LevelSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        // 홈 버튼 가져오기
        Button homeBtn = (Button) findViewById(R.id.backMain);

        // 버튼 클릭 시 액티비티 종료
        homeBtn.setOnClickListener(view -> {
            finish(); // 현재 액티비티 종료
        });
    }
}
