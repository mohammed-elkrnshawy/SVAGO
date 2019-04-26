package com.example.svago.SidePackage.OrdersPackage.TripsOrdersPackage;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class TripOrderFragment extends Fragment {

    com.example.svago.Models.SharedResponses.userData userData;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.recTrip)
    RecyclerView recTrip;
    Unbinder unbinder;
    @BindView(R.id.bar)
    ProgressBar bar;

    TripPresenter mTripPresenter ;

    public TripOrderFragment(com.example.svago.Models.SharedResponses.userData userData) {
        this.userData = userData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        mTripPresenter = new TripPresenter(userData , this);
        mTripPresenter.initView();
        mTripPresenter.callTrips("Bearer "+userData.getToken());
        mTripPresenter.setRecycler();
        //mTripPresenter.listViewScroll();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
