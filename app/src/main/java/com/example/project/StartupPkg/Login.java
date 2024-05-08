package com.example.project.StartupPkg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import com.example.project.MainPkg.MainActivity;
import com.example.project.R;
import com.example.project.exceptions.EmailFormatException;
import com.example.project.exceptions.NoInputException;
import com.example.project.exceptions.WhiteSpaceException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class Login extends AppCompatActivity {

    TextView link;
    EditText email, password;
    FirebaseAuth sAuth;
    FirebaseUser sUser;
    Button signin;
    SharedPreferences sharedpreferences;
    public static final String FILE_NAME = "loginPref_File";// mypref_File--- name of the file
    public static final String PASSWORD = "passwordKey";
    public static final String EMAIL = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        link = findViewById(R.id.link);
        signin = findViewById((R.id.sign_in));
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        sAuth = FirebaseAuth.getInstance();
        sUser = sAuth.getCurrentUser();
        loaddata();

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CreateAccount.class);
                startActivity(intent);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailT = email.getText().toString();
                String passwordT = password.getText().toString();
                String format =  "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";;
                try {
                    if (emailT.isEmpty()) {
                        throw new NoInputException("Input Require");
                    }
                    else if (emailT.contains(" "))
                    {
                        throw new WhiteSpaceException("Whitespace");
                    }
                    else if (!emailT.matches(format))
                    {
                        throw new EmailFormatException();
                    }
                    sAuth.signInWithEmailAndPassword(emailT, passwordT).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, "Your Email or Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    });
                    sAuth.signInWithEmailAndPassword(emailT, passwordT).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            //intent.putExtra("username", sUser.getDisplayName());
                            sharedpreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            //putString---to edit the file in key value pairs
                            editor.putString(PASSWORD, passwordT);
                            editor.putString(EMAIL, emailT);
                            editor.apply();
                            intent.putExtra("Username", emailT.substring(0,1).toUpperCase()+emailT.substring(1,emailT.indexOf('@')));
                            startActivity(intent);
                        }
                    });
                } catch (NoInputException i) {
                    Toast.makeText(Login.this, "Please enter an email", Toast.LENGTH_SHORT).show();
                } catch (WhiteSpaceException w)
                {
                    Toast.makeText(Login.this, "Whitespace within email", Toast.LENGTH_SHORT).show();
                } catch (EmailFormatException e)
                {
                    Toast.makeText(Login.this, "Please format into an email template", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void loaddata() {
        sharedpreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        if (sharedpreferences.contains(PASSWORD) && (sharedpreferences.contains(EMAIL))) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            email.setText(sharedpreferences.getString(EMAIL, ""));
            String emailT = email.getText().toString();
            intent.putExtra("Username", emailT.substring(0,1).toUpperCase()+emailT.substring(1,emailT.indexOf('@')));
            password.setText(sharedpreferences.getString(PASSWORD, ""));
            startActivity(intent);
        } else {
            Toast.makeText(this, "First time logging in?", Toast.LENGTH_LONG).show();
        }
    }

}

