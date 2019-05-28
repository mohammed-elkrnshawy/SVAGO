package com.travel.svago.AuthPackage.RegisterPackage;

import android.widget.EditText;
import android.widget.ImageView;

import com.travel.svago.Models.SharedResponses.userData;

public interface RegisterInterface {
    void validDate();
    void successRegister(userData userData);
    void showAndHidePassword(EditText editText , ImageView imageView) ;
}
