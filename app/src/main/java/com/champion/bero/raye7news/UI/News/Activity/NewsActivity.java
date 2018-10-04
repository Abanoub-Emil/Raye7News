package com.champion.bero.raye7news.UI.News.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.champion.bero.raye7news.Model.Articles;
import com.champion.bero.raye7news.R;
import com.champion.bero.raye7news.UI.Favorites.Activity.FavoritesActivity;
import com.champion.bero.raye7news.UI.News.Presenter.NewsPresenter;
import com.champion.bero.raye7news.UI.News.Presenter.NewsPresenterInt;
import com.champion.bero.raye7news.UI.News.RecyclerView.MySection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class NewsActivity extends AppCompatActivity implements NewsActivityInt {

    NewsPresenterInt newsPresenter;
    private static int page = 1;
    RecyclerView recyclerView;
    private boolean isFirstRequest = true;
    private boolean isRequesting = false;
    HashMap<String, ArrayList<Articles>> sortedArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsPresenter = new NewsPresenter(this);
        newsPresenter.requestData(page);

        detectReachingBottom();
    }


    @Override
    public void setSortedArticles(HashMap<String, ArrayList<Articles>> sortedArticles) {
        this.sortedArticles = sortedArticles;

        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        for (Map.Entry<String, ArrayList<Articles>> entry : sortedArticles.entrySet()) {

            sectionAdapter.addSection(new MySection(entry.getKey(), entry.getValue(), getApplicationContext()));
        }

        if(isFirstRequest){
            recyclerView.setAdapter(sectionAdapter);
            isFirstRequest = false;
        }else{

            sectionAdapter.notifyDataSetChanged();
            isRequesting = false;
        }

    }

    private void detectReachingBottom(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && !isRequesting) {
                    isRequesting = true;
                    page++;
                    newsPresenter.requestData(page);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent favoIntent = new Intent(NewsActivity.this, FavoritesActivity.class);
            startActivity(favoIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
