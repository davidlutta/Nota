package com.galactic_ninja.nota.nota.UserInterface;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.galactic_ninja.nota.nota.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Holder extends AppCompatActivity {
    @BindView(R.id.bottomMenuView) BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);
        ButterKnife.bind(this);

        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()){
                    case R.id.bottombaritem_Upload:
                        selectedFragment = UploadFrag.newInstance();
                        break;
                    case R.id.bottombaritem_Feed:
                        selectedFragment = FeedFrag.newInstance();
                        break;
                    case R.id.bottombaritem_Profile:
                        selectedFragment = ProfileFrag.newInstance();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout,selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, UploadFrag.newInstance());
        transaction.commit();

    }
}
