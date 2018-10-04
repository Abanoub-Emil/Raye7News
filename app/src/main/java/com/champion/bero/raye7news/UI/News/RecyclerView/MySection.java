package com.champion.bero.raye7news.UI.News.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.champion.bero.raye7news.Model.Articles;
import com.champion.bero.raye7news.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Champion on 02-Oct-18.
 */
public class MySection extends StatelessSection {

    String title;
    ArrayList<Articles> articles;
    Context context;

    public MySection(String title, ArrayList<Articles> articles, Context context) {
        // call constructor with layout resources for this Section header, footer and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_item)
                .headerResourceId(R.layout.section_header)
                .build());

        this.title = title;
        this.articles = articles;
        this.context = context;
    }

    @Override
    public int getContentItemsTotal() {
        return articles.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyItemViewHolder itemHolder = (MyItemViewHolder) holder;

        itemHolder.newsTitle.setText(articles.get(position).getTitle());
        itemHolder.newsDate.setText(articles.get(position).getPublishedAt());
        Glide.with(context)
                .load(articles.get(position).getUrlToImage())
                .into(itemHolder.newsImage);
        itemHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse( articles.get(position).getUrl()));
                context.startActivity(browserIntent);
            }
        });
        itemHolder.newsFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveArticlesList(articles.get(position));
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new MyHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;
        headerHolder.tvItem.setText(title);
    }

    private void saveArticlesList(Articles article) {
        SharedPreferences mPrefs = context.getSharedPreferences("store", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Type type = new TypeToken<ArrayList<Articles>>(){}.getType();
        String getJson = mPrefs.getString("FavoriteList", null);

        if (getJson == null) {

            ArrayList<Articles> favoriteArticles = new ArrayList<>();
            favoriteArticles.add(article);
            String json = gson.toJson(favoriteArticles);
            prefsEditor.putString("FavoriteList", json);
            prefsEditor.apply();
        } else {

            ArrayList<Articles> favoriteArticles = gson.fromJson(getJson, type);
            boolean addedBefore = checkItemAddedBefore(favoriteArticles, article);
            if (addedBefore){
                Toast.makeText(context, "Already exist in Favorite List", Toast.LENGTH_SHORT).show();
                return;
            }
            favoriteArticles.add(article);
            String json = gson.toJson(favoriteArticles);
            prefsEditor.putString("FavoriteList", json);
            prefsEditor.apply();

        }
        Toast.makeText(context, "Article Added to Favorite List", Toast.LENGTH_SHORT).show();
    }

    private boolean checkItemAddedBefore(ArrayList<Articles> favoriteArticles, Articles article){
        for (Articles myArticle : favoriteArticles){
            if (myArticle.getTitle().equals(article.getTitle()))
                return true;
        }

        return false;
    }
}
