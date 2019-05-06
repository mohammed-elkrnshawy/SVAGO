package com.travel.svago.SidePackage.ContactUsPackage;

import android.view.ViewGroup;
import android.widget.EditText;

public interface ContactUsViewPresenter {
    void validate(EditText edtName , EditText edtEmail , EditText edtMessage, ViewGroup viewGroup);
    void callContactUs(String name , String email , String message,EditText editText , EditText editText1 ,EditText editText2, ViewGroup viewGroup);
    void snackBar(String s , ViewGroup viewGroup);
}
