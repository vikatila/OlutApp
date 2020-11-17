package com.example.olutapp_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.olutapp_v2.ui.login.LoginActivity;

public class SuosikitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suosikit);
    }

    public void out(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

}