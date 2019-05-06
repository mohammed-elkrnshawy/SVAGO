package com.travel.svago.SvagoPackage.TripPackage;

import android.os.Bundle;

import com.travel.svago.Models.TripDetailsResponses.TripData;

public interface SvagoDetailsInterface {
    void getIntentData(Bundle bundle);
    void getData();
    void setData(TripData data);
    void onProcessClick();
    void setRecycler();
}
