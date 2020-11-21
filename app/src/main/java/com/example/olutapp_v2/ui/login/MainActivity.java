package com.example.olutapp_v2.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.olutapp_v2.ChatActivity;
import com.example.olutapp_v2.DatabaseHelper;
import com.example.olutapp_v2.OluetActivity;
import com.example.olutapp_v2.R;
import com.example.olutapp_v2.RavintolatActivity;
import com.example.olutapp_v2.StorageHelper;
import com.example.olutapp_v2.SuosikitActivity;
import com.example.olutapp_v2.SuositutActivity;
import com.example.olutapp_v2.data.Beer;
import com.example.olutapp_v2.data.Restaurant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHelper db = new DatabaseHelper();
        final StorageHelper storageHelper = new StorageHelper();
        Beer beerToInsert = new Beer();
        beerToInsert.Name = "Lapin Kulta";
        beerToInsert.Alcohol = 5.6;
        beerToInsert.Bitterness = 45;
        beerToInsert.Flavors = "Spicy, lemon";
        beerToInsert.Brewery = 1;
        beerToInsert.Type = "Lager";
        db.insertBeer(beerToInsert);
        /*db.getBeer(1).observe(this, new Observer<Beer>(){
            @Override
            public void onChanged(Beer beer){
                Log.d("Observer", "Passed " + beer.Name);
                for (String path: beer.Images
                     ) {
                    File imagePath = storageHelper.downloadImage(path);
                    Log.d("Download image", path);
                    storageHelper.uploadImage(imagePath.getPath(), "Lager/test.jpg");
                }
            }
        });
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            return super.onOptionsItemSelected(item);

    }

    public void out(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}