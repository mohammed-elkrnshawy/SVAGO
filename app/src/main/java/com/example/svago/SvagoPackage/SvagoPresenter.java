package com.example.svago.SvagoPackage;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.svago.Models.SvagoResponses.SvagoData;
import com.example.svago.Models.SvagoResponses.SvagoResponse;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SharedPackage.Classes.Constant;

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

    public SvagoPresenter(SvagoFragment svagoFragment){
        this.view=svagoFragment;
        userServicePost= ApiUtlis.getUserServices_Post();
        svagoAdapter=new SvagoAdapter(svagoDataList,view.getContext());
    }


    private void callSvagoList(final int page){
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


                    }else {
                        Toast.makeText(view.getContext(), "Not 200", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SvagoResponse> call, Throwable t) {
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
