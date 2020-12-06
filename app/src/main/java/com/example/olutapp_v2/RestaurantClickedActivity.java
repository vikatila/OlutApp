package com.example.olutapp_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olutapp_v2.ui.login.LoginActivity;
import com.example.olutapp_v2.ui.login.MainActivity;
import com.example.olutapp_v2.ui.login.OluetActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RestaurantClickedActivity extends AppCompatActivity {

    // Määritellään vastaanotettavalle positiontiedolle muuttuja
    private int receiverRestaurantID;

    private ImageView clickedRestaurantImage, clickedRestaurantImage2;
    private TextView clickedRestaurantName, clickedRestaurantAddress, clickedRestaurantOpen;



    private DatabaseReference RestaurantRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_clicked);

        // Referenssi tietokantaan
        RestaurantRef = FirebaseDatabase.getInstance().getReference().child("RecRestaurants");
        // Hakee tiedot adapterilta ja vastaanottaa tuon positiontiedon
        receiverRestaurantID = (int) getIntent().getExtras().get("currentPosition2");


        clickedRestaurantImage = (ImageView) findViewById(R.id.ClickedRavintolaImage);
        clickedRestaurantImage2 = (ImageView) findViewById(R.id.ClickedRavintolaImage2);
        clickedRestaurantName = (TextView) findViewById(R.id.ClickedRavintola);
        clickedRestaurantAddress = (TextView) findViewById(R.id.ClickedRavintolaAddress);
        clickedRestaurantOpen = (TextView) findViewById(R.id.ClickedRavintolaOpen);

        RetrieveBeerInfo();


    }

    private void RetrieveBeerInfo() {

        RestaurantRef.child(String.valueOf(receiverRestaurantID)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                // Tähän lisätään, mitä tietoja vastaanotetaan tietokannasta.
                String restaurantImage = snapshot.child("Images").getValue(String.class);
                String restaurantImage2 = snapshot.child("Images2").getValue(String.class);
                String restaurantName = snapshot.child("Name").getValue(String.class);
                String restaurantAddress = snapshot.child("Address").getValue(String.class);
                String restaurantOpen = snapshot.child("Open").getValue(String.class);

                Picasso.get().load(restaurantImage).into(clickedRestaurantImage);
                Picasso.get().load(restaurantImage2).into(clickedRestaurantImage2);
                clickedRestaurantName.setText(restaurantName);
                clickedRestaurantAddress.setText(restaurantAddress);
                clickedRestaurantOpen.setText(restaurantOpen);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void out(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Oluet) {
            Intent intent = new Intent(RestaurantClickedActivity.this, OluetActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Ravintolat) {
            Intent intent = new Intent(RestaurantClickedActivity.this, RavintolatActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Suositut) {
            Intent intent = new Intent(RestaurantClickedActivity.this, SuositutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Suosikit) {
            Intent intent = new Intent(RestaurantClickedActivity.this, SuosikitActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Chat) {
            Intent intent = new Intent(RestaurantClickedActivity.this, ChatActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.logOut) {
            Intent intent = new Intent(RestaurantClickedActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.Etusivu) {
            Intent intent = new Intent(RestaurantClickedActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


}



