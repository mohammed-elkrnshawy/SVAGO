
package com.example.svago.Models.CarDetailsResponses;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Data {

    @Expose
    private CarData car;

    public CarData getCar() {
        return car;
    }

    public void setCar(CarData car) {
        this.car = car;
    }

}
