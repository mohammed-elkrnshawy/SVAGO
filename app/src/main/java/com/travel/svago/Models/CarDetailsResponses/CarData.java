
package com.travel.svago.Models.CarDetailsResponses;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class CarData implements Serializable {

    @Expose
    private String ac;
    @Expose
    private String address;
    @Expose
    private String doors;
    @Expose
    private String gears;
    @SerializedName("has_driver")
    private Boolean hasDriver;
    @Expose
    private int id;
    @Expose
    private String image;
    @Expose
    private List<String> images;
    @Expose
    private String lat;
    @Expose
    private String lng;
    @Expose
    private String model;
    @Expose
    private String name;
    @Expose
    private String passengers;
    @Expose
    private String price;

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getGears() {
        return gears;
    }

    public void setGears(String gears) {
        this.gears = gears;
    }

    public Boolean getHasDriver() {
        return hasDriver;
    }

    public void setHasDriver(Boolean hasDriver) {
        this.hasDriver = hasDriver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
