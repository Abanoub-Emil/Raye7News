package com.champion.bero.raye7news.UI.Favorites.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.champion.bero.raye7news.Model.Articles;
import com.champion.bero.raye7news.R;
import com.champion.bero.raye7news.UI.Favorites.RecyclerView.FavoriteArticleAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FavoriteArticleAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Articles> favoriteArticles;
    private TextView noListAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        noListAvailable = findViewById(R.id.favo_sorry);
        mRecyclerView = findViewById(R.id.favo_rv);
        getFavoriteArticles();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (favoriteArticles != null){
            adapter = new FavoriteArticleAdapter(getApplicationContext(), favoriteArticles);
            mRecyclerView.setAdapter(adapter);
        }else{
            noListAvailable.setVisibility(View.VISIBLE);
        }
    }

    private void getFavoriteArticles(){
        SharedPreferences mPrefs = getSharedPreferences("store", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Articles>>(){}.getType();
        String getJson = mPrefs.getString("FavoriteList", null);
        favoriteArticles = gson.fromJson(getJson, type);
    }

}
