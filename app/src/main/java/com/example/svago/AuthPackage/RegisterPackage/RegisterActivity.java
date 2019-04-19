package com.example.svago.AuthPackage.RegisterPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.svago.AuthPackage.LoginPackage.LoginActivity;
import com.example.svago.Models.CountriesResponses.CountyData;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Activity.HomeActivity;
import com.example.svago.SharedPackage.Classes.Constant;
import com.example.svago.SharedPackage.Classes.CountryAdapterHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterInterface {

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtCountry)
    Spinner edtCountry;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.imgShow)
    ImageView imgShow;
    @BindView(R.id.cartRegister)
    CardView cartRegister;
    @BindView(R.id.txtLogin)
    TextView txtLogin;

    private CountryAdapterHelper adapterHelper;
    private RegisterPresenter registerPresenter;
    private CountyData countyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents() {
        registerPresenter=new RegisterPresenter(this);
        adapterHelper=new CountryAdapterHelper(this);
        adapterHelper.PrepareSpinner(edtCountry);
        SelectItems();
    }

    private void SelectItems() {
        edtCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countyData=(CountyData) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.cartRegister,R.id.txtLogin}) void onButtonClick(View view){
        switch (view.getId()){
            case R.id.cartRegister:
                registerPresenter.validData();
                break;
            case R.id.txtLogin:
                finish();
                break;
        }
    }

    @Override
    public void validDate() {
        if(TextUtils.isEmpty(edtName.getText())){
            edtName.setError(getResources().getString(R.string.requiredField));
            edtName.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(edtPhone.getText())){
            edtPhone.setError(getResources().getString(R.string.requiredField));
            edtPhone.requestFocus();
            return;
        }

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

        userData userData=new userData();
        userData.setEmail(edtEmail.getText().toString().trim());
        userData.setUsername(edtName.getText().toString().trim());
        userData.setPassword(edtPassword.getText().toString().trim());
        userData.setCountry(countyData.getTitle());
        userData.setPhone(edtPhone.getText().toString().trim());
        userData.setCountryID(countyData.getId());
        registerPresenter.callRegister(userData);
    }

    @Override
    public void successRegister(userData userData) {
        Intent intent=new Intent(this, LoginActivity.class);
        intent.putExtra(Constant.userFlag,userData);
        startActivity(intent);
        finishAffinity();
    }
}
