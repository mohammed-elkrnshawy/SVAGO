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

import com.example.svago.CarPackage.Fragments.CarHomeFragment;
import com.example.svago.FlightPackage.Fragments.FlightHomeFragment;
import com.example.svago.HotelPackage.Fragments.HotelHomeFragment;
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
            setFragment(new CarHomeFragment(imgCar),getString(R.string.car));
        }
        else if (type.equals(Constant.FlightTag))
        {
            setFragment(new FlightHomeFragment(imgFlight),getString(R.string.flight));
        }
        else if (type.equals(Constant.HotelTag))
        {
            setFragment(new HotelHomeFragment(imgHotel),getString(R.string.hotel));
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
        relativeFlight=view.findViewById(R.id.relativeFlight);
        relativeHotel=view.findViewById(R.id.relativeHotel);
        relativeCar=view.findViewById(R.id.relativeCar);

        relativeCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new CarHomeFragment(imgCar),getString(R.string.car));
            }
        });

        relativeFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new FlightHomeFragment(imgFlight),getString(R.string.flight));
            }
        });

        relativeHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new HotelHomeFragment(imgHotel),getString(R.string.hotel));
            }
        });

        toolbar.addView(view);
    }
}
