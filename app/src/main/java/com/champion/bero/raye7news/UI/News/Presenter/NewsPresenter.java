package com.champion.bero.raye7news.UI.News.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.champion.bero.raye7news.Model.Articles;
import com.champion.bero.raye7news.Model.MyResponse;
import com.champion.bero.raye7news.RetrofitWrapper.APIHelper;
import com.champion.bero.raye7news.RetrofitWrapper.APIHelperImpl;
import com.champion.bero.raye7news.UI.News.Activity.NewsActivityInt;
import com.champion.bero.raye7news.UI.News.Adapter.ArticlesAdapter;
import com.champion.bero.raye7news.UI.News.Adapter.ArticlesAdapterInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Champion on 02-Oct-18.
 */
public class NewsPresenter implements NewsPresenterInt{

    NewsActivityInt newsView;
    ArrayList<Articles> articles;
    APIHelper apiHelper = new APIHelperImpl();
    HashMap<String, ArrayList<Articles>> sortedArticles = new HashMap<>();
    ArticlesAdapterInt articlesAdapter ;
    public NewsPresenter (NewsActivityInt newsView) {
        this.newsView = newsView;

    }

    public void requestData(int page) {
        try {
            apiHelper.getData(new Callback<MyResponse>() {
                @Override
                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                    if(response.body().getStatus().equals("ok")){
                    System.out.println(response.body().getStatus());
                        articles = response.body().getArticles();
                        articlesAdapter = new ArticlesAdapter(articles, sortedArticles);
                        sortedArticles = articlesAdapter.getSortedArticles();
                        newsView.setSortedArticles(sortedArticles);
                    }
                }

                @Override
                public void onFailure(Call<MyResponse> call, Throwable t) {

                }
            },page);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
