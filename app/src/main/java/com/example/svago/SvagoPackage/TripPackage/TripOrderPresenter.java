package com.example.svago.SvagoPackage.TripPackage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.svago.Models.OrderTripResponses.OrderTripResponse;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.Models.TripDetailsResponses.TripData;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SharedPackage.Activity.PaymentActivity;
import com.example.svago.SharedPackage.Classes.Constant;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripOrderPresenter implements TripOrderInterface {

    private TripOrderActivity view;
    private UserService_POST userServicePost;
    private userData userObject;
    private TripData tripData;

    public TripOrderPresenter(TripOrderActivity activity){
        view=activity;
    }

    @Override
    public void getIntentData(Bundle bundle) {
        if (!bundle.isEmpty()){
            userObject=(userData) bundle.getSerializable(Constant.userFlag);
            tripData=(TripData) bundle.getSerializable("tripData");
        }
    }

    @Override
    public void initImageLoader() {
        //region ImageLoader
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(view.getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
        //endregion
    }

    @Override
    public void callConfirmOrder() {
        userServicePost= ApiUtlis.getUserServices_Post();
        Call<OrderTripResponse> call=userServicePost.orderTrip(
                "Bearer "+userObject.getToken(),tripData.getId(),Integer.parseInt(view.edtNumber.getText().toString().trim())
        );
        call.enqueue(new Callback<OrderTripResponse>() {
            @Override
            public void onResponse(Call<OrderTripResponse> call, Response<OrderTripResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        Intent intent=new Intent(view, PaymentActivity.class);
                        intent.putExtra("URL",response.body().getData().getUrl());
                        view.startActivity(intent);
                    }else {
                        Toast.makeText(view, response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderTripResponse> call, Throwable t) {

            }
        });
    }
}
