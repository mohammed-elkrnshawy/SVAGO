
package com.example.svago.Models.SvagoResponses;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class SvagoResponse {

    @Expose
    private List<SvagoData> data;
    @Expose
    private int status;

    public List<SvagoData> getData() {
        return data;
    }

    public void setData(List<SvagoData> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
