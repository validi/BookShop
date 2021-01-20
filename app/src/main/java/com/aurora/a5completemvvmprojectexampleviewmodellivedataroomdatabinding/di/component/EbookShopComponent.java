package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.di.component;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.di.module.ApplicationModule;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.di.module.RepositoryModule;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class })
public interface EbookShopComponent {

    void Inject(MainActivity mainActivity);

}
