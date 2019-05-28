package com.travel.svago.SvagoPackage.TripPackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.travel.svago.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.svago.SharedPackage.Activity.OpenPictureActivity;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    List<String> imagesList ;
    Context context ;

    public ImagesAdapter(List<String> imagesList, Context context) {
        this.imagesList = imagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_images , viewGroup , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        //ImageLoader.getInstance().displayImage(imagesList.get(i) , viewHolder.imageView);
        ImageLoader.getInstance().loadImage(imagesList.get(i), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                viewHolder.imageView.setImageBitmap(loadedImage);
                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context , OpenPictureActivity.class);
                        intent.putExtra("imageURL" ,imagesList.get(i)) ;
                        context.startActivity(intent);
                    }
                });
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.image) ;
        }
    }
}
