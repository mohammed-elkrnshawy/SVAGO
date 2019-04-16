package com.example.svago.GuidePackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

public interface GuideViewPresenter {

    void  getData(Bundle bundle);
    void validate(EditText edtLocation , EditText edtFrom , EditText edtTo , EditText edtDesc  ,EditText edtBudget, double lat ,  double lng);
    void makeOrder(String location , double lat , double lng , String from , String to , String description , String budget);
    void setAnimation(EditText editText);
    void setDatePickDialog(EditText da) ;
    void setOnEdtFocus(EditText da , Fragment fragment) ;
    void setOnEdtFocus(EditText da) ;
}
