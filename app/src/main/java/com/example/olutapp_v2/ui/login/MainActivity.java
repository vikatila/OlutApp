package com.example.olutapp_v2.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.olutapp_v2.ChatActivity;
import com.example.olutapp_v2.OluetActivity;
import com.example.olutapp_v2.R;
import com.example.olutapp_v2.RavintolatActivity;
import com.example.olutapp_v2.SuosikitActivity;
import com.example.olutapp_v2.SuositutActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
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