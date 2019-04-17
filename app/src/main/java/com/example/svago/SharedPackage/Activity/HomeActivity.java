package com.example.svago.SharedPackage.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.cardCar) CardView cardCar;
    @BindView(R.id.cardDeal) CardView cardDeal;
    @BindView(R.id.cardFlight) CardView cardFlight;
    @BindView(R.id.cardHotel) CardView cardHotel;
    @BindView(R.id.cardSvago) CardView cardSvago;

    private userData userObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        getIntentData();
    }

    private void getIntentData() {
        Bundle bundle=getIntent().getExtras();
        if (!bundle.isEmpty()){
            userObject=(userData) bundle.get(Constant.userFlag);
            if (userObject.getToken()==null){
                Constant.isLogin=false;
            }else {
                Constant.isLogin=true;
            }
        }
    }

    @OnClick({R.id.cardHotel, R.id.cardSvago,R.id.cardFlight,R.id.cardDeal,R.id.cardCar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardSvago:
                Intent Svago=new Intent(HomeActivity.this, MainHomeActivity.class);
                Svago.putExtra(Constant.TypeTag,Constant.SvagoTag);
                Svago.putExtra(Constant.userFlag,userObject);
                startActivity(Svago);
                break;
            case R.id.cardHotel:
                Intent Hotel=new Intent(HomeActivity.this, MainHomeActivity.class);
                Hotel.putExtra(Constant.TypeTag,Constant.HotelTag);
                startActivity(Hotel);
                break;
            case R.id.cardCar:
                Intent Car=new Intent(HomeActivity.this, MainHomeActivity.class);
                Car.putExtra(Constant.TypeTag,Constant.CarTag);
                startActivity(Car);                break;
            case R.id.cardFlight:
                Intent Flight=new Intent(HomeActivity.this, MainHomeActivity.class);
                Flight.putExtra(Constant.TypeTag,Constant.FlightTag);
                startActivity(Flight);
                break;
            case R.id.cardDeal:
                Intent Offers=new Intent(HomeActivity.this, MainHomeActivity.class);
                Offers.putExtra(Constant.TypeTag,Constant.OfferTag);
                startActivity(Offers);
                break;
        }
    }

}
