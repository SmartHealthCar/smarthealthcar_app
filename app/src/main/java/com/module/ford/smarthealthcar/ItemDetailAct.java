package com.module.ford.smarthealthcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.module.ford.smarthealthcar.model.CarData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemDetailAct extends AppCompatActivity {

    CarData objCarData;

    private TextView txtDt, txtVin, txtPrndl, txtSpeed, txtRpm, txtFuelLevel, txtFuelLevel_State, txtOdometer, txtExternalTemperature, txtDriverBreaking, txtEngineTorque,
            txtLatitude, txtLongitude, txtAltitude, txtHeading, txtCompassDirection,
            txtLowBeamsOn, txtHighBeamsOn, txtAmbientLightSensorStatus,
            txtRightRear, txtLeftRear, txtRightFront, txtLeftFront, txtInnerRightRear, txtInnerLeftRear, txtPressureTelltale,
            txtDriverDoorAjar, txtPassengerDoorAjar, txtRearRightDoorAjar, txtRearLeftDoorAjar, txtIgnitionStatus, txtIgnitionStableStatus, txtParkBreakeActive;



    public void initToolbar(){
        setTitle("Data Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        initToolbar();
        initComponents();



        objCarData = (CarData) getIntent().getExtras().getSerializable("CarData");

        long yourmilliseconds = objCarData.getTimestamp() ;
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultdate = new Date(yourmilliseconds);
        txtDt.setText(sdf.format(resultdate));

        txtVin.setText("VIN: " + objCarData.getVin());
        txtPrndl.setText("PRNDL: " + objCarData.getPrndl());
        txtSpeed.setText("Speed: " + objCarData.getSpeed());
        txtRpm.setText("RPM: " + objCarData.getRpm());
        txtFuelLevel.setText("Fuel Level: " + objCarData.getFuelLevel());
        txtFuelLevel_State.setText("Fuel Level State: " + objCarData.getFuelLevel_State());
        txtOdometer.setText("Odometer: " + objCarData.getOdometer());
        txtExternalTemperature.setText("Ext. Temperature: " + objCarData.getExternalTemperature());
        txtDriverBreaking.setText("Driver Braking: " + objCarData.getDriverBraking());
        txtEngineTorque.setText("Eng. Torque: " + objCarData.getEngineTorque());

        /* GPS */
        txtLatitude.setText("Latitude: " + objCarData.getLatitudeDegrees());
        txtLongitude.setText("Longitude: " + objCarData.getLongitudeDegrees());
        txtAltitude.setText("Altitude: " + objCarData.getAltitude());
        txtHeading.setText("Heading: " + objCarData.getHeading());
        txtCompassDirection.setText("Compass Direction: " + objCarData.getCompassDirection());

        /* Head Lamps */
        txtLowBeamsOn.setText("Low Beams On: " + objCarData.getLowBeamsOn());
        txtHighBeamsOn.setText("High Beams On: " + objCarData.getHighBeamsOn());
        txtAmbientLightSensorStatus.setText("Ambient Light Sensor: " + objCarData.getAmbientLightSensorStatus());

        /* Body Information */
        txtDriverDoorAjar.setText("Driver Door: " + objCarData.getDriverDoorAjar());
        txtPassengerDoorAjar.setText("Passenger Door: " + objCarData.getPassengerDoorAjar());
        txtRearRightDoorAjar.setText("Rear Right Door: " + objCarData.getRearRightDoorAjar());
        txtRearLeftDoorAjar.setText("Rear Left Door: " + objCarData.getRearLeftDoorAjar());
        txtIgnitionStatus.setText("Ignition Status: " + objCarData.getIgnitionStatus());
        txtIgnitionStableStatus.setText("Ignition Status Stable: " + objCarData.getIgnitionStableStatus());
        txtIgnitionStableStatus.setVisibility(View.GONE);

        txtParkBreakeActive.setText("Park Brake Active: " + objCarData.getParkBrakeActive());

        /* Tire Pressures */
        txtRightRear.setText("Right Rear: " + objCarData.getRightRear());
        txtLeftRear.setText("Left Rear: " + objCarData.getLeftRear());
        txtRightFront.setText("Right Front: " + objCarData.getRightFront());
        txtLeftFront.setText("Left Front: " + objCarData.getLeftFront());
        txtInnerRightRear.setText("Inner Right Rear: " + objCarData.getInnerRightRear());
        txtInnerRightRear.setVisibility(View.GONE);
        txtInnerLeftRear.setText("Inner Left Rear: " + objCarData.getInnerLeftRear());
        txtInnerLeftRear.setVisibility(View.GONE);
        txtPressureTelltale.setText("Pressure Telltale: " + objCarData.getPressureTelltale());
        txtPressureTelltale.setVisibility(View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    private void initComponents(){
        txtDt =  (TextView) findViewById(R.id.txtDt);

        txtVin = (TextView) findViewById(R.id.txtVin);
        txtPrndl = (TextView) findViewById(R.id.txtPrndl);
        txtSpeed = (TextView) findViewById(R.id.txtSpeed);
        txtRpm = (TextView) findViewById(R.id.txtRpm);
        txtFuelLevel = (TextView) findViewById(R.id.txtFuelLevel);
        txtFuelLevel_State = (TextView) findViewById(R.id.txtFuelLevel_State);
        txtOdometer = (TextView) findViewById(R.id.txtOdometer);
        txtExternalTemperature = (TextView) findViewById(R.id.txtExternalTemperature);
        txtDriverBreaking = (TextView) findViewById(R.id.txtDriverBreaking);
        txtEngineTorque = (TextView) findViewById(R.id.txtEngineTorque);

        /* GPS */
        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        txtAltitude = (TextView) findViewById(R.id.txtAltitude);
        txtHeading = (TextView) findViewById(R.id.txtHeading);
        txtCompassDirection = (TextView) findViewById(R.id.txtCompassDirection);

        /* Head Lamps */
        txtLowBeamsOn = (TextView) findViewById(R.id.txtLowBeamsOn);
        txtHighBeamsOn = (TextView) findViewById(R.id.txtHighBeamsOn);
        txtAmbientLightSensorStatus = (TextView) findViewById(R.id.txtAmbientLightSensorStatus);

        /* Body Information */
        txtDriverDoorAjar = (TextView) findViewById(R.id.txtDriverDoorAjar);
        txtPassengerDoorAjar = (TextView) findViewById(R.id.txtPassengerDoorAjar);
        txtRearRightDoorAjar = (TextView) findViewById(R.id.txtRearRightDoorAjar);
        txtRearLeftDoorAjar = (TextView) findViewById(R.id.txtRearLeftDoorAjar);
        txtIgnitionStatus = (TextView) findViewById(R.id.txtIgnitionStatus);
        txtIgnitionStableStatus = (TextView) findViewById(R.id.txtIgnitionStableStatus);
        txtParkBreakeActive = (TextView) findViewById(R.id.txtParkBreakeActive);

        /* Tire Pressures */
        txtRightRear = (TextView) findViewById(R.id.txtRightRear);
        txtLeftRear = (TextView) findViewById(R.id.txtLeftRear);
        txtRightFront = (TextView) findViewById(R.id.txtRightFront);
        txtLeftFront = (TextView) findViewById(R.id.txtLeftFront);
        txtInnerRightRear = (TextView) findViewById(R.id.txtInnerRightRear);
        txtInnerLeftRear = (TextView) findViewById(R.id.txtInnerLeftRear);
        txtPressureTelltale = (TextView) findViewById(R.id.txtPressureTelltale);
    }

}
