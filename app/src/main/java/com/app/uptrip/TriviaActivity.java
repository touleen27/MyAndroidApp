package com.app.uptrip;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TriviaActivity extends AppCompatActivity {

    private TextView questionText, scoreText, timerText;
    private Button answer1, answer2, answer3, answer4;
    private int score = 0;
    private int questionIndex = 0;
    private List<TriviaQuestion> triviaQuestions;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        // Initialize views
        questionText = findViewById(R.id.question_text);
        scoreText = findViewById(R.id.score_text);
        timerText = findViewById(R.id.timer_text);
        answer1 = findViewById(R.id.answer_button1);
        answer2 = findViewById(R.id.answer_button2);
        answer3 = findViewById(R.id.answer_button3);
        answer4 = findViewById(R.id.answer_button4);

        // Create some trivia questions
        triviaQuestions = new ArrayList<>();
        triviaQuestions.add(new TriviaQuestion("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, "Paris"));
        triviaQuestions.add(new TriviaQuestion("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, "Mars"));
        triviaQuestions.add(new TriviaQuestion("What is the largest ocean?", new String[]{"Atlantic", "Pacific", "Indian", "Arctic"}, "Pacific"));
        triviaQuestions.add(new TriviaQuestion("Who painted the Mona Lisa?", new String[]{"Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"}, "Leonardo da Vinci"));

        loadQuestion(questionIndex);

        // Set up the timer
        startTimer();

        // Set button listeners for answers
        answer1.setOnClickListener(view -> checkAnswer(answer1.getText().toString()));
        answer2.setOnClickListener(view -> checkAnswer(answer2.getText().toString()));
        answer3.setOnClickListener(view -> checkAnswer(answer3.getText().toString()));
        answer4.setOnClickListener(view -> checkAnswer(answer4.getText().toString()));
    }

    private void loadQuestion(int index) {
        if (index < triviaQuestions.size()) {
            TriviaQuestion question = triviaQuestions.get(index);
            questionText.setText(question.getQuestion());
            answer1.setText(question.getOptions()[0]);
            answer2.setText(question.getOptions()[1]);
            answer3.setText(question.getOptions()[2]);
            answer4.setText(question.getOptions()[3]);
        } else {
            // End of questions
            questionText.setText("Game Over! Your score: " + score);
            scoreText.setVisibility(View.INVISIBLE);
            answer1.setVisibility(View.INVISIBLE);
            answer2.setVisibility(View.INVISIBLE);
            answer3.setVisibility(View.INVISIBLE);
            answer4.setVisibility(View.INVISIBLE);
        }
    }

    private void checkAnswer(String selectedAnswer) {
        TriviaQuestion currentQuestion = triviaQuestions.get(questionIndex);
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            score++;
            scoreText.setText("Score: " + score);
        }

        // Move to next question
        questionIndex++;
        loadQuestion(questionIndex);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time Left: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // When the timer finishes, show the final score
                loadQuestion(questionIndex);
            }
        }.start();
    }
}
