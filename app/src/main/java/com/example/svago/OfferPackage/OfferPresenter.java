package com.example.svago.OfferPackage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.svago.Models.ResponseOffers.Offer;
import com.example.svago.Models.ResponseOffers.ResponseOffers;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService;
import com.example.svago.Remote.UserService_POST;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferPresenter implements OfferViewPresenter {

    Context context ;
    UserService_POST userService ;
    userData userData ;
    ProgressBar bar ;
    LinearLayoutManager layoutManager ;
    List<Offer> offerList ;
    OfferAdapter mOfferAdapter ;
    private int breDy=0;
    private int page=1;
    private boolean mLoading = false;

    public OfferPresenter(Context context , ProgressBar bar) {
        this.context = context;
        this.bar = bar ;
        userService = ApiUtlis.getUserServices_Post() ;
        offerList = new ArrayList<>() ;
    }

    @Override
    public void initView() {

    }

    @Override
    public void getData(Bundle bundle) {
        if (bundle != null){
            userData = (userData) bundle.get("data") ;
        }
    }

    @Override
    public void setRecycle(RecyclerView recycle) {
        layoutManager = new LinearLayoutManager(context) ;
        recycle.setLayoutManager(layoutManager);
        recycle.setHasFixedSize(true);
        mOfferAdapter = new OfferAdapter(context , offerList);
        recycle.setAdapter(mOfferAdapter);
    }

    @Override
    public void callOffers(int currency_id , final RecyclerView recyclerView) {
        bar.setVisibility(View.VISIBLE);
        Call<ResponseOffers> call = userService.svagoOffers(currency_id) ;
        call.enqueue(new Callback<ResponseOffers>() {
            @Override
            public void onResponse(Call<ResponseOffers> call, Response<ResponseOffers> response) {
                bar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        if (page == 1) {
                            offerList.clear();
                        }
                        page++;
                        mLoading = false;
                        offerList.addAll(response.body().getData());
                        mOfferAdapter.notifyDataSetChanged();

                        if (page == 2)
                            recyclerView.smoothScrollToPosition(0);

                    }else {
                        Toast.makeText(context, response.body().getMsg() , Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message() , Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseOffers> call, Throwable t) {
                bar.setVisibility(View.GONE);
                Toast.makeText(context, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void listViewScroll(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.findLastVisibleItemPosition();
                if (!mLoading && visibleItemCount >= totalItemCount - 1 && page > 1) {
                    mLoading = true;
                    if (dy>=breDy)
                    {
                        breDy=dy;
                        //callUnits();

                    }
                    else
                    {
                        breDy=dy;
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
