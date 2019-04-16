package com.example.svago.GuidePackage;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.svago.Models.ResponseStatus.ResponseStatus;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SharedPackage.Activity.MainHomeActivity;
import com.example.svago.SharedPackage.Activity.MapsActivity;
import com.example.svago.SharedPackage.Classes.Constant;
import com.example.svago.SharedPackage.Classes.SharedClass;
import com.example.svago.SharedPackage.Classes.SharedUtils;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuidePresenter implements GuideViewPresenter {
    private static final int MAP_RESULT = 100 ;
    Context context ;
    userData userData ;
    UserService_POST userService ;
    Dialog progressDialog ;
    String sDate ;


    public GuidePresenter(Context context) {
        this.context = context;
        userService = ApiUtlis.getUserServices_Post() ;
    }

    @Override
    public void getData(Bundle bundle) {
        if (bundle != null){
            userData = (com.example.svago.Models.SharedResponses.userData)bundle.get(Constant.userFlag) ;
        }
    }

    @Override
    public void validate(EditText edtLocation, EditText edtFrom, EditText edtTo, EditText edtDesc, EditText edtBudget , double lat ,  double lng) {
        String location = edtLocation.getText().toString().trim() ;
        String from = edtFrom.getText().toString().trim() ;
        String to = edtTo.getText().toString().trim() ;
        String desc = edtDesc.getText().toString().trim() ;
        String budget = edtBudget.getText().toString().trim() ;

        if (TextUtils.isEmpty(location)) {
            edtLocation.setError(context.getString(R.string.required_field));
            setAnimation(edtLocation);
            return;
        }


        if (TextUtils.isEmpty(from)) {
            edtFrom.setError(context.getString(R.string.required_field));
            setAnimation(edtFrom);
            return;
        }


        if (TextUtils.isEmpty(to)) {
            edtTo.setError(context.getString(R.string.required_field));
            setAnimation(edtTo);
            return;
        }

        if (TextUtils.isEmpty(desc)) {
            edtDesc.setError(context.getString(R.string.required_field));
            setAnimation(edtDesc);
            return;
        }


        if (TextUtils.isEmpty(budget)) {
            edtBudget.setError(context.getString(R.string.required_field));
            setAnimation(edtBudget);
            return;
        }

        makeOrder(location ,lat,lng ,from , to ,desc,budget );
    }

    @Override
    public void makeOrder(String location, double lat, double lng, String from, String to, String description, String budget) {
        SharedClass.ShowWaiting(context);
        Call<ResponseStatus> call = userService.requestGuide("Bearer "+userData.getToken() , location , lat , lng , from , to , SharedClass.getLocalization(context) ,description , budget) ; 
        call.enqueue(new Callback<ResponseStatus>() {
            @Override
            public void onResponse(Call<ResponseStatus> call, Response<ResponseStatus> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus() == 200){
                        SharedClass.hideWaiting();
                        MainHomeActivity.setFragmentWithType(Constant.SvagoTag);
                        Toast.makeText(context, context.getString(R.string.guide_requested), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, response.body().getMsg() , Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
                SharedClass.hideWaiting();
            }

            @Override
            public void onFailure(Call<ResponseStatus> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(context, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setAnimation(EditText editText) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(0)
                .playOn(editText);
    }

    @Override
    public void setDatePickDialog(final EditText da) {
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        sDate = year + "-" + monthOfYear + "-" + dayOfMonth;
                        da.setText(sDate);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        //datePickerDialog.setTitle(getString(R.string.select_date));
        datePickerDialog.show();
        // dialog.show();
    }

    @Override
    public void setOnEdtFocus(EditText da , final Fragment fragment) {
        da.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Intent intent = new Intent(context, MapsActivity.class);
                    fragment.startActivityForResult(intent, MAP_RESULT);
                }
            }
        });
        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.startActivityForResult(new Intent(context, MapsActivity.class), MAP_RESULT);
            }
        });
    }

    @Override
    public void setOnEdtFocus(final EditText da) {
        da.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    setDatePickDialog(da);
                }
            }
        });
        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatePickDialog(da);
            }
        });
    }
}
