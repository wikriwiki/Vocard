package com.example.vocard;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class StoryActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private int chapter;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        chapter = getIntent().getIntExtra("chapter", -1);
        if (chapter < 1 || chapter > 3) {
            Toast.makeText(this, "잘못된 챕터 번호입니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ImageView story = findViewById(R.id.imageView);
        story.setClipToOutline(true);
        TextView eng = findViewById(R.id.englishWord);
        TextView kor = findViewById(R.id.koreanMeaning);
        Button next = findViewById(R.id.nextButton);
        Button prev = findViewById(R.id.prevButton);
        ImageView ttsbtn = findViewById(R.id.speakerIcon);
        ttsbtn.setClipToOutline(true);
        tts = new TextToSpeech(this, this);

        // 챕터별 데이터 설정
        int[] currentImages;
        String[] currentEnglishTexts, currentKoreanTexts;
        switch (chapter) {
            case 1:
                currentImages = new int[]{R.drawable.santastory1, R.drawable.santastory2, R.drawable.santastory3};
                currentEnglishTexts = new String[]{
                        "Santa prepares gifts for Christmas night",
                        "He flies with Rudolph",
                        "Santa brings joy and laughter to children"
                };
                currentKoreanTexts = new String[]{
                        "산타는 크리스마스 밤을 위한 선물을 준비합니다",
                        "루돌프와 함께 비행합니다",
                        "산타는 아이들에게 기쁨과 웃음을 선사합니다"
                };
                break;
            case 2:
                currentImages = new int[]{R.drawable.bravegirl1, R.drawable.bravegirl2, R.drawable.bravegirl3}; // 이미지 없음 처리
                currentEnglishTexts = new String[]{
                        "A girl finds a map",
                        "A brave girl explores a forest",
                        "In a cave, she discovers treasure"
                };
                currentKoreanTexts = new String[]{
                        "한 소녀가 지도를 찾습니다",
                        "용감한 소녀는 숲을 탐험합니다",
                        "동굴에서 보물을 발견합니다."
                };
                break;
            case 3:
                currentImages = new int[]{R.drawable.ghost1,R.drawable.ghost2,R.drawable.ghost3}; // 이미지 없음 처리
                currentEnglishTexts = new String[]{
                        "A ghost haunts an abandoned house",
                        "One night, she decides on adventure",
                        "She realizes that the ghost seeks friendship"
                };
                currentKoreanTexts = new String[]{
                        "유령이 버려진 집을 괴롭힙니다",
                        "어느 날 밤, 그녀는 탐험을 결심합니다",
                        "그녀는 유령이 우정을 추구한다는 사실을 알게 됩니다"
                };
                break;
            default:
                return;
        }

        AtomicInteger currentIndex = new AtomicInteger();
        updateContent(currentIndex.get(), story, eng, kor, currentImages, currentEnglishTexts, currentKoreanTexts);
        setButtonListeners(next, prev, currentIndex, story, eng, kor, currentImages, currentEnglishTexts, currentKoreanTexts);

        ttsbtn.setOnClickListener(v -> {
            if (tts != null) {
                String textToRead = eng.getText().toString();
                if (!textToRead.isEmpty()) {
                    tts.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, null);
                    Toast.makeText(getApplicationContext(), "발음 재생 중!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "영어 단어가 비어 있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setSpeechRate(0.6f);
        } else {
            Toast.makeText(this, "TTS 초기화 실패", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private void updateContent(int index, ImageView story, TextView eng, TextView kor,
                               int[] storyImages, String[] englishTexts, String[] koreanTexts) {
        if (storyImages.length > 0) {
            story.setImageResource(storyImages[index]);
            story.setVisibility(View.VISIBLE);
        } else {
            story.setVisibility(View.GONE);
        }
        eng.setText(englishTexts[index]);
        kor.setText(koreanTexts[index]);
    }

    private void setButtonListeners(Button next, Button prev, AtomicInteger currentIndex,
                                    ImageView story, TextView eng, TextView kor,
                                    int[] storyImages, String[] englishTexts, String[] koreanTexts) {
        next.setOnClickListener(v -> {
            currentIndex.getAndIncrement();
            if (currentIndex.get() >= englishTexts.length) {
                currentIndex.set(englishTexts.length - 1);
                Toast.makeText(this, "마지막 내용입니다.", Toast.LENGTH_SHORT).show();
            } else {
                updateContent(currentIndex.get(), story, eng, kor, storyImages, englishTexts, koreanTexts);
            }
        });

        prev.setOnClickListener(v -> {
            currentIndex.getAndDecrement();
            if (currentIndex.get() < 0) {
                currentIndex.set(0);
                Toast.makeText(this, "첫 번째 내용입니다.", Toast.LENGTH_SHORT).show();
            } else {
                updateContent(currentIndex.get(), story, eng, kor, storyImages, englishTexts, koreanTexts);
            }
        });
    }
}