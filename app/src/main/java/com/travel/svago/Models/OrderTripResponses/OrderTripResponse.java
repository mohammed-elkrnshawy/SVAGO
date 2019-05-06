
package com.travel.svago.Models.OrderTripResponses;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class OrderTripResponse {

    @Expose
    private Data data;
    @Expose
    private Long status;
    @Expose
    private String error;

    public String getError() {
        return error;
    }

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

}
