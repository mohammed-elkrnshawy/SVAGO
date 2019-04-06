package com.example.svago.AuthPackage;

import android.widget.Toast;

import com.example.svago.Models.LoginResponses.AuthResponse;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    private RegisterActivity view;
    private UserService_POST userServicePost;

    public RegisterPresenter(RegisterActivity view){
        this.view=view;
        userServicePost= ApiUtlis.getUserServices_Post();
    }

    public void validData(){
        view.validDate();
    }

    public void callRegister(userData userData) {
        Call<AuthResponse> call=userServicePost.Register(
                userData.getUsername(),userData.getEmail(),userData.getPassword(),"token","en"
        );
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        view.successRegister(response.body().getData());
                    }else {
                        Toast.makeText(view, response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(view, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
