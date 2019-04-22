package com.example.svago.SvagoPackage.CarPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.svago.Models.CarDetailsResponses.CarData;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarDetailsActivity extends AppCompatActivity implements CarDetailsInterface {

    private int carID;
    private userData userObject;
    private CarDetailsPresenter carDetailsPresenter;
    private CarData carData;

    @BindView(R.id.btnProcess)
    Button btnProcess;
    @BindView(R.id.txtNameCar)
    TextView txtNameCar;
    @BindView(R.id.txtDoor)
    TextView txtDoor;
    @BindView(R.id.txtGear)
    TextView txtGear;
    @BindView(R.id.txtAir)
    TextView txtAir;
    @BindView(R.id.txtPassenger)
    TextView txtPassenger;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.txtModel)
    TextView txtModel;
    @BindView(R.id.txtDriver)
    TextView txtDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        ButterKnife.bind(this);
        getIntentData();
        initComponents();
    }

    private void initComponents() {
        carDetailsPresenter=new CarDetailsPresenter(this,carID);
    }

    private void getIntentData() {
        Bundle bundle=getIntent().getExtras();
        if (!bundle.isEmpty()){
            userObject=(userData)bundle.getSerializable(Constant.userFlag);
            carID=bundle.getInt("carID");
        }
    }

    @Override
    public void setData(CarData carData) {
        this.carData=carData;
        txtNameCar.setText(carData.getName());
        txtDoor.setText(carData.getDoors());
        txtGear.setText(carData.getGears());
        txtAir.setText(carData.getAc());
        txtPassenger.setText(carData.getPassengers());
        txtAddress.setText(carData.getAddress());
        txtPrice.setText(carData.getPrice());
        txtModel.setText(carData.getModel());
        if (carData.getHasDriver()){
            txtDriver.setText(getString(R.string.withDriver));
        }else {
            txtDriver.setText(getString(R.string.noDriver));
        }
    }

    @OnClick({R.id.btnProcess}) void onClick(View view){
        switch (view.getId()){
            case R.id.btnProcess:
                Intent intent=new Intent(CarDetailsActivity.this,CarOrderActivity.class);
                intent.putExtra("carData",carData);
                intent.putExtra(Constant.userFlag,userObject);
                startActivity(intent);
                break;
        }
    }
}
