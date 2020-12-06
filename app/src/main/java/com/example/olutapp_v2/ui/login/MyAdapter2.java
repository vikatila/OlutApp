package com.example.olutapp_v2.ui.login;

import android.content.Context;
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
import com.example.olutapp_v2.RestaurantClickedActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;



public class MyAdapter2 extends FirebaseRecyclerAdapter<ModelR,MyAdapter2.myviewholder2>

{

    public MyAdapter2(@NonNull FirebaseRecyclerOptions<ModelR> options1) {
        super(options1);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder2 myviewholder2, int i, @NonNull ModelR modelR)
    {

        myviewholder2.name.setText(modelR.getName());
        Glide.with(myviewholder2.img.getContext())
                .load(modelR.getImages())
                .into(myviewholder2.img);



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

        public myviewholder2(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.olut1_Image);
            name=(TextView)itemView.findViewById(R.id.olut1_Text);
        }
    }

    private int getClickedPosition2(View clickedView)
    {
        RecyclerView recyclerView2 = (RecyclerView) clickedView.getParent();
        MyAdapter2.myviewholder2 currentViewHolder = (MyAdapter2.myviewholder2) recyclerView2.getChildViewHolder(clickedView);
        return currentViewHolder.getAdapterPosition();

    }


}