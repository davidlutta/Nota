package com.galactic_ninja.nota.nota.UserInterface;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galactic_ninja.nota.nota.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment implements View.OnClickListener{


    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
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

    }

}
