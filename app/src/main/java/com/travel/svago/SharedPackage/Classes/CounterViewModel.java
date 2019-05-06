package com.travel.svago.SharedPackage.Classes;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

    private MutableLiveData<Integer> count = new MutableLiveData<>();

    public CounterViewModel(){
        count.setValue(0);
    }

    public void setCount(int count) {
        this.count.setValue(count);
    }

    public MutableLiveData<Integer> getCount() {
        return count;
    }
}
