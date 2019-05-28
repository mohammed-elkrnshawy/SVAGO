
package com.travel.svago.Models.LoginResponses;

import com.google.gson.annotations.SerializedName;
import com.travel.svago.Models.SharedResponses.userData;
import com.google.gson.annotations.Expose;

import java.util.List;

@SuppressWarnings("unused")
public class AuthResponse {

    @Expose
    private userData data;
    @Expose
    private int status;
    @Expose
    private String error;
    @SerializedName("errors")
    @Expose
    private List<String> errors;

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

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
