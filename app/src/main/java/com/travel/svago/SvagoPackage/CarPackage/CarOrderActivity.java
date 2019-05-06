package com.travel.svago.SvagoPackage.CarPackage;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarOrderActivity extends AppCompatActivity {

    @BindView(R.id.txtNameCar)
    TextView txtNameCar;
    @BindView(R.id.photo_Img)
    ImageView photo_Img;
    @BindView(R.id.edtLocation)
    TextView edtLocation;
    @BindView(R.id.edtFrom)
    TextView edtFrom;
    @BindView(R.id.edtTo)
    TextView edtTo;
    @BindView(R.id.cartOrder)
    CardView cartOrder;

    private CarOrderPresent carOrderPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_order);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        //region ImageLoader
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
        //endregion
        carOrderPresent=new CarOrderPresent(this);
        carOrderPresent.getIntentData(getIntent().getExtras());
    }

    @OnClick({R.id.edtTo,R.id.edtFrom,R.id.edtLocation,R.id.cartOrder}) void Clicked(View view){
        switch (view.getId()){

            case R.id.edtTo:
                carOrderPresent.openDateDialog(edtTo);
                break;
            case R.id.edtFrom:
                carOrderPresent.openDateDialog(edtFrom);
                break;
            case R.id.cartOrder:
                carOrderPresent.callConfirmOrder();
                break;
            case R.id.edtLocation:
                carOrderPresent.openMap();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Constant.openMap&&resultCode== Activity.RESULT_OK){
            carOrderPresent.result(data);
        }
    }
}
