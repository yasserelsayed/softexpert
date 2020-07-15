package com.example.softexpert.domain;

import com.example.softexpert.data.models.CarDataModel;

public class Car {

    private CarDataModel mCarDataModel;

    public Car(CarDataModel mCarDataModel) {
        this.mCarDataModel = mCarDataModel;
    }

    public int getId(){
       return this.mCarDataModel.id;
    }

    public String getBrand(){
        return this.mCarDataModel.brand;
    }

    public String getImageUrl(){
        return this.mCarDataModel.image;
    }

    public Boolean getIsUsed(){
        return this.mCarDataModel.isUsed;
    }

    public Integer getConstractionYear(){
        if(this.mCarDataModel.constractionYear!=null)
        return this.mCarDataModel.constractionYear;
        else  return 0;
    }


}
