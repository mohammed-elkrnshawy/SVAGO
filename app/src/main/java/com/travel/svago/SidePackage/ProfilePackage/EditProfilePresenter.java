package com.travel.svago.SidePackage.ProfilePackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.travel.svago.Models.LoginResponses.AuthResponse;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.SharedClass;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter {

    private EditProfileActivity view;
    private UserService_POST userServicePost;
    private Context context ;

    public EditProfilePresenter(EditProfileActivity view , Context context){
        this.view=view;
        this.context = context ;
        userServicePost= ApiUtlis.getUserServices_Post();
        setData();
    }

    private void setData(){
        view.setData();
    }
    
    public void validData() {
        view.validData();
    }

    public void callEditProfile(final userData userData , MultipartBody.Part part) {
        SharedClass.ShowWaiting(context);
        Call<AuthResponse> call=userServicePost.EditProfile(
                "Bearer "+userData.getToken(),
                userData.getUsername(),userData.getEmail(),userData.getPhone() , userData.getCode() , userData.getCountryID() , userData.getPassword() , part);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        response.body().getData().setToken(userData.getToken());
                        Intent intent=view.getIntent();
                        intent.putExtra(Constant.userFlag,response.body().getData());
                        view.setResult(Activity.RESULT_OK,intent);
                        view.finish();
                    }else {
                        Toast.makeText(view,response.body().getErrors().get(0), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(view, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void callEditProfile(final userData userData) {
        SharedClass.ShowWaiting(context);
        Call<AuthResponse> call=userServicePost.EditProfileWithoutPhoto(
                "Bearer "+userData.getToken(),
                userData.getUsername(),userData.getEmail(),userData.getPhone() , userData.getCode() , userData.getCountryID() , userData.getPassword());
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        response.body().getData().setToken(userData.getToken());
                        Intent intent=view.getIntent();
                        intent.putExtra(Constant.userFlag,response.body().getData());
                        view.setResult(Activity.RESULT_OK,intent);
                        view.finish();
                    }else {
                        Toast.makeText(view,response.body().getErrors().get(0), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(view, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
