package com.example.proyectofinal.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel2 extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel2() {
        mText = new MutableLiveData<>();
        mText.setValue("Pruebita");
    }

    public LiveData<String> getText() {
        return mText;
    }
}