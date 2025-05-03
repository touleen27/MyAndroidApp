package com.app.uptrip;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class TypingTestActivity extends AppCompatActivity {

    private TextView textToType, resultView, timerView;
    private EditText userInput;
    private Button submitButton;

    private final String paragraph = "Typing is a skill used in almost every job. " +
            "Your typing speed and accuracy can affect your productivity and communication.";

    private long startTime = 0;
    private boolean typingStarted = false;

    private Handler timerHandler = new Handler();
    private Runnable timerRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing_test);

        textToType = findViewById(R.id.textToType);
        userInput = findViewById(R.id.userInput);
        submitButton = findViewById(R.id.submitButton);
        resultView = findViewById(R.id.resultView);
        timerView = findViewById(R.id.timerView);

        textToType.setText(paragraph);

        userInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !typingStarted) {
                typingStarted = true;
                startTime = SystemClock.elapsedRealtime();
                startTimer();
            }
        });

        submitButton.setOnClickListener(v -> evaluateTyping());
    }

    private void startTimer() {
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.elapsedRealtime() - startTime;
                int seconds = (int) (elapsed / 1000);
                timerView.setText("Time: " + seconds + " sec");
                timerHandler.postDelayed(this, 1000);
            }
        };
        timerHandler.postDelayed(timerRunnable, 0);
    }

    private void stopTimer() {
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void evaluateTyping() {
        stopTimer();

        long elapsedTime = SystemClock.elapsedRealtime() - startTime;
        String typed = userInput.getText().toString().trim();

        int correctChars = 0;
        for (int i = 0; i < Math.min(paragraph.length(), typed.length()); i++) {
            if (paragraph.charAt(i) == typed.charAt(i)) {
                correctChars++;
            }
        }

        double minutes = elapsedTime / 60000.0;
        int wordsTyped = typed.isEmpty() ? 0 : typed.split("\\s+").length;
        int wpm = (int) (wordsTyped / minutes);
        int accuracy = (int) (((double) correctChars / paragraph.length()) * 100);

        // Normalize speed to 100 = 40 WPM
        int speedScore = Math.min((int)((wpm / 40.0) * 100), 100);
        int score = (accuracy + speedScore) / 2;

        resultView.setText("WPM: " + wpm +
                "\nAccuracy: " + accuracy + "%" +
                "\nScore: " + score + "/100");
    }
}
