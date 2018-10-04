package com.champion.bero.raye7news.UI.News.RecyclerView;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.champion.bero.raye7news.R;

/**
 * Created by Champion on 02-Oct-18.
 */
public class MyItemViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView newsTitle;
    public TextView newsDate;
    public ImageButton newsFavo;
    public ImageView newsImage;

    public MyItemViewHolder(View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.news_card);
        newsTitle = itemView.findViewById(R.id.news_title);
        newsDate = itemView.findViewById(R.id.news_date);
        newsFavo = itemView.findViewById(R.id.news_favo);
        newsImage = itemView.findViewById(R.id.news_image);
    }
}
