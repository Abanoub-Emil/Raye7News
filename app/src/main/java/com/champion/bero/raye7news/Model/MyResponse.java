package com.champion.bero.raye7news.Model;

import java.util.ArrayList;

/**
 * Created by Champion on 02-Oct-18.
 */
public class MyResponse {

    private String status;
    private ArrayList<Articles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
