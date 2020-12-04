package com.example.olutapp_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.olutapp_v2.ui.login.LoginActivity;
import com.example.olutapp_v2.ui.login.MainActivity;
import com.example.olutapp_v2.ui.login.Model;
import com.example.olutapp_v2.ui.login.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BeerClickedActivity extends AppCompatActivity  {

    // Määritellään vastaanotettavalle positiontiedolle muuttuja
    private int receiverBeerID;

    private ImageView clickedBeerImage;
    private TextView clickedBeerName;
    private EditText clickedBeerInfo;

    private DatabaseReference BeerRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_clicked);
        // Referenssi tietokantaan
        BeerRef = FirebaseDatabase.getInstance().getReference().child("Recommended");
        // Hakee tiedot adapterilta ja vastaanottaa tuon positiontiedon
        receiverBeerID = (int) getIntent().getExtras().get("currentPosition");


        clickedBeerImage = (ImageView) findViewById(R.id.ClickedOlutImage);
        clickedBeerName = (TextView) findViewById(R.id.ClickedOlut);
        clickedBeerInfo= (EditText) findViewById(R.id.ClickedBeerInfo);

        RetrieveBeerInfo();


    }

    private void RetrieveBeerInfo() {

        BeerRef.child(String.valueOf(receiverBeerID)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    // Tähän lisätään, mitä tietoja vastaanotetaan tietokannasta. Lisäsin tuon Info kohdan kahteen ensimmäiseen olueen.
                    String beerImage = snapshot.child("Images").getValue(String.class);
                    String beerName = snapshot.child("Name").getValue(String.class);
                    String beerInfo = snapshot.child("Info").getValue(String.class);

                    Picasso.get().load(beerImage).into(clickedBeerImage);
                    clickedBeerName.setText(beerName);
                    clickedBeerInfo.setText(beerInfo);

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
            Intent intent = new Intent(BeerClickedActivity.this, OluetActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Ravintolat) {
            Intent intent = new Intent(BeerClickedActivity.this, RavintolatActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Suositut) {
            Intent intent = new Intent(BeerClickedActivity.this, SuositutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Suosikit) {
            Intent intent = new Intent(BeerClickedActivity.this, SuosikitActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Chat) {
            Intent intent = new Intent(BeerClickedActivity.this, ChatActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.logOut) {
            Intent intent = new Intent(BeerClickedActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.Etusivu) {
            Intent intent = new Intent(BeerClickedActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


}