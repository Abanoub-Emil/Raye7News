package com.champion.bero.raye7news.RetrofitWrapper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Champion on 23-Sep-18.
 */
public class RetrofitClientInstance  {

    private static Retrofit retrofit;
    private static  String BASE_URL = "https://newsapi.org/v2/";

    public static Retrofit getRetrofitInstance() {
        System.out.println(BASE_URL);
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}