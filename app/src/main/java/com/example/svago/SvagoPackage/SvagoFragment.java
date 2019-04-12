package com.example.svago.SvagoPackage;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.svago.R;
import com.example.svago.SharedPackage.Activity.MainHomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SvagoFragment extends Fragment {

    private View view;
    private SvagoPresenter svagoPresenter;

    @BindView(R.id.recycleSvago)
    RecyclerView recyclerSvago;

    public SvagoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_svago, container, false);
        ButterKnife.bind(this,view);
        initComponents();

        return view;
    }

    private void initComponents() {
        svagoPresenter=new SvagoPresenter(this);
        svagoPresenter.callData();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainHomeActivity.toolbar.setVisibility(View.GONE);
    }

}
