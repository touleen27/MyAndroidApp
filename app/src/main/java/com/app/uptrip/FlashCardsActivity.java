package com.app.uptrip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FlashCardsActivity extends AppCompatActivity {

    private TextView flashcardFront, flashcardBack, tvScore;
    private Button btnFlipCard;
    private int score = 0;
    private int currentCardIndex = 0;

    private String[] questions = {"What is the capital of France?", "What is 2 + 2?", "Name the largest planet."};
    private String[] answers = {"Paris", "4", "Jupiter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        flashcardFront = findViewById(R.id.flashcard_front);
        flashcardBack = findViewById(R.id.flashcard_back);
        btnFlipCard = findViewById(R.id.btnFlipCard);

        // Initial display
        flashcardFront.setText(questions[currentCardIndex]);

        // Flip the card when button is clicked
        btnFlipCard.setOnClickListener(v -> flipCard());
    }

    private void flipCard() {
        // If the card is showing the front (question), show the back (answer)
        if (flashcardFront.getVisibility() == View.VISIBLE) {
            flashcardFront.setVisibility(View.GONE);
            flashcardBack.setVisibility(View.VISIBLE);
            flashcardBack.setText(answers[currentCardIndex]);
        } else {
            // If the card is showing the back, show the front
            flashcardBack.setVisibility(View.GONE);
            flashcardFront.setVisibility(View.VISIBLE);

            // Move to the next card
            currentCardIndex++;
            if (currentCardIndex >= questions.length) {
                currentCardIndex = 0; // Loop back to the first card
            }

            flashcardFront.setText(questions[currentCardIndex]);
        }
    }
}
