package com.example.softexpert.di.module;

import com.example.softexpert.data.CarsRepository;
import com.example.softexpert.data.network.DataService;
import com.example.softexpert.di.scope.MainActivityScope;
import com.example.softexpert.domain.NetWorkHandler;
import com.example.softexpert.ui.cars.CarsMVP;
import com.example.softexpert.ui.cars.presenters.GetCarsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModule {

    @Provides
    @MainActivityScope
    public CarsMVP.CarsModel provideCarsRepositry(DataService mDataService) {
        return new CarsRepository(mDataService);
    }

    @Provides
    @MainActivityScope
    public CarsMVP.GetCarsPresenter provideGetCarsPresenter(CarsMVP.CarsModel mCarsModel, NetWorkHandler mNetWorkHandler) {
        return new GetCarsPresenter(mCarsModel,mNetWorkHandler);
    }
}
