package com.hfad.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText firstName, lastName, email, password;
    public Button createAccount;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        firstName = (EditText) findViewById(R.id.et_firstName);
        lastName = (EditText) findViewById(R.id.et_lastName);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);

        createAccount = (Button) findViewById(R.id.btn_create_account);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Removes the top bar where battery and cell service is displayed
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setUpActionBar();
    }

    /*@Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }*/

    private void setUpActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_sign_up_activity));
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_back_arrow_nav);
        }
    }

    public void onSignIn(View view) {
        onSuccess(view);
    }

    public void onSuccess(View view){
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void registerUser(View view) {
        String sFirstName = firstName.getText().toString().trim();
        String sLastName = lastName.getText().toString().trim();
        String sEmail = email.getText().toString().trim();
        String sPassword = password.getText().toString().trim();

        if (sFirstName.isEmpty()) {
            firstName.setError("First name is required.");
            firstName.requestFocus();
            return;
        } else if (sLastName.isEmpty()) {
            lastName.setError("Last name is required.");
            lastName.requestFocus();
            return;
        } else if (sEmail.isEmpty()) {
            email.setError("Email is required.");
            email.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            email.setError("Please provide a valid email.");
            email.requestFocus();
            return;
        } else if (sPassword.isEmpty()) {
            password.setError("Password is required.");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(sFirstName, sLastName, sEmail, sPassword);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "User has been registered successfully.", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                        // sends user to MainActivity
                                        onSuccess(view);
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Failed to register. Try again.", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(SignUpActivity.this, "Failed to register.", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}