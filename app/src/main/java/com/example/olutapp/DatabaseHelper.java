package com.example.olutapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private FirebaseDatabase database;
    private List<String> listBeers;

    public void Favorites (Integer userID)
    {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("User").child(userID.toString()).child("Beers");
        listBeers = new ArrayList<String>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot d : dataSnapshot.getChildren()) {
                        listBeers.add(d.getValue().toString());
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Cancelled call", databaseError.getMessage());
            }
        });

    }

    public List<String> getFavorites()
    {
        return listBeers;
    }

    public void Beers()
    {
        
    }

}
