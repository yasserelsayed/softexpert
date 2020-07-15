package com.example.softexpert.di.component;

import com.example.softexpert.di.module.CarModule;
import com.example.softexpert.di.scope.MainActivityScope;
import com.example.softexpert.ui.cars.views.MainActivity;

import dagger.Component;

@MainActivityScope
@Component(modules = {CarModule.class},dependencies = {AppComponent.class})
public interface MainActivityComponent {
     void inject(MainActivity mMainActivity);
}
