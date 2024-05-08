package com.example.project.MainPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.project.R;

import java.util.Random;


public class HomepageFragment extends Fragment {
    TextView welcomeText, tips;
    ImageButton settings;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        settings = view.findViewById(R.id.settingsButton);
        tips = view.findViewById(R.id.tvtips);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });


        welcomeText = view.findViewById(R.id.textView2);
        welcomeText.setText("Welcome " + MainActivity.getUsername());

        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        Random random =  new Random();
        String[] tipsArray = {
                "Tip: Eating food makes you full",
                "Tip: Do not underestimate the cauliflower",
                "Tip: Once a carrot always a carrot", "Hummus much?",
                "Tip: Tuna rehen de",
                "Tip: The best writer in the world? John Green Beans",
                "Tip: Rice to meet you",
                "Tip: Relish the moment ",
                "Tip: Espresso Yourself",
                "Tip: Another One Bites the Crust",
                "Tip: Find Your Inner Peas",
                "Tip: Pasta La Vista, Baby",
                "Tip: Lettuce turnip the beat!",
                "Tip: How dairy be so udderly amazing?",
                "Tip: You’re one in a melon."};
        tips.setText(tipsArray[random.nextInt(tipsArray.length)]);
    }
}
