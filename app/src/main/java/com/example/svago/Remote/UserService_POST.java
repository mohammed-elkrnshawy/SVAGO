package com.example.svago.Remote;


import com.example.svago.Models.LoginResponses.AuthResponse;

import retrofit2.Call;
import retrofit2.http.POST;
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

    @POST("auth/login")
    Call<AuthResponse> Login(
            @Query("email") String email,
            @Query("password") String password,
            @Query("language") String language,
            @Query("device_token") String device_token
    );
}

