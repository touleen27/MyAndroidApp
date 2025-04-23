package com.app.uptrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private CardView haifaCard,TelavivCard,Tabaria,Yaffa,Jerusalem,Eilat;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the CardView in the inflated layout
        haifaCard = (CardView)v.findViewById(R.id.haifaCard);
            haifaCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle the click event
                    Log.d("HomeFragment", "CardView clicked");
                    Intent intent = new Intent(getActivity(), HaifaActivity.class);
                    startActivity(intent);
                }
            });

        TelavivCard = (CardView)v.findViewById(R.id.TelavivCard);
        TelavivCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                Log.d("HomeFragment", "CardView clicked");
                Intent intent = new Intent(getActivity(), TelavivActivity.class);
                startActivity(intent);
            }
        });

         Tabaria= (CardView)v.findViewById(R.id.TabariaCard);
        Tabaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                Log.d("HomeFragment", "CardView clicked");
                Intent intent = new Intent(getActivity(), TabariaActivity.class);
                startActivity(intent);
            }
        });


        Yaffa= (CardView)v.findViewById(R.id.YaffaCard);
        Yaffa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                Log.d("HomeFragment", "CardView clicked");
                Intent intent = new Intent(getActivity(), YaffaActivity.class);
                startActivity(intent);
            }
        });

        Jerusalem= (CardView)v.findViewById(R.id.JerusalemCard);
        Jerusalem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                Log.d("HomeFragment", "CardView clicked");
                Intent intent = new Intent(getActivity(), JerusalemActivity.class);
                startActivity(intent);
            }
        });


        Eilat= (CardView)v.findViewById(R.id.EilatCard);
        Eilat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                Log.d("HomeFragment", "CardView clicked");
                Intent intent = new Intent(getActivity(), EilatActivity.class);
                startActivity(intent);
            }
        });








        return v;
    }
}
