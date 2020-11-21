package com.example.olutapp_v2;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.olutapp_v2.data.Beer;
import com.example.olutapp_v2.data.Brewery;
import com.example.olutapp_v2.data.Restaurant;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatabaseHelper {

    private FirebaseDatabase database;
    private MutableLiveData<Beer> beer = new MutableLiveData<>();
    private MutableLiveData<Restaurant> restaurant = new MutableLiveData<>();
    private MutableLiveData<ArrayList<String>> listFavorite = new MutableLiveData<>();
    private MutableLiveData<Brewery> brewery = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Beer>> beersByType = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Restaurant>> restaurantsByLocation = new MutableLiveData<>();
    private MutableLiveData<Boolean> uploadedBeers = new MutableLiveData<>();

    public MutableLiveData<ArrayList<String>> Favorites (Integer userID)
    {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("User").child(userID.toString()).child("Beers");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<String> beers = new ArrayList<String>();
                    for(DataSnapshot d : dataSnapshot.getChildren()) {
                        beers.add(d.getKey());
                    }
                    listFavorite.setValue(beers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Cancelled call", databaseError.getMessage());
            }
        });

        return listFavorite;
    }

    public MutableLiveData<Beer> getBeer(Integer beerID)
    {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("Beers").child(beerID.toString());
        beer = new MutableLiveData<Beer>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                beer.setValue(dataSnapshot.getValue(Beer.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Cancelled call", databaseError.getMessage());
            }
        });
        return beer;
    }

    public MutableLiveData<Restaurant> getRestaurant(Integer restaurantID)
    {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("Restaurants").child(restaurantID.toString());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                restaurant.setValue(dataSnapshot.getValue(Restaurant.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Cancelled call", databaseError.getMessage());
            }
        });
        return restaurant;
    }

    public MutableLiveData<Brewery> getBrewery(Integer breweryID)
    {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("Brewery").child(breweryID.toString());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                brewery.setValue(dataSnapshot.getValue(Brewery.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Cancelled call", databaseError.getMessage());
            }
        });
        return brewery;
    }

    public MutableLiveData<ArrayList<Beer>> getBeersByType(String beerType)
    {
        database = FirebaseDatabase.getInstance();
        Query ref = database.getReference().child("Beers").orderByChild("Type").equalTo(beerType);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Beer> beer = new ArrayList<Beer>();
                for (DataSnapshot d: dataSnapshot.getChildren()
                     ) {
                    beer.add(d.getValue(Beer.class));
                }
                beersByType.setValue(beer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Cancelled call", databaseError.getMessage());
            }
        });

        return beersByType;
    }

    public MutableLiveData<ArrayList<Restaurant>> getRestaurantsByLocation(String location)
    {
        database = FirebaseDatabase.getInstance();
        Query ref = database.getReference().child("Restaurants").orderByChild("City").equalTo(location);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
                for (DataSnapshot d: dataSnapshot.getChildren()
                ) {
                    restaurants.add(d.getValue(Restaurant.class));
                }
                restaurantsByLocation.setValue(restaurants);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Cancelled call", databaseError.getMessage());
            }
        });

        return restaurantsByLocation;
    }

    public MutableLiveData<Boolean> insertBeer(Beer beer)
    {
        uploadedBeers.setValue(false);
        database = FirebaseDatabase.getInstance();
        String key = database.getReference().child("Beers").push().getKey();
        beer._ID = key;
        database.getReference().child("Beers").child(key).setValue(beer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Insert:", "Success");
                        uploadedBeers.setValue(true);
                    }
                });
        return uploadedBeers;
    }
}
