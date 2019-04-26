package com.example.svago.SidePackage.OrdersPackage.TripsOrdersPackage;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.svago.Models.ResponseCarsOrder.Car;
import com.example.svago.Models.ResponseTripOrders.ResponseTripOrders;
import com.example.svago.Models.ResponseTripOrders.Trip;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SidePackage.OrdersPackage.CarsOrderPackage.CarsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripPresenter implements TripViewPresenter {

    private UserService_POST userService ;
    private com.example.svago.Models.SharedResponses.userData userData ;
    private LinearLayoutManager layoutManager ;
    private boolean mLoading = false;
    private int breDy = 0;
    private int page = 1;
    private TripOrderFragment view ;
    private TripAdapter mTripAdapter ;
    List<Trip> tripList ;

    public TripPresenter(com.example.svago.Models.SharedResponses.userData userData, TripOrderFragment view) {
        this.userData = userData;
        this.view = view;
        userService = ApiUtlis.getUserServices_Post();
    }

    @Override
    public void initView() {
        tripList = new ArrayList<>();
    }

    @Override
    public void callTrips(String token) {
        view.bar.setVisibility(View.VISIBLE);
        Call<ResponseTripOrders> call = userService.callTripsOrder(token , page);
        call.enqueue(new Callback<ResponseTripOrders>() {
            @Override
            public void onResponse(Call<ResponseTripOrders> call, Response<ResponseTripOrders> response) {
                view.bar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        if (page == 1) {
                            tripList.clear();
                        }
                        page++;
                        mLoading = false;
                        tripList.addAll(response.body().getData().getTrips());
                        mTripAdapter.notifyDataSetChanged();

                        if (page == 2)
                            view.recTrip.smoothScrollToPosition(0);

                        if (tripList.size()==0)
                            view.empty.setVisibility(View.VISIBLE);
                        else
                            view.empty.setVisibility(View.GONE);

                    }else {
                        Toast.makeText(view.getActivity(), response.body().getErrors().get(0), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view.getActivity(), response.message() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTripOrders> call, Throwable t) {
                view.bar.setVisibility(View.GONE);
                Toast.makeText(view.getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setRecycler() {
        layoutManager = new LinearLayoutManager(view.getActivity()) ;
        view.recTrip.setLayoutManager(layoutManager);
        view.recTrip.setHasFixedSize(true);
        mTripAdapter = new TripAdapter(view.getActivity() , tripList , userData);
        view.recTrip.setAdapter(mTripAdapter);
    }

    @Override
    public void listViewScroll() {
        view.recTrip.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.findLastVisibleItemPosition();
                if (!mLoading && visibleItemCount >= totalItemCount - 1 && page > 1) {
                    mLoading = true;
                    if (dy >= breDy) {
                        breDy = dy;
                    } else {
                        breDy = dy;
                    }
                    callTrips("Bearer "+userData.getToken());
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
