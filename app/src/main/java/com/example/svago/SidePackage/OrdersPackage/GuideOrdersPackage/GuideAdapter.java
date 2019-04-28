package com.example.svago.SidePackage.OrdersPackage.GuideOrdersPackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.svago.Models.ResponseGuideOrders.Guide;
import com.example.svago.R;

import java.util.List;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {

    Context context ;
    List<Guide> guideList ;

    public GuideAdapter(Context context, List<Guide> guideList) {
        this.context = context;
        this.guideList = guideList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_guide_order , viewGroup , false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.txtLoc.setText(guideList.get(i).getLocation());
        holder.txtFrom.setText(guideList.get(i).getFrom());
        holder.txtTo.setText(guideList.get(i).getTo());
    }

    @Override
    public int getItemCount() {
        return guideList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtLoc , txtFrom , txtTo ;
        public ViewHolder(@NonNull View view) {
            super(view);
            txtLoc = view.findViewById(R.id.txtLocation) ;
            txtFrom = view.findViewById(R.id.from) ;
            txtTo = view.findViewById(R.id.to) ;
        }
    }
}
