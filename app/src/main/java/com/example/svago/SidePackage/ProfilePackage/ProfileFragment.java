package com.example.svago.SidePackage.ProfilePackage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Activity.MainHomeActivity;
import com.example.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private View view;
    private userData userObject;

    @BindView(R.id.imgEdit)
    ImageView imgEdit;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);
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

    @OnClick({R.id.imgEdit}) void onClick(View view){
        switch (view.getId()){
            case R.id.imgEdit:
                Intent intentEdit=new Intent(getContext(), EditProfileActivity.class);
                intentEdit.putExtra(Constant.userFlag,userObject);
                startActivityForResult(intentEdit, Constant.editProfile);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Constant.editProfile&&resultCode== Activity.RESULT_OK){
            MainHomeActivity.updateUserData((userData) data.getSerializableExtra(Constant.userFlag));
        }
    }
}
