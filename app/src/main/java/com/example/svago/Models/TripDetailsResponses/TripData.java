
package com.example.svago.Models.TripDetailsResponses;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class TripData implements Serializable {

    @Expose
    private String address;
    @Expose
    private String description;
    @Expose
    private Long duration;
    @Expose
    private String from;
    @Expose
    private Long id;
    @Expose
    private String image;
    @Expose
    private List<String> images;
    @Expose
    private String lat;
    @Expose
    private String lng;
    @Expose
    private String price;
    @Expose
    private String title;
    @Expose
    private String to;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
