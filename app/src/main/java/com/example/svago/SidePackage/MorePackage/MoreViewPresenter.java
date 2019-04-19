package com.example.svago.SidePackage.MorePackage;

import android.support.v4.app.Fragment;

import com.example.svago.Models.SharedResponses.userData;

public interface MoreViewPresenter {

    userData getIntentData(Fragment fragment);
}
