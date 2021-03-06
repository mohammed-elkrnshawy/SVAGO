package com.travel.svago.SvagoPackage.CarPackage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.svago.Models.CarDetailsResponses.CarData;
import com.travel.svago.Models.OrderCarResponses.OrderCarResponse;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Activity.MapsActivity;
import com.travel.svago.SharedPackage.Activity.PaymentActivity;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.SharedUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarOrderPresent implements CarOrderInterface {

    private Dialog progressDialog;
    private UserService_POST userService_post;
    private CarOrderActivity view;
    private userData userObject;
    private CarData carData;
    private double lat,lng;

    public CarOrderPresent(CarOrderActivity context){
        this.view=context;
        userService_post= ApiUtlis.getUserServices_Post();
        progressDialog= SharedUtils.ShowWaiting(view,progressDialog);
    }

    @Override
    public void getIntentData(Bundle bundle) {
        if (!bundle.isEmpty()){
            userObject=(userData)bundle.getSerializable(Constant.userFlag);
//            Log.d("LOLO" , userObject.getToken());
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

                        textView.setText(String.format("%d-%s-%s", year, String.format("%02d", monthOfYear), String.format("%02d", dayOfMonth)));
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

        if(TextUtils.isEmpty(view.edtLocation.getText().toString().trim())){
            view.edtLocation.setError(view.getResources().getString(R.string.requiredField));
            view.edtLocation.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(view.edtFrom.getText().toString().trim())){
            view.edtFrom.setError(view.getResources().getString(R.string.requiredField));
            view.edtFrom.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(view.edtTo.getText().toString().trim())){
            view.edtTo.setError(view.getResources().getString(R.string.requiredField));
            view.edtTo.requestFocus();
            return;
        }

        progressDialog.show();

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
                        progressDialog.dismiss();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(view, response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(view, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderCarResponse> call, Throwable t) {
                Toast.makeText(view, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void openMap() {
        Intent intent = new Intent(view, MapsActivity.class);
        view.startActivityForResult(intent, Constant.openMap);
    }


}
