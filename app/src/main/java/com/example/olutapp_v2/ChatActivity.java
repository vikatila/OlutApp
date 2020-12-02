package com.example.olutapp_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olutapp_v2.ui.login.LoginActivity;
import com.example.olutapp_v2.ui.login.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {

    // luodaan muuttujat
    TextView fb_Beers,fb_Alcohol,fb_Bitterness,fb_Flavors,fb_Name,fb_Reviews,fb_Reviews_Comment,fb_Reviews_DateReview,fb_Reviews_UserID,fb_Reviews_UserName,fb_Type,fb_ID;
    Button button_Hae_Olut;
    DatabaseReference ref;


    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


       // ref ekaref = new DatabaseReference();
        //eventsref = ((DatabaseReference) ekaref).child("Beers");

        /*
        fb_Alcohol =(TextView)findViewById(R.id.textView_Alcohol);
        fb_Bitterness =(TextView)findViewById(R.id.textView_Bitterness);



        button_Hae_Olut.setOnClickListener(new View.OnClickListener() {     //button_Hae_Olut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref1 = FirebaseDatabase.getInstance().getReference().child("Beers").child("1");
                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String sAlcohol=dataSnapshot.child("Alcohol").getValue().toString();
                        String sBitterness=dataSnapshot.child("Bitterness").getValue().toString();

                        //fb_Alcohol.setText(sAlcohol);
                        //fb_Bitterness.setText(sBitterness);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                       // Toast.makeText(getApplicationContext(),"Hello Javatpoint", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });*/

    }

    //






    //






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
            Intent intent = new Intent(ChatActivity.this, OluetActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Ravintolat) {
            Intent intent = new Intent(ChatActivity.this, RavintolatActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Suositut) {
            Intent intent = new Intent(ChatActivity.this, SuositutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Suosikit) {
            Intent intent = new Intent(ChatActivity.this, SuosikitActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Chat) {
            Intent intent = new Intent(ChatActivity.this, ChatActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.logOut) {
            Intent intent = new Intent(ChatActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.Etusivu) {
            Intent intent = new Intent(ChatActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}