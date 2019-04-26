package com.example.svago.SidePackage.OrdersPackage.AllOrdersPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

public interface OrdersViewPresenter {
    void  initView();
    void getData(Bundle bundle);
    void  setupViewPager(ViewPager viewPager , Fragment fragment) ;
}
