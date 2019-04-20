package com.example.svago.Remote;


import com.example.svago.Models.CountriesResponses.CountriesResponse;
import com.example.svago.Models.LoginResponses.AuthResponse;
import com.example.svago.Models.ResponseAboutUs.ResponseAboutUs;
import com.example.svago.Models.ResponseCurrencies.ResponseCurrencies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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