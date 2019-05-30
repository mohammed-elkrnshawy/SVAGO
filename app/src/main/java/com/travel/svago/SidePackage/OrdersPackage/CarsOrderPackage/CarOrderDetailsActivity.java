package com.travel.svago.SidePackage.OrdersPackage.CarsOrderPackage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.svago.Models.AffiliateResponse.AffiliateResponse;
import com.travel.svago.Models.ResponseCarsOrder.Car;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.Models.SimpleResponse.SimpleResponse;
import com.travel.svago.R;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.SharedClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarOrderDetailsActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.share)
    Button share;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.txtNameCar)
    TextView txtNameCar;
    @BindView(R.id.photo_Img)
    ImageView photoImg;
    @BindView(R.id.txtPassenger)
    TextView txtPassenger;
    @BindView(R.id.txtDoor)
    TextView txtDoor;
    @BindView(R.id.txtGear)
    TextView txtGear;
    @BindView(R.id.txtAir)
    TextView txtAir;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.txtModel)
    TextView txtModel;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.txtDriver)
    TextView txtDriver;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    userData userData ;
    UserService_POST userService ;
    private Car carData ;
    private int pos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_order_details);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    private void initView(){
        userService = ApiUtlis.getUserServices_Post() ;
    }

    private void getData(){
        Bundle bundle = getIntent().getExtras() ;
        if (bundle != null){
            userData = (com.travel.svago.Models.SharedResponses.userData) bundle.get(Constant.userFlag) ;
            carData = (Car) bundle.get("car") ;
            pos = bundle.getInt("position") ;
            setData();
        }
    }

    private void setData(){
        ImageLoader.getInstance().displayImage(carData.getCar().getImage(), photoImg);
        toolbarTitle.setText(carData.getStatus());
        txtNameCar.setText(carData.getCar().getName());
        txtDoor.setText(carData.getCar().getDoors());
        txtGear.setText(carData.getCar().getGears());
        txtAir.setText(carData.getCar().getAc());
        txtPassenger.setText(carData.getCar().getPassengers());
        txtAddress.setText(carData.getCar().getAddress());
        txtPrice.setText(carData.getTotal());
        txtModel.setText(carData.getCar().getModel());
        if (carData.getCar().getHasDriver()) {
            txtDriver.setText(getString(R.string.withDriver));
        } else {
            txtDriver.setText(getString(R.string.noDriver));
        }
        if (carData.getStatus().equals("cancelled"))
            btnCancel.setVisibility(View.GONE);
        else
            btnCancel.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back, R.id.share, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
                callLink(carData.getCar().getId());
                break;
            case R.id.btnCancel:
                cancelOrder(carData.getId());
                break;
        }
    }


    private void cancelOrder(String id){
        SharedClass.ShowWaiting(this);
        Call<SimpleResponse> call = userService.cancelCarOrder("Bearer "+userData.getToken() , id) ;
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus() == 200){
                        Toast.makeText(CarOrderDetailsActivity.this, getResources().getString(R.string.order_canelled), Toast.LENGTH_SHORT).show();
                        //finish();
                        btnCancel.setVisibility(View.GONE);
                        CarsPresenter.updateOrder(pos);
                    }else {
                        Toast.makeText(CarOrderDetailsActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(CarOrderDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(CarOrderDetailsActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callLink(int id){
        SharedClass.ShowWaiting(this);
        Call<AffiliateResponse> call = userService.getCarLink("Bearer "+userData.getToken() , id) ;
        call.enqueue(new Callback<AffiliateResponse>() {
            @Override
            public void onResponse(Call<AffiliateResponse> call, Response<AffiliateResponse> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus() == 200){
                       copy(response.body().getData().getLink());
                    }else {
                        //Toast.makeText(CarOrderDetailsActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(CarOrderDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AffiliateResponse> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(CarOrderDetailsActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void copy(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(getResources().getString(R.string.url_is_copied), text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, getResources().getString(R.string.url_is_copied), Toast.LENGTH_SHORT).show();
    }

}
