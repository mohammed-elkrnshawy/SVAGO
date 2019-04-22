package com.example.svago.SvagoPackage.TripPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripOrderActivity extends AppCompatActivity {

    @BindView(R.id.txtNameTrip)
    TextView txtNameTrip;
    @BindView(R.id.photo_Img)
    ImageView photo_Img;
    @BindView(R.id.edtNumber)
    TextView edtNumber;
    @BindView(R.id.cartOrder)
    CardView cartOrder;

    private TripOrderPresenter orderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_order);
        ButterKnife.bind(this);

        orderPresenter=new TripOrderPresenter(this);
        orderPresenter.getIntentData(getIntent().getExtras());
        orderPresenter.initImageLoader();

    }

    @OnClick({R.id.cartOrder}) void Clicked(View view){
        switch (view.getId()){
            case R.id.cartOrder:
                orderPresenter.callConfirmOrder();
                break;
        }
    }
}
