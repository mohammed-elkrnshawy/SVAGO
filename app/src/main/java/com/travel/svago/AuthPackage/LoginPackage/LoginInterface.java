package com.travel.svago.AuthPackage.LoginPackage;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.travel.svago.Models.SharedResponses.userData;

public interface LoginInterface {
    void validDate();
    void getData();
    void openRegister();
    void successLogin(userData userData);
    void showAndHidePassword() ;
    void initGoogleSignin() ;
    void updateUI(GoogleSignInAccount account) ;
    void signIn() ;
}
