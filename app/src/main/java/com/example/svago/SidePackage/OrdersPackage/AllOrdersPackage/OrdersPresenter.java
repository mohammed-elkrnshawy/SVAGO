package com.example.svago.SidePackage.OrdersPackage.AllOrdersPackage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService;
import com.example.svago.SidePackage.OrdersPackage.CarsOrderPackage.CarsAdapter;
import com.example.svago.SidePackage.OrdersPackage.CarsOrderPackage.CarsOrderFragment;
import com.example.svago.SidePackage.OrdersPackage.GuideOrdersPackage.GuideOrderFragment;
import com.example.svago.SidePackage.OrdersPackage.TripsOrdersPackage.TripOrderFragment;

public class OrdersPresenter implements OrdersViewPresenter {

    private Context context ;
    private UserService userService ;
    private userData userData ;
    private ViewPagerAdapter adapter;


    public OrdersPresenter(Context context) {
        this.context = context;
        userService = ApiUtlis.getUserService();
    }

    @Override
    public void initView() {

    }

    @Override
    public void getData(Bundle bundle) {
        if (bundle != null){
            userData = (com.example.svago.Models.SharedResponses.userData)bundle.get("userData");
        }
    }



    @Override
    public void setupViewPager(ViewPager viewPager , Fragment fragment) {
        adapter = new ViewPagerAdapter(fragment.getChildFragmentManager());
        adapter.addFragment(new CarsOrderFragment(userData), context.getString(R.string.cars));
        adapter.addFragment(new TripOrderFragment(userData), context.getString(R.string.trips));
        adapter.addFragment(new GuideOrderFragment(userData), context.getString(R.string.guides));
        viewPager.setAdapter(adapter);
    }
}
