package com.travel.svago.SidePackage.OrdersPackage.GuideOrdersPackage;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.travel.svago.Models.ResponseGuideOrders.Guide;
import com.travel.svago.Models.ResponseGuideOrders.ResponseGuideOrders;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuidePresenter implements GuideViewPresenter {

    private GuideOrderFragment view ;
    private com.travel.svago.Models.SharedResponses.userData userData ;
    private UserService_POST userService ;
    private LinearLayoutManager layoutManager ;
    private boolean mLoading = false;
    private int breDy = 0;
    private int page = 1;
    private GuideAdapter mGuideAdapter ;
    private List<Guide> guideList ;

    public GuidePresenter(GuideOrderFragment view, com.travel.svago.Models.SharedResponses.userData userData) {
        this.view = view;
        this.userData = userData;
        userService = ApiUtlis.getUserServices_Post() ;
    }

    @Override
    public void initView() {
        guideList = new ArrayList<>();
    }

    @Override
    public void callGuide(String token) {
        view.bar.setVisibility(View.VISIBLE);
        Call<ResponseGuideOrders> call = userService.callGuidesOrder(token , page);
        call.enqueue(new Callback<ResponseGuideOrders>() {
            @Override
            public void onResponse(Call<ResponseGuideOrders> call, Response<ResponseGuideOrders> response) {
                view.bar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        if (page == 1) {
                            guideList.clear();
                        }
                        page++;
                        mLoading = false;
                        guideList.addAll(response.body().getData().getGuides());
                        mGuideAdapter.notifyDataSetChanged();

                        if (page == 2)
                            view.recGuides.smoothScrollToPosition(0);

                        if (guideList.size()==0)
                            view.empty.setVisibility(View.VISIBLE);
                        else
                            view.empty.setVisibility(View.GONE);


                    }else {
                        Toast.makeText(view.getActivity(), response.body().getErrors().get(0) , Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view.getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGuideOrders> call, Throwable t) {

            }
        });
    }

    @Override
    public void setRecycler() {
        layoutManager = new LinearLayoutManager(view.getActivity()) ;
        view.recGuides.setLayoutManager(layoutManager);
        view.recGuides.setHasFixedSize(true);
        mGuideAdapter = new GuideAdapter(view.getActivity() , guideList );
        view.recGuides.setAdapter(mGuideAdapter);
    }

    @Override
    public void listViewScroll() {
        view.recGuides.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    callGuide("Bearer "+userData.getToken());
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
