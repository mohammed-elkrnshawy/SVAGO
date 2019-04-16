package com.example.svago.SharedPackage.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.svago.GuidePackage.GuideFragment;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.OfferPackage.OfferFragment;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;
import com.example.svago.SidePackage.MoreFragment;
import com.example.svago.SvagoPackage.SvagoFragment;
import com.example.svago.TravelPayPackage.MainTravelFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeActivity extends AppCompatActivity {

    public static BottomNavigationView navigation;

    private static userData userObject;
    private String stringType;
    private RelativeLayout relativeFlight,relativeHotel,relativeCar;
    private ImageView imgFlight,imgHotel,imgCar;
    private TextView txtFlight,txtHotel,txtCar;
    private Bundle fragmentBundle=new Bundle();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        ButterKnife.bind(this);
        initComponents();
        onBottomItemClicked();
        getIntentData();
    }

    private void initComponents() {
        navigation = findViewById(R.id.navigation) ;
    }

    private void getIntentData() {
        Bundle bundle=getIntent().getExtras();
        if (!bundle.isEmpty()) {
            userObject=(userData)bundle.getSerializable(Constant.userFlag);
            stringType=bundle.getString(Constant.TypeTag);
            setFragmentWithType(stringType);
        }
    }

    public static void setFragmentWithType(String type) {
        if (type.equals(Constant.CarTag)) {
            navigation.setSelectedItemId(R.id.booking);
        } else if (type.equals(Constant.FlightTag)) {
            navigation.setSelectedItemId(R.id.booking);
        } else if (type.equals(Constant.HotelTag)) {
            navigation.setSelectedItemId(R.id.booking);
        } else if (type.equals(Constant.SvagoTag)) {
            navigation.setSelectedItemId(R.id.svago);
        }else if (type.equals(Constant.OfferTag)) {
            navigation.setSelectedItemId(R.id.offers);
        }
    }

    private void setFragment(Fragment fragment, String Title) {
            fragmentBundle.putSerializable(Constant.userFlag,userObject);
            fragmentBundle.putString(Constant.TypeTag,stringType);
            fragment.setArguments(fragmentBundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.conainerHome,fragment).addToBackStack(Title)
                    .commitAllowingStateLoss();
    }

    private void onBottomItemClicked(){
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.booking:
                        setFragment(new MainTravelFragment(),Constant.TravelTag);
                        return true;
                        case R.id.offers:
                        setFragment(new OfferFragment(),Constant.TravelTag);
                        return true;
                    case R.id.svago:
                        setFragment(new SvagoFragment(),Constant.SvagoTag);
                        return true;
                    case R.id.guide:
                        setFragment(new GuideFragment(),Constant.GuideTag);
                        return true;
                    case R.id.more:
                        setFragment(new MoreFragment(),getString(R.string.more));
                        return true;
                }
                return false ;
            }
        });
    }

    public static void updateUserData(userData userData){
        userObject=userData;
    }


}
