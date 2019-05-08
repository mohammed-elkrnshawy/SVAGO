package com.travel.svago.SidePackage.MorePackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.SharedPackage.Classes.Constant;

import static android.content.Context.MODE_PRIVATE;

public class MorePresenter implements MoreViewPresenter {

    private userData userObject;
    private Context context ;
    private MoreFragment view ;

    public MorePresenter(Context context , MoreFragment view) {
        this.context = context;
        this.view = view ;
    }

    @Override
    public void initView() {
        if (!Constant.isLogin){
            view.txtLogin.setVisibility(View.VISIBLE);
            view.txtOrders.setVisibility(View.GONE);
            view.txtProfile.setVisibility(View.GONE);
            view.txtLogout.setVisibility(View.GONE);
        }else {
            view.txtLogin.setVisibility(View.GONE);
            view.txtOrders.setVisibility(View.VISIBLE);
            view.txtProfile.setVisibility(View.VISIBLE);
            view.txtLogout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public userData getIntentData(Fragment fragment) {
        Bundle bundle = fragment.getArguments();
        if (!bundle.isEmpty()){
            userObject=(userData) bundle.getSerializable(Constant.userFlag);
        }

        return userObject;
    }

    @Override
    public void SharedPreferencesPut(String s) {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putString("Token", s);
        editor.putBoolean("isLogin", false);
        editor.apply();
    }
}
