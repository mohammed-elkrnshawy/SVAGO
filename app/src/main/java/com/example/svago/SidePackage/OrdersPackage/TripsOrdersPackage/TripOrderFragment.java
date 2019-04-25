package com.example.svago.SidePackage.OrdersPackage.TripsOrdersPackage;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;


@SuppressLint("ValidFragment")
public class TripOrderFragment extends Fragment {

    com.example.svago.Models.SharedResponses.userData userData ;

    public TripOrderFragment(com.example.svago.Models.SharedResponses.userData userData) {
        this.userData = userData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trip_order, container, false);
    }

}
