
package com.example.svago.Models.CountriesResponses;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class CountriesResponse {

    @Expose
    private Data data;
    @Expose
    private Long status;
    @Expose
    String msg;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }
}
