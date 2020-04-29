package com.example.proiect_android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proiect_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {
    private static final String TAG = "AuthActivity";
    private static final String EXTRA_USER = "com.example.proiect_android.activities.EXTRA_USER";
    private static final String EXTRA_FIREBASE = "com.example.proiect_android.activities.EXTRA_FIREBASE";
    private FirebaseAuth mAuth;
    private EditText mail,password;
    private Button signUp,logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.sign_up_button);
        logIn = findViewById(R.id.login_button);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = findViewById(R.id.edit_email);
                password = findViewById(R.id.edit_password);
                String email = mail.getText().toString();
                String pass = password.getText().toString();
                signUpUser(email, pass);
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = findViewById(R.id.edit_email);
                password = findViewById(R.id.edit_password);
                String email = mail.getText().toString();
                String pass = password.getText().toString();
                logInUser(email, pass);
            }
        });
        
    }

    private void logInUser(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void signUpUser(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update
                            // UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_USER,currentUser);
            startActivity(intent);
        }
    }


}
