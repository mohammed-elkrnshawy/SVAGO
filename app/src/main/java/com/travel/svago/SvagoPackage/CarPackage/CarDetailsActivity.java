package com.travel.svago.SvagoPackage.CarPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.svago.Models.CarDetailsResponses.CarData;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.SharedUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarDetailsActivity extends AppCompatActivity implements CarDetailsInterface {

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.line)
    LinearLayout line;
    private int carID;
    private userData userObject;
    private CarDetailsPresenter carDetailsPresenter;
    private CarData carData;
    private SkeletonScreen skeletonScreen;

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
    @BindView(R.id.photo_Img)
    ImageView photo_Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        ButterKnife.bind(this);
        getIntentData();
        initComponents();
    }

    private void initComponents() {
        skeletonScreen = Skeleton.bind((ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0))
                .load(R.layout.wait_car_details)
                .color(R.color.ColorGray)       // the shimmer color.
                //.angle(30)// the shimmer angle.
                .duration(1000)
                .shimmer(true)
                .show();

        carDetailsPresenter = new CarDetailsPresenter(this, carID, skeletonScreen);
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()) {
            userObject = (userData) bundle.getSerializable(Constant.userFlag);
            carID = bundle.getInt("carID");
        }
    }

    @Override
    public void setData(CarData carData) {
        ImageLoader.getInstance().displayImage(carData.getImage(), photo_Img);
        this.carData = carData;
        toolbarTitle.setText(carData.getName());
        txtNameCar.setText(carData.getName());
        txtDoor.setText(carData.getDoors());
        txtGear.setText(carData.getGears());
        txtAir.setText(carData.getAc());
        txtPassenger.setText(carData.getPassengers());
        txtAddress.setText(carData.getAddress());
        txtPrice.setText(carData.getPrice());
        txtModel.setText(carData.getModel());
        if (carData.getHasDriver()) {
            txtDriver.setText(getString(R.string.withDriver));
        } else {
            txtDriver.setText(getString(R.string.noDriver));
        }
    }

    @OnClick({R.id.btnProcess})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnProcess:
                SharedUtils.stopTouch(this);
                Intent intent = new Intent(CarDetailsActivity.this, CarOrderActivity.class);
                intent.putExtra("carData", carData);
                intent.putExtra(Constant.userFlag, userObject);
                startActivity(intent);
                SharedUtils.enableTouch(this);
                break;
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
