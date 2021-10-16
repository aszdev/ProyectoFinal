package com.example.proyectofinal.ui.MisViajes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MisViajesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MisViajesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Pruebita");
    }

    public LiveData<String> getText() {
        return mText;
    }
}