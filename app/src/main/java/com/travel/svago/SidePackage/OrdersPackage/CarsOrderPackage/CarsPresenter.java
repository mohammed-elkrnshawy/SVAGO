package com.travel.svago.SidePackage.OrdersPackage.CarsOrderPackage;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.travel.svago.Models.ResponseCarsOrder.Car;
import com.travel.svago.Models.ResponseCarsOrder.ResponseCars;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarsPresenter implements CarsViewPresenter {

    private CarsOrderFragment view ;
    private UserService_POST userService ;
    private com.travel.svago.Models.SharedResponses.userData userData ;
    private CarsAdapter mCarsAdapter ;
    private LinearLayoutManager layoutManager ;
    private List<Car> carList ;
    private boolean mLoading = false;
    private int breDy = 0;
    private int page = 1;
    public static  boolean  hide = true ;

    public CarsPresenter(CarsOrderFragment view, com.travel.svago.Models.SharedResponses.userData userData) {
        this.view = view;
        this.userData = userData;
        carList = new ArrayList<>();
        hide=true;
        userService = ApiUtlis.getUserServices_Post() ;
    }

    @Override
    public void initView() {

    }

    @Override
    public void callCars(String token) {
        view.bar.setVisibility(View.VISIBLE);
        Call<ResponseCars> call = userService.callCarsOrder(token , page) ;
        call.enqueue(new Callback<ResponseCars>() {
            @Override
            public void onResponse(Call<ResponseCars> call, Response<ResponseCars> response) {
                hideBar();
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        if (page == 1) {
                            carList.clear();
                        }
                        page++;
                        mLoading = false;
                        carList.addAll(response.body().getData().getCars());
                        mCarsAdapter.notifyDataSetChanged();

                       /* if (page == 2)
                            view.recCars.smoothScrollToPosition(0);
*/
                        /*if (carList.size()==0)
                            view.empty.setVisibility(View.VISIBLE);
                        else
                            view.empty.setVisibility(View.GONE);
*/
                        hideEmpty(carList.size());

                    }else {
                        Toast.makeText(view.getActivity(), response.body().getErrors().get(0) , Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view.getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCars> call, Throwable t) {
                view.bar.setVisibility(View.GONE);
                Toast.makeText(view.getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setRecycler() {
        layoutManager = new LinearLayoutManager(view.getActivity()) ;
        view.recCars.setLayoutManager(layoutManager);
        view.recCars.setHasFixedSize(true);
        mCarsAdapter = new CarsAdapter(view.getActivity() , carList , userData);
        view.recCars.setAdapter(mCarsAdapter);
    }

    @Override
    public void listViewScroll() {
        view.recCars.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    callCars("Bearer "+userData.getToken());
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void hideBar(){
        if (hide)
            view.bar.setVisibility(View.GONE);
    }

    private void hideEmpty(int size){
        if (hide){
            if (size == 0) {
                view.empty.setVisibility(View.VISIBLE);
            } else {
                view.empty.setVisibility(View.GONE);
            }
        }
    }


}
