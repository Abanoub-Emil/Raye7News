package com.champion.bero.raye7news.RetrofitWrapper;


import com.champion.bero.raye7news.Model.MyResponse;

import retrofit2.Callback;

/**
 * Created by Champion on 24-Sep-18.
 */
public interface APIHelper {

    public void getData(Callback<MyResponse> callback, int page);


}
