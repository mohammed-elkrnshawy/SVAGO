package com.example.svago.SharedPackage.Classes;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.svago.Models.CountriesResponses.CountriesResponse;
import com.example.svago.Models.CountriesResponses.CountyData;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService;
import com.example.svago.SharedPackage.Adapter.CountryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CountryAdapterHelper {

    private UserService userService;
    private Context context;
    private List<CountyData> dataList=new ArrayList<>();
    private CountryAdapter locationAdapter;
    private CountyData data;

    public CountryAdapterHelper(Context context){
        this.context=context;
        userService= ApiUtlis.getUserService();
    }

    public void PrepareSpinner(Spinner spinner){
        locationAdapter=new CountryAdapter(dataList,context);
        spinner.setAdapter(locationAdapter);
        getData();
    }


    private void getData(){
        Call<CountriesResponse> call=userService.getCountry();
        call.enqueue(new Callback<CountriesResponse>() {
            @Override
            public void onResponse(Call<CountriesResponse> call, Response<CountriesResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        dataList.addAll(response.body().getData().getCounties());
                        locationAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CountriesResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
