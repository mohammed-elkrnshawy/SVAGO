package com.example.svago.SidePackage.ProfilePackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends AppCompatActivity implements EditProfileInterface {

    private userData userObject;
    private EditProfilePresenter profilePresenter;

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtCountry)
    EditText edtCountry;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.imgShow)
    ImageView imgShow;
    @BindView(R.id.cardSave)
    CardView cardSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        getIntentData();
        initComponents();
    }

    private void initComponents() {
        profilePresenter=new EditProfilePresenter(this);
    }

    private void getIntentData() {
        Bundle bundle=getIntent().getExtras();
        if (!bundle.isEmpty()){
            userObject=(userData) bundle.getSerializable(Constant.userFlag);
        }
    }

    @Override
    public void setData() {
        edtName.setText(userObject.getUsername());
        edtPassword.setText(userObject.getPassword());
        edtPhone.setText(userObject.getPhone());
        edtCountry.setText(userObject.getCountry());
    }

    @Override
    public void validData() {
        if(TextUtils.isEmpty(edtName.getText())){
            edtName.setError(getResources().getString(R.string.requiredField));
            edtName.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(edtCountry.getText().toString().trim())){
            edtCountry.setError(getResources().getString(R.string.requiredField));
            edtCountry.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(edtPhone.getText())){
            edtPhone.setError(getResources().getString(R.string.requiredField));
            edtPhone.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(edtPassword.getText())){
            edtPassword.setError(getResources().getString(R.string.requiredField));
            edtPassword.requestFocus();
            return;
        }

        userData userData=new userData();
        userData.setPhone(userObject.getToken());
        userData.setUsername(edtName.getText().toString().trim());
        userData.setPassword(edtPassword.getText().toString().trim());
        userData.setCountry(edtCountry.getText().toString().trim());
        userData.setPhone(edtPhone.getText().toString().trim());

        profilePresenter.callEditProfile(userData);
    }

    @OnClick({R.id.cardSave})void onClick(View view){
        switch (view.getId()){
            case R.id.cardSave:
                profilePresenter.validData();
                break;
        }
    }
}
