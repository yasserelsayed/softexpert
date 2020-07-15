package com.example.softexpert.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CarDataModel implements Serializable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("constractionYear")
    @Expose
    public Integer constractionYear;
    @SerializedName("imageUrl")
    @Expose
    public String image;
    @SerializedName("isUsed")
    @Expose
    public boolean isUsed;
}
