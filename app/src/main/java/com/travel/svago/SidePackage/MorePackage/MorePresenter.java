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
            view.linLogin.setVisibility(View.VISIBLE);
            view.linOrder.setVisibility(View.GONE);
            view.linProfile.setVisibility(View.GONE);
            view.linLogout.setVisibility(View.GONE);
        }else {
            view.linLogin.setVisibility(View.GONE);
            view.linOrder.setVisibility(View.VISIBLE);
            view.linProfile.setVisibility(View.VISIBLE);
            view.linLogout.setVisibility(View.VISIBLE);
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
