package com.example.vocard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WordStudyActivity extends AppCompatActivity {

    private int currentIndex = 0; // 현재 단어 인덱스
    private Word[] wordArray; // 단어 배열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_study);

        ImageView img = findViewById(R.id.imageView);
        TextView englishWordView = findViewById(R.id.englishWord);
        TextView koreanMeaningView = findViewById(R.id.koreanMeaning);

        Button nxtBtn = findViewById(R.id.nextButton);
        Button prvBtn = findViewById(R.id.prevButton);
        Button gameBtn = findViewById(R.id.goToGameBtn);
        int chapter = getIntent().getIntExtra("chapter", -1);
        // 단어 배열 생성
        wordArray = new Word[]{
                new Word(R.drawable.bring, "Bring", "가져오다"),
                new Word(R.drawable.child, "Child", "아이"),
                new Word(R.drawable.fly, "Fly", "날다"),
                new Word(R.drawable.gift, "Gift", "선물"),
                new Word(R.drawable.joy, "Joy", "기쁨"),
                new Word(R.drawable.laughter, "Laughter", "웃음"),
                new Word(R.drawable.prepare, "Prepare", "준비하다"),
                new Word(R.drawable.rudolph, "Rudolph", "루돌프"),
                new Word(R.drawable.santa, "Santa", "산타")
        };

        // 초기 단어 표시
        updateWord(img, englishWordView, koreanMeaningView);

        // 다음 버튼 클릭 이벤트
        nxtBtn.setOnClickListener(view -> {
            currentIndex = (currentIndex + 1) % wordArray.length; // 순환
            updateWord(img, englishWordView, koreanMeaningView);
        });

        // 이전 버튼 클릭 이벤트
        prvBtn.setOnClickListener(view -> {
            currentIndex = (currentIndex - 1 + wordArray.length) % wordArray.length; // 순환
            updateWord(img, englishWordView, koreanMeaningView);
        });

        // 게임하러 가기 버튼 클릭 이벤트
        gameBtn.setOnClickListener(view -> {
            Intent intent = new Intent(WordStudyActivity.this, CardFlipActivity.class);
            startActivity(intent);
        });
    }

    // 현재 단어 정보를 업데이트
    private void updateWord(ImageView img, TextView englishWordView, TextView koreanMeaningView) {
        Word currentWord = wordArray[currentIndex];
        img.setImageResource(currentWord.getImageResId());
        englishWordView.setText(currentWord.getEnglishWord());
        koreanMeaningView.setText(currentWord.getKoreanMeaning());
    }
}
