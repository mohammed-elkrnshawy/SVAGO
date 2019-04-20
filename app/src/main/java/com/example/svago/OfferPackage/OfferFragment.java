package com.example.svago.OfferPackage;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferFragment extends Fragment {


    @BindView(R.id.recycleOffer)
    RecyclerView recycleOffer;
    @BindView(R.id.bar)
    ProgressBar bar;
    Unbinder unbinder;
    OfferPresenter mOfferPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer, container, false);
        unbinder = ButterKnife.bind(this, view);
        mOfferPresenter = new OfferPresenter(getActivity() , bar);
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
}
