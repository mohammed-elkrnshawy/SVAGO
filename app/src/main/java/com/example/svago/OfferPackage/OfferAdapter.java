package com.example.svago.OfferPackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.svago.Models.ResponseOffers.Offer;
import com.example.svago.Models.SharedResponses.userData;
import com.example.svago.R;
import com.example.svago.SharedPackage.Classes.Constant;
import com.example.svago.SvagoPackage.CarPackage.CarDetailsActivity;
import com.example.svago.SvagoPackage.TripPackage.SvagoDetailsActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private Context context ;
    private List<Offer> offerList ;
    private userData userObject;

    public OfferAdapter(Context context, List<Offer> offerList,userData userData) {
        this.context = context;
        this.offerList = offerList;
        this.userObject=userData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_offer , viewGroup , false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        if (offerList.get(i).getType().equals("car")){
            ImageLoader.getInstance().displayImage(offerList.get(i).getImage(),holder.imageView);
            holder.layout.setVisibility(View.VISIBLE);
            holder.TxtPassenger.setText(offerList.get(i).getPassengers());
            holder.TxtDoor.setText(offerList.get(i).getDoors());
            holder.TxtGear.setText(offerList.get(i).getGears());
            holder.TxtCrystal.setText(offerList.get(i).getAc());
            holder.TxtName.setText(offerList.get(i).getName());
            holder.TxtLocation.setText(offerList.get(i).getAddress());
            holder.TxtPrice.setText(String.format("%s %s", offerList.get(i).getPrice(), offerList.get(i).getCurrency()));
            holder.TxtPrice.setPaintFlags(holder.TxtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.TxtOffer.setText(String.format("%s %s", offerList.get(i).getOffer(), offerList.get(i).getCurrency()));
        }else {
            ImageLoader.getInstance().displayImage(offerList.get(i).getImage(),holder.imageView);
            holder.layout.setVisibility(View.GONE);
            holder.TxtName.setText(offerList.get(i).getTitle());
            holder.TxtLocation.setText(offerList.get(i).getAddress());
            holder.TxtPrice.setText(String.format("%s %s", offerList.get(i).getPrice(), offerList.get(i).getCurrency()));
            holder.TxtPrice.setPaintFlags(holder.TxtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.TxtOffer.setText(String.format("%s %s", offerList.get(i).getOffer(), offerList.get(i).getCurrency()));
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offerList.get(i).getType().equals("car")){
                    Intent intent=new Intent(context, CarDetailsActivity.class);
                    intent.putExtra(Constant.userFlag,userObject);
                    intent.putExtra("carID",offerList.get(i).getId());
                    context.startActivity(intent);
                }else {
                    Intent intent=new Intent(context, SvagoDetailsActivity.class);
                    intent.putExtra(Constant.userFlag,userObject);
                    intent.putExtra("tripID",offerList.get(i).getId());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView TxtPassenger , TxtDoor , TxtGear , TxtCrystal , TxtName , TxtLocation
                , TxtPrice , TxtOffer;
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
            TxtPrice = view.findViewById(R.id.txtPrice);
            TxtOffer = view.findViewById(R.id.txtOffer);
            imageView = view.findViewById(R.id.image);
            layout = view.findViewById(R.id.lin);
            parent = view.findViewById(R.id.parent);
        }
    }
}
