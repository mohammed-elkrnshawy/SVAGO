package com.travel.svago.SvagoPackage.TripPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.Models.TripDetailsResponses.TripData;
import com.travel.svago.Models.TripDetailsResponses.TripDetailsResponse;
import com.travel.svago.R;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.SharedClass;
import com.nostra13.universalimageloader.core.ImageLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SvagoDetailsPresenter implements SvagoDetailsInterface {

    private TripData tripData;
    private int tripID;
    private SvagoDetailsActivity view;
    private userData userObject;
    private UserService_POST userServicePost;
    GridLayoutManager layoutManager ;

    public SvagoDetailsPresenter(SvagoDetailsActivity activity){
        this.view=activity;
        userServicePost= ApiUtlis.getUserServices_Post();
    }

    @Override
    public void getIntentData(Bundle bundle) {
        if (!bundle.isEmpty()){
            userObject=(userData)bundle.getSerializable(Constant.userFlag);
            tripID=bundle.getInt("tripID");
        }
    }

    @Override
    public void getData() {
        SharedClass.ShowWaiting(view);
        Call<TripDetailsResponse> call=userServicePost.TripDetails(tripID,1);
        call.enqueue(new Callback<TripDetailsResponse>() {
            @Override
            public void onResponse(Call<TripDetailsResponse> call, Response<TripDetailsResponse> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        tripData=response.body().getData().getTrip();
                        setData(tripData);
                    }else {
                        Toast.makeText(view, response.code()+"", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view,response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TripDetailsResponse> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(view,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setData(TripData data) {
        view.toolbarTitle .setText(data.getTitle());
        view.txtTitle.setText(data.getTitle());
        ImageLoader.getInstance().displayImage(data.getImage() , view.photoImg);
        view.date1.setText(data.getFrom());
        view.date2.setText(data.getTo());
        view.duration.setText(String.format("%d %s", data.getDuration(), view.getString(R.string.day)));
        view.txtDesc.setText(data.getDescription());
        ImagesAdapter mImagesAdapter = new ImagesAdapter(data.getImages() , view);
        view.imageRec.setAdapter(mImagesAdapter);
    }

    @Override
    public void onProcessClick() {
        Intent intent=new Intent(view, TripOrderActivity.class);
        intent.putExtra("tripData",tripData);
        intent.putExtra(Constant.userFlag,userObject);
        view.startActivity(intent);
    }

    @Override
    public void setRecycler() {
        layoutManager = new GridLayoutManager(view , 3 , GridLayoutManager.VERTICAL , true ) ;
        view.imageRec.setLayoutManager(layoutManager);
        view.imageRec.setHasFixedSize(true);

    }
}
