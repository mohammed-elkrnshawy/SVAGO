
package com.example.svago.Models.OrderCarResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class OrderCarResponse {

    @SerializedName("date")
    @Expose
    private Data data;
    @SerializedName("status")
    @Expose
    private Long status;
    @Expose
    private String error;

    public Data getData() {
        return data;
    }

    public void setData(Data date) {
        this.data = date;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }
}
