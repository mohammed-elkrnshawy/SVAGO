package com.travel.svago.TravelPayPackage.FlightPackage.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.travel.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlightResultActivity extends AppCompatActivity {

    @BindView(R.id.rowClick)
    View rowClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_flight_result);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rowClick}) void onClick()
    {
        startActivity(new Intent(this, TripDetailsActivity.class));
    }
}
