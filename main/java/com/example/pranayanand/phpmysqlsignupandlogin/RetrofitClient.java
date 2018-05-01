package com.example.pranayanand.phpmysqlsignupandlogin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pranay Anand on 23-12-2017.
 */

public class RetrofitClient {

    private static Retrofit retrofit;
    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit  = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
