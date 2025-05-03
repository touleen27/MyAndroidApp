package com.app.uptrip;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MathChallengeActivity extends AppCompatActivity {

    TextView challengeTitle, questionText;
    EditText answerInput;
    Button submitButton;

    int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_challenge);

        challengeTitle = findViewById(R.id.challengeTitle);
        questionText = findViewById(R.id.questionText);
        answerInput = findViewById(R.id.answerInput);
        submitButton = findViewById(R.id.submitButton);

        generateNewQuestion();

        submitButton.setOnClickListener(v -> {
            String userAnswer = answerInput.getText().toString();
            if (userAnswer.isEmpty()) {
                Toast.makeText(this, "Please enter an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            if (Integer.parseInt(userAnswer) == correctAnswer) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong! Correct answer is: " + correctAnswer, Toast.LENGTH_SHORT).show();
            }

            answerInput.setText("");
            generateNewQuestion(); // Load next question
        });
    }

    private void generateNewQuestion() {
        Random random = new Random();
        int a = random.nextInt(50);
        int b = random.nextInt(50);
        int operation = random.nextInt(4); // 0=+, 1=-, 2=*, 3=division (optional)

        String question;
        switch (operation) {
            case 0:
                correctAnswer = a + b;
                question = a + " + " + b + " = ?";
                break;
            case 1:
                correctAnswer = a - b;
                question = a + " - " + b + " = ?";
                break;
            case 2:
                correctAnswer = a * b;
                question = a + " × " + b + " = ?";
                break;
            case 3:
                // Ensure no divide by zero
                b = random.nextInt(49) + 1;
                correctAnswer = a / b;
                question = a + " ÷ " + b + " = ? (rounded down)";
                break;
            default:
                correctAnswer = 0;
                question = "Error";
        }

        questionText.setText(question);
    }
}
