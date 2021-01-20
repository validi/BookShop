package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.EBookShopRepository;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    EBookShopRepository eBookShopRepository;

    public MainActivityViewModelFactory(EBookShopRepository eBookShopRepository) {
        this.eBookShopRepository = eBookShopRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new MainActivityViewModel(eBookShopRepository);
    }
}
