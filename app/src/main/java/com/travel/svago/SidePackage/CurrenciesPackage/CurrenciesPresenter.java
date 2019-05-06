package com.travel.svago.SidePackage.CurrenciesPackage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.travel.svago.Models.ResponseCurrencies.Currency;
import com.travel.svago.Models.ResponseCurrencies.ResponseCurrencies;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrenciesPresenter implements CurrenciesViewPresenter {

    Context context ;
    List<Currency> currencyList ;
    UserService userService ;
    LinearLayoutManager layoutManager ;
    ProgressBar progressBar ;
    CurrenciesAdapter mCurrenciesAdapter ;

    public CurrenciesPresenter(Context context , ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar ;
        userService = ApiUtlis.getUserService();
        currencyList = new ArrayList<>();
    }

    @Override
    public void setRecycle(RecyclerView recycle) {
        layoutManager=  new LinearLayoutManager(context);
        recycle.setLayoutManager(layoutManager);
        recycle.setHasFixedSize(true);
        mCurrenciesAdapter = new CurrenciesAdapter(currencyList , context);
        recycle.setAdapter(mCurrenciesAdapter);
    }

    @Override
    public void callCurrencies(final RecyclerView recyclerView) {
        progressBar.setVisibility(View.VISIBLE);
        Call<ResponseCurrencies> call = userService.getCurrencies();
        call.enqueue(new Callback<ResponseCurrencies>() {
            @Override
            public void onResponse(Call<ResponseCurrencies> call, Response<ResponseCurrencies> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        currencyList.addAll(response.body().getData().getCurrencies());
                        mCurrenciesAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseCurrencies> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
