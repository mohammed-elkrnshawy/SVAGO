package com.travel.svago.Models.ResponseSocialLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSocialLogin {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("token")
    @Expose
    private String token;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}