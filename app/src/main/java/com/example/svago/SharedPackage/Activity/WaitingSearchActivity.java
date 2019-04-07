package com.example.svago.SharedPackage.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.svago.CarPackage.Activities.CarResultActivity;
import com.example.svago.CarPackage.Fragments.CarHomeFragment;
import com.example.svago.FlightPackage.Activities.FlightResultActivity;
import com.example.svago.FlightPackage.Fragments.FlightHomeFragment;
import com.example.svago.HotelPackage.Activities.HotelResultActivity;
import com.example.svago.HotelPackage.Fragments.HotelHomeFragment;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;

import butterknife.ButterKnife;

public class WaitingSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_waiting_search);
        ButterKnife.bind(this);
        initComponents();
        getIntentData();
    }


    private void initComponents() {

    }

    private void getIntentData() {
        final Bundle bundle=getIntent().getExtras();
        if (!bundle.isEmpty())
        {
            new CountDownTimer(3500, 2000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    setActivityWithType(bundle.getString(Constant.TypeTag));
                }
            }.start();
        }
    }

    private void setActivityWithType(String type) {
        if (type.equals(Constant.CarTag))
        {
            startActivity(new Intent(this, CarResultActivity.class));
            finish();
        }
        else if (type.equals(Constant.FlightTag))
        {
            startActivity(new Intent(this, FlightResultActivity.class));
            finish();
        }
        else if (type.equals(Constant.HotelTag))
        {
            startActivity(new Intent(this, HotelResultActivity.class));
            finish();
        }
    }

}
