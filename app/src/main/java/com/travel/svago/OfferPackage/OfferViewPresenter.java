package com.travel.svago.OfferPackage;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public interface OfferViewPresenter {
    void initView();
    void getData(Bundle bundle);
    void setRecycle(RecyclerView recycle);
    void callOffers(int currency_id ,  RecyclerView recyclerView);
    void listViewScroll(RecyclerView recyclerView);
}
