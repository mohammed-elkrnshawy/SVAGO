package com.example.svago.SidePackage.LanguagePackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.svago.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LanguageFragment extends Fragment {

    LanguagePresenter mLanguagePresenter;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        unbinder = ButterKnife.bind(this, view);
        mLanguagePresenter = new LanguagePresenter(getActivity()) ;
        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.English, R.id.Arabic, R.id.Turkish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.English:
                mLanguagePresenter.chooseLanguage("english","en");
                break;
            case R.id.Arabic:
                mLanguagePresenter.chooseLanguage("arabic","ar");
                break;
            case R.id.Turkish:
//                mLanguagePresenter.chooseLanguage("arabic","ar");
                break;
        }
    }
}
