package com.travel.svago.AuthPackage.LoginPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.svago.AuthPackage.RegisterPackage.RegisterActivity;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Activity.HomeActivity;
import com.travel.svago.SharedPackage.Classes.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginInterface {


    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.imgShow)
    ImageView imgShow;
    @BindView(R.id.cartLogin)
    CardView cartLogin;
    @BindView(R.id.txtRegister)
    TextView txtRegister;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents() {
        loginPresenter=new LoginPresenter(this);
    }

    @OnClick({R.id.cartLogin,R.id.txtRegister}) void onButtonClick(View view){
        switch (view.getId()){
            case R.id.cartLogin:
                loginPresenter.validData();
                break;
            case R.id.txtRegister:
                loginPresenter.openRegister();
                break;
        }
    }

    @Override
    public void validDate() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText()).matches()){
            edtEmail.setError(getResources().getString(R.string.requiredField));
            edtEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(edtPassword.getText())){
            edtPassword.setError(getResources().getString(R.string.requiredField));
            edtPassword.requestFocus();
            return;
        }

        loginPresenter.callLogon(edtEmail.getText().toString().trim()
                ,edtPassword.getText().toString().trim()
        );
    }

    @Override
    public void openRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void successLogin(userData userData) {
        Intent intent=new Intent(this, HomeActivity.class);
        intent.putExtra(Constant.userFlag,userData);
        startActivity(intent);
        finishAffinity();
    }
}
