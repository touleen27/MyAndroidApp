package com.app.uptrip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FlagQuizActivity extends AppCompatActivity {

    private ImageView flagImage;
    private Button option1, option2, option3, option4;
    private TextView scoreText;

    private List<FlagItem> flagList;
    private Set<String> usedFlags;
    private int score = 0;
    private FlagItem currentFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_quiz);

        flagImage = findViewById(R.id.flagImage);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        scoreText = findViewById(R.id.scoreText);

        flagList = new ArrayList<>();
        usedFlags = new HashSet<>();

        // Add flags (Assumes you have these images in res/drawable)
        flagList.add(new FlagItem("france", R.drawable.france));
        flagList.add(new FlagItem("germany", R.drawable.germany));
        flagList.add(new FlagItem("italy", R.drawable.italy));
        flagList.add(new FlagItem("japan", R.drawable.japan));
        flagList.add(new FlagItem("brazil", R.drawable.brazil));
        flagList.add(new FlagItem("canada", R.drawable.canada));
        flagList.add(new FlagItem("india", R.drawable.india));
        flagList.add(new FlagItem("china", R.drawable.china));
        flagList.add(new FlagItem("mexico", R.drawable.mexico));
        flagList.add(new FlagItem("australia", R.drawable.australia));

        loadNewFlag();
    }

    private void loadNewFlag() {
        if (usedFlags.size() == flagList.size()) {
            Toast.makeText(this, "Game over! Score: " + score, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Random random = new Random();
        do {
            currentFlag = flagList.get(random.nextInt(flagList.size()));
        } while (usedFlags.contains(currentFlag.getName()));

        usedFlags.add(currentFlag.getName());
        flagImage.setImageResource(currentFlag.getImageResId());

        // Set options
        List<String> options = new ArrayList<>();
        options.add(currentFlag.getName());

        while (options.size() < 4) {
            String randomName = flagList.get(random.nextInt(flagList.size())).getName();
            if (!options.contains(randomName)) {
                options.add(randomName);
            }
        }

        Collections.shuffle(options);

        option1.setText(capitalize(options.get(0)));
        option2.setText(capitalize(options.get(1)));
        option3.setText(capitalize(options.get(2)));
        option4.setText(capitalize(options.get(3)));

        View.OnClickListener listener = v -> {
            Button clicked = (Button) v;
            if (clicked.getText().toString().equalsIgnoreCase(currentFlag.getName())) {
                score++;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong! Correct: " + capitalize(currentFlag.getName()), Toast.LENGTH_SHORT).show();
            }
            scoreText.setText("Score: " + score);
            loadNewFlag();
        };

        option1.setOnClickListener(listener);
        option2.setOnClickListener(listener);
        option3.setOnClickListener(listener);
        option4.setOnClickListener(listener);
    }

    private String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    static class FlagItem {
        private final String name;
        private final int imageResId;

        FlagItem(String name, int imageResId) {
            this.name = name;
            this.imageResId = imageResId;
        }

        public String getName() {
            return name;
        }

        public int getImageResId() {
            return imageResId;
        }
    }
}
