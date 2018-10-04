package com.champion.bero.raye7news.RetrofitWrapper;

import com.champion.bero.raye7news.Model.MyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Champion on 23-Sep-18.
 */
public interface API {

    @Headers("Content-Type: application/json")
    @GET("everything?q=google&language=en&sources=usa-toda&apiKey=f23fe19528c748cb8d7d00b3a15b1312")
    Call<MyResponse> getData(@Query("page") int page);
}
