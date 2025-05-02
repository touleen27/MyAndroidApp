package com.app.uptrip;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.app.uptrip.OnDialogAction;
import com.app.uptrip.R;

public class ResultDialog extends Dialog {

    private final String message;
    private final OnDialogAction listener;

    public ResultDialog(@NonNull Context context, String message, OnDialogAction listener) {
        super(context);
        this.message = message;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dialog);

        TextView messageText = findViewById(R.id.messageText);
        Button startAgainButton = findViewById(R.id.startAgainButton);

        messageText.setText(message);

        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRestartMatch();
                dismiss();
            }
        });
    }
}
