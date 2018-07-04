package com.galactic_ninja.nota.nota.UserInterface;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galactic_ninja.nota.nota.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment implements View.OnClickListener{
    @BindView(R.id.logOutButton) CardView logoutButton;
    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        logoutButton.setOnClickListener(this);
        return view;
    }

    public static ProfileFrag newInstance(){
        ProfileFrag frag = new ProfileFrag();
        return frag;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.logOutButton:
                logOut();
                break;
        }
    }
    private void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

}
