package com.example.svago.SidePackage.ContactUsPackage;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.svago.Models.ResponseSimple.ResponseSimple;
import com.example.svago.R;
import com.example.svago.Remote.ApiUtlis;
import com.example.svago.Remote.UserService_POST;
import com.example.svago.SharedPackage.Classes.SharedClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsPresenter implements ContactUsViewPresenter{
    UserService_POST userService ; 
    Context context ;

    public ContactUsPresenter(Context context) {
        this.context = context;
        userService = ApiUtlis.getUserServices_Post() ; 
    }

    @Override
    public void callContactUs(String name, String email, String message, final EditText editText , final EditText editText1 , final EditText editText2, final ViewGroup viewGroup) {
        SharedClass.ShowWaiting(context);
        Call<ResponseSimple> call = userService.contactUs(name , email ,message);
        call.enqueue(new Callback<ResponseSimple>() {
            @Override
            public void onResponse(Call<ResponseSimple> call, Response<ResponseSimple> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus()==200){
                        editText.setText("");
                        editText1.setText("");
                        editText2.setText("");
                        snackBar(context.getString(R.string.sent_success) , viewGroup );
                    }else {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show(); 
                    }
                }else {
                    Toast.makeText(context, response.message() , Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseSimple> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(context, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void validate(EditText edtName , EditText edtEmail , EditText edtMessage, ViewGroup viewGroup) {
        String name = edtName.getText().toString().trim() ;
        String email = edtEmail.getText().toString().trim() ;
        String message = edtMessage.getText().toString().trim() ;
        if (TextUtils.isEmpty(name)){
            edtName.setError(context.getResources().getString(R.string.requiredField));
            edtName.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText()).matches()){
            edtEmail.setError(context.getResources().getString(R.string.requiredField));
            edtEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(message)){
            edtMessage.setError(context.getResources().getString(R.string.requiredField));
            edtMessage.requestFocus();
            return;
        }

        callContactUs(name , email , message ,edtName , edtEmail , edtMessage , viewGroup);
    }

    @Override
    public void snackBar(String s, ViewGroup viewGroup) {
        Snackbar snack = Snackbar.make(viewGroup, s , Snackbar.LENGTH_LONG);
        View view = snack.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        view.setBackgroundColor(Color.parseColor("#FF85EF77"));
        snack.show();
    }
}
