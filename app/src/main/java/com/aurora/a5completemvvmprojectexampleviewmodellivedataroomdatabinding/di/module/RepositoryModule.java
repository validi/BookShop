package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.di.module;

import android.app.Application;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.model.EBookShopRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    EBookShopRepository eBookShopRepository;

    @Singleton
    @Provides
    EBookShopRepository providerEBookShopRepository(Application application){
        return new EBookShopRepository(application);
    }
}
