package com.example.svago.SharedPackage.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.example.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentSuccessActivity extends AppCompatActivity {

    @BindView(R.id.btnDetails)
    Button btnDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_payment_success);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnDetails}) void onClick()
    {
        startActivity(new Intent(this,TicketStatusActivity.class));
    }
}
