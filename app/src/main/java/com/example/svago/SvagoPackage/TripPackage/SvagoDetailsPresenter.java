package com.example.svago.SvagoPackage.TripPackage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.Models.TripDetailsResponses.TripData;
import com.example.svago.Models.TripDetailsResponses.TripDetailsResponse;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SharedPackage.Classes.Constant;
import com.example.svago.SvagoPackage.CarPackage.CarDetailsActivity;
import com.example.svago.SvagoPackage.CarPackage.CarOrderActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SvagoDetailsPresenter implements SvagoDetailsInterface {

    private TripData tripData;
    private int tripID;
    private SvagoDetailsActivity view;
    private userData userObject;
    private UserService_POST userServicePost;

    public SvagoDetailsPresenter(SvagoDetailsActivity activity){
        this.view=activity;
        userServicePost= ApiUtlis.getUserServices_Post();
    }

    @Override
    public void getIntentData(Bundle bundle) {
        if (!bundle.isEmpty()){
            userObject=(userData)bundle.getSerializable(Constant.userFlag);
            tripID=bundle.getInt("tripID");
        }
    }

    @Override
    public void getData() {
        Call<TripDetailsResponse> call=userServicePost.TripDetails(tripID,1);
        call.enqueue(new Callback<TripDetailsResponse>() {
            @Override
            public void onResponse(Call<TripDetailsResponse> call, Response<TripDetailsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        tripData=response.body().getData().getTrip();
                        setData();
                    }else {
                        Toast.makeText(view, response.code()+"", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view,response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TripDetailsResponse> call, Throwable t) {
                Toast.makeText(view,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setData() {

    }

    @Override
    public void onProcessClick() {
        Intent intent=new Intent(view, CarOrderActivity.class);
        intent.putExtra("tripData",tripData);
        intent.putExtra(Constant.userFlag,userObject);
        view.startActivity(intent);
    }
}
