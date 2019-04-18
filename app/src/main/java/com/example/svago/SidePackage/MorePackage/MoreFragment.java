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
import com.example.svago.SidePackage.AboutUsPackage.AboutUsFragment;
import com.example.svago.SidePackage.ContactUsPackage.ContactUsFragment;
import com.example.svago.SidePackage.LanguagePackage.LanguageFragment;
import com.example.svago.SidePackage.ProfilePackage.ProfileFragment;
import com.example.svago.SidePackage.TermsPackage.TermsFragment;

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
    MorePresenter morePresenter ;

    @BindView(R.id.txtProfile)
    TextView txtProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this,view);
        morePresenter = new MorePresenter(getActivity()) ;
        morePresenter.getIntentData(this);
        return view;
    }




    @OnClick({R.id.txtProfile, R.id.txtContact, R.id.txtLanguage, R.id.txtAbout, R.id.txtTerms, R.id.txtLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtProfile:
                setFragment(new ProfileFragment(),getString(R.string.my_profile));
                break;
            case R.id.txtContact:
                setFragment(new ContactUsFragment() , getString(R.string.contact_us));
                break;
            case R.id.txtLanguage:
                setFragment(new LanguageFragment() , getString(R.string.language));
                break;
            case R.id.txtAbout:
                setFragment(new AboutUsFragment() , getString(R.string.about));
                break;
            case R.id.txtTerms:
                setFragment(new TermsFragment() , getString(R.string.terms_and_conditions));
                break;
            case R.id.txtLogout:
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
