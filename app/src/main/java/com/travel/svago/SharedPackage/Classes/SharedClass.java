package com.travel.svago.SharedPackage.Classes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.travel.svago.AuthPackage.LoginPackage.LoginActivity;
import com.travel.svago.R;

import hari.bounceview.BounceView;

import static android.content.Context.MODE_PRIVATE;

public class SharedClass {
    private static Dialog progressDialog ;
    public static String Currency="";
    public static int CurrencyID ;
    public static boolean isLogin ;

    public static String getLocalization(Context context)
    {
        return context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).getString("language","ar") ;
    }

    public static void ShowWaiting(Context context) {
        progressDialog = new Dialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.view_waiting);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public static void  hideWaiting(){
        progressDialog.dismiss();
    }

    public   static void setDialog(final Context context , final String tag){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_login , null) ;

        Button Login = view.findViewById(R.id.login) ;
        Button Cancel = view.findViewById(R.id.cancel) ;


        dialogBuilder.setCancelable(true);
        dialogBuilder.setView(view) ;


        final AlertDialog dialog = dialogBuilder.create();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        BounceView.addAnimTo(dialog);

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , LoginActivity.class) ;
                intent.putExtra("tag" , tag) ;
                context.startActivity(intent);
                ((Activity)context).finishAffinity();
            }
        });
        dialog.show() ;
    }

}
