package com.travel.svago.SidePackage.CurrenciesPackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.travel.svago.R;
import com.travel.svago.SharedPackage.Activity.MainHomeActivity;
import com.travel.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CurrenciesFragment extends Fragment {


    @BindView(R.id.recycleCurrency)
    RecyclerView recycleCurrency;
    @BindView(R.id.bar)
    ProgressBar bar;
    Unbinder unbinder;

    CurrenciesPresenter mCurrenciesPresenter;
    @BindView(R.id.save)
    Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currencies, container, false);
        unbinder = ButterKnife.bind(this, view);
        mCurrenciesPresenter = new CurrenciesPresenter(getActivity(), bar);
        mCurrenciesPresenter.setRecycle(recycleCurrency);
        mCurrenciesPresenter.callCurrencies(recycleCurrency);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        if (!CurrenciesAdapter.save){
            Toast.makeText(getActivity(), getResources().getString(R.string.choose_currency), Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getActivity(), getResources().getString(R.string.currency_changed_successfully), Toast.LENGTH_SHORT).show();
        MainHomeActivity.setFragmentWithType(Constant.SvagoTag);
    }
}
