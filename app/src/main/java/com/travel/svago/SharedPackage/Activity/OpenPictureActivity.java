package com.travel.svago.SharedPackage.Activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.travel.svago.R;

public class OpenPictureActivity extends AppCompatActivity {

    private String imageUrl ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_picture);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_open_picture);
        getData();
        final ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnTouchListener(new ImageMatrixTouchHandler(this));
        ImageLoader.getInstance().loadImage(imageUrl,new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                imageView.setImageBitmap(loadedImage);
            }
        });

    }
    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()) {
            imageUrl = bundle.getString("imageURL");
        }
    }

}
