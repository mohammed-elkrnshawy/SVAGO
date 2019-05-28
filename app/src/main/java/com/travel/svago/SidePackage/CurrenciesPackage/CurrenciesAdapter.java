package com.travel.svago.SidePackage.CurrenciesPackage ;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.travel.svago.Models.ResponseCurrencies.Currency;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.SharedClass;

import java.util.List;


import static android.content.Context.MODE_PRIVATE;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.ViewHolder> {

    List<Currency> currencyList ;
    Context context ;
    int isSelect = -1 ;
    String s = SharedClass.Currency ;
    public static boolean save = false ;

    public CurrenciesAdapter(List<Currency> currencyList, Context context) {
        this.currencyList = currencyList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_currency , viewGroup , false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

        holder.TxtCurrency.setText(currencyList.get(i).getTitle());


        if (isSelect == i){
            holder.checkBox.setChecked(true);
            ChangeCurrency(currencyList.get(i).getSymbol() , currencyList.get(i).getId());
            save=true;
        }else {
            if (s.equals(currencyList.get(i).getSymbol())){
                save=true;
                holder.checkBox.setChecked(true);
            }else {
                holder.checkBox.setChecked(false);
            }
        }


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelect = i ;
                s = "" ;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView TxtCurrency ;
        CheckBox checkBox ;
        public ViewHolder(@NonNull View view) {
            super(view);
            TxtCurrency = view.findViewById(R.id.text) ;
            checkBox = view.findViewById(R.id.checkbox) ;

        }
    }


    private void ChangeCurrency(String Currency , int CurrencyID) {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putString("Currency", Currency);
        editor.putInt("CurrencyID", CurrencyID);
        editor.apply();
        SharedClass.Currency=Currency;
        SharedClass.CurrencyID=CurrencyID;
    }

}
