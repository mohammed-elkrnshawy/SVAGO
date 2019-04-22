package com.example.svago.SvagoPackage.CarPackage;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.svago.Models.CarDetailsResponses.CarData;
import com.example.svago.Models.OrderCarResponses.OrderCarResponse;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SharedPackage.Activity.MapsActivity;
import com.example.svago.SharedPackage.Activity.PaymentActivity;
import com.example.svago.SharedPackage.Classes.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarOrderPresent implements CarOrderInterface {

    private UserService_POST userService_post;
    private CarOrderActivity view;
    private userData userObject;
    private CarData carData;
    private double lat,lng;

    public CarOrderPresent(CarOrderActivity context){
        this.view=context;
        userService_post= ApiUtlis.getUserServices_Post();
    }

    @Override
    public void getIntentData(Bundle bundle) {
        if (!bundle.isEmpty()){
            userObject=(userData)bundle.getSerializable(Constant.userFlag);
            carData=(CarData) bundle.getSerializable("carData");
            setData();
        }
    }

    @Override
    public void setData() {
        ImageLoader.getInstance().displayImage(carData.getImage(),view.photo_Img);
        view.txtNameCar.setText(carData.getName());
    }

    @Override
    public void openDateDialog(final TextView textView) {
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(view,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        textView.setText(year + "-" + String.format("%02d",monthOfYear) + "-" + String.format("%02d",dayOfMonth));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }

    @Override
    public void result(Intent data) {
        lat = data.getDoubleExtra("lat", 0);
        lng = data.getDoubleExtra("lng", 0);
        view.edtLocation.setText(data.getStringExtra("address"));
    }

    @Override
    public void callConfirmOrder() {
        Call<OrderCarResponse> call=userService_post.orderCar(
                "Bearer "+userObject.getToken(),carData.getId(),view.edtFrom.getText().toString().trim(),
                view.edtTo.getText().toString().trim(),lat,lng
        );

        call.enqueue(new Callback<OrderCarResponse>() {
            @Override
            public void onResponse(Call<OrderCarResponse> call, Response<OrderCarResponse> response) {
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
            public void onFailure(Call<OrderCarResponse> call, Throwable t) {
                Toast.makeText(view, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void openMap() {
        Intent intent = new Intent(view, MapsActivity.class);
        view.startActivityForResult(intent, Constant.openMap);
    }


}
