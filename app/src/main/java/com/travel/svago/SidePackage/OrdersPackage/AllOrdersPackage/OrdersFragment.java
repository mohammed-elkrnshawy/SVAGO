package com.travel.svago.SidePackage.OrdersPackage.AllOrdersPackage;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.booking.rtlviewpager.RtlViewPager;
import com.travel.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OrdersFragment extends Fragment {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    RtlViewPager viewpager;
    Unbinder unbinder;

    OrdersPresenter mOrdersPresenter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        unbinder = ButterKnife.bind(this, view);
        mOrdersPresenter = new OrdersPresenter(getActivity());
        mOrdersPresenter.getData(getArguments());
        mOrdersPresenter.setupViewPager(viewpager , this);
        tabs.setupWithViewPager(viewpager);
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
