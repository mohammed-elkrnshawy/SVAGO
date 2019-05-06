package com.travel.svago.SidePackage.MorePackage;

import android.support.v4.app.Fragment;

import com.travel.svago.Models.SharedResponses.userData;

public interface MoreViewPresenter {

    userData getIntentData(Fragment fragment);

    void SharedPreferencesPut(String s);
}