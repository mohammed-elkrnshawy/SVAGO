package com.example.svago.OfferPackage;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.svago.Models.ResponseOffers.Offer;
import com.example.svago.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    Context context ;
    List<Offer> offerList ;

    public OfferAdapter(Context context, List<Offer> offerList) {
        this.context = context;
        this.offerList = offerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_offers2 , viewGroup , false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        if (offerList.get(i).getType().equals("car")){
            Picasso.with(context).load(offerList.get(i).getImage()).placeholder(R.drawable.ic_car1).into(holder.imageView);
            holder.layout.setVisibility(View.VISIBLE);
            holder.TxtDuration.setVisibility(View.GONE);
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
            Picasso.with(context).load(offerList.get(i).getImage()).placeholder(R.drawable.ic_hotel1).into(holder.imageView);
            holder.layout.setVisibility(View.GONE);
            holder.TxtDuration.setVisibility(View.VISIBLE);
            holder.TxtDuration.setText(String.format("%s %s", offerList.get(i).getDuration(), context.getString(R.string.days_with)));
            holder.TxtName.setText(offerList.get(i).getTitle());
            holder.TxtLocation.setText(offerList.get(i).getAddress());
            holder.TxtPrice.setText(String.format("%s %s", offerList.get(i).getPrice(), offerList.get(i).getCurrency()));
            holder.TxtPrice.setPaintFlags(holder.TxtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.TxtOffer.setText(String.format("%s %s", offerList.get(i).getOffer(), offerList.get(i).getCurrency()));
        }
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView TxtPassenger , TxtDoor , TxtGear , TxtCrystal , TxtName , TxtLocation , TxtPrice , TxtOffer , TxtDuration;
        ImageView imageView ;
        LinearLayout layout ;
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
            TxtDuration = view.findViewById(R.id.txtDuration);
            imageView = view.findViewById(R.id.image);
            layout = view.findViewById(R.id.lin);
        }
    }
}
