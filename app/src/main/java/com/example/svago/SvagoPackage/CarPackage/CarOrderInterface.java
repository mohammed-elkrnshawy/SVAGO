package com.example.svago.SvagoPackage.CarPackage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public interface CarOrderInterface {
    void getIntentData(Bundle bundle);
    void setData();
    void openDateDialog(TextView textView);
    void result(Intent intent);
    void callConfirmOrder();
    void openMap();
}
