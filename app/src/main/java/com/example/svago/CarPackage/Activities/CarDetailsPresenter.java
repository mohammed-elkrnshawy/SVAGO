package com.example.svago.CarPackage.Activities;

import android.widget.Toast;

import com.example.svago.Models.CarDetailsResponses.CarDetailsResponse;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarDetailsPresenter {

    private int carID;
    private CarDetailsActivity view;
    private UserService_POST userServicePost;

    public CarDetailsPresenter(CarDetailsActivity carDetailsActivity,int carID){
        this.view=carDetailsActivity;
        userServicePost= ApiUtlis.getUserServices_Post();
        this.carID=carID;
        callData();
    }

    private void callData(){
        Call<CarDetailsResponse> call=userServicePost.carDetails(carID,2);
        call.enqueue(new Callback<CarDetailsResponse>() {
            @Override
            public void onResponse(Call<CarDetailsResponse> call, Response<CarDetailsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        view.setData(response.body().getData().getCar());
                    }else {

                    }
                }else {
                    Toast.makeText(view, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CarDetailsResponse> call, Throwable t) {
                Toast.makeText(view,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
