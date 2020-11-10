package com.example.olutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseHelper dbHelper;
    private StorageHelper storageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper();
        storageHelper = new StorageHelper();
        dbHelper.Favorites(1);
        //String key = ref.getKey();
        //System.console().writer().println(ref.toString());
    }

    public void onClickBtn(View view) {
        List<String> list = dbHelper.getFavorites();
        Log.d("1", "List");
    }

}