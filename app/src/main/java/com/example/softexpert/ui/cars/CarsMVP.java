package com.example.softexpert.ui.cars;

import com.example.softexpert.domain.Car;

import java.util.List;

import io.reactivex.Observable;

public interface CarsMVP {
    interface CarsView {
        void setCars(List<Car> lstCars);
        void showNotification(Integer resNum);
        void showNotification(String notification);
        void showLoadingPopup();
        void hideLoadingPopup();
        void showNetworkError();
        void raiseError();
    }

    interface GetCarsPresenter{
         void setView(CarsMVP.CarsView mCarsView);
         void submit();
         int getPage();
         void unsubscribe();
         void resetPaging();

    }

    interface CarsModel {
        Observable<List<Car>>  getCars(int page);
    }
}
