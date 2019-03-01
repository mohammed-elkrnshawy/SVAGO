package com.example.svago;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(3500, 2000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                Intent intent = new Intent(SplashActivity.this , HomeActivity.class) ;
                startActivity(intent);
                finish();

            }
        }.start();

    }
}
