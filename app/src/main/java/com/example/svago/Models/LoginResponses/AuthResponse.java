
package com.example.svago.Models.LoginResponses;

import com.example.svago.Models.SharedResponses.userData;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class AuthResponse {

    @Expose
    private userData data;
    @Expose
    private int status;
    @Expose
    private String error;

    public userData getData() {
        return data;
    }

    public void setData(userData data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
