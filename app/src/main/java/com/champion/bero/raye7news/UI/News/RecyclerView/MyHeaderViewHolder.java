package com.champion.bero.raye7news.UI.News.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.champion.bero.raye7news.R;

/**
 * Created by Champion on 03-Oct-18.
 */
public class MyHeaderViewHolder extends RecyclerView.ViewHolder {


    TextView tvItem;

    public MyHeaderViewHolder(View itemView) {
        super(itemView);
        tvItem = itemView.findViewById(R.id.news_header);
    }

}