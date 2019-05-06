package com.travel.svago.SidePackage.TermsPackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermsFragment extends Fragment {


    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.about)
    TextView about;
    Unbinder unbinder;
    TermsPresenter mTermsPresenter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terms, container, false);
        unbinder = ButterKnife.bind(this, view);
        mTermsPresenter = new TermsPresenter(getActivity());
        mTermsPresenter.callTerms(about);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
