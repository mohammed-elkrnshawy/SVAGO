package com.example.svago.SharedPackage.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.svago.FlightPackage.Fragments.FlightHomeFragment;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;

public class MainHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        getIntentData();
    }

    private void getIntentData() {
        Bundle bundle=getIntent().getExtras();
        if (!bundle.isEmpty())
        {
            setFragmentWithType(bundle.getString(Constant.TypeTag));
        }
    }

    private void setFragmentWithType(String type) {
        if (type.equals(Constant.CarTag))
        {
        }
        else if (type.equals(Constant.FlightTag))
        {
            setFragment(new FlightHomeFragment(),getString(R.string.flight));
        }
        else if (type.equals(Constant.HotelTag))
        {

        }
    }

    private void setFragment(Fragment fragment, String Title) {
        getSupportFragmentManager().beginTransaction().replace(R.id.conainerHome,fragment).addToBackStack(Title)
                .commitAllowingStateLoss();
    }
}
