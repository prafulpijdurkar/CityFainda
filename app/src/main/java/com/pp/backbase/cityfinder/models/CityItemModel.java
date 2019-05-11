package com.pp.backbase.cityfinder.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Praful Pijdurkar on 5/7/19.
 */
public class CityItemModel  implements Comparable<CityItemModel>,Parcelable {
    public CityItemModel() {
    }

    @JsonProperty("_id")
    private int id;

    @JsonProperty("country")
    private String country;

    @JsonProperty("name")
    private String name;
    @JsonProperty("coord")
    private CoordinatesModel coordinatesModel;


    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public CoordinatesModel getCoordinatesModel() {
        return coordinatesModel;
    }

    @Override
    public int compareTo(CityItemModel cityItemModel) {
         return  this.getName().compareTo(cityItemModel.getName());
    }

    @Override
    public int describeContents() {
        return 0;
    }


    private CityItemModel(Parcel in){
        id =  in.readInt();
        country =  in.readString();
        name =  in.readString();
        coordinatesModel = in.readParcelable(CoordinatesModel.class.getClassLoader());

    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(country);
        dest.writeString(name);
        dest.writeParcelable(coordinatesModel, flags);

    }

    public static final Creator<CityItemModel> CREATOR = new Creator<CityItemModel>() {
        @Override
        public CityItemModel createFromParcel(Parcel in) {
            return new CityItemModel(in);
        }

        @Override
        public CityItemModel[] newArray(int size) {
            return new CityItemModel[size];
        }
    };
}
