package com.example.svago.SvagoPackage.TripPackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.svago.R;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ImageLoader.getInstance().displayImage(imagesList.get(i) , viewHolder.imageView);
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
