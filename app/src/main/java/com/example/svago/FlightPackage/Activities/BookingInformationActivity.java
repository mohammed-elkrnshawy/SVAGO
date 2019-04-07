package com.example.svago.FlightPackage.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import com.example.svago.R;
import com.example.svago.SharedPackage.Activity.PaymentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingInformationActivity extends AppCompatActivity {

    @BindView(R.id.cardClick)
    CardView cardClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_information);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.cardClick}) void onClick()
    {
        startActivity(new Intent(this, PaymentActivity.class));
    }
}
