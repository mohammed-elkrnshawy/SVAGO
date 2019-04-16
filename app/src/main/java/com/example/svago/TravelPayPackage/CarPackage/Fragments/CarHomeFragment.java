package com.example.svago.TravelPayPackage.CarPackage.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.svago.R;
import com.example.svago.SharedPackage.Activity.WaitingSearchActivity;
import com.example.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CarHomeFragment extends Fragment {

    private ImageView imgCar;
    private TextView txtCar;
    private View view;

    @BindView(R.id.cardClick) CardView cardClick;

    public CarHomeFragment(ImageView imgCar,TextView txtCar) {
       this.imgCar=imgCar;
       this.txtCar=txtCar;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_car_home, container, false);
        ButterKnife.bind(this,view);

        return view;
    }


    @OnClick({R.id.cardClick}) void onClick()
    {
        Intent intent=new Intent(getContext(), WaitingSearchActivity.class);
        intent.putExtra(Constant.TypeTag,Constant.CarTag);
        startActivity(intent);
    }










    @Override
    public void onResume() {
        super.onResume();
        txtCar.setTextColor(ContextCompat.getColor(getContext(), R.color.ColorPink));
        imgCar.setColorFilter(ContextCompat.getColor(getContext(), R.color.ColorPink), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onPause() {
        super.onPause();
        txtCar.setTextColor(ContextCompat.getColor(getContext(), R.color.ColorGray));
        imgCar.setColorFilter(ContextCompat.getColor(getContext(), R.color.ColorGray), android.graphics.PorterDuff.Mode.SRC_IN);
    }



}
