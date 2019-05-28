package com.travel.svago.OfferPackage;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agrawalsuneet.loaderspack.loaders.CurvesLoader;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Activity.MainHomeActivity;
import com.travel.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferFragment extends Fragment {


    @BindView(R.id.recycleOffer)
    RecyclerView recycleOffer;
    @BindView(R.id.progress)
    CurvesLoader progress;
    Unbinder unbinder;
    OfferPresenter mOfferPresenter;

    public static boolean offer  ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer, container, false);
        unbinder = ButterKnife.bind(this, view);
        mOfferPresenter = new OfferPresenter(getActivity() , progress);
        mOfferPresenter.getData(getArguments());
        mOfferPresenter.setRecycle(recycleOffer);
        SharedPreferences prefs = getActivity().getSharedPreferences(getActivity().getPackageName(), getActivity().MODE_PRIVATE);
        mOfferPresenter.callOffers(prefs.getInt("CurrencyID" , 1) , recycleOffer);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainHomeActivity.setFragmentWithType(Constant.OfferTag);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (offer) {
                        FragmentManager fm = getFragmentManager();
                        fm.popBackStack();
                   }else {
                       getActivity().finish();
                    }
                    return true ;
                }
                return false;
            }
        });
    }
}
