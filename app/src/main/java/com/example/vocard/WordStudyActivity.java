package com.example.vocard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WordStudyActivity extends AppCompatActivity {

    private int currentIndex = 0; // 현재 단어 인덱스
    private Word[] wordArray; // 현재 선택된 단어 배열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_study);
        ImageView img = findViewById(R.id.imageView);
        img.setClipToOutline(true);

        ImageView ttsBtn = findViewById(R.id.speakerIcon);
        ttsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 원하는 동작을 여기에 작성
                Toast.makeText(getApplicationContext(), "발음 재생중!", Toast.LENGTH_SHORT).show();
            }
        });

        TextView englishWordView = findViewById(R.id.englishWord);
        TextView koreanMeaningView = findViewById(R.id.koreanMeaning);

        Button nxtBtn = findViewById(R.id.nextButton);
        Button prvBtn = findViewById(R.id.prevButton);
        Button gameBtn = findViewById(R.id.goToGameBtn);


        Button prevBtn = (Button) findViewById(R.id.backMain);
        prevBtn.setOnClickListener(view -> {
            finish();
        });



        // 전달받은 챕터 정보 가져오기
        int chapter = getIntent().getIntExtra("chapter", -1);

        // 챕터별 단어 배열 생성
        Word[] chapter1Words = new Word[]{
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
/*
        Word[] chapter2Words = new Word[]{
                new Word(R.drawable.girl, "Girl", "소녀"),
                new Word(R.drawable.finds, "Finds", "찾다"),
                new Word(R.drawable.map, "Map", "지도"),
                new Word(R.drawable.she, "She", "그녀"),
                new Word(R.drawable.explores, "Explores", "탐험하다"),
                new Word(R.drawable.forest, "Forest", "숲"),
                new Word(R.drawable.cave, "Cave", "동굴"),
                new Word(R.drawable.discovers, "Discovers", "발견하다"),
                new Word(R.drawable.treasure, "Treasure", "보물")
        };

        Word[] chapter3Words = new Word[]{
                new Word(R.drawable.ghost, "Ghost", "유령"),
                new Word(R.drawable.haunts, "Haunts", "괴롭히다"),
                new Word(R.drawable.abandoned, "Abandoned", "버려진"),
                new Word(R.drawable.night, "Night", "밤"),
                new Word(R.drawable.decides, "Decides", "결정하다"),
                new Word(R.drawable.explore, "Explore", "탐험하다"),
                new Word(R.drawable.discovers, "Discovers", "발견하다"),
                new Word(R.drawable.seeks, "Seeks", "추구하다"),
                new Word(R.drawable.friendship, "Friendship", "우정")
        };
*/
        // 챕터에 따라 단어 배열 선택
        switch (chapter) {
            case 1:
                wordArray = chapter1Words;
                break;
                /*
            case 2:
                wordArray = chapter2Words;
                break;
            case 3:
                wordArray = chapter3Words;
                break;*/
            default:
                wordArray = new Word[0]; // 잘못된 챕터일 경우 빈 배열
                break;
        }

        // 초기 단어 표시
        if (wordArray.length > 0) {
            updateWord(img, englishWordView, koreanMeaningView);
        } else {
            englishWordView.setText("챕터를 찾을 수 없습니다.");
            koreanMeaningView.setText("");
        }

        // 다음 버튼 클릭 이벤트
        nxtBtn.setOnClickListener(view -> {
            if (wordArray.length > 0) {
                currentIndex = (currentIndex + 1) % wordArray.length; // 순환
                updateWord(img, englishWordView, koreanMeaningView);
            }
        });

        // 이전 버튼 클릭 이벤트
        prvBtn.setOnClickListener(view -> {
            if (wordArray.length > 0) {
                currentIndex = (currentIndex - 1 + wordArray.length) % wordArray.length; // 순환
                updateWord(img, englishWordView, koreanMeaningView);
            }
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
