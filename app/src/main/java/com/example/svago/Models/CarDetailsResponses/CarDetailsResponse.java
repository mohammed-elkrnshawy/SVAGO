
package com.example.svago.Models.CarDetailsResponses;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class CarDetailsResponse {

    @Expose
    private Data data;
    @Expose
    private Long status;

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
