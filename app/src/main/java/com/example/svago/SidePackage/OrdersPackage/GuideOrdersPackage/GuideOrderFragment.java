package com.example.svago.SidePackage.OrdersPackage.GuideOrdersPackage;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class GuideOrderFragment extends Fragment {


    com.example.svago.Models.SharedResponses.userData userData;
    @BindView(R.id.recGuides)
    RecyclerView recGuides;
    @BindView(R.id.bar)
    ProgressBar bar;
    @BindView(R.id.empty)
    TextView empty;
    Unbinder unbinder;

    GuidePresenter mGuidePresenter ;

    public GuideOrderFragment(com.example.svago.Models.SharedResponses.userData userData) {
        this.userData = userData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        mGuidePresenter = new GuidePresenter(this , userData);
        mGuidePresenter.initView();
        mGuidePresenter.setRecycler();
        mGuidePresenter.callGuide("Bearer "+userData.getToken());
        //mGuidePresenter.listViewScroll();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
