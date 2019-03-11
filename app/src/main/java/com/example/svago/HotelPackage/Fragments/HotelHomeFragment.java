package com.example.svago.HotelPackage.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.svago.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class HotelHomeFragment extends Fragment {

    private ImageView imgHotel;
    private TextView txtHotel;

    public HotelHomeFragment(ImageView imgHotel,TextView txtHotel) {
       this.imgHotel=imgHotel;
       this.txtHotel=txtHotel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotel_home, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        txtHotel.setTextColor(ContextCompat.getColor(getContext(), R.color.ColorPink));
        imgHotel.setColorFilter(ContextCompat.getColor(getContext(), R.color.ColorPink), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onPause() {
        super.onPause();
        txtHotel.setTextColor(ContextCompat.getColor(getContext(), R.color.ColorGray));
        imgHotel.setColorFilter(ContextCompat.getColor(getContext(), R.color.ColorGray), android.graphics.PorterDuff.Mode.SRC_IN);
    }

}
