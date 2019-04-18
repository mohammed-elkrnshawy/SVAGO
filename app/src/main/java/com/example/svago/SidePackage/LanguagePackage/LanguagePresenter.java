package com.example.svago.SidePackage.LanguagePackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import com.example.svago.SharedPackage.Activity.SplashActivity;
import com.example.svago.SharedPackage.Classes.LanguageType;
import com.example.svago.SharedPackage.Classes.SharedPrefManager;

import java.util.Locale;

public class LanguagePresenter implements LanguageViewPresenter {
    Context context ;

    public LanguagePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void chooseLanguage(String type , String language) {
        LanguageType languageType=new LanguageType();
        languageType.languageType = type;
        Configuration config = new Configuration();
        config.locale = new Locale(language);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        SharedPrefManager.getInstance(context).setLanguage(type , language);
        context.startActivity(new Intent(context, SplashActivity.class));
        ((Activity)context).finish();
    }
}
