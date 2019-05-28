package com.travel.svago.AuthPackage.RegisterPackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.svago.AuthPackage.LoginPackage.LoginActivity;
import com.travel.svago.Models.CountriesResponses.CountyData;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Activity.HomeActivity;
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

public class RegisterActivity extends ParentActivity implements RegisterInterface {

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtCountry)
    Spinner edtCountry;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.imgShow)
    ImageView imgShow;
    @BindView(R.id.cartRegister)
    CardView cartRegister;
    @BindView(R.id.txtLogin)
    TextView txtLogin;
    @BindView(R.id.edtLastName)
    EditText edtLastName;
    @BindView(R.id.edtCode)
    EditText edtCode;
    @BindView(R.id.edtCPassword)
    EditText edtCPassword;
    @BindView(R.id.imgShow2)
    ImageView imgShow2;
    @BindView(R.id.userImage)
    CircleImageView userImage;

    private CountryAdapterHelper adapterHelper;
    private RegisterPresenter registerPresenter;
    private CountyData countyData;
    private boolean isHide = true;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents() {
        registerPresenter = new RegisterPresenter(this);
        adapterHelper = new CountryAdapterHelper(this);
        adapterHelper.PrepareSpinner(edtCountry);
        SelectItems();
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

    @OnClick({R.id.cartRegister, R.id.txtLogin, R.id.imgShow})
    void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.cartRegister:
                registerPresenter.validData();
                break;
            case R.id.txtLogin:
                finish();
                break;
            case R.id.imgShow:
                showAndHidePassword(edtPassword, imgShow);
                break;
        }
    }

    @Override
    public void showAndHidePassword(EditText editText, ImageView imageView) {
        if (isHide) {
            isHide = false;
            imageView.setImageResource(R.drawable.ic_view);
            editText.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_NORMAL);
            editText.setSelection(editText.getText().length());
        } else {
            isHide = true;
            imageView.setImageResource(R.drawable.ic_hide);
            editText.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editText.setSelection(editText.getText().length());
        }
    }

    @Override
    public void validDate() {

        if (bitmap == null) {
            Toast.makeText(this, getString(R.string.select_photo), Toast.LENGTH_SHORT).show();
            userImage.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(edtName.getText())) {
            edtName.setError(getResources().getString(R.string.requiredField));
            edtName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(edtLastName.getText())) {
            edtLastName.setError(getResources().getString(R.string.requiredField));
            edtLastName.requestFocus();
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

        if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText()).matches()) {
            edtEmail.setError(getResources().getString(R.string.requiredField));
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(edtPassword.getText())) {
            edtPassword.setError(getResources().getString(R.string.requiredField));
            edtPassword.requestFocus();
            return;
        }

        if (edtPassword.getText().toString().length() < 8) {
            edtPassword.setError(getResources().getString(R.string.password_must_be));
            edtPassword.requestFocus();
            return;
        }

        if (!edtPassword.getText().toString().trim().equals(edtCPassword.getText().toString().trim())) {
            edtCPassword.setError(getResources().getString(R.string.not_equal));
            edtCPassword.requestFocus();
            return;
        }

        userData userData = new userData();
        userData.setEmail(edtEmail.getText().toString().trim());
        userData.setFirstName(edtName.getText().toString().trim());
        userData.setLastName(edtLastName.getText().toString().trim());
        userData.setPassword(edtPassword.getText().toString().trim());
        userData.setCountry(countyData.getTitle());
        userData.setPhone(edtPhone.getText().toString().trim());
        userData.setCode(edtCode.getText().toString().trim());
        userData.setCountryID(countyData.getId());
        registerPresenter.callRegister(userData , persistImageLicense(bitmap , "picture"));
    }

    @Override
    public void successRegister(userData userData) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constant.userFlag, userData);
        startActivity(intent);
        finishAffinity();
    }


    @Override
    public void SelectPhotoDialog(String auth, int GALLERY) {
        super.SelectPhotoDialog(auth, GALLERY);
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


    @OnClick({R.id.userImage, R.id.imgShow2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userImage:
                SelectPhotoDialog("com.travel.svago.fileprovider" , 3);
                break;
            case R.id.imgShow2:
                showAndHidePassword(edtCPassword, imgShow2);
                break;
        }
    }
}
