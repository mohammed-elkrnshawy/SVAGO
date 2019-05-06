package com.travel.svago.SidePackage.ContactUsPackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.travel.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {


    @BindView(R.id.EdtName)
    EditText EdtName;
    @BindView(R.id.EdtEmail)
    EditText EdtEmail;
    @BindView(R.id.EdtMessage)
    EditText EdtMessage;
    @BindView(R.id.send)
    Button send;
    Unbinder unbinder;
    ContactUsPresenter mContactUsPresenter;
    @BindView(R.id.rel)
    RelativeLayout rel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContactUsPresenter = new ContactUsPresenter(getActivity());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.send)
    public void onViewClicked() {
        mContactUsPresenter.validate(EdtName, EdtEmail , EdtMessage , rel);
    }
}
