package com.example.svago.Remote;


import com.example.svago.Models.CarDetailsResponses.CarDetailsResponse;
import com.example.svago.Models.LoginResponses.AuthResponse;
import com.example.svago.Models.SvagoResponses.SvagoResponse;
import com.example.svago.Models.TripDetailsResponses.TripDetailsResponse;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService_POST {

    @POST("auth/register")
    Call<AuthResponse> Register(
            @Query("name") String name,
            @Query("email") String email,
            @Query("password") String password,
            @Query("device_token") String device_token,
            @Query("language") String language
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/profile")
    Call<AuthResponse> EditProfile(
            @Header("Authorization") String Authorization,
            @Query("name") String name,
            @Query("email") String email,
            @Query("phone") String phone
    );

    @POST("auth/login")
    Call<AuthResponse> Login(
            @Query("email") String email,
            @Query("password") String password,
            @Query("language") String language,
            @Query("device_token") String device_token
    );

    @POST("svago/list")
    Call<SvagoResponse> svagoList(
            @Query("currency_id") int currency_id
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("cars/{id}")
    Call<CarDetailsResponse> carDetails(
            @Path("id") int carID,
            @Query("currency_id") int currency_id
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("trips/{id}")
    Call<TripDetailsResponse> TripDetails(
            @Path("id") int carID,
            @Query("currency_id") int currency_id
    );

}

