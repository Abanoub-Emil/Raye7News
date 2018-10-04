package com.champion.bero.raye7news.UI.News.Adapter;

import com.champion.bero.raye7news.Model.Articles;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Champion on 03-Oct-18.
 */
public class ArticlesAdapter implements ArticlesAdapterInt{

    ArrayList<Articles> articles;
    HashMap<String, ArrayList<Articles>> sortedArticles ;

    public ArticlesAdapter(ArrayList<Articles> articles, HashMap<String, ArrayList<Articles>> sortedArticles) {
        this.articles = articles;
        this.sortedArticles = sortedArticles;
    }

    private void trimDate(){
        for (Articles article : articles){
            String date = article.getPublishedAt();
            date =date.substring(0,10);
            article.setPublishedAt(date);
        }
    }

    private void sortArticles(){
        for (Articles article : articles){
            if (!sortedArticles.containsKey(article.getPublishedAt())){
                sortedArticles.put(article.getPublishedAt(),new ArrayList<Articles>());
            }
            ArrayList<Articles> myArticles =  sortedArticles.get(article.getPublishedAt());
            myArticles.add(article);
            sortedArticles.put(article.getPublishedAt(),myArticles);
        }
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public HashMap<String, ArrayList<Articles>> getSortedArticles() {
        trimDate();
        sortArticles();
        return sortedArticles;
    }

    public void setSortedArticles(HashMap<String, ArrayList<Articles>> sortedArticles) {
        this.sortedArticles = sortedArticles;
    }
}
