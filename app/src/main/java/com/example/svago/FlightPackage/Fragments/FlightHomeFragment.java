package com.example.svago.FlightPackage.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.svago.R;


@SuppressLint("ValidFragment")
public class FlightHomeFragment extends Fragment {

    private TextView txtFlight;
    private ImageView imgFlight;

    public FlightHomeFragment(ImageView imgFlight,TextView txtFlight) {
        this.imgFlight=imgFlight;
        this.txtFlight=txtFlight;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flight_home, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        txtFlight.setTextColor(ContextCompat.getColor(getContext(), R.color.ColorPink));
        imgFlight.setColorFilter(ContextCompat.getColor(getContext(), R.color.ColorPink), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onPause() {
        super.onPause();
        txtFlight.setTextColor(ContextCompat.getColor(getContext(), R.color.ColorGray));
        imgFlight.setColorFilter(ContextCompat.getColor(getContext(), R.color.ColorGray), android.graphics.PorterDuff.Mode.SRC_IN);
    }
}
