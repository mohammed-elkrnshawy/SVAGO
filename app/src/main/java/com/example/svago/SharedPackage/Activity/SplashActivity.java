package com.example.svago.SharedPackage.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.svago.AuthPackage.LoginPackage.LoginActivity;
import com.example.svago.Models.LoginResponses.AuthResponse;
import com.example.svago.R;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SharedPackage.Classes.Constant;
import com.example.svago.SharedPackage.Classes.LanguageType;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    private String type,language;
    private UserService_POST userService ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash);
        ReadSharedPreference();
        setLanguages();

    }

    private void setLanguages() {
        LanguageType languageType=new LanguageType();
        languageType.languageType = type;
        Configuration config = new Configuration();
        config.locale = new Locale(language);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private void ReadSharedPreference() {
        SharedPreferences prefs = getSharedPreferences(getApplication().getPackageName(), MODE_PRIVATE);
        type=prefs.getString("type","english");
        language=prefs.getString("language","en");
        boolean isLogin = prefs.getBoolean("isLogin",false);

        if (isLogin)
        {
            getUser(prefs.getString("Token",""));
        }
        else {
            loading();
        }
    }

    private void getUser(final String token) {
        userService= ApiUtlis.getUserServices_Post();
        Call<AuthResponse> call = userService.getUser("Bearer "+token);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        response.body().getData().setToken(token);
                        Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                        intent.putExtra(Constant.userFlag,response.body().getData());
                        startActivity(intent);
                        finishAffinity();
                    }else {
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }
                }else {
                    Toast.makeText(SplashActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(SplashActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loading() {
        new CountDownTimer(3500, 2000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                Intent intent = new Intent(SplashActivity.this , LoginActivity.class) ;
                startActivity(intent);
                finish();

            }
        }.start();

    }

}
