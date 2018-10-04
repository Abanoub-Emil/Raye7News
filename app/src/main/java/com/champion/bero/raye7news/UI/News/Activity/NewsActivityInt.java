package com.champion.bero.raye7news.UI.News.Activity;

import com.champion.bero.raye7news.Model.Articles;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Champion on 02-Oct-18.
 */
public interface NewsActivityInt {

    void setSortedArticles(HashMap<String, ArrayList<Articles>> sortedArticles);
}
