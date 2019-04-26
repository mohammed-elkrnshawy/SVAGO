package com.example.svago.SidePackage.MorePackage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.svago.AuthPackage.LoginPackage.LoginActivity;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;
import com.example.svago.SidePackage.AboutUsPackage.AboutUsFragment;
import com.example.svago.SidePackage.ContactUsPackage.ContactUsFragment;
import com.example.svago.SidePackage.CurrenciesPackage.CurrenciesFragment;
import com.example.svago.SidePackage.LanguagePackage.LanguageFragment;
import com.example.svago.SidePackage.OrdersPackage.AllOrdersPackage.OrdersFragment;
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
        view= inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this,view);
        morePresenter = new MorePresenter(getActivity()) ;
        userObject=morePresenter.getIntentData(this);
        return view;
    }




    @OnClick({R.id.txtProfile, R.id.txtContact, R.id.txtLanguage, R.id.txtAbout, R.id.txtTerms, R.id.txtLogout , R.id.txtCurrency , R.id.txtOrders})
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
            case R.id.txtCurrency:
                setFragment(new CurrenciesFragment() , getString(R.string.currencies));
                break;
            case R.id.txtOrders:
                setFragment(new OrdersFragment() , getString(R.string.currencies));
                break;
            case R.id.txtLogout:
                morePresenter.SharedPreferencesPut("");
                startActivity(new Intent(getActivity() , LoginActivity.class));
                getActivity().finishAffinity();
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
