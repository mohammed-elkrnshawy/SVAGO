
package com.example.svago.Models.CountriesResponses;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class CountyData {

    @Expose
    private int id;
    @Expose
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
