package com.example.svago.SidePackage.MorePackage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;

import static android.content.Context.MODE_PRIVATE;

public class MorePresenter implements MoreViewPresenter {

    private userData userObject;
    private Context context ;

    public MorePresenter(Context context) {
        this.context = context;
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
