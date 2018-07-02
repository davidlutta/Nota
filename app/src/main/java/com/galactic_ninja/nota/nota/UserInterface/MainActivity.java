package com.galactic_ninja.nota.nota.UserInterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Switch;

import com.galactic_ninja.nota.nota.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.loginButton) CardView mLoginButton;
    @BindView(R.id.createAccountButton) CardView mCreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mCreateAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view == mCreateAccountButton){
            Intent intent = new Intent(MainActivity.this,CreateAccount.class);
            startActivity(intent);
        }
    }

}
