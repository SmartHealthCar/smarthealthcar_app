package com.module.ford.smarthealthcar.model;

import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.OnVehicleData;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;



public class CarData implements Serializable{



    /* Singleton */
    private static CarData INSTANCE;
    public static CarData getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CarData();
        return INSTANCE;
    }


    /* All Attributes */
    public String[] VehicleParams = {"vin","fuelLevel_State", "tirePressure", "prndl", "fuelLevel", "speed","externalTemperature","rpm", "engineTorque", "odometer","driverBraking","headLampStatus","gps","tirePressure"};
    private String vin = " -- ";
    private String fuelLevel_State= " -- ";
    private String prndl = " -- ";
    private String fuelLevel = " -- ";
    private String speed = " -- ";
    private String externalTemperature = " -- ";
    private String rpm = "0";
    private String engineTorque = " -- ";
    private String odometer = " -- ";
    private String driverBraking = " -- ";


    private long timestamp;

    /* headLampStatus */
    public String[] headLampParams = {"lowBeamsOn","ambientLightSensorStatus","highBeamsOn"};
    private String lowBeamsOn = " -- ";
    private String ambientLightSensorStatus = "";
    private String highBeamsOn = " -- ";

    /* GPS */
    public String[] gpsParams = {"latitudeDegrees","longitudeDegrees","altitude","heading","compassDirection"};
    private String latitudeDegrees = " -- ";
    private String longitudeDegrees = " -- ";
    private String altitude = " -- ";
    private String heading = " -- ";
    private String compassDirection = " -- ";

    /* tirePressure */
    public String[] tirePressureParams = {"rightRear","pressureTelltale","innerLeftRear","rightFront","innerRightRear","leftRear","leftFront"};
    private String rightRear = " -- ";
    private String pressureTelltale = " -- ";
    private String innerLeftRear = " -- ";
    private String rightFront = " -- ";
    private String innerRightRear = " -- ";
    private String leftRear = " -- ";
    private String leftFront = " -- ";

    /* bodyInformation */
    public String[] bodyInformationParams = {"rearLeftDoorAjar","parkBrakeActive","driverDoorAjar","rearRightDoorAjar","ignitionStableStatus","passengerDoorAjar","ignitionStatus"};
    private String rearLeftDoorAjar = " -- ";
    private String parkBrakeActive = " -- ";
    private String driverDoorAjar = " -- ";
    private String rearRightDoorAjar = " -- ";
    private String ignitionStableStatus = " -- ";
    private String passengerDoorAjar = " -- ";
    private String ignitionStatus = " -- ";

    /* Getters & Setters */
    public String getLatitudeDegrees() {
        return latitudeDegrees;
    }

    public void setLatitudeDegrees(String latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    public String getLongitudeDegrees() {
        return longitudeDegrees;
    }

    public void setLongitudeDegrees(String longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getCompassDirection() {
        return compassDirection;
    }

    public void setCompassDirection(String compassDirection) {
        this.compassDirection = compassDirection;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getFuelLevel_State() {
        return fuelLevel_State;
    }

    public void setFuelLevel_State(String fuelLevel_State) {
        this.fuelLevel_State = fuelLevel_State;
    }

    public String getPrndl() {
        return prndl;
    }

    public void setPrndl(String prndl) {
        this.prndl = prndl;
    }

    public String getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(String fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getExternalTemperature() {
        return externalTemperature;
    }

    public void setExternalTemperature(String externalTemperature) {
        this.externalTemperature = externalTemperature;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }

    public String getEngineTorque() {
        return engineTorque;
    }

    public void setEngineTorque(String engineTorque) {
        this.engineTorque = engineTorque;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public String getDriverBraking() {
        return driverBraking;
    }

    public void setDriverBraking(String driverBraking) {
        this.driverBraking = driverBraking;
    }

    public String getLowBeamsOn() {
        return lowBeamsOn;
    }

    public void setLowBeamsOn(String lowBeamsOn) {
        this.lowBeamsOn = lowBeamsOn;
    }

    public String getAmbientLightSensorStatus() {
        return ambientLightSensorStatus;
    }

    public void setAmbientLightSensorStatus(String ambientLightSensorStatus) {
        this.ambientLightSensorStatus = ambientLightSensorStatus;
    }

    public String getHighBeamsOn() {
        return highBeamsOn;
    }

    public void setHighBeamsOn(String highBeamsOn) {
        this.highBeamsOn = highBeamsOn;
    }

    public String getRightRear() {
        return rightRear;
    }

    public void setRightRear(String rightRear) {
        this.rightRear = rightRear;
    }

    public String getPressureTelltale() {
        return pressureTelltale;
    }

    public void setPressureTelltale(String pressureTelltale) {
        this.pressureTelltale = pressureTelltale;
    }

    public String getInnerLeftRear() {
        return innerLeftRear;
    }

    public void setInnerLeftRear(String innerLeftRear) {
        this.innerLeftRear = innerLeftRear;
    }

    public String getRightFront() {
        return rightFront;
    }

    public void setRightFront(String rightFront) {
        this.rightFront = rightFront;
    }

    public String getInnerRightRear() {
        return innerRightRear;
    }

    public void setInnerRightRear(String innerRightRear) {
        this.innerRightRear = innerRightRear;
    }

    public String getLeftRear() {
        return leftRear;
    }

    public void setLeftRear(String leftRear) {
        this.leftRear = leftRear;
    }

    public String getLeftFront() {
        return leftFront;
    }

    public void setLeftFront(String leftFront) {
        this.leftFront = leftFront;
    }

    public String getRearLeftDoorAjar() {
        return rearLeftDoorAjar;
    }

    public void setRearLeftDoorAjar(String rearLeftDoorAjar) {
        this.rearLeftDoorAjar = rearLeftDoorAjar;
    }

    public String getParkBrakeActive() {
        return parkBrakeActive;
    }

    public void setParkBrakeActive(String parkBrakeActive) {
        this.parkBrakeActive = parkBrakeActive;
    }

    public String getDriverDoorAjar() {
        return driverDoorAjar;
    }

    public void setDriverDoorAjar(String driverDoorAjar) {
        this.driverDoorAjar = driverDoorAjar;
    }

    public String getRearRightDoorAjar() {
        return rearRightDoorAjar;
    }

    public void setRearRightDoorAjar(String rearRightDoorAjar) {
        this.rearRightDoorAjar = rearRightDoorAjar;
    }

    public String getIgnitionStableStatus() {
        return ignitionStableStatus;
    }

    public void setIgnitionStableStatus(String ignitionStableStatus) {
        this.ignitionStableStatus = ignitionStableStatus;
    }

    public String getPassengerDoorAjar() {
        return passengerDoorAjar;
    }

    public void setPassengerDoorAjar(String passengerDoorAjar) {
        this.passengerDoorAjar = passengerDoorAjar;


    }

    public String getIgnitionStatus() {
        return ignitionStatus;
    }

    public void setIgnitionStatus(String ignitionStatus) {
        this.ignitionStatus = ignitionStatus;
    }

    /* GET DATA */
    public void processVehicleData(GetVehicleDataResponse notification) {

        for (HashMap.Entry<String, Object> obj : ((Hashtable<String, Object>)notification.getStore().get("response") ).entrySet() ) {
            if (obj.getKey().equals("parameters")) {
                for (HashMap.Entry<String, Object> item : ((Hashtable<String, Object>)obj.getValue()).entrySet() ) {
                    if (item.getKey().equals("tirePressure")) {
                        for ( HashMap.Entry<String, Object> objTirePressute : ((Hashtable<String,Object>)item.getValue()).entrySet() ) {
                            if (objTirePressute.getValue().getClass() == "".getClass()){
                                setItem(objTirePressute.getKey(), objTirePressute.getValue().toString());
                            }
                            else {
                                setItem(objTirePressute.getKey().toString(), ((Hashtable<String, Object>)objTirePressute.getValue()).get("status").toString() );
                            }
                        }
                    }
                    else if (item.getKey().equals("bodyInformation")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("headLampStatus")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("gps")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else {
                        setItem(item.getKey(), item.getValue().toString());
                    }
                }
            }
        }

        setTimestamp(System.currentTimeMillis());
    }

    /* SUBSCRIBE */
    public void processVehicleSubscribe(OnVehicleData responseSubs){
        for (HashMap.Entry<String, Object> obj : ((Hashtable<String, Object>)responseSubs.getStore().get("notification") ).entrySet() ) {
            if (obj.getKey().equals("parameters")) {
                for (HashMap.Entry<String, Object> item : ((Hashtable<String, Object>)obj.getValue()).entrySet() ) {
                    if (item.getKey().equals("tirePressure")) {
                        for ( HashMap.Entry<String, Object> objTirePressute : ((Hashtable<String,Object>)item.getValue()).entrySet() ) {
                            if (objTirePressute.getValue().getClass() == "".getClass()){
                                setItem(objTirePressute.getKey(), objTirePressute.getValue().toString());
                            }
                            else {
                                setItem(objTirePressute.getKey().toString(), ((Hashtable<String, Object>)objTirePressute.getValue()).get("status").toString() );
                            }
                        }
                    }
                    else if (item.getKey().equals("bodyInformation")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("headLampStatus")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else if (item.getKey().equals("gps")) {
                        setItemSubItem((Hashtable<String, Object>)item.getValue());
                    }
                    else {
                        setItem(item.getKey(), item.getValue().toString());
                    }
                }
            }
        }
    }

    /* process Hasmap to get sub-itens */
    public void setItemSubItem(Hashtable<String, Object> item) {
        for (HashMap.Entry<String, Object> obj : item.entrySet() ){
            setItem(obj.getKey(), obj.getValue().toString());
        }
    }

    /* set attributes by string name */
    public void setItem(String item, String valor) {

        valor = valor == null ? " -- " : valor;

        switch (item){
            case "vin":
                setVin(valor);
                break;
            case "fuelLevel_State":
                setFuelLevel_State(valor);
                break;
            case "prndl":
                setPrndl(valor);
                break;
            case "fuelLevel":
                setFuelLevel(valor);
                break;
            case "speed":
                setSpeed(valor);
                break;
            case "externalTemperature":
                setExternalTemperature(valor);
                break;
            case "rpm":
                setRpm(valor);
                break;
            case "driverBraking":
                setDriverBraking(valor);
                break;
            case "engineTorque":
                setEngineTorque(valor);
                break;
            case "odometer":
                setOdometer(valor);
                break;
            case "lowBeamsOn":
                setLowBeamsOn(valor);
                break;
            case "highBeamsOn":
                setHighBeamsOn(valor);
                break;
            case "ambientLightSensorStatus":
                setAmbientLightSensorStatus(valor);
                break;
            case "rearLeftDoorAjar":
                setRearLeftDoorAjar(valor);
                break;
            case "rearRightDoorAjar":
                setRearRightDoorAjar(valor);
                break;
            case "ignitionStatus":
                setIgnitionStatus(valor);
                break;
            case "driverDoorAjar":
                setDriverDoorAjar(valor);
                break;
            case "parkBrakeActive":
                setParkBrakeActive(valor);
                break;
            case "passengerDoorAjar":
                setPassengerDoorAjar(valor);
                break;
            case "ignitionStableStatus":
                setIgnitionStableStatus(valor);
                break;
            case "latitudeDegrees":
                setLatitudeDegrees(valor);
                break;
            case "longitudeDegrees":
                setLongitudeDegrees(valor);
                break;
            case "altitude":
                setAltitude(valor);
                break;
            case "heading":
                setHeading(valor);
                break;
            case "compassDirection":
                setCompassDirection(valor);
                break;
            case "rightRear":
                setRightRear(valor);
                break;
            case "pressureTelltale":
                setPressureTelltale(valor);
                break;
            case "innerLeftRear":
                setInnerLeftRear(valor);
                break;
            case "rightFront":
                setRightFront(valor);
                break;
            case "innerRightRear":
                setInnerRightRear(valor);
                break;
            case "leftRear":
                setLeftRear(valor);
                break;
            case "leftFront":
                setLeftFront(valor);
                break;
        }
    }

    /* get attributes by string name */
    public String getItem(String item) {
        switch (item){
            case "vin":
                return getVin();
            case "fuelLevel_State":
                return getFuelLevel_State();
            case "prndl":
                return getPrndl();
            case "fuelLevel":
                return getFuelLevel();
            case "speed":
                return getSpeed();
            case "externalTemperature":
                return getExternalTemperature();
            case "rpm":
                return getRpm();
            case "driverBraking":
                return getDriverBraking();
            case "engineTorque":
                return getEngineTorque();
            case "odometer":
                return getOdometer();
            case "lowBeamsOn":
                return getLowBeamsOn();
            case "highBeamsOn":
                return getHighBeamsOn();
            case "ambientLightSensorStatus":
                return getAmbientLightSensorStatus();
            case "rearLeftDoorAjar":
                return  getRearLeftDoorAjar();
            case "rearRightDoorAjar":
                return getRearRightDoorAjar();
            case "ignitionStatus":
                return getIgnitionStatus();
            case "driverDoorAjar":
                return  getDriverDoorAjar();
            case "parkBrakeActive":
                return  getParkBrakeActive();
            case "passengerDoorAjar":
                return getPassengerDoorAjar();
            case "ignitionStableStatus":
                return getIgnitionStableStatus();
            case "latitudeDegrees":
                return getLatitudeDegrees();
            case "longitudeDegrees":
                return getLongitudeDegrees();
            case "altitude":
                return getAltitude();
            case "heading":
                return getHeading();
            case "compassDirection":
                return getCompassDirection();
            case "rightRear":
                return getRightRear();
            case "pressureTelltale":
                return getPressureTelltale();
            case "innerLeftRear":
                return getInnerLeftRear();
            case "rightFront":
                return getRightFront();
            case "innerRightRear":
                return getInnerRightRear();
            case "leftRear":
                return getLeftRear();
            case "leftFront":
                return getLeftFront();
            default:
                return "";
        }
    }

    /* set attributes from Static CarData Instance (used in Subscribe only) */
    public void newCarDataSubscribe(){
        for (String obj : VehicleParams) {
            setItem(obj, CarData.INSTANCE.getItem(obj));
        }

        for (String obj : tirePressureParams) {
            setItem(obj, CarData.INSTANCE.getItem(obj));
        }

        for (String obj : bodyInformationParams) {
            setItem(obj, CarData.INSTANCE.getItem(obj));
        }

        for (String obj : headLampParams) {
            setItem(obj, CarData.INSTANCE.getItem(obj));
        }

        for (String obj : gpsParams) {
            setItem(obj, CarData.INSTANCE.getItem(obj));
        }

        setTimestamp(System.currentTimeMillis());
    }

}
