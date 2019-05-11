package com.pp.backbase.cityfinder.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Praful Pijdurkar on 5/7/19.
 */
public class CoordinatesModel implements Parcelable {
    public CoordinatesModel() {
    }

    @JsonProperty("lon")
    private double lon;

    @JsonProperty("lat")
    private double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lon);
        dest.writeDouble(lat);

    }

    private CoordinatesModel(Parcel in){
        lon =  in.readDouble();
        lat =  in.readDouble();
   }

    public static final Creator<CoordinatesModel> CREATOR = new Creator<CoordinatesModel>() {
        @Override
        public CoordinatesModel createFromParcel(Parcel in) {
            return new CoordinatesModel(in);
        }

        @Override
        public CoordinatesModel[] newArray(int size) {
            return new CoordinatesModel[size];
        }
    };

}
