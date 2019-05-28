package com.travel.svago.OfferPackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.travel.svago.Models.ResponseOffers.Offer;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SvagoPackage.CarPackage.CarDetailsActivity;
import com.travel.svago.SvagoPackage.TripPackage.SvagoDetailsActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vv , viewGroup , false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        if (offerList.get(i).getType().equals("car")){
            ImageLoader.getInstance().displayImage(offerList.get(i).getImage(),holder.imgCar);
            //holder.layout.setVisibility(View.VISIBLE);
            holder.layoutTrip.setVisibility(View.GONE);
            holder.txtPassenger.setText(offerList.get(i).getPassengers());
            holder.txtDoor.setText(offerList.get(i).getDoors());
            holder.txtGear.setText(offerList.get(i).getGears());
            holder.txtCrystal.setText(offerList.get(i).getAc());
            holder.txtNameCar.setText(offerList.get(i).getName());
            holder.txtLocation.setText(offerList.get(i).getAddress());
            holder.txtPrice.setText(String.format("%s %s", offerList.get(i).getPrice(), offerList.get(i).getCurrency()));
            holder.txtPrice.setPaintFlags(holder.txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtOffer.setText(String.format("%s %s", offerList.get(i).getOffer(), offerList.get(i).getCurrency()));
        }else {
            ImageLoader.getInstance().displayImage(offerList.get(i).getImage(),holder.imgTrip);
            //holder.layout.setVisibility(View.GONE);
            holder.layoutCar.setVisibility(View.GONE);
            holder.txtNameTrip.setText(offerList.get(i).getTitle());
            holder.txtLocation.setText(offerList.get(i).getAddress());
            holder.txtTripPrice.setText(String.format("%s %s", offerList.get(i).getPrice(), offerList.get(i).getCurrency()));
            holder.txtTripPrice.setPaintFlags(holder.txtTripPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtTripOffer.setText(String.format("%s %s", offerList.get(i).getOffer(), offerList.get(i).getCurrency()));
        }

        holder.layoutTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SvagoDetailsActivity.class);
                intent.putExtra(Constant.userFlag,userObject);
                intent.putExtra("tripID",offerList.get(i).getId());
                context.startActivity(intent);

            }
        });

        holder.layoutCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CarDetailsActivity.class);
                intent.putExtra(Constant.userFlag,userObject);
                intent.putExtra("carID",offerList.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        /*TextView TxtPassenger , TxtDoor , TxtGear , TxtCrystal , TxtName , TxtLocation
                , TxtPrice , TxtOffer;
        ImageView imageView ;
        LinearLayout layout ;
        CardView parent;
*/
        LinearLayout layoutTrip,layoutCar;
        CardView viewParent;
        ImageView imgCar,imgTrip;
        TextView txtCrystal , txtOffer , txtTripOffer ;

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


        public ViewHolder(@NonNull View view) {
            super(view);
            /*TxtPassenger = view.findViewById(R.id.txtPassenger);
            TxtDoor = view.findViewById(R.id.txtDoor);
            TxtGear = view.findViewById(R.id.txtGear);
            TxtCrystal = view.findViewById(R.id.txtCrystal);
            TxtName = view.findViewById(R.id.name);
            TxtLocation = view.findViewById(R.id.txtLocation);
            TxtPrice = view.findViewById(R.id.txtPrice);
            TxtOffer = view.findViewById(R.id.txtOffer);
            imageView = view.findViewById(R.id.image);
            layout = view.findViewById(R.id.lin);
            parent = view.findViewById(R.id.parent);*/
            layoutTrip=view.findViewById(R.id.linearTrip);
            layoutCar=view.findViewById(R.id.linearCar);
            viewParent=view.findViewById(R.id.parent);
            imgTrip=view.findViewById(R.id.imgTrip);
            imgCar=view.findViewById(R.id.imgCar);
            txtOffer = view.findViewById(R.id.txtOffer);
            txtTripOffer = view.findViewById(R.id.txtTripOffer);
            txtCrystal = view.findViewById(R.id.txtAir);
            ButterKnife.bind(this,view);
        }
    }
}
