package com.module.ford.smarthealthcar.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class JsonArrayConverter {
    public JsonArrayConverter() {
    }

    private ArrayList<Car> getArrayListCarInformation(JSONArray array) throws JSONException {

        ArrayList<Car> userClasses = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            Car car = getUserClassFromJson(array.getJSONObject(i));
            userClasses.add(car);
        }

        return userClasses;
    }

    private Car getUserClassFromJson(JSONObject jsonObject) throws JSONException {
        Car car = new Car();

        car.setAverage_fuel_consumption(Double.valueOf(jsonObject.getString("average_fuel_consumption")));
        car.setDistance_traveled(Double.valueOf(jsonObject.getString("distance_traveled")));
        car.setAverage_rpm(jsonObject.getString("average_rpm"));

        return car;
    }


}
