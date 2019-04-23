package com.example.svago.SvagoPackage.SvagoFragmentListPackage;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.svago.SvagoPackage.TripPackage.SvagoDetailsActivity;
import com.example.svago.SvagoPackage.CarPackage.CarDetailsActivity;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.Models.SvagoResponses.SvagoData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SvagoAdapter extends RecyclerView.Adapter<SvagoAdapter.ViewHolder> {

    private List<SvagoData> svagoDataList;
    private Context context ;
    private userData userObject;

    public SvagoAdapter(List<SvagoData> cartList, Context context,userData userObject) {
        this.svagoDataList = cartList;
        this.context = context;
        this.userObject=userObject;
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
            ImageLoader.getInstance().displayImage(svagoDataList.get(position).getImage(),holder.imgCar);
            holder.layoutTrip.setVisibility(View.GONE);
            holder.txtDoor.setText(svagoDataList.get(position).getDoors());
            holder.txtGear.setText(svagoDataList.get(position).getGears());
            holder.txtPassenger.setText(svagoDataList.get(position).getPassengers());
            holder.txtAir.setText(svagoDataList.get(position).isAc()+"");
            holder.txtNameCar.setText(svagoDataList.get(position).getName());
            holder.txtPrice.setText(svagoDataList.get(position).getPrice());
            holder.txtLocation.setText(svagoDataList.get(position).getAddress());

        }else {
            ImageLoader.getInstance().displayImage(svagoDataList.get(position).getImage(),holder.imgTrip);
            holder.layoutCar.setVisibility(View.GONE);
            holder.txtNameTrip.setText(svagoDataList.get(position).getTitle());
            holder.txtTripLocation.setText(svagoDataList.get(position).getAddress());
            holder.txtTripPrice.setText(svagoDataList.get(position).getPrice());
        }

        //region ClickRegion
        holder.layoutCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CarDetailsActivity.class);
                intent.putExtra(Constant.userFlag,userObject);
                intent.putExtra("carID",svagoDataList.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.layoutTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SvagoDetailsActivity.class);
                intent.putExtra(Constant.userFlag,userObject);
                intent.putExtra("tripID",svagoDataList.get(position).getId());
                context.startActivity(intent);
            }
        });
        //endregion
    }


    @Override
    public int getItemCount() {
        return svagoDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layoutTrip,layoutCar;
        CardView viewParent;
        ImageView imgCar,imgTrip;

        @BindView(R.id.txtDoor)
        TextView txtDoor;
        @BindView(R.id.txtGear)
        TextView txtGear;
        @BindView(R.id.txtAir)
        TextView txtAir;
        @BindView(R.id.txtPassenger)
        TextView txtPassenger;
        @BindView(R.id.txtNameCar)
        TextView txtNameCar;
        @BindView(R.id.txtPrice)
        TextView txtPrice;
        @BindView(R.id.txtLocation)
        TextView txtLocation;
        @BindView(R.id.txtNameTrip)
        TextView txtNameTrip;
        @BindView(R.id.txtTripLocation)
        TextView txtTripLocation;
        @BindView(R.id.txtTripPrice)
        TextView txtTripPrice;

        public ViewHolder(View view) {
            super(view);
            layoutTrip=view.findViewById(R.id.linearTrip);
            layoutCar=view.findViewById(R.id.linearCar);
            viewParent=view.findViewById(R.id.parent);
            imgTrip=view.findViewById(R.id.imgTrip);
            imgCar=view.findViewById(R.id.imgCar);
            ButterKnife.bind(this,view);
        }
    }
}
