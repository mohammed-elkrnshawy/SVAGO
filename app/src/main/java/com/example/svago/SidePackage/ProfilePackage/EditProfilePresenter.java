package com.example.svago.SidePackage.ProfilePackage;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.svago.Models.LoginResponses.AuthResponse;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SharedPackage.Classes.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter {

    private EditProfileActivity view;
    private UserService_POST userServicePost;

    public EditProfilePresenter(EditProfileActivity view){
        this.view=view;
        userServicePost= ApiUtlis.getUserServices_Post();
        setData();
    }

    private void setData(){
        view.setData();
    }
    
    public void validData() {
        view.validData();
    }

    public void callEditProfile(final userData userData) {
        Call<AuthResponse> call=userServicePost.EditProfile(
                "Bearer"+userData.getToken(),
                userData.getUsername(),userData.getEmail(),userData.getPhone());
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        response.body().getData().setToken(userData.getToken());
                        Intent intent=view.getIntent();
                        intent.putExtra(Constant.userFlag,response.body().getData());
                        view.setResult(Activity.RESULT_OK,intent);
                        view.finish();
                    }else {
                        Toast.makeText(view,response.body().getError(), Toast.LENGTH_SHORT).show();
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
