package com.example.svago.Models.ResponseOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tickets")
    @Expose
    private String tickets;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("cancellable")
    @Expose
    private Boolean cancellable;
    @SerializedName("trip")
    @Expose
    private Trip_ trip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCancellable() {
        return cancellable;
    }

    public void setCancellable(Boolean cancellable) {
        this.cancellable = cancellable;
    }

    public Trip_ getTrip() {
        return trip;
    }

    public void setTrip(Trip_ trip) {
        this.trip = trip;
    }

}