package com.example.olutapp_v2.ui.login;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.olutapp_v2.BeerClickedActivity;
import com.example.olutapp_v2.R;
import com.example.olutapp_v2.data.model.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

//Recyclerviewin firebase adapteri joka sisältää myös viewholderin
public class MyAdapter extends FirebaseRecyclerAdapter<Model,MyAdapter.myviewholder>


{

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull final myviewholder myviewholder, final int i, @NonNull final Model model)
    {

        myviewholder.star1.setVisibility(View.INVISIBLE);
        myviewholder.star2.setVisibility(View.INVISIBLE);
        myviewholder.star3.setVisibility(View.INVISIBLE); //nämä täytyi asettaa näkymättömiin alussa, jotta tähtien määrä ei lähtenyt muuttumaan randomilla
        myviewholder.star4.setVisibility(View.INVISIBLE);
        myviewholder.star5.setVisibility(View.INVISIBLE);
        // Model luokasta saadaan Oluen nimi getName:lla
       myviewholder.name.setText(model.getName());
       // Käytetään Glide widgettiä, jolla saa näytettyä kuvan yksinkertaisesti
       Glide.with(myviewholder.img.getContext())
               .load(model.getImages())
               .into(myviewholder.img);

       String sReview = model.getReview();              //haetaan arvostelurivi tietokannasta "Review"
       float fReview = Float.parseFloat(sReview);       //muutetaan tietokannan string arvo integeriksi
        // Näillä ehdoilla haetaan tähtien määrä näkyville
       if (fReview < 1.5) {
           myviewholder.star1.setVisibility(View.VISIBLE);
       }
       if ((fReview >= 1.5) && (fReview < 2.5)){
           myviewholder.star1.setVisibility(View.VISIBLE);
           myviewholder.star2.setVisibility(View.VISIBLE);
       }
        if ((fReview >= 2.5) && (fReview < 3.5)){
            myviewholder.star1.setVisibility(View.VISIBLE);
            myviewholder.star2.setVisibility(View.VISIBLE);
            myviewholder.star3.setVisibility(View.VISIBLE);
        }
        if ((fReview >= 3.5) && (fReview < 4.5)){
            myviewholder.star1.setVisibility(View.VISIBLE);
            myviewholder.star2.setVisibility(View.VISIBLE);
            myviewholder.star3.setVisibility(View.VISIBLE);
            myviewholder.star4.setVisibility(View.VISIBLE);
        }
        if (fReview >= 4.5){
            myviewholder.star1.setVisibility(View.VISIBLE);
            myviewholder.star2.setVisibility(View.VISIBLE);
            myviewholder.star3.setVisibility(View.VISIBLE);
            myviewholder.star4.setVisibility(View.VISIBLE);
            myviewholder.star5.setVisibility(View.VISIBLE);
        }

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        // Otetaan my_row layout käyttöön viewholderin käynnistyessä
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,parent,false);

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Näyttää klikatun positionin recyclerviewistä
                int currentPosition = getClickedPosition(view);
                Log.d("DEBUG", "Klikkasit olutta: " + currentPosition);

                // Avaa BeerClickedActivityn klikattaessa
                Intent BeerClickedIntent = new Intent(view.getContext(), BeerClickedActivity.class);
                // Lähettää currentPositionin tuolle toiselle activitylle
                BeerClickedIntent.putExtra("currentPosition", currentPosition);

                view.getContext().startActivity(BeerClickedIntent);


            }
        });

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView name;
        ImageView star1,star2,star3,star4,star5;    //imageviewit arvostelun kuville

        // Viewholderiin määritellään, mille viewille halutaan asettaa tietoja
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.olut1_Image);
            name=(TextView)itemView.findViewById(R.id.olut1_Text);

           star1=(ImageView)itemView.findViewById(R.id.imageView_star1);
           star2=(ImageView)itemView.findViewById(R.id.imageView_star2);
           star3=(ImageView)itemView.findViewById(R.id.imageView_star3);        //luodaan paikat tiedoille. Näihin paikkoihin haetaan tiedot "onBindViewHolderissa"
           star4=(ImageView)itemView.findViewById(R.id.imageView_star4);
           star5=(ImageView)itemView.findViewById(R.id.imageView_star5);

        }
    }
    // Metodi, jolla saadaan haettua klikattu position
    private int getClickedPosition(View clickedView)
    {
        RecyclerView recyclerView = (RecyclerView) clickedView.getParent();
        myviewholder currentViewHolder = (myviewholder) recyclerView.getChildViewHolder(clickedView);
        // getAdapterPosition komento näyttää, mitä itemiä recyclerviewissä on klikattu
        return currentViewHolder.getAdapterPosition();

    }

}