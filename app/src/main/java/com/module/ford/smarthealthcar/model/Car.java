package com.module.ford.smarthealthcar.model;



public class Car {

    private Double average_fuel_consumption = 0.0;
    private Double distance_traveled = 0.0;
    private String average_rpm = "0";
    private String vin = "";
    private String odometer = "";


    public Car( Double average_fuel_consumption, Double distance_traveled, String average_rpm, String vin, String odometer) {

        this.average_fuel_consumption = average_fuel_consumption;
        this.distance_traveled = distance_traveled;
        this.average_rpm = average_rpm;
        this.vin = vin;
        this.odometer = odometer;
    }
    public Car() {


    }



    public Double getAverage_fuel_consumption() {
        return average_fuel_consumption;
    }

    public void setAverage_fuel_consumption(Double average_fuel_consumption) {
        this.average_fuel_consumption = average_fuel_consumption;
    }

    public Double getDistance_traveled() {
        return distance_traveled;
    }

    public void setDistance_traveled(Double distance_traveled) {
        this.distance_traveled = distance_traveled;
    }

    public String getAverage_rpm() {
        return average_rpm;
    }

    public void setAverage_rpm(String average_rpm) {
        this.average_rpm = average_rpm;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }
}
