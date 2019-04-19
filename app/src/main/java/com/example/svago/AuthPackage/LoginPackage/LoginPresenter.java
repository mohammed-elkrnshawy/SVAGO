package com.example.svago.AuthPackage.LoginPackage;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.svago.Models.LoginResponses.AuthResponse;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SharedPackage.Classes.SharedUtils;

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
