package com.example.project.StartupPkg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project.R;

public class Launch extends AppCompatActivity {

    Button create, sign;

    SharedPreferences sharedpreferences;
    public static final String FILE_NAME = "loginPref_File";
    public static final String PASSWORD = "passwordKey";
    public static final String EMAIL = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        create=findViewById(R.id.createA);

        sign=findViewById(R.id.sign_in);
        loaddata();



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Launch.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Launch.this, Login.class);
                startActivity(intent);
            }
        });
    }
    public void loaddata() {
        sharedpreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        if (sharedpreferences.contains(PASSWORD) && (sharedpreferences.contains(EMAIL))) {
            Intent intent = new Intent(Launch.this, Login.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "First time logging in?", Toast.LENGTH_LONG).show();
        }
    }

}