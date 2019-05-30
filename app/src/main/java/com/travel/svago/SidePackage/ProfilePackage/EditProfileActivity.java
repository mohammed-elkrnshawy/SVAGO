package com.travel.svago.SidePackage.ProfilePackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.travel.svago.Models.CountriesResponses.CountyData;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Activity.ParentActivity;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.CountryAdapterHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProfileActivity extends ParentActivity implements EditProfileInterface {

    @BindView(R.id.userImage)
    CircleImageView userImage;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtCountry)
    AppCompatSpinner edtCountry;
    @BindView(R.id.edtCode)
    EditText edtCode;
    private userData userObject;
    private EditProfilePresenter profilePresenter;

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.imgShow)
    ImageView imgShow;
    @BindView(R.id.cardSave)
    CardView cardSave;

    private CountryAdapterHelper adapterHelper  ;
    private CountyData countyData;
    private boolean isHide = true , isUpdate = false;
    File file;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        getIntentData();
        initComponents();
    }

    private void initComponents() {
        profilePresenter = new EditProfilePresenter(this ,  EditProfileActivity.this);
        adapterHelper = new CountryAdapterHelper(this);
        adapterHelper.PrepareSpinner(edtCountry);
        SelectItems();
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()) {
            userObject = (userData) bundle.getSerializable(Constant.userFlag);
            if (userObject.isSocial()){
                edtEmail.setEnabled(false);
                edtName.setEnabled(false);
            }
        }
    }

    @Override
    public void setData() {
        Picasso.with(this).load(userObject.getPicture()).into(userImage);
        edtName.setText(userObject.getUsername());
        edtEmail.setText(userObject.getEmail());
        edtPassword.setText(userObject.getPassword());
        edtPhone.setText(userObject.getPhone());
        edtCode.setText(userObject.getCode());

    }

    @Override
    public void validData() {
        if (TextUtils.isEmpty(edtName.getText())) {
            edtName.setError(getResources().getString(R.string.requiredField));
            edtName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(edtEmail.getText())) {
            edtEmail.setError(getResources().getString(R.string.requiredField));
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(countyData.getTitle())) {
            Toast.makeText(this, getResources().getString(R.string.Choose_country), Toast.LENGTH_SHORT).show();
            edtCountry.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(edtCode.getText())) {
            edtCode.setError(getResources().getString(R.string.requiredField));
            edtCode.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(edtPhone.getText())) {
            edtPhone.setError(getResources().getString(R.string.requiredField));
            edtPhone.requestFocus();
            return;
        }

       /* if (TextUtils.isEmpty(edtPassword.getText())) {
            edtPassword.setError(getResources().getString(R.string.requiredField));
            edtPassword.requestFocus();
            return;
        }*/

        userData userData = new userData();
        userData.setPhone(userObject.getToken());
        userData.setUsername(edtName.getText().toString().trim());
        userData.setPassword(edtPassword.getText().toString().trim());
        userData.setCountryID(countyData.getId());
        userData.setCode(edtCode.getText().toString().trim());
        userData.setPhone(edtPhone.getText().toString().trim());

        if (isUpdate)
            profilePresenter.callEditProfile(userData , persistImageLicense(bitmap , "picture"));
        else
            profilePresenter.callEditProfile(userData);
    }

    private void SelectItems() {
        edtCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countyData = (CountyData) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick({R.id.userImage, R.id.cardSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userImage:
                isUpdate=true;
                SelectPhotoDialog("com.travel.svago.fileprovider" , 3);
                break;
            case R.id.cardSave:
                profilePresenter.validData();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }

        if (requestCode == CAMERA) {

            setImage( userImage , mCurrentPhotoPath);

        }else if (requestCode == GALLERY){
            Uri contentURI = data.getData();
            mCurrentPhotoPath = getImagePathFromInputStreamUri(contentURI);
            setImage( userImage , mCurrentPhotoPath);
        }

    }

    private MultipartBody.Part persistImageLicense(Bitmap mBitmap, String name) {
        File filesDir = getApplicationContext().getFilesDir();
        file = new File(filesDir, name + ".jpg");
        OutputStream os;
        try {

            os = new FileOutputStream(file);
            Log.i("sadsad", mBitmap.getByteCount() + "");

            mBitmap.compress(Bitmap.CompressFormat.JPEG, 30, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "select photo", Toast.LENGTH_SHORT).show();
            Log.e("Error writing bitmap", e.getMessage());
        }
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData(name, file.getName(), requestFile);
        return body;
    }

    @Override
    public void setImage(ImageView image, String photoPath) {
        super.setImage(image, photoPath);
    }

}
