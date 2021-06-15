package com.hfad.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInActivity extends AppCompatActivity {

    private EditText email, password;
    private Button signIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Removes the top bar where battery and cell service is displayed
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setUpActionBar();

        mAuth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.btn_sign_in);
        email = findViewById(R.id.sign_in_email);
        password = findViewById(R.id.sign_in_password);
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null)
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        signIn = findViewById(R.id.btn_sign_in);
        signIn.setVisibility(View.VISIBLE);
    }

    public void onSignIn(View view){

        String sEmail = email.getText().toString().trim();
        String sPassword = password.getText().toString().trim();

        if (sEmail.isEmpty()) {
            email.setError("Email is required.");
            email.requestFocus();
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()){
            email.setError("Please enter a valid email.");
            email.requestFocus();
            return;
        }
        else if (sPassword.isEmpty()){
            password.setError("Password is required.");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    onSuccessfulLogIn(view);
                }else{
                    Toast.makeText(SignInActivity.this, "Failed to login.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setUpActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar_sign_in_activity));
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_back_arrow_nav);
        }
    }

    public void onSignUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onSuccessfulLogIn(View view){
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
    }
}