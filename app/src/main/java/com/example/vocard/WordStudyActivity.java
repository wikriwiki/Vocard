package com.example.vocard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WordStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_study);

        Button game = (Button) findViewById(R.id.goToGameBtn);

        // 전달된 레벨 데이터 가져오기
        int level = getIntent().getIntExtra("chapter", -1);

        game.setOnClickListener(view -> {
            Intent intent = new Intent(WordStudyActivity.this, CardFilpActivity.class);
            startActivity(intent);
        });



    }
}
