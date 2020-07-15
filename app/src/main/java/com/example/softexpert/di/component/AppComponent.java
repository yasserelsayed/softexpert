package com.example.softexpert.di.component;

import com.example.softexpert.data.network.DataService;
import com.example.softexpert.di.module.AppModule;
import com.example.softexpert.domain.NetWorkHandler;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    DataService ProvideDataService();
    NetWorkHandler ProvideNetWorkHandler();
}
