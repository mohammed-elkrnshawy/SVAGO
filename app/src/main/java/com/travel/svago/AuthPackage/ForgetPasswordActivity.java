package com.travel.svago.AuthPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.travel.svago.Models.SimpleResponse.SimpleResponse;
import com.travel.svago.R;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Classes.SharedClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.send)
    Button send;

    private UserService_POST userService ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        userService = ApiUtlis.getUserServices_Post() ;
    }

    @OnClick(R.id.send)
    public void onViewClicked() {
        if (TextUtils.isEmpty(edtEmail.getText().toString().trim())){
            edtEmail.setError(getResources().getString(R.string.required_fielddd));
            return;
        }
        callReset(edtEmail.getText().toString().trim());
    }

    private void callReset(String email){
        SharedClass.ShowWaiting(this);
        Call<SimpleResponse> call = userService.resetPass(email);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus() == 200){
                        Toast.makeText(ForgetPasswordActivity.this, getResources().getString(R.string.check_your_email_inbox), Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(ForgetPasswordActivity.this, getResources().getString(R.string.error)+response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ForgetPasswordActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(ForgetPasswordActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

}
