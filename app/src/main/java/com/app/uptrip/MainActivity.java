package com.app.uptrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.app.uptrip.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Declare the binding variable
    ActivityMainBinding binding;
    CardView imagesCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ERR: main");
        // Initialize binding and set the layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        View v = getLayoutInflater().inflate(R.layout.fragment_home,null);




//        imagesCard = (CardView)v.findViewById(R.id.imageCard);

//
//        System.out.println("ERR: Card");
//        System.out.println(imagesCard);
//        imagesCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("ERR: onClick");
//                Intent intent = new Intent(MainActivity.this, HaifaActivity.class);
//                startActivity(intent);
//                                          }
//        });
        // Set the default fragment when the activity is created

        // Remove background for the BottomNavigationView
        binding.bottomNavigationView.setBackground(null);

        // Set an item selected listener for the BottomNavigationView
        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Use if-else instead of switch-case to handle item selection
                if (item.getItemId() == R.id.home) {
                    replaceFragment(new HomeFragment());
                } else if (item.getItemId() == R.id.orders) {
                    replaceFragment(new NotificationFragment());
                } else if (item.getItemId() == R.id.person) {
                    replaceFragment(new ProfileFragment());
                } else if (item.getItemId() == R.id.commute) {
                    replaceFragment(new CommuteFragment());
                }
                return true; // Indicate the item selection was handled
            }
        });
    }

    // Method to replace fragments
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
