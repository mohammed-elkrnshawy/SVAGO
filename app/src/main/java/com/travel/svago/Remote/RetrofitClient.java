package com.travel.svago.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Omnia on 11/11/2018.
 */

public class RetrofitClient {

    private static Retrofit retrofit=null;


    public static Retrofit getClient(String Url) {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        RetrofitClient.retrofit = retrofit;
    }
}
