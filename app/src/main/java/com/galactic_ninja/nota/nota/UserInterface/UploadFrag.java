package com.galactic_ninja.nota.nota.UserInterface;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.galactic_ninja.nota.nota.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFrag extends Fragment implements View.OnClickListener    {
    @BindView(R.id.UploadButton) CardView mUploadButton;
    @BindView(R.id.TitleEditText) EditText mTitle;
    @BindView(R.id.CategoryEditText) EditText mCategory;
    @BindView(R.id.NoteEditText) EditText mNote;
    public UploadFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        ButterKnife.bind(this,view);

        mUploadButton.setOnClickListener(this);
        return view;
    }
    public static UploadFrag newInstance(){
        UploadFrag frag = new UploadFrag();
        return frag;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.UploadButton:
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

        }
    }

}
