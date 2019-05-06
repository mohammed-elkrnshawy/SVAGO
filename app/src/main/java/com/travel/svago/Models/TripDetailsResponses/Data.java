
package com.travel.svago.Models.TripDetailsResponses;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Data {

    @Expose
    private TripData trip;

    public TripData getTrip() {
        return trip;
    }

    public void setTrip(TripData trip) {
        this.trip = trip;
    }

}
