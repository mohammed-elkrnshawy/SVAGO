package com.travel.svago.SvagoPackage.SvagoFragmentListPackage;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.Models.SvagoResponses.SvagoData;
import com.travel.svago.Models.SvagoResponses.SvagoResponse;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SvagoPresenter {

    private SvagoFragment view;
    private UserService_POST userServicePost;
    private SvagoAdapter svagoAdapter;
    private List<SvagoData> svagoDataList=new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    public SvagoPresenter(SvagoFragment svagoFragment, userData userData){
        this.view=svagoFragment;
        userServicePost= ApiUtlis.getUserServices_Post();
        svagoAdapter=new SvagoAdapter(svagoDataList,view.getContext(),userData);
    }


    private void callSvagoList(final int page){
        view.progress.setVisibility(View.VISIBLE);
        Call<SvagoResponse> svagoResponseCall=userServicePost.svagoList(2);
        svagoResponseCall.enqueue(new Callback<SvagoResponse>() {
            @Override
            public void onResponse(Call<SvagoResponse> call, Response<SvagoResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()== 200){

                        if (page==1)
                            svagoDataList.clear();

                        svagoDataList.addAll(response.body().getData());
                        svagoAdapter.notifyDataSetChanged();

                        view.progress.setVisibility(View.GONE);

                    }else {
                        view.progress.setVisibility(View.GONE);
                        Toast.makeText(view.getContext(), "Not 200", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    view.progress.setVisibility(View.GONE);
                    Toast.makeText(view.getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SvagoResponse> call, Throwable t) {
                view.progress.setVisibility(View.GONE);
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void callData() {
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        view.recyclerSvago.setLayoutManager(linearLayoutManager);
        view.recyclerSvago.setAdapter(svagoAdapter);
        callSvagoList(1);
    }
}
