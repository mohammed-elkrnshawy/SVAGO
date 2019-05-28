package com.travel.svago.AuthPackage.LoginPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.svago.AuthPackage.ForgetPasswordActivity;
import com.travel.svago.AuthPackage.RegisterPackage.RegisterActivity;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Activity.HomeActivity;
import com.travel.svago.SharedPackage.Activity.MainHomeActivity;
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
    @BindView(R.id.skip)
    TextView skip;

    String tag = "";

    private LoginPresenter loginPresenter;
    private boolean isHide = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getData();
        initComponents();
    }

    private void initComponents() {
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tag = bundle.getString("tag", "");
            Log.d("OOO", tag);
        }
    }

    @OnClick({R.id.cartLogin, R.id.txtRegister, R.id.skip, R.id.imgShow})
    void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.cartLogin:
                loginPresenter.validData();
                break;
            case R.id.txtRegister:
                loginPresenter.openRegister();
                break;
            case R.id.skip:
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra(Constant.userFlag, new userData());
                startActivity(intent);
                finish();
                break;
            case R.id.imgShow:
                showAndHidePassword();
                break;
        }
    }

    @Override
    public void validDate() {
        if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText()).matches()) {
            edtEmail.setError(getResources().getString(R.string.requiredField));
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(edtPassword.getText())) {
            edtPassword.setError(getResources().getString(R.string.requiredField));
            edtPassword.requestFocus();
            return;
        }

        loginPresenter.callLogon(edtEmail.getText().toString().trim()
                , edtPassword.getText().toString().trim()
        );
    }

    @Override
    public void openRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void successLogin(userData userData) {
        if (tag.equals(Constant.GuideTag)) {
            Intent intent = new Intent(this, MainHomeActivity.class);
            intent.putExtra(Constant.userFlag, userData);
            intent.putExtra(Constant.TypeTag, Constant.GuideTag);
            HomeActivity.setData(userData);
            startActivity(intent);
            finishAffinity();
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(Constant.userFlag, userData);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    public void showAndHidePassword() {
        if (isHide) {
            isHide = false;
            imgShow.setImageResource(R.drawable.ic_view);
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_NORMAL);
            edtPassword.setSelection(edtPassword.getText().length());
        } else {
            isHide = true;
            imgShow.setImageResource(R.drawable.ic_hide);
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            edtPassword.setSelection(edtPassword.getText().length());
        }
    }

    @OnClick(R.id.forget)
    public void onViewClicked() {
        Intent intent = new Intent(this , ForgetPasswordActivity.class) ;
        startActivity(intent);
    }
}
