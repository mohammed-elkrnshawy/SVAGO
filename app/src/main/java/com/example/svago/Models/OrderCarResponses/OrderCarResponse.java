
package com.example.svago.Models.OrderCarResponses;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class OrderCarResponse {

    @Expose
    private Date date;
    @Expose
    private Long status;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

}
