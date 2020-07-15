package com.example.softexpert.data.network;

import com.example.softexpert.data.models.CarsDataModels;
import com.example.softexpert.util.Constants;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {
    @GET(Constants.GET_CARS)
    Call<CarsDataModels>getCarsList(@Query("page")int page);
}
