package com.example.svago.SidePackage.OrdersPackage.CarsOrderPackage;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;

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

    CarsPresenter mCarsPresenter ;

    public CarsOrderFragment(com.example.svago.Models.SharedResponses.userData userData) {
        this.userData = userData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cars_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        mCarsPresenter = new CarsPresenter(this , userData) ;
        mCarsPresenter.initView();
        mCarsPresenter.setRecycler();
        mCarsPresenter.callCars("Bearer "+userData.getToken());
        ///mCarsPresenter.listViewScroll();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
