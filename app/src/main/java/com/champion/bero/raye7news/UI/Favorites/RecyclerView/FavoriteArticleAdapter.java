package com.champion.bero.raye7news.UI.Favorites.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.champion.bero.raye7news.Model.Articles;
import com.champion.bero.raye7news.R;
import com.champion.bero.raye7news.UI.News.RecyclerView.MyItemViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Champion on 03-Oct-18.
 */
public class FavoriteArticleAdapter extends RecyclerView.Adapter<MyItemViewHolder> {


    Context context;
    ArrayList<Articles> articles;


    public FavoriteArticleAdapter(Context context, ArrayList<Articles> articles ) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent, false);
        MyItemViewHolder tvh = new MyItemViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyItemViewHolder holder, final int position) {
        holder.newsTitle.setText(articles.get(position).getTitle());
        holder.newsDate.setText(articles.get(position).getPublishedAt());
        Glide.with(context)
                .load(articles.get(position).getUrlToImage())
                .into(holder.newsImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse( articles.get(position).getUrl()));
                context.startActivity(browserIntent);
            }
        });
        holder.newsFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeArticle(articles.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    private void removeArticle(Articles article){
        articles.remove(article);
        notifyDataSetChanged();
        editSharedPreferences();
    }

    private void editSharedPreferences(){
        SharedPreferences mPrefs = context.getSharedPreferences("store", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String json = gson.toJson(articles);
        prefsEditor.putString("FavoriteList", json);
        prefsEditor.apply();
    }
}
