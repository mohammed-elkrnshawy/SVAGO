package com.travel.svago.SidePackage.MorePackage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.travel.svago.AuthPackage.LoginPackage.LoginActivity;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Activity.MainHomeActivity;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SidePackage.AboutUsPackage.AboutUsFragment;
import com.travel.svago.SidePackage.ContactUsPackage.ContactUsFragment;
import com.travel.svago.SidePackage.CurrenciesPackage.CurrenciesFragment;
import com.travel.svago.SidePackage.LanguagePackage.LanguageFragment;
import com.travel.svago.SidePackage.OrdersPackage.AllOrdersPackage.OrdersFragment;
import com.travel.svago.SidePackage.ProfilePackage.ProfileFragment;
import com.travel.svago.SidePackage.TermsPackage.TermsFragment;
import com.travel.svago.SidePackage.VipTripsPackage.VipTripsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    @BindView(R.id.txtOrders)
    TextView txtOrders;
    @BindView(R.id.txtLogout)
    TextView txtLogout;
    @BindView(R.id.txtLogin)
    TextView txtLogin;
    private View view;
    private userData userObject;
    private Bundle bundleExtra = new Bundle();
    MorePresenter morePresenter;

    @BindView(R.id.txtProfile)
    TextView txtProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        morePresenter = new MorePresenter(getActivity(), this);
        morePresenter.initView();
        userObject = morePresenter.getIntentData(this);
        return view;
    }


    @OnClick({R.id.txtProfile, R.id.txtVip,R.id.txtContact, R.id.txtLanguage, R.id.txtAbout, R.id.txtTerms, R.id.txtLogout, R.id.txtCurrency, R.id.txtOrders, R.id.txtLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtProfile:
                setFragment(new ProfileFragment(), getString(R.string.my_profile));
                break;
            case R.id.txtContact:
                setFragment(new ContactUsFragment(), getString(R.string.contact_us));
                break;
            case R.id.txtLanguage:
                setFragment(new LanguageFragment(), getString(R.string.language));
                break;
            case R.id.txtAbout:
                setFragment(new AboutUsFragment(), getString(R.string.about));
                break;
            case R.id.txtTerms:
                setFragment(new TermsFragment(), getString(R.string.terms_and_conditions));
                break;
            case R.id.txtCurrency:
                setFragment(new CurrenciesFragment(), getString(R.string.currencies));
                break;
            case R.id.txtOrders:
                setFragment(new OrdersFragment(), getString(R.string.currencies));
                break;
            case R.id.txtVip:
                setFragment(new VipTripsFragment(), getString(R.string.currencies));
                break;
            case R.id.txtLogout:
                LoginManager.getInstance().logOut();
                morePresenter.SharedPreferencesPut("");
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finishAffinity();
                break;
            case R.id.txtLogin:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finishAffinity();
                break;
        }
    }

    private void setFragment(Fragment fragment, String Title) {
        bundleExtra.putSerializable(Constant.userFlag, userObject);
        fragment.setArguments(bundleExtra);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conainerHome, fragment).addToBackStack(Title)
                .commitAllowingStateLoss();
    }


    @Override
    public void onResume() {
        super.onResume();
        MainHomeActivity.setFragmentWithType(Constant.MoreTag);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack();
                    return true;
                }
                return false;
            }
        });
    }


}
