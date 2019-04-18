package com.example.svago.SidePackage.MorePackage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;

public class MorePresenter implements MoreViewPresenter {

    private View view;
    private userData userObject;
    private Bundle bundleExtra=new Bundle();
    private Context context ;

    public MorePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void getIntentData(Fragment fragment) {
        Bundle bundle = fragment.getArguments();
        if (!bundle.isEmpty()){
            userObject=(userData) bundle.getSerializable(Constant.userFlag);
        }
    }
}
