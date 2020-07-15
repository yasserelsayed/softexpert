package com.example.softexpert.data;

import com.example.softexpert.data.models.CarDataModel;
import com.example.softexpert.data.models.CarsDataModels;
import com.example.softexpert.data.network.DataService;
import com.example.softexpert.domain.Car;
import com.example.softexpert.ui.cars.CarsMVP;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarsRepository implements CarsMVP.CarsModel {
    DataService mDataService;
    public CarsRepository(DataService _DataService) {
        this.mDataService = _DataService;
    }

    @Override
    public Observable<List<Car>> getCars(final int page) {
         return Observable.create(e -> mDataService.getCarsList(page).enqueue(new Callback<CarsDataModels>() {
             @Override
             public void onResponse(Call<CarsDataModels> call, Response<CarsDataModels> response) {
                          List<Car> lstCars = new ArrayList<>();
                         if(response.body().data!=null )
                             for(CarDataModel mCarDataModel : response.body().data)
                                 lstCars.add(new Car(mCarDataModel));
                          e.onNext(lstCars);
             }
             @Override
             public void onFailure(Call<CarsDataModels> call, Throwable t) {
                 e.onError(t.getCause());
             }
         }));
    }
}
