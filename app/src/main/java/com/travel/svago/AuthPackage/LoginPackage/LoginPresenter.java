package com.travel.svago.AuthPackage.LoginPackage;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.travel.svago.Models.LoginResponses.AuthResponse;
import com.travel.svago.Models.ResponseSocialLogin.ResponseSocialLogin;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Classes.SharedUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginPresenter {

    private UserService_POST userServicePost;
    private LoginActivity view;
    private Dialog progressDialog;

    public LoginPresenter(LoginActivity view) {
        this.view=view;
        userServicePost= ApiUtlis.getUserServices_Post();
        progressDialog= SharedUtils.ShowWaiting(view,progressDialog);
    }

    public void validData(){
        view.validDate();
    }

    public void callLogon(String stringEmail, String stringPassword) {
        progressDialog.show();
        Call<AuthResponse> call=userServicePost.Login(
                stringEmail,stringPassword,"en","Token"
        );
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        SharedPreferencesPut(response.body().getData().getToken());
                        view.successLogin(response.body().getData());
                        progressDialog.dismiss();
                    }else {
                        Toast.makeText(view, response.body().getError(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }else {
                    Toast.makeText(view, response.message(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(view, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void socialLogin(final String email , final String name , String driver , String id , final String image){
        progressDialog.show();
        Call<ResponseSocialLogin> call = userServicePost.socialLogin(email , name , driver , id)  ;
        call.enqueue(new Callback<ResponseSocialLogin>() {
            @Override
            public void onResponse(Call<ResponseSocialLogin> call, Response<ResponseSocialLogin> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    if (response.body().getStatus()==205||response.body().getStatus()==207){
                        SharedPreferencesPut(response.body().getToken());
                        userData userData = new userData() ;
                        userData.setToken(response.body().getToken());
                        userData.setUsername(name);
                        userData.setEmail(email);
                        userData.setPicture(image);
                        userData.setSocial(true);
                        view.successLogin(userData);
                    }else {

                    }
                }else {
                    Toast.makeText(view, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSocialLogin> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(view, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openRegister() {
        view.openRegister();
    }

    private void SharedPreferencesPut(String UserUID) {
        SharedPreferences.Editor editor = view.getSharedPreferences(view.getPackageName(), MODE_PRIVATE).edit();
        editor.putString("Token", UserUID);
        editor.putBoolean("isLogin", true);
        editor.apply();
    }
}
