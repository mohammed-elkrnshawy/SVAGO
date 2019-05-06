package com.travel.svago.AuthPackage.RegisterPackage;

import android.app.Dialog;
import android.widget.Toast;

import com.travel.svago.Models.LoginResponses.AuthResponse;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Classes.SharedUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    private RegisterActivity view;
    private UserService_POST userServicePost;
    private Dialog progressDialog;

    public RegisterPresenter(RegisterActivity view){
        this.view=view;
        userServicePost= ApiUtlis.getUserServices_Post();
        progressDialog= SharedUtils.ShowWaiting(view,progressDialog);
    }

    public void validData(){
        view.validDate();
    }

    public void callRegister(userData userData) {
        progressDialog.show();
        Call<AuthResponse> call=userServicePost.Register(
                userData.getUsername(),userData.getEmail(),userData.getPassword(),
                "token","en",userData.getCountryID(),userData.getPhone());
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        view.successRegister(response.body().getData());
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
}
