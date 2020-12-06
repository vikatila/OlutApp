package com.example.olutapp_v2.ui.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.olutapp_v2.BeerClickedActivity;
import com.example.olutapp_v2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;



public class MyAdapter extends FirebaseRecyclerAdapter<Model,MyAdapter.myviewholder>


{

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull final myviewholder myviewholder, final int i, @NonNull final Model model)
    {

       myviewholder.name.setText(model.getName());
       Glide.with(myviewholder.img.getContext())
               .load(model.getImages())
               .into(myviewholder.img);


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,parent,false);

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
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
       

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.olut1_Image);
            name=(TextView)itemView.findViewById(R.id.olut1_Text);

        }
    }

    private int getClickedPosition(View clickedView)
    {
        RecyclerView recyclerView = (RecyclerView) clickedView.getParent();
        myviewholder currentViewHolder = (myviewholder) recyclerView.getChildViewHolder(clickedView);
        return currentViewHolder.getAdapterPosition();

    }

}