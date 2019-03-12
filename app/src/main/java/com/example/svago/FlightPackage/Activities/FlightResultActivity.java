package com.example.svago.FlightPackage.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.svago.R;
import com.example.svago.SharedPackage.Activity.WaitingSearchActivity;
import com.example.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlightResultActivity extends AppCompatActivity {

    @BindView(R.id.rowClick)
    View rowClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_result);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rowClick}) void onClick()
    {
        startActivity(new Intent(this,TripDetailsActivity.class));
    }
}
