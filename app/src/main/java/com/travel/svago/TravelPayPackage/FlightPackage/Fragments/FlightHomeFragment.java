package com.travel.svago.TravelPayPackage.FlightPackage.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.svago.R;
import com.travel.svago.SharedPackage.Activity.WaitingSearchActivity;
import com.travel.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@SuppressLint("ValidFragment")
public class FlightHomeFragment extends Fragment {

    private TextView txtFlight;
    private ImageView imgFlight;
    private View view;

    @BindView(R.id.cardClick)
    CardView cardClick;

    public FlightHomeFragment(ImageView imgFlight,TextView txtFlight) {
        this.imgFlight=imgFlight;
        this.txtFlight=txtFlight;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_flight_home, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick({R.id.cardClick}) void onClick()
    {
        Intent intent=new Intent(getContext(), WaitingSearchActivity.class);
        intent.putExtra(Constant.TypeTag,Constant.FlightTag);
        startActivity(intent);
    }





    @Override
    public void onResume() {
        super.onResume();
        txtFlight.setTextColor(ContextCompat.getColor(getContext(), R.color.ColorPink));
        imgFlight.setColorFilter(ContextCompat.getColor(getContext(), R.color.ColorPink), android.graphics.PorterDuff.Mode.SRC_IN);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        txtFlight.setTextColor(ContextCompat.getColor(getContext(), R.color.ColorGray));
        imgFlight.setColorFilter(ContextCompat.getColor(getContext(), R.color.ColorGray), android.graphics.PorterDuff.Mode.SRC_IN);
    }
}
