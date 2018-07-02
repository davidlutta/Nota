package com.galactic_ninja.nota.nota.UserInterface;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.galactic_ninja.nota.nota.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.loginButton) CardView mLoginButton;
    @BindView(R.id.createAccountButton) CardView mCreateAccountButton;
    @BindView(R.id.emailEditText) EditText mEmail;
    @BindView(R.id.passwordEditText) EditText mPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mCreateAccountButton.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, Holder.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onClick(View view){
        if (view == mCreateAccountButton){
            Intent intent = new Intent(MainActivity.this,CreateAccount.class);
            startActivity(intent);
        }

        if (view == mLoginButton){
            loginWithPassword();
        }
    }

    private void loginWithPassword(){
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (email.equals("")){
            mEmail.setError("Please Enter your Email");
            return;
        }

        if (password.equals("")){
            mPassword.setError("Please Enter your Password");
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Login Failed please try again", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(MainActivity.this, "please verify Email", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

}
