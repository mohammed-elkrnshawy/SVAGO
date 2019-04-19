
package com.example.svago.Models.CountriesResponses;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Data {

    @Expose
    private List<CountyData> counties;

    public List<CountyData> getCounties() {
        return counties;
    }

    public void setCounties(List<CountyData> counties) {
        this.counties = counties;
    }

}
