package com.example.svago.SvagoPackage.TripPackage;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public interface TripOrderInterface {
    void getIntentData(Bundle bundle);
    void initImageLoader();
    void callConfirmOrder();
    void setData(ImageView imageView, TextView txtName);
}
