package com.travel.svago.SharedPackage.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.svago.GuidePackage.GuideFragment;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.OfferPackage.OfferFragment;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.SharedClass;
import com.travel.svago.SidePackage.MorePackage.MoreFragment;
import com.travel.svago.SvagoPackage.SvagoFragmentListPackage.SvagoFragment;
import com.travel.svago.TravelPayPackage.MainTravelFragment;

import butterknife.ButterKnife;

public class MainHomeActivity extends AppCompatActivity {

    public static BottomNavigationView navigation;

    private static userData userObject;
    private String stringType;
    private RelativeLayout relativeFlight,relativeHotel,relativeCar;
    private ImageView imgFlight,imgHotel,imgCar;
    private TextView txtFlight,txtHotel,txtCar;
    private Bundle fragmentBundle=new Bundle();
    private String preTitle = "" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        ButterKnife.bind(this);
        initComponents();
        onBottomItemClicked();
        getIntentData();
    }

    private void initComponents() {
        navigation = findViewById(R.id.navigation) ;
    }

    private void getIntentData() {
        Bundle bundle=getIntent().getExtras();
        if (!bundle.isEmpty()) {
            userObject=(userData)bundle.getSerializable(Constant.userFlag);
            stringType=bundle.getString(Constant.TypeTag);
            if (userObject.getToken()==null){
                Constant.isLogin=false;
            }else {
                Constant.isLogin=true;
            }
            OfferFragment.offer = bundle.getBoolean("offer" , true) ;
            SvagoFragment.svago = bundle.getBoolean("svago" , true) ;
            setFragmentWithType(stringType);
        }
    }

    public static void setFragmentWithType(String type) {
        if (type.equals(Constant.CarTag)) {
            navigation.setSelectedItemId(R.id.booking);
        } else if (type.equals(Constant.FlightTag)) {
            navigation.setSelectedItemId(R.id.booking);
        } else if (type.equals(Constant.HotelTag)) {
            navigation.setSelectedItemId(R.id.booking);
        } else if (type.equals(Constant.SvagoTag)) {
            navigation.setSelectedItemId(R.id.svago);
        }else if (type.equals(Constant.OfferTag)) {
            navigation.setSelectedItemId(R.id.offers);
        }else if (type.equals(Constant.GuideTag)){
            navigation.setSelectedItemId(R.id.guide);
        }else if (type.equals(Constant.MoreTag)){
            navigation.setSelectedItemId(R.id.more);
        }
    }

    private void setFragment(Fragment fragment, String Title) {
        if (!preTitle.equals(Title)){
            fragmentBundle.putSerializable(Constant.userFlag,userObject);
            fragmentBundle.putString(Constant.TypeTag,stringType);
            fragment.setArguments(fragmentBundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.conainerHome,fragment).addToBackStack(Title)
                    .commitAllowingStateLoss();
            preTitle = Title ;
        }

    }

    private void onBottomItemClicked(){
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.booking:
                        setFragment(new MainTravelFragment(),Constant.TravelTag);
                        return true;
                        case R.id.offers:
                            setFragment(new OfferFragment(),Constant.OfferTag);
                        return true;
                    case R.id.svago:
                        setFragment(new SvagoFragment(),Constant.SvagoTag);
                        return true;
                    case R.id.guide:
                        setFragment(new GuideFragment(),Constant.GuideTag);
                        return true;
                    case R.id.more:
                        setFragment(new MoreFragment(),getString(R.string.more));
                        return true;
                }
                return false ;
            }
        });
    }

    public static void updateUserData(userData userData){
        userObject=userData;
    }


}
