package com.example.svago.SharedPackage.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.svago.FlightPackage.Fragments.FlightHomeFragment;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeActivity extends AppCompatActivity {

    @BindView(R.id.homeToolbar)
    Toolbar toolbar;


    private RelativeLayout relativeFlight,relativeHotel,relativeCar;
    private ImageView imgFlight,imgHotel,imgCar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        ButterKnife.bind(this);
        initComponents();
        setToolBar();
        getIntentData();
    }

    private void initComponents() {

    }

    private void getIntentData() {
        Bundle bundle=getIntent().getExtras();
        if (!bundle.isEmpty())
        {
            setFragmentWithType(bundle.getString(Constant.TypeTag));
        }
    }

    private void setFragmentWithType(String type) {
        if (type.equals(Constant.CarTag))
        {
        }
        else if (type.equals(Constant.FlightTag))
        {
            setFragment(new FlightHomeFragment(imgFlight),getString(R.string.flight));
        }
        else if (type.equals(Constant.HotelTag))
        {

        }
    }

    private void setFragment(Fragment fragment, String Title) {
        getSupportFragmentManager().beginTransaction().replace(R.id.conainerHome,fragment).addToBackStack(Title)
                .commitAllowingStateLoss();
    }

    private void setToolBar(){
        setSupportActionBar(toolbar);
        final LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.bar_home, null);

        imgFlight=view.findViewById(R.id.imgFlight);
        imgHotel=view.findViewById(R.id.imgHotel);
        imgCar=view.findViewById(R.id.imgCar);

        toolbar.addView(view);
    }
}
