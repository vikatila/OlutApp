package com.example.olutapp_v2.ui.login;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Observable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.olutapp_v2.ChatActivity;
import com.example.olutapp_v2.R;
import com.example.olutapp_v2.RavintolatActivity;
import com.example.olutapp_v2.SuosikitActivity;
import com.example.olutapp_v2.SuositutActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class OluetActivity extends AppCompatActivity {

    private FirebaseDatabase tietokanta;
    TextView etUrl;
    ImageView JanoImage;
    Button btJano;

    private DatabaseReference mDatabase;
    public static final Random RANDOM = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oluet);
        JanoImage = findViewById(R.id.imageView5);
        btJano = findViewById(R.id.button2);
        etUrl = findViewById(R.id.textView4);


        btJano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = RandomBeerValue();
                if (value == 1) {
                    etUrl.setText("https://firebasestorage.googleapis.com/v0/b/oulut-opas.appspot.com/o/Arvonta%2F1.jpg?alt=media&token=bf6a2a93-495d-4fa6-af7b-dcf57b517f6c");
                    JanoImage.setImageBitmap(null);
                    String urlLink = etUrl.getText().toString();
                    LoadImage loadImage = new LoadImage(JanoImage);
                    loadImage.execute(urlLink);
                }
                if (value == 2) {
                    etUrl.setText("https://firebasestorage.googleapis.com/v0/b/oulut-opas.appspot.com/o/Arvonta%2F2.jpg?alt=media&token=ad11cb63-ea69-4c48-95df-078bc38900e5");
                    JanoImage.setImageBitmap(null);
                    String urlLink = etUrl.getText().toString();
                    LoadImage loadImage = new LoadImage(JanoImage);
                    loadImage.execute(urlLink);
                }
                if (value == 3) {
                    etUrl.setText("https://firebasestorage.googleapis.com/v0/b/oulut-opas.appspot.com/o/Arvonta%2F3.jpg?alt=media&token=389cbaf7-019f-44bf-9bac-0c3cd08175b4");
                    JanoImage.setImageBitmap(null);
                    String urlLink = etUrl.getText().toString();
                    LoadImage loadImage = new LoadImage(JanoImage);
                    loadImage.execute(urlLink);
                }
                if (value == 4) {
                    etUrl.setText("https://firebasestorage.googleapis.com/v0/b/oulut-opas.appspot.com/o/Arvonta%2F4.jpg?alt=media&token=dbe0910e-5d7a-4803-972b-0f9022b2fa0");
                    JanoImage.setImageBitmap(null);
                    String urlLink = etUrl.getText().toString();
                    LoadImage loadImage = new LoadImage(JanoImage);
                    loadImage.execute(urlLink);
                }
                if (value == 5) {
                    etUrl.setText("https://firebasestorage.googleapis.com/v0/b/oulut-opas.appspot.com/o/Arvonta%2F5.jpg?alt=media&token=79ecd54e-2117-44bc-b0a2-8072b48e8a34");
                    JanoImage.setImageBitmap(null);
                    String urlLink = etUrl.getText().toString();
                    LoadImage loadImage = new LoadImage(JanoImage);
                    loadImage.execute(urlLink);
                }
                if (value == 6) {
                    etUrl.setText("https://firebasestorage.googleapis.com/v0/b/oulut-opas.appspot.com/o/Arvonta%2F6.jpg?alt=media&token=9bdd4df1-e750-40e1-b9cc-0ef42119571c");
                    JanoImage.setImageBitmap(null);
                    String urlLink = etUrl.getText().toString();
                    LoadImage loadImage = new LoadImage(JanoImage);
                    loadImage.execute(urlLink);
                }
                if (value == 7) {
                    etUrl.setText("https://firebasestorage.googleapis.com/v0/b/oulut-opas.appspot.com/o/Arvonta%2F7.jpg?alt=media&token=4b5e0422-7e91-48e5-89b5-71ce4b751b03");
                    JanoImage.setImageBitmap(null);
                    String urlLink = etUrl.getText().toString();
                    LoadImage loadImage = new LoadImage(JanoImage);
                    loadImage.execute(urlLink);
                }

            }
        });


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
            Intent intent = new Intent(OluetActivity.this, OluetActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Ravintolat) {
            Intent intent = new Intent(OluetActivity.this, RavintolatActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Suositut) {
            Intent intent = new Intent(OluetActivity.this, SuositutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Suosikit) {
            Intent intent = new Intent(OluetActivity.this, SuosikitActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Chat) {
            Intent intent = new Intent(OluetActivity.this, ChatActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.logOut) {
            Intent intent = new Intent(OluetActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Etusivu) {
            Intent intent = new Intent(OluetActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public LoadImage(ImageView imageView) {
            this.imageView = JanoImage;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            JanoImage.setImageBitmap(bitmap);
        }
    }



    public static int RandomBeerValue(){
        return RANDOM.nextInt(7);

    }


}
