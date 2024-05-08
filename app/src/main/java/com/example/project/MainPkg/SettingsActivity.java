package com.example.project.MainPkg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.StartupPkg.Launch;
import com.example.project.exceptions.NoInputException;
import com.example.project.exceptions.PassLengthException;
import com.example.project.exceptions.SpecialCharacterException;
import com.example.project.exceptions.WhiteSpaceException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Set;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String FILE_NAME = "loginPref_File";// mypref_File--- name of the file
    public static final String PASSWORD = "passwordKey";
    public static final String EMAIL = "emailKey";
    FirebaseAuth sAuth;
    FirebaseUser sUser;
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button logout, back, changePassword;

        logout = findViewById(R.id.logoutButton);
        back = findViewById(R.id.backButton);
        changePassword = findViewById(R.id.passwordChangeButton);
        sAuth = FirebaseAuth.getInstance();
        sUser = sAuth.getCurrentUser();
        String emailT = sUser.getEmail();
        email = findViewById(R.id.emailEditText);
        pass = findViewById(R.id.passwordEditText);
        email.setText(emailT);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(SettingsActivity.this, Launch.class);
                sharedpreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                sAuth.signOut();
                Toast.makeText(SettingsActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passT;
                passT = pass.getText().toString();
                String specialCharacters = " !#$%&'()*+,-./:;<=>?@[]^_`{|}~";

                String special[] = specialCharacters.split("");
                try {
                    if (passT.isEmpty()) {
                        throw new NoInputException("Input Require");
                    } else if (passT.contains(" ")) {
                        throw new WhiteSpaceException("Whitespace");
                    } else if (passT.length() < 6) {
                        throw new PassLengthException("Length");
                    } else {
                        boolean passChecker = false;

                        for (int i = 0; i < specialCharacters.length(); i++) {
                            char specialChar = specialCharacters.charAt(i);
                            if (passT.contains(String.valueOf(specialChar))) {
                                passChecker = true;
                            }
                        }

                        if (passChecker != true) {
                            throw new SpecialCharacterException("Exception");
                        }
                    }
                    sUser.updatePassword(passT);
                    pass.setText("");
                } catch (NoInputException i) {
                    Toast.makeText(SettingsActivity.this, "Please enter in a password", Toast.LENGTH_SHORT).show();
                } catch (WhiteSpaceException w) {
                    Toast.makeText(SettingsActivity.this, "Whitespace within password", Toast.LENGTH_SHORT).show();
                } catch (SpecialCharacterException ss) {
                    Toast.makeText(SettingsActivity.this, "Need one special characters in password", Toast.LENGTH_SHORT).show();
                } catch (PassLengthException l) {
                    Toast.makeText(SettingsActivity.this, "Password needs to be longer than 6 characters", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}