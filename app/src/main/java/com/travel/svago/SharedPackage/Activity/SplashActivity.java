package com.travel.svago.SharedPackage.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.travel.svago.AuthPackage.LoginPackage.LoginActivity;
import com.travel.svago.Models.LoginResponses.AuthResponse;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.LanguageType;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.Rel)
    RelativeLayout Rel;
    private String type, language;
    private UserService_POST userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
        ReadSharedPreference();
        setLanguages();

    }

    private void initView() {
        Point point = getNavigationBarSize(this);
        Log.d("HH", point.y + " Oo");
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) Rel.getLayoutParams();
        params.setMargins(0, 0, 0, point.y);

    }

    private void setLanguages() {
        LanguageType languageType = new LanguageType();
        languageType.languageType = type;
        Configuration config = new Configuration();
        config.locale = new Locale(language);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private void ReadSharedPreference() {
        SharedPreferences prefs = getSharedPreferences(getApplication().getPackageName(), MODE_PRIVATE);
        type = prefs.getString("type", "english");
        language = prefs.getString("language", "en");
        boolean isLogin = prefs.getBoolean("isLogin", false);

        if (isLogin) {
            getUser(prefs.getString("Token", ""));
        } else {
            loading();
        }
    }

    private void getUser(final String token) {
        userService = ApiUtlis.getUserServices_Post();
        Call<AuthResponse> call = userService.getUser("Bearer " + token);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        response.body().getData().setToken(token);
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        intent.putExtra(Constant.userFlag, response.body().getData());
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        Toast.makeText(SplashActivity.this, response.body().getErrors().get(0), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("OOO", response.message());
                    Toast.makeText(SplashActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                //Log.d("OOO" , t.getMessage());
                Toast.makeText(SplashActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loading() {
        new CountDownTimer(3500, 2000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                userData userData = new userData();
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                intent.putExtra(Constant.userFlag, userData);
                startActivity(intent);
                finish();

            }
        }.start();

    }

    public static Point getNavigationBarSize(Context context) {
        Point appUsableSize = getAppUsableScreenSize(context);
        Point realScreenSize = getRealScreenSize(context);

        // navigation bar on the side
        if (appUsableSize.x < realScreenSize.x) {
            return new Point(realScreenSize.x - appUsableSize.x, appUsableSize.y);
        }

        // navigation bar at the bottom
        if (appUsableSize.y < realScreenSize.y) {
            return new Point(appUsableSize.x, realScreenSize.y - appUsableSize.y);
        }

        // navigation bar is not present
        return new Point();
    }

    public static Point getAppUsableScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static Point getRealScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            }
        }

        return size;
    }


}
