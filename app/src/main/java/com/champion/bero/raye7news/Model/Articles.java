package com.champion.bero.raye7news.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Champion on 02-Oct-18.
 */
public class Articles implements Parcelable {

    private String title;
    private String urlToImage;
    private String publishedAt;
    private String url;

    public Articles(){}

    protected Articles(Parcel in) {
        title = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
        url = in.readString();
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(urlToImage);
        dest.writeString(publishedAt);
        dest.writeString(url);
    }

    public static final Creator<Articles> CREATOR = new Creator<Articles>() {
        @Override
        public Articles createFromParcel(Parcel in) {
            return new Articles(in);
        }

        @Override
        public Articles[] newArray(int size) {
            return new Articles[size];
        }
    };
}
