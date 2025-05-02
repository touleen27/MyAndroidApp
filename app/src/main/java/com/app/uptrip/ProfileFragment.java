package com.app.uptrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.uptrip.databinding.FragmentProfileBinding;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements OnDialogAction {

    private String playerOneName;
    private String playerTwoName;

    private FragmentProfileBinding binding;
    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;

    private final ActivityResultLauncher<Intent> addPlayersLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            playerOneName = data.getStringExtra("playerOne");
                            playerTwoName = data.getStringExtra("playerTwo");

                            binding.playerOneName.setText(playerOneName);
                            binding.playerTwoName.setText(playerTwoName);

                            Toast.makeText(getContext(), "Starting: " + playerOneName + " vs " + playerTwoName, Toast.LENGTH_SHORT).show();
                        }
                    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Launch AddPlayers activity when the fragment starts
        Intent intent = new Intent(requireContext(), AddPlayers.class);
        addPlayersLauncher.launch(intent);

        // Set up winning combinations
        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        setClickListeners();

        return view;
    }

    private void setClickListeners() {
        binding.image1.setOnClickListener(v -> {
            if (isBoxSelectable(0)) performAction(binding.image1, 0);
        });
        binding.image2.setOnClickListener(v -> {
            if (isBoxSelectable(1)) performAction(binding.image2, 1);
        });
        binding.image3.setOnClickListener(v -> {
            if (isBoxSelectable(2)) performAction(binding.image3, 2);
        });
        binding.image4.setOnClickListener(v -> {
            if (isBoxSelectable(3)) performAction(binding.image4, 3);
        });
        binding.image5.setOnClickListener(v -> {
            if (isBoxSelectable(4)) performAction(binding.image5, 4);
        });
        binding.image6.setOnClickListener(v -> {
            if (isBoxSelectable(5)) performAction(binding.image6, 5);
        });
        binding.image7.setOnClickListener(v -> {
            if (isBoxSelectable(6)) performAction(binding.image7, 6);
        });
        binding.image8.setOnClickListener(v -> {
            if (isBoxSelectable(7)) performAction(binding.image8, 7);
        });
        binding.image9.setOnClickListener(v -> {
            if (isBoxSelectable(8)) performAction(binding.image9, 8);
        });
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            if (checkResults()) {
                showResult(binding.playerOneName.getText().toString() + " is a Winner!");
            } else if (totalSelectedBoxes == 9) {
                showResult("Match Draw");
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);
            if (checkResults()) {
                showResult(binding.playerTwoName.getText().toString() + " is a Winner!");
            } else if (totalSelectedBoxes == 9) {
                showResult("Match Draw");
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    @Override
    public void onRestartMatch() {
        restartMatch();
    }

    private void showResult(String message) {
        ResultDialog resultDialog = new ResultDialog(requireContext(), message, this);
        resultDialog.setCancelable(false);
        resultDialog.show();
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResults() {
        for (int[] combination : combinationList) {
            if (boxPositions[combination[0]] == playerTurn &&
                    boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn = 1;
        totalSelectedBoxes = 1;

        binding.image1.setImageResource(R.drawable.white_box);
        binding.image2.setImageResource(R.drawable.white_box);
        binding.image3.setImageResource(R.drawable.white_box);
        binding.image4.setImageResource(R.drawable.white_box);
        binding.image5.setImageResource(R.drawable.white_box);
        binding.image6.setImageResource(R.drawable.white_box);
        binding.image7.setImageResource(R.drawable.white_box);
        binding.image8.setImageResource(R.drawable.white_box);
        binding.image9.setImageResource(R.drawable.white_box);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
