package com.example.viewmodelexample;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//Factory : 새로운 뷰모델프로바이더를 만든다. 뷰모델프로바이더는 뷰모델을 사용할 수 있게끔 해주는 클래스다.
public class NameViewModelFactory implements ViewModelProvider.Factory{
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Factory Runtime Error");
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Runtime Error");
        }
    }
}
