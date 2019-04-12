package com.example.svago.SidePackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;
import com.example.svago.SidePackage.ProfilePackage.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    private View view;
    private userData userObject;
    private Bundle bundleExtra=new Bundle();

    @BindView(R.id.txtProfile)
    TextView txtProfile;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this,view);
        getIntentData();
        return view;
    }

    private void getIntentData() {
        Bundle bundle=getArguments();
        if (!bundle.isEmpty()){
            userObject=(userData) bundle.getSerializable(Constant.userFlag);
        }
    }

    @OnClick({R.id.txtProfile})void onClick(View view){
        switch (view.getId()){
            case R.id.txtProfile:
                setFragment(new ProfileFragment(),getString(R.string.my_profile));
                break;
        }
    }

    private void setFragment(Fragment fragment, String Title) {
        bundleExtra.putSerializable(Constant.userFlag,userObject);
        fragment.setArguments(bundleExtra);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conainerHome,fragment).addToBackStack(Title)
                .commitAllowingStateLoss();
    }
}
