package com.example.svago.SvagoPackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.Models.SvagoResponses.SvagoData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SvagoAdapter extends RecyclerView.Adapter<SvagoAdapter.ViewHolder> {

    private List<SvagoData> svagoDataList;
    private Context context ;
    private userData userObject;

    public SvagoAdapter(List<SvagoData> cartList, Context context) {
        this.svagoDataList = cartList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_svago , parent , false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (svagoDataList.get(position).getType().equals("car")){
            holder.layoutTrip.setVisibility(View.GONE);
        }else {
            holder.layoutCar.setVisibility(View.GONE);
        }

        holder.viewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SvagoDetailsActivity.class);
                intent.putExtra(Constant.userFlag,userObject);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return svagoDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layoutTrip,layoutCar;
        CardView viewParent;

        public ViewHolder(View view) {
            super(view);
            layoutTrip=view.findViewById(R.id.linearTrip);
            layoutCar=view.findViewById(R.id.linearCar);
            viewParent=view.findViewById(R.id.parent);
        }
    }
}
