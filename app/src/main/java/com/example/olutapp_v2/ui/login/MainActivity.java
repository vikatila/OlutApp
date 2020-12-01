package com.example.olutapp_v2.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.olutapp_v2.ChatActivity;
import com.example.olutapp_v2.DatabaseHelper;
import com.example.olutapp_v2.OluetActivity;
import com.example.olutapp_v2.R;
import com.example.olutapp_v2.RavintolatActivity;
import com.example.olutapp_v2.StorageHelper;
import com.example.olutapp_v2.RegisterActivity;
import com.example.olutapp_v2.SuosikitActivity;
import com.example.olutapp_v2.SuositutActivity;
import com.example.olutapp_v2.data.Beer;
import com.example.olutapp_v2.data.Restaurant;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    RecyclerView firstrecyclerView, secondrecyclerView;
    MyAdapter adapter1;
    MyAdapter2 adapter2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstrecyclerView = findViewById(R.id.recyclerView);
        secondrecyclerView = findViewById(R.id.recyclerview2);




        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model> ()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Recommended"), Model.class)
                        .build();

        firstrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new MyAdapter(options);
        firstrecyclerView.setAdapter(adapter1);


        FirebaseRecyclerOptions<ModelR> options1 = new FirebaseRecyclerOptions.Builder<ModelR>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("RecRestaurants"), ModelR.class)
                .build();



        secondrecyclerView.setLayoutManager((new LinearLayoutManager(this)));
        adapter2 = new MyAdapter2(options1);
        secondrecyclerView.setAdapter(adapter2);



     /*   DatabaseHelper db = new DatabaseHelper();
        final StorageHelper storageHelper = new StorageHelper();
        Beer beerToInsert = new Beer();
        beerToInsert.Name = "Lapin Kulta";
        beerToInsert.Alcohol = 5.6;
        beerToInsert.Bitterness = 45;
        beerToInsert.Flavors = "Spicy, lemon";
        beerToInsert.Brewery = 1;
        beerToInsert.Type = "Lager";
       /* db.insertBeer(beerToInsert); */
     /*   db.getBeer(1).observe(this, new Observer<Beer>(){
            @Override
            public void onChanged(Beer beer){
                Log.d("Observer", "Passed " + beer.Name);
                ((TextView)findViewById(R.id.olut1_Text)).setText(beer.Name);

                for (String path: beer.Images
                     ) {
                    File imagePath = storageHelper.downloadImage(path);
                    Log.d("Download image", path);
                    ImageView img =  findViewById(R.id.olut1_Image);

                    Uri imgUri = Uri.fromFile(imagePath);
                    img.setImageURI(imgUri);

                   storageHelper.uploadImage(imagePath.getPath(), "Lager/test.jpg");
                }
            }
        }); */

        /*
        db.getRestaurant(1).observe(this, new Observer<Restaurant>() {
            @Override
            public void onChanged(Restaurant restaurant) {
                Log.d("Observer", "Passed" + restaurant.Name);
            }
        });
        db.getBeersByType("Pale Ale").observe(this, new Observer<ArrayList<Beer>>() {
            @Override
            public void onChanged(ArrayList<Beer> beers) {
                Log.d("Observer", "Passed beers");
            }
        });
        db.Favorites(1).observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                Log.d("Observer", "Passed");
            }
        });*/

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Oluet) {
            Intent intent = new Intent(MainActivity.this,OluetActivity.class);
            startActivity(intent);
            return true;

        }
        else
        if (id == R.id.Ravintolat) {
            Intent intent = new Intent(MainActivity.this,RavintolatActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.Suositut) {
            Intent intent = new Intent(MainActivity.this, SuositutActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.Suosikit) {
            Intent intent = new Intent(MainActivity.this, SuosikitActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.Chat) {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.logOut) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
            return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
        adapter2.stopListening();
    }


}