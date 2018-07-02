package com.galactic_ninja.nota.nota.UserInterface;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.galactic_ninja.nota.nota.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.emailEditText) EditText mEmailEdittext;
    @BindView(R.id.passwordEditText) EditText mPasswordEditText;
    @BindView(R.id.signUpButton) CardView mSignUpButton;
    @BindView(R.id.loginButton) CardView mLoginButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        mSignUpButton.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.signUpButton:
                createNewUser();
                break;
            case R.id.loginButton:
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
        }
    }

    private void createNewUser(){
        final String email = mEmailEdittext.getText().toString().trim();
        final String password = mPasswordEditText.getText().toString().trim();


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        Toast.makeText(CreateAccount.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(CreateAccount.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreateAccount.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(CreateAccount.this, "Email Sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void createAuthStateListener(){
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    sendVerificationEmail();
                    Intent intent = new Intent(CreateAccount.this,Holder.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }



    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (mAuthStateListener != null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }


}
