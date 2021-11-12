package com.example.viewmodelexample;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NameViewModel extends ViewModel {
    private MutableLiveData<String> current_data;
    public MutableLiveData<String> getCurrent_data() {
        if (current_data == null) {
            current_data = new MutableLiveData<String>();
        }
        return current_data;
    }
}