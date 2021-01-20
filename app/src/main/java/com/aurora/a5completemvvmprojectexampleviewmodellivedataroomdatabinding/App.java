package com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding;

import android.app.Application;

import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.di.component.DaggerEbookShopComponent;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.di.component.EbookShopComponent;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.di.module.ApplicationModule;
import com.aurora.a5completemvvmprojectexampleviewmodellivedataroomdatabinding.di.module.RepositoryModule;

public class App extends Application {
    private static App app;
    EbookShopComponent ebookShopComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        ebookShopComponent= DaggerEbookShopComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public static App getApp() {
        return app;
    }

    public EbookShopComponent getEbookShopComponent() {
        return ebookShopComponent;
    }
}
