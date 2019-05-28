package com.travel.svago.AuthPackage.LoginPackage;

import com.travel.svago.Models.SharedResponses.userData;

public interface LoginInterface {
    void validDate();
    void getData();
    void openRegister();
    void successLogin(userData userData);
    void showAndHidePassword() ;
}
