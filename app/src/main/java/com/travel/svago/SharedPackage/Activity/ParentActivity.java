package com.travel.svago.SharedPackage.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.travel.svago.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MultipartBody;

public class ParentActivity extends AppCompatActivity {

    private Integer imageType;
    public String mCurrentPhotoPath, address;
    public Bitmap bitmap;
    private Bitmap[] bitmapArray;
    private File file;
    private MultipartBody.Part body;
    public int GALLERY = 3, CAMERA = 2;
    private static final int permessionConstant = 0;
    public Double lat, lon;
    public Button tryagain , cancel ;
    private BroadcastReceiver networkStateReceiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //region ConnectionCheck
    private boolean connection;
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        //registerReceiver(networkStateReceiver, intentFilter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //unregisterReceiver(networkStateReceiver);
    }

    /*public BroadcastReceiver netWorkBroadcast() {
        networkStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                connection = hasConnection(context);
                if (!connection)
                    openDialog();
            }
        };

        return networkStateReceiver ;
    }*/

/*
    private void openDialog(){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_connection);
        tryagain = dialog.findViewById(R.id.tryagain);
        cancel = dialog.findViewById(R.id.cancel);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!connection)
                    Toast.makeText( ParentActivity.this , R.string.check_your_network, Toast.LENGTH_SHORT).show();
                else {

                    dialog.dismiss();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        dialog.show();
    }
*/
    public boolean hasConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }



    //endregion


    //region PHOTO
    public void SelectPhotoDialog(final String auth , final int GALLERY) {
        final CharSequence[] items = {
                getResources().getString(R.string.takephot),
                getResources().getString(R.string.choosegallery),
                getResources().getString(R.string.cancel_dialog)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.add_photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getResources().getString(R.string.takephot))) {

                    int currentapiVersion = Build.VERSION.SDK_INT;
                    if (currentapiVersion >= Build.VERSION_CODES.M) {

                        int permissionCheck = ContextCompat.checkSelfPermission(ParentActivity.this,
                                Manifest.permission.CAMERA);


                        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                            ChooseImageCamera(auth);

                        } else {  //  PERMISSION_DENIED


                            ActivityCompat.requestPermissions(ParentActivity.this,
                                    new String[]{Manifest.permission.CAMERA}, permessionConstant);
                        }
                    } else {
                        ChooseImageCamera(auth);
                    }

                } else if (items[item].equals(getResources().getString(R.string.choosegallery))) {
                    int currentapiVersion = Build.VERSION.SDK_INT;
                    if (currentapiVersion >= Build.VERSION_CODES.M) {

                        int permissionCheck = ContextCompat.checkSelfPermission(ParentActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE);


                        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                            ChooseImageGallery(GALLERY);


                        } else {  //  PERMISSION_DENIED
                            ActivityCompat.requestPermissions(ParentActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, permessionConstant);
                        }

                    } else {
                        ChooseImageGallery(GALLERY);
                    }

                } else if (items[item].equals(getResources().getString(R.string.cancel_dialog))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    private void ChooseImageGallery(int GALLERY) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.choose_photo)), GALLERY);
    }

    private void ChooseImageCamera(String auth) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        auth,
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA);
            }
        }
    }

    //private String

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d("PO" , mCurrentPhotoPath);
        return image;
    }

    public String getImagePathFromInputStreamUri(Uri uri) {
        InputStream inputStream = null;
        String filePath = null;

        if (uri.getAuthority() != null) {
            try {
                inputStream = getContentResolver().openInputStream(uri); // context needed
                File photoFile = createTemporalFileFrom(inputStream);

                filePath = photoFile.getPath();

            } catch (FileNotFoundException e) {
                // log
            } catch (IOException e) {
                // log
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filePath;
    }

    private File createTemporalFileFrom(InputStream inputStream) throws IOException {
        File targetFile = null;

        if (inputStream != null) {
            int read;
            byte[] buffer = new byte[8 * 1024];

            targetFile = createTemporalFile();
            OutputStream outputStream = new FileOutputStream(targetFile);

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();

            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return targetFile;
    }

    private File createTemporalFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        return new File(getExternalCacheDir(), imageFileName+".jpg"); // context needed
    }

    public Bitmap fixRotate(String photoPath , Bitmap bitmap1) throws IOException {
        ExifInterface ei = new ExifInterface(photoPath);
        Bitmap bitmap = bitmap1;
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                bitmap = rotateImage(90 , bitmap1);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                bitmap = rotateImage(180 , bitmap1);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                bitmap = rotateImage(270 , bitmap1);
                break;
            case ExifInterface.ORIENTATION_NORMAL:
                bitmap = bitmap1 ;
                break;
        }
        return bitmap ;
    }

    private Bitmap rotateImage(float angle , Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }

        if (requestCode == 1 && resultCode == RESULT_OK) {
            lat = data.getDoubleExtra("lat", 0);
            lon = data.getDoubleExtra("lng", 0);
            address = data.getStringExtra("address");
        }

        if (requestCode == 3) {
            if (data != null) {
                Uri contentURI = data.getData();
                mCurrentPhotoPath = getImagePathFromInputStreamUri(contentURI);
                //setImage();
            }

        } else if (requestCode == CAMERA) {
            //setImage();
        }
    }



    /*public void setImage(ImageView image , Bitmap bitmap , String photoPath) {
        bitmap = BitmapFactory.decodeFile(photoPath, new BitmapFactory.Options());
        try {
            bitmap = fixRotate(photoPath , bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(bitmap);
       // fillImageViews(bitmap);
    }
 */
    public void setImage(ImageView image  , String photoPath) {
        bitmap = BitmapFactory.decodeFile(photoPath, new BitmapFactory.Options());
        try {
            bitmap = fixRotate(photoPath , bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(bitmap);
        // fillImageViews(bitmap);
    }

    public void setImage( String photoPath) {
        bitmap = BitmapFactory.decodeFile(photoPath, new BitmapFactory.Options());
        try {
            bitmap = fixRotate(photoPath , bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // fillImageViews(bitmap);
    }



    private void checkComing(int requestCode, int resultCode, Intent data){
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }

        if (requestCode == 1 && resultCode == RESULT_OK) {
            lat = data.getDoubleExtra("lat", 0);
            lon = data.getDoubleExtra("lng", 0);
            address = data.getStringExtra("address");
        }

        if (requestCode == 2) {
            if (data != null) {
                Uri contentURI = data.getData();
                mCurrentPhotoPath = getImagePathFromInputStreamUri(contentURI);
                //setImage();
            }

        } else if (requestCode == CAMERA) {
            //setImage();
        }
    }

    public void setImage() {
        try {
           /* if (type == GALLERY) {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                mCurrentPhotoPath = getImagePathFromInputStreamUri(contentURI);
            } else*/
            bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, new BitmapFactory.Options());
            fixRotate(mCurrentPhotoPath , bitmap);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SelectPhotoFromCameraDialog(final String auth) {

        final CharSequence[] items = {
                getResources().getString(R.string.takephot),
                getResources().getString(R.string.cancel_dialog)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.add_photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getResources().getString(R.string.takephot))) {

                    int currentapiVersion = Build.VERSION.SDK_INT;
                    if (currentapiVersion >= Build.VERSION_CODES.M) {

                        int permissionCheck = ContextCompat.checkSelfPermission(ParentActivity.this,
                                Manifest.permission.CAMERA);


                        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                            ChooseImageCamera(auth);

                        } else {  //  PERMISSION_DENIED


                            ActivityCompat.requestPermissions(ParentActivity.this,
                                    new String[]{Manifest.permission.CAMERA}, permessionConstant);
                        }
                    } else {

                        ChooseImageCamera(auth);
                    }

                } else if (items[item].equals(getResources().getString(R.string.cancel_dialog))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    //endregion


    public  void setAnimate(final Context context, final ViewGroup Container, final ImageView DownArrow, final ViewGroup parent, final int height, float rotation) {
        if (Container.getVisibility() == View.GONE) {
            DownArrow.animate().rotation(rotation).setDuration(300).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    DownArrow.setEnabled(false);
                    Container.setVisibility(View.VISIBLE);
                    int px = Math.round(TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, height, context.getResources().getDisplayMetrics()));
                    ValueAnimator anim = ValueAnimator.ofInt(parent.getMeasuredHeight(), parent.getMeasuredHeight() + px);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();
                            layoutParams.height = val;
                            parent.setLayoutParams(layoutParams);
                        }
                    });
                    anim.setDuration(300);
                    anim.start();

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    DownArrow.setEnabled(true);
                }
            });
        } else {
            DownArrow.animate().rotation(0).setDuration(300).setListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    DownArrow.setEnabled(false);
                    int px = Math.round(TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, height, context.getResources().getDisplayMetrics()));
                    ValueAnimator anim = ValueAnimator.ofInt(parent.getMeasuredHeight(), parent.getMeasuredHeight() - px);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();
                            layoutParams.height = val;
                            parent.setLayoutParams(layoutParams);
                        }
                    });
                    anim.setDuration(300);
                    anim.start();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Container.setVisibility(View.GONE);
                    DownArrow.setEnabled(true);
                }
            });
        }
    }


    public void setImageLoaderConfig(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public String getCurrentTime(boolean date , String format ){
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat Format = new SimpleDateFormat(format); // yyyy
        return Format.format(currentTime) ;
    }

}
