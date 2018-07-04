package com.galactic_ninja.nota.nota.ArrayAdapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galactic_ninja.nota.nota.R;
import com.galactic_ninja.nota.nota.UserInterface.User;

public class FirebaseResultListAdapter extends FirebaseRecyclerAdapter<User, FirebaseViewHolder> {
    public FirebaseResultListAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }
    @NonNull
    @Override
    public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.style_card,parent,false);
        return new FirebaseViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull User model) {
        holder.bindNotes(model);
    }
}
