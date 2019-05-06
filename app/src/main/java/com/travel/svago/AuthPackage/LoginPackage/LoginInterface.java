package com.travel.svago.AuthPackage.LoginPackage;

import com.travel.svago.Models.SharedResponses.userData;

public interface LoginInterface {
    void validDate();
    void openRegister();
    void successLogin(userData userData);
}
