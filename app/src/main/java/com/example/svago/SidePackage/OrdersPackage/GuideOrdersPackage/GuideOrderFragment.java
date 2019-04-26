package com.example.svago.SidePackage.OrdersPackage.GuideOrdersPackage;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class GuideOrderFragment extends Fragment {


    com.example.svago.Models.SharedResponses.userData userData ;

    public GuideOrderFragment(com.example.svago.Models.SharedResponses.userData userData) {
        this.userData = userData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide_order, container, false);
    }

}
