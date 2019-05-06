package com.travel.svago.TravelPayPackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.travel.svago.TravelPayPackage.CarPackage.Fragments.CarHomeFragment;
import com.travel.svago.TravelPayPackage.FlightPackage.Fragments.FlightHomeFragment;
import com.travel.svago.TravelPayPackage.HotelPackage.Fragments.HotelHomeFragment;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainTravelFragment extends Fragment {

    private View view;

    @BindView(R.id.imgFlight) ImageView imgFlight;
    @BindView(R.id.imgHotel) ImageView imgHotel;
    @BindView(R.id.imgCar) ImageView imgCar;

    @BindView(R.id.txtFlight) TextView txtFlight;
    @BindView(R.id.txtHotel) TextView txtHotel;
    @BindView(R.id.txtCar) TextView txtCar;

    @BindView(R.id.relativeFlight) RelativeLayout relativeFlight;
    @BindView(R.id.relativeHotel) RelativeLayout relativeHotel;
    @BindView(R.id.relativeCar) RelativeLayout relativeCar;


    public MainTravelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_travel, container, false);
        ButterKnife.bind(this,view);
        getIntentData();
        return view;
    }

    private void getIntentData() {
        Bundle bundle=getArguments();
        if (!bundle.isEmpty()){
            setFragmentWithType(bundle.getString(Constant.TypeTag));
        }
    }

    private void setFragmentWithType(String type) {
        if (type.equals(Constant.CarTag)) {
            setFragment(new CarHomeFragment(imgCar,txtCar),getString(R.string.car));
        } else if (type.equals(Constant.HotelTag)) {
            setFragment(new HotelHomeFragment(imgHotel,txtHotel),getString(R.string.hotel));
        } else {
            setFragment(new FlightHomeFragment(imgFlight,txtFlight),getString(R.string.flight));
        }
    }

    private void setFragment(Fragment fragment, String Title) {
        /*fragmentBundle.putSerializable(Constant.userFlag,userObject);
        fragmentBundle.putString(Constant.TypeTag,stringType);
        fragment.setArguments(fragmentBundle);*/
        getFragmentManager().beginTransaction().replace(R.id.conainerTravel,fragment)
                .addToBackStack(Title).commitAllowingStateLoss();
    }

    @OnClick({R.id.relativeCar,R.id.relativeFlight,R.id.relativeHotel}) void onClick(View view) {
        switch (view.getId()) {
            case R.id.relativeCar:
                setFragment(new CarHomeFragment(imgCar, txtCar), getString(R.string.car));
                break;
            case R.id.relativeFlight:
                setFragment(new FlightHomeFragment(imgFlight, txtFlight), getString(R.string.flight));
                break;
            case R.id.relativeHotel:
                setFragment(new HotelHomeFragment(imgHotel, txtHotel), getString(R.string.hotel));
                break;
        }
    }

}
