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
import com.example.olutapp_v2.R;
import com.example.olutapp_v2.RestaurantClickedActivity;
import com.example.olutapp_v2.data.model.ModelR;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class MyAdapter2 extends FirebaseRecyclerAdapter<ModelR,MyAdapter2.myviewholder2>

{

    public MyAdapter2(@NonNull FirebaseRecyclerOptions<ModelR> options1) {
        super(options1);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder2 myviewholder2, int i, @NonNull ModelR modelR)
    {
        myviewholder2.star1.setVisibility(View.INVISIBLE);
        myviewholder2.star2.setVisibility(View.INVISIBLE);
        myviewholder2.star3.setVisibility(View.INVISIBLE); //nämä täytyi asettaa näkymöttömiin alussa, jotta tähtien määrä ei lähtenyt muuttumaan randomilla
        myviewholder2.star4.setVisibility(View.INVISIBLE);
        myviewholder2.star5.setVisibility(View.INVISIBLE);

        myviewholder2.name.setText(modelR.getName());
        Glide.with(myviewholder2.img.getContext())
                .load(modelR.getImages())
                .into(myviewholder2.img);

        String sReview = modelR.getReview();              //haetaan arvostelurivi tietokannasta "Review"
        float fReview = Float.parseFloat(sReview);       //muutetaan tietokannan string arvo integeriksi

        if (fReview < 1.5) {
            myviewholder2.star1.setVisibility(View.VISIBLE);
        }
        if ((fReview >= 1.5) && (fReview < 2.5)){
            myviewholder2.star1.setVisibility(View.VISIBLE);
            myviewholder2.star2.setVisibility(View.VISIBLE);
        }
        if ((fReview >= 2.5) && (fReview < 3.5)){
            myviewholder2.star1.setVisibility(View.VISIBLE);
            myviewholder2.star2.setVisibility(View.VISIBLE);
            myviewholder2.star3.setVisibility(View.VISIBLE);
        }
        if ((fReview >= 3.5) && (fReview < 4.5)){
            myviewholder2.star1.setVisibility(View.VISIBLE);
            myviewholder2.star2.setVisibility(View.VISIBLE);
            myviewholder2.star3.setVisibility(View.VISIBLE);
            myviewholder2.star4.setVisibility(View.VISIBLE);
        }
        if (fReview > 4.5){
            myviewholder2.star1.setVisibility(View.VISIBLE);
            myviewholder2.star2.setVisibility(View.VISIBLE);
            myviewholder2.star3.setVisibility(View.VISIBLE);
            myviewholder2.star4.setVisibility(View.VISIBLE);
            myviewholder2.star5.setVisibility(View.VISIBLE);
        }

    }

    @NonNull
    @Override
    public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,parent,false);

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int currentPosition2 = getClickedPosition2(view);
                Log.d("DEBUG", "Klikkasit Ravintolaa: " + currentPosition2);

                // Avaa BeerClickedActivityn klikattaessa
                Intent RestaurantClickedIntent = new Intent(view.getContext(), RestaurantClickedActivity.class);
                // Lähettää currentPositionin tuolle toiselle activitylle
                RestaurantClickedIntent.putExtra("currentPosition2", currentPosition2);

                view.getContext().startActivity(RestaurantClickedIntent);


            }
        });

        return new myviewholder2(view);
    }

    class myviewholder2 extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView name;

        ImageView star1,star2,star3,star4,star5;    //imageviewit arvostelun kuville

        public myviewholder2(@NonNull View itemView) {
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

    private int getClickedPosition2(View clickedView)
    {
        RecyclerView recyclerView2 = (RecyclerView) clickedView.getParent();
        MyAdapter2.myviewholder2 currentViewHolder = (MyAdapter2.myviewholder2) recyclerView2.getChildViewHolder(clickedView);
        return currentViewHolder.getAdapterPosition();

    }


}