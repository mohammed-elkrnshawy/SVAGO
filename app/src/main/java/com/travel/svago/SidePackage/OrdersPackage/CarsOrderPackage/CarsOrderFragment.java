package com.travel.svago.SidePackage.OrdersPackage.CarsOrderPackage;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SidePackage.OrdersPackage.GuideOrdersPackage.GuidePresenter;
import com.travel.svago.SidePackage.OrdersPackage.TripsOrdersPackage.TripPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CarsOrderFragment extends Fragment {

    userData userData;
    @BindView(R.id.recCars)
    RecyclerView recCars;
    Unbinder unbinder;
    @BindView(R.id.bar)
    ProgressBar bar;
    @BindView(R.id.empty)
    TextView empty;
    CarsPresenter mCarsPresenter;

    public CarsOrderFragment(com.travel.svago.Models.SharedResponses.userData userData) {
        this.userData = userData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cars_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        mCarsPresenter = new CarsPresenter(this, userData);
        mCarsPresenter.initView();
        mCarsPresenter.setRecycler();
        mCarsPresenter.callCars("Bearer " + userData.getToken());
        ///mCarsPresenter.listViewScroll();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
//        GuidePresenter.hide=false;
  //      TripPresenter.hide=false ;
    }


    @Override
    public void onPause() {
        super.onPause();
        CarsPresenter.hide=false;
    }
}
