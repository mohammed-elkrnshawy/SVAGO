package com.travel.svago.Remote;


import com.travel.svago.Models.CountriesResponses.CountriesResponse;
import com.travel.svago.Models.ResponseAboutUs.ResponseAboutUs;
import com.travel.svago.Models.ResponseCurrencies.ResponseCurrencies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface UserService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("resources/countries")
    Call<CountriesResponse> getCountry(
            //@Header("Authorization") String Authorization
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("resources/currencies")
    Call<ResponseCurrencies> getCurrencies(
            //@Header("Authorization") String Authorization
    );
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("pages/about")
    Call<ResponseAboutUs> aboutUS(
            //@Header("Authorization") String Authorization
    );
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("pages/terms")
    Call<ResponseAboutUs> terms(
            //@Header("Authorization") String Authorization
    );
}