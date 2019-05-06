package com.travel.svago.SharedPackage.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.travel.svago.Models.CountriesResponses.CountyData;
import com.travel.svago.R;

import java.util.List;


public class CountryAdapter extends BaseAdapter {

    private List<CountyData> locationsList;
    private Context context;

    public CountryAdapter(List<CountyData> locationsList, Context context) {
        this.locationsList = locationsList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return locationsList.size();
    }

    @Override
    public Object getItem(int position) {
        return locationsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CountryAdapter.ViewHolder holder = null ;

        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.view_spinner,parent,false);

        holder = new CountryAdapter.ViewHolder() ;

        holder.txtAddress = row.findViewById(R.id.txtTitle)   ;

        holder.txtAddress.setText(locationsList.get(position).getTitle());

        return row ;
    }


    class ViewHolder  {
        TextView txtAddress;
    }

}