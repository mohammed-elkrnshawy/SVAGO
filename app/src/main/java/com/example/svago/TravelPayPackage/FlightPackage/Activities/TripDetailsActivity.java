package com.example.svago.TravelPayPackage.FlightPackage.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripDetailsActivity extends AppCompatActivity {

    @BindView(R.id.btnProcess)
    Button btnProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnProcess}) void onClick()
    {
        startActivity(new Intent(this,BookingInformationActivity.class));
    }

}
