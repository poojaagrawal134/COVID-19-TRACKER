package com.example.covidtracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.parameter.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Articles> articles;
    public Adapter(Context context,List<Articles> articles)
    {
        this.context=context;
        this.articles=articles;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Articles art=articles.get(position);
        String url=art.getUrl();
        holder.newstitle.setText(art.getTitle());
        holder.newsdate.setText(art.getPublishedAt());
        String imageurl=art.getUrlToImage();
        Picasso.get().load(imageurl).into(holder.image);
        holder.cardVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,NewsDetails.class);
                intent.putExtra("url",art.getUrl());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView newstitle,newsdate;
        ImageView image;
        CardView cardVi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsdate=itemView.findViewById(R.id.newsdate);
            newstitle=itemView.findViewById(R.id.newstitle);
            image=itemView.findViewById(R.id.im);
            cardVi=itemView.findViewById(R.id.cardview);
        }
    }
    public String getCountry()
    {
        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
    }
}
