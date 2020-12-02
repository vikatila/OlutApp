package com.example.olutapp_v2.ui.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull Model model)
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

}