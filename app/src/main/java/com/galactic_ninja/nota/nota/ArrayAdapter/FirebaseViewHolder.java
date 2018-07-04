package com.galactic_ninja.nota.nota.ArrayAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.galactic_ninja.nota.nota.Constants;
import com.galactic_ninja.nota.nota.UserInterface.MainActivity;
import com.galactic_ninja.nota.nota.UserInterface.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;
    public FirebaseViewHolder(View view){
        super(view);
        mView = view;
        mContext = view.getContext();
    }
    public void bindNotes(User user){

    }
    @Override
    public void onClick(View view){
        final ArrayList<User> users = new ArrayList<>();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(Constants.NODE);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    users.add(snapshot.getValue(User.class));

                }
                //Change the class
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
