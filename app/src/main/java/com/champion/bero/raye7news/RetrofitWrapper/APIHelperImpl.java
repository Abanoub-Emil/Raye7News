package com.champion.bero.raye7news.RetrofitWrapper;

import com.champion.bero.raye7news.Model.MyResponse;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Champion on 24-Sep-18.
 */
public class APIHelperImpl implements APIHelper {


    @Override
    public void getData(Callback <MyResponse>callback, int page) {
        Call<MyResponse> call = RetrofitClientInstance.getRetrofitInstance()
                .create(API.class).getData(page);
        call.enqueue(callback);
    }

}
