package com.example.svago.SidePackage.AboutUsPackage;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.svago.Models.ResponseAboutUs.ResponseAboutUs;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService;
import com.example.svago.SharedPackage.Classes.SharedClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsPresenter implements AboutUsViewPresenter {

    UserService userService ;
    Context context ;

    public AboutUsPresenter(Context context) {
        this.context = context;
        userService = ApiUtlis.getUserService() ;
    }

    @Override
    public void callAbout(final TextView textView) {
        SharedClass.ShowWaiting(context);
        Call<ResponseAboutUs> call = userService.aboutUS();
        call.enqueue(new Callback<ResponseAboutUs>() {
            @Override
            public void onResponse(Call<ResponseAboutUs> call, Response<ResponseAboutUs> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        textView.setText(response.body().getData().getContent());
                    }else {
                        Toast.makeText(context, response.body().getMsg() , Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseAboutUs> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(context, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
