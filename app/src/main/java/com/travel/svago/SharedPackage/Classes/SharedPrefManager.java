package com.travel.svago.SharedPackage.Classes;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "SharedPref";


    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will save the device token to shared preferences
    public boolean saveValue(String TAG , String value){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG, value);
        editor.apply();
        return true;
    }
    public static String ConvertNumber(String num)
    {
        return   num.replace("٠","0").replace("١","1").replace("٢","2")
                .replace("٣","3").replace("٤","4").replace("٥","5").
                        replace("٦","6").replace("٧","7").replace("٨","8").replace("٩","9").
                        replace("٫",".").replace(",","")
                ;
    }
    public  void setLanguage(String type,String language){
        SharedPreferences.Editor editor = mCtx.getSharedPreferences(mCtx.getPackageName(), MODE_PRIVATE).edit();
        editor.putString("type", type);
        editor.putString("language", language);
        editor.apply();
    }

    //this method will fetch the device token from shared preferences
    public String getValue(String Tag , String def){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(Tag, def);
    }
}
