package com.travel.svago.GuidePackage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.SharedClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment {

    private static final int MAP_RESULT = 100 ;
    @BindView(R.id.edtLocation)
    EditText edtLocation;
    @BindView(R.id.edtFrom)
    EditText edtFrom;
    @BindView(R.id.edtTo)
    EditText edtTo;
    @BindView(R.id.edtDesc)
    EditText edtDesc;
    @BindView(R.id.edtBudget)
    EditText edtBudget;
    @BindView(R.id.cartOrder)
    CardView cartOrder;
    Unbinder unbinder;

    double lat , lng ;
    String address ;
    GuidePresenter mGuidePresenter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        unbinder = ButterKnife.bind(this, view);
        mGuidePresenter = new GuidePresenter(getActivity());
        mGuidePresenter.getData(getArguments());
        mGuidePresenter.setOnEdtFocus(edtLocation , this);
        mGuidePresenter.setOnEdtFocus(edtFrom);
        mGuidePresenter.setOnEdtFocus(edtTo);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cartOrder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cartOrder:
                if (Constant.isLogin)
                    mGuidePresenter.validate(edtLocation , edtFrom , edtTo , edtDesc , edtBudget , lat , lng);
                else
                    SharedClass.setDialog(getActivity());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAP_RESULT && resultCode == RESULT_OK) {
            lat = data.getDoubleExtra("lat", 0);
            lng = data.getDoubleExtra("lng", 0);
            address = data.getStringExtra("address");
            edtLocation.setText(address);
        }

    }
}
