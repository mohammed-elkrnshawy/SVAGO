package com.travel.svago.SidePackage.OrdersPackage.TripsOrdersPackage;

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

import com.travel.svago.Models.ResponseTripOrders.Trip;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SvagoPackage.TripPackage.SvagoDetailsActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    Context context ;
    List<Trip> tripList ;
    com.travel.svago.Models.SharedResponses.userData userData ;

    public TripAdapter(Context context, List<Trip> tripList, com.travel.svago.Models.SharedResponses.userData userData) {
        this.context = context;
        this.tripList = tripList;
        this.userData = userData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_cars_orders , viewGroup , false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.layout.setVisibility(View.GONE);
        ImageLoader.getInstance().displayImage(tripList.get(i).getTrip().getImage(),holder.imageView);
        holder.TxtName.setText(tripList.get(i).getTrip().getTitle());
        holder.TxtLocation.setText(tripList.get(i).getTrip().getAddress());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , SvagoDetailsActivity.class);
                intent.putExtra(Constant.userFlag , userData );
                intent.putExtra("tripID" , tripList.get(i).getTrip().getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView TxtName , TxtLocation ;
        ImageView imageView ;
        LinearLayout layout ;
        CardView parent;
        public ViewHolder(@NonNull View view) {
            super(view);
            TxtName = view.findViewById(R.id.name);
            TxtLocation = view.findViewById(R.id.txtLocation);
            imageView = view.findViewById(R.id.image);
            layout = view.findViewById(R.id.lin);
            parent = view.findViewById(R.id.parent);
        }
    }
}
