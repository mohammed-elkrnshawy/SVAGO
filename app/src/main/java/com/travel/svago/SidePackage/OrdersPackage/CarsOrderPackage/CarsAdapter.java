package com.travel.svago.SidePackage.OrdersPackage.CarsOrderPackage;

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

import com.travel.svago.Models.ResponseCarsOrder.Car;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SvagoPackage.TripPackage.SvagoDetailsActivity;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    Context context ;
    List<Car> carList ;
    userData userData ;

    public CarsAdapter(Context context, List<Car> carList, com.travel.svago.Models.SharedResponses.userData userData) {
        this.context = context;
        this.carList = carList;
        this.userData = userData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_cars_orders , viewGroup , false) ;
        return new CarsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  final int i) {
        ImageLoader.getInstance().displayImage(carList.get(i).getCar().getImage(),holder.imageView);
        holder.TxtPassenger.setText(carList.get(i).getCar().getPassengers());
        holder.TxtDoor.setText(carList.get(i).getCar().getDoors());
        holder.TxtGear.setText(carList.get(i).getCar().getGears());
        holder.TxtCrystal.setText(carList.get(i).getCar().getAc());
        holder.TxtName.setText(carList.get(i).getCar().getName());
        holder.TxtLocation.setText(carList.get(i).getCar().getAddress());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , CarOrderDetailsActivity.class);
                intent.putExtra(Constant.userFlag , userData );
                intent.putExtra("car" , carList.get(i));
                intent.putExtra("position" , i);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView TxtPassenger , TxtDoor , TxtGear , TxtCrystal , TxtName , TxtLocation ;
        ImageView imageView ;
        LinearLayout layout ;
        CardView parent;
        public ViewHolder(@NonNull View view) {
            super(view);
            TxtPassenger = view.findViewById(R.id.txtPassenger);
            TxtDoor = view.findViewById(R.id.txtDoor);
            TxtGear = view.findViewById(R.id.txtGear);
            TxtCrystal = view.findViewById(R.id.txtCrystal);
            TxtName = view.findViewById(R.id.name);
            TxtLocation = view.findViewById(R.id.txtLocation);
            imageView = view.findViewById(R.id.image);
            layout = view.findViewById(R.id.lin);
            parent = view.findViewById(R.id.parent);
        }
    }
}
