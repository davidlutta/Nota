package com.galactic_ninja.nota.nota.UserInterface;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.galactic_ninja.nota.nota.Constants;
import com.galactic_ninja.nota.nota.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
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
                String title = mTitle.getText().toString().trim();
                String category = mCategory.getText().toString().trim();
                String note = mNote.getText().toString();
                if (title.length() > 1 && category.length() > 1 && note.length() > 1) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    DatabaseReference noteRef = FirebaseDatabase
                            .getInstance()
                            .getReference("note")
                            .child(uid);
                    Map<String, User> newNote = new HashMap<>();
                    User user1 = new User(title, category, note);
                    newNote.put(user1.getmTitle(), new User(title, category, note));
                    noteRef.push().setValue(newNote);
                    Toast.makeText(getActivity(), "Note saved", Toast.LENGTH_SHORT).show();
                    onCompleteForm();
                } else {
                    Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
public void onCompleteForm(){
        mTitle.getText().clear();
        mCategory.getText().clear();
        mNote.getText().clear();
    }

}
