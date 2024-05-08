package com.example.project.StartupPkg;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import com.example.project.R;
import com.example.project.exceptions.EmailFormatException;
import com.example.project.exceptions.NoInputException;
import com.example.project.exceptions.PassLengthException;
import com.example.project.exceptions.SpecialCharacterException;
import com.example.project.exceptions.WhiteSpaceException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateAccount extends AppCompatActivity {

    TextView error, link;
    FirebaseAuth sAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser sUser;
    EditText email, name, password;
    Button createAcc;
    String emailT, passT, nameT;
    private static boolean errors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        email = findViewById(R.id.email);
        name= findViewById(R.id.username);
        password = findViewById(R.id.password);
        error = findViewById(R.id.show);
        link = findViewById(R.id.link);
        sAuth = FirebaseAuth.getInstance();
        createAcc = findViewById(R.id.createA);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("username");
        errors = true;

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailT = email.getText().toString();
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
                    else
                    {
                        error.setText("");
                    }
                    errors = false;
                } catch (NoInputException i) {
                    error.setText("Please enter an email");
                } catch (WhiteSpaceException w)
                {
                    error.setText("Whitespace within email");
                } catch (EmailFormatException e)
                {
                    error.setText("Please format into an email template");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passT = password.getText().toString();
                String specialCharacters=" !#$%&'()*+,-./:;<=>?@[]^_`{|}~";

                String special[] = specialCharacters.split("");
                try {
                    if (passT.isEmpty()) {
                        throw new NoInputException("Input Require");
                    }
                    else if (passT.contains(" "))
                    {
                        throw new WhiteSpaceException("Whitespace");
                    }
                    else if (passT.length() < 6)
                    {
                        throw new PassLengthException("Length");
                    }
                    else {
                        boolean passChecker = false;

                        for (int i = 0; i < specialCharacters.length(); i++) {
                            char specialChar = specialCharacters.charAt(i);
                            if (passT.contains(String.valueOf(specialChar))) {
                                passChecker = true;
                            }
                        }

                        if (passChecker != true)
                        {
                            throw new SpecialCharacterException("Exception");
                        }
                        else
                        {
                            error.setText("");
                        }
                    }
                    errors = false;
                } catch (NoInputException i) {
                    error.setText("Please enter in a password");
                } catch (WhiteSpaceException w)
                {
                    error.setText("Whitespace within password");
                }
                catch (SpecialCharacterException ss)
                {
                    error.setText("Need one special characters in password");
                } catch (PassLengthException l)
                {
                    error.setText("Password needs to be longer than 6 characters");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailT = email.getText().toString();
                nameT = name.getText().toString();

                if(!nameT.equals(emailT)){
                    error.setText("Emails do not match");
                    errors = true;
                }
                else {
                    error.setText("");
                    errors = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) throws NoInputException, WhiteSpaceException, EmailFormatException {
//
//            }
//        });
//        password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) throws NoInputException, WhiteSpaceException, SpecialCharacterException {
//
//            }
//        });
//        name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) throws NoInputException, WhiteSpaceException {
//
//            }
//        });

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sAuth.createUserWithEmailAndPassword(emailT, passT);
                if (!errors) {
                    sAuth.createUserWithEmailAndPassword(emailT, passT);
                    sUser = sAuth.getCurrentUser();
//                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
//                                    FirebaseUser sUser = sAuth.getCurrentUser();
//                                    updateUI(sUser);
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                                    Toast.makeText(CreateAccount.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
//                                }
//                            }
//                        });
//                Toast.makeText(CreateAccount.this, sUser.getDisplayName(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateAccount.this, Login.class);
                    startActivity(intent);
                }
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccount.this, Login.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = sAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
    public boolean isEmpty(String ent)
    {
        if (ent == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = sAuth.getCurrentUser();
        if(currentUser != null){
            sUser.reload();
        }
    }
}