package com.example.softexpert.domain;

import android.app.Application;

import com.example.softexpert.di.component.AppComponent;
import com.example.softexpert.di.component.DaggerAppComponent;
import com.example.softexpert.di.module.AppModule;

public class App extends Application {

   public AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
