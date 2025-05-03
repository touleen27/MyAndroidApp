package com.app.uptrip;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuickTapActivity extends AppCompatActivity {

    private TextView timerTextView, scoreTextView, resultTextView;
    private Button tapButton, startButton;
    private int score = 0;
    private boolean isRunning = false;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_tap);

        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        tapButton = findViewById(R.id.tapButton);
        startButton = findViewById(R.id.startButton);

        tapButton.setEnabled(false);

        tapButton.setOnClickListener(v -> {
            if (isRunning) {
                score++;
                scoreTextView.setText("Score: " + score);
            }
        });

        startButton.setOnClickListener(v -> startChallenge());
    }

    private void startChallenge() {
        score = 0;
        scoreTextView.setText("Score: 0");
        resultTextView.setText("");
        tapButton.setEnabled(true);
        isRunning = true;

        countDownTimer = new CountDownTimer(10000, 1000) {
            int secondsLeft = 10;

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time: " + secondsLeft-- + "s");
            }

            @Override
            public void onFinish() {
                isRunning = false;
                tapButton.setEnabled(false);
                timerTextView.setText("Time: 0s");
                resultTextView.setText("Time's up! You tapped " + score + " times.");
            }
        }.start();
    }
}
