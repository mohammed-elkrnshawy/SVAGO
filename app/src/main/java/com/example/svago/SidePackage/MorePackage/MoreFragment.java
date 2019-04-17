package com.example.svago.SidePackage.MorePackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    @BindView(R.id.txtContact)
    TextView txtContact;
    @BindView(R.id.txtLanguage)
    TextView txtLanguage;
    @BindView(R.id.txtAbout)
    TextView txtAbout;
    @BindView(R.id.txtTerms)
    TextView txtTerms;
    @BindView(R.id.txtLogout)
    TextView txtLogout;
    private View view;
    private userData userObject;
    private Bundle bundleExtra = new Bundle();

    @BindView(R.id.txtProfile)
    TextView txtProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        getIntentData();
        return view;
    }

    private void getIntentData() {
        Bundle bundle = getArguments();
        if (!bundle.isEmpty()) {
            userObject = (userData) bundle.getSerializable(Constant.userFlag);
        }
    }


    private void setFragment(Fragment fragment, String Title) {
        bundleExtra.putSerializable(Constant.userFlag, userObject);
        fragment.setArguments(bundleExtra);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conainerHome, fragment).addToBackStack(Title)
                .commitAllowingStateLoss();
    }


    @OnClick({R.id.txtProfile, R.id.txtContact, R.id.txtLanguage, R.id.txtAbout, R.id.txtTerms, R.id.txtLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtProfile:
                break;
            case R.id.txtContact:
                break;
            case R.id.txtLanguage:
                break;
            case R.id.txtAbout:
                break;
            case R.id.txtTerms:
                break;
            case R.id.txtLogout:
                break;
        }
    }
}
