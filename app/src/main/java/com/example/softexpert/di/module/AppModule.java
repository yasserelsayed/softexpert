package com.example.softexpert.di.module;

import com.example.softexpert.data.CarsRepository;
import com.example.softexpert.data.network.DataService;
import com.example.softexpert.domain.App;
import com.example.softexpert.domain.NetWorkHandler;
import com.example.softexpert.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    App mAppContext;

    public AppModule(App _AppContext) {
        mAppContext = _AppContext;
    }

    @Provides
    @Singleton
    public App provideAppContext() {
        return mAppContext;
    }

    @Provides
    @Singleton
    public DataService provideMoviesRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(DataService.class);
    }

    @Provides
    @Singleton
    public NetWorkHandler provideNetWorkHandler() {
       return  new NetWorkHandler(mAppContext);
    }
}
