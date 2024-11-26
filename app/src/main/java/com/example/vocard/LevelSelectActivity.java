package com.example.vocard;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
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
            finish();
        });





        // 레벨 1 버튼
        Button level1Btn = (Button) findViewById(R.id.level1);
        level1Btn.setOnClickListener(view -> {
            Intent intent = new Intent(LevelSelectActivity.this, WordStudyActivity.class);
            intent.putExtra("chapter", 1);
            startActivity(intent);
        });

        String title1 = "Chapter 1\n\n";
        String description1 = "산타와 루돌프의 \n크리스마스 선물";
        SpannableString spannableString1 = new SpannableString(title1 + description1);
        spannableString1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                0, title1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString1.setSpan(new android.text.style.RelativeSizeSpan(1.3f),
                0, title1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        level1Btn.setText(spannableString1);


        // 레벨 2 버튼
        Button level2Btn = (Button) findViewById(R.id.level2);
        level2Btn.setOnClickListener(view -> {
            Intent intent = new Intent(LevelSelectActivity.this, WordStudyActivity.class);
            intent.putExtra("chapter", 2); // 레벨 2 데이터 전달
            startActivity(intent);
        });

        String title2 = "Chapter 2\n\n";
        String description2 = "용감한 소녀와 \n 숲 속의 보물";
        SpannableString spannableString2 = new SpannableString(title2 + description2);
        spannableString2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                0, title2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(new android.text.style.RelativeSizeSpan(1.3f),
                0, title2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        level2Btn.setText(spannableString2);

        // 레벨 3 버튼
        Button level3Btn = (Button) findViewById(R.id.level3);
        level3Btn.setOnClickListener(view -> {
            Intent intent = new Intent(LevelSelectActivity.this, WordStudyActivity.class);
            intent.putExtra("chapter", 3); // 레벨 3 데이터 전달
            startActivity(intent);
        });

        String title3 = "Chapter 3\n\n";
        String description3 = "유령과의 우정:\n 버려진 집의 비밀";
        SpannableString spannableString3 = new SpannableString(title3 + description3);
        spannableString3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                0, title3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString3.setSpan(new android.text.style.RelativeSizeSpan(1.3f),
                0, title3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        level3Btn.setText(spannableString3);
    }
}
