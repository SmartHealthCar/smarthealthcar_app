package com.module.ford.smarthealthcar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.module.ford.smarthealthcar.model.CarData;
import com.ford.sa.interfacesdl.Config;
import com.ford.sa.interfacesdl.LockScreenActivity;
import com.ford.sa.interfacesdl.hmi.CurrentHMIState;
import com.ford.sa.interfacesdl.hmi.EnumDisplayLayout;
import com.ford.sa.interfacesdl.hmi.HMIScreenManager;
import com.ford.sa.interfacesdl.listeners.ServiceListeners;
import com.ford.sa.interfacesdl.telematics.TelematicsCollector;
import com.smartdevicelink.proxy.rpc.AddCommand;
import com.smartdevicelink.proxy.rpc.AddCommandResponse;
import com.smartdevicelink.proxy.rpc.AddSubMenuResponse;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.MenuParams;
import com.smartdevicelink.proxy.rpc.OnButtonPress;
import com.smartdevicelink.proxy.rpc.OnCommand;
import com.smartdevicelink.proxy.rpc.OnHMIStatus;
import com.smartdevicelink.proxy.rpc.OnLockScreenStatus;
import com.smartdevicelink.proxy.rpc.OnVehicleData;
import com.smartdevicelink.proxy.rpc.SoftButton;
import com.smartdevicelink.proxy.rpc.enums.ImageType;
import com.smartdevicelink.proxy.rpc.enums.LockScreenStatus;
import com.smartdevicelink.proxy.rpc.enums.SoftButtonType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

public class SdlApplication extends com.ford.sa.interfacesdl.SdlApplication {

    Vector<SoftButton> softButtons = new Vector<SoftButton>();
    SoftButton btnGetData = new SoftButton();
    SoftButton btnSubsData = new SoftButton();
    SoftButton btnStopSub = new SoftButton();
    SoftButton btnNEW = new SoftButton();
    int unique_id = 0;

    private boolean isFirstRun = true;

    @Override
    public void onCreate() {
        super.onCreate();
        try {

            ////------------------------------------------------------------------------------------------
            /**
             * Screen Manager Listener
             */
            ServiceListeners.getInstance().listenerScreen = new ServiceListeners.ListenerScreen() {
                @Override
                public void showWelcomeScreen() {

                    if (isFirstRun == true) {

                        //Create the Buttons GetData and Subscribe Data
                        btnGetData = new SoftButton();
                        btnGetData.setSoftButtonID(1);
                        btnGetData.setType(SoftButtonType.SBT_BOTH);
                        Image imgGetData = new Image();
                        imgGetData.setValue("start.png");
                        imgGetData.setImageType(ImageType.DYNAMIC);
                        btnGetData.setImage(imgGetData);
                        btnGetData.setText("Get");
                        btnGetData.setIsHighlighted(false);

                        Image newImage2 = new Image();
                        newImage2.setValue("campusparty.png");
                        newImage2.setImageType(ImageType.DYNAMIC);
                        HMIScreenManager.getInstance().newShow.setGraphic(newImage2);

                        btnSubsData = new SoftButton();
                        btnSubsData.setSoftButtonID(2);
                        btnSubsData.setType(SoftButtonType.SBT_BOTH);
                        Image imgSubsData = new Image();
                        imgSubsData.setValue("diag.png");
                        imgSubsData.setImageType(ImageType.DYNAMIC);
                        btnSubsData.setImage(imgSubsData);
                        btnSubsData.setText("Subs");

                        btnStopSub = new SoftButton();
                        btnStopSub.setSoftButtonID(3);
                        btnStopSub.setType(SoftButtonType.SBT_BOTH);
                        Image imgStopSubsData = new Image();
                        imgStopSubsData.setValue("stop.png");
                        imgStopSubsData.setImageType(ImageType.DYNAMIC);
                        btnStopSub.setImage(imgStopSubsData);
                        btnStopSub.setText("Stop");

                        // Create the menu parameters
                        // The parent id is 0 if adding to the root menu
                        // If adding to a submenu, the parent id is the submenu's id

                        MenuParams menuParams0 = new MenuParams();
                        menuParams0.setParentID(unique_id);
                        menuParams0.setPosition(1);
                        menuParams0.setMenuName("Home Screen");
                        AddCommand addCommand2 = new AddCommand();
                        addCommand2.setCmdID(70); // Ensure this is unique
                        addCommand2.setMenuParams(menuParams0);  // Set the menu parameters
                        HMIScreenManager.getInstance().AddCommandMenu(addCommand2);

                        MenuParams menuParams1 = new MenuParams();
                        menuParams1.setParentID(unique_id);
                        menuParams1.setPosition(2);
                        menuParams1.setMenuName("Tela 1");
                        AddCommand addCommand1 = new AddCommand();
                        addCommand1.setCmdID(71); // Ensure this is unique
                        addCommand1.setMenuParams(menuParams1);  // Set the menu parameters
                        HMIScreenManager.getInstance().AddCommandMenu(addCommand1);


                       isFirstRun = false;
                    }

                    HMIScreenManager.getInstance().setNewShow();

                    HMIScreenManager.getInstance().setShowLayout( EnumDisplayLayout.TEXT_AND_SOFTBUTTONS_WITH_GRAPHIC );



                    Image newImage = new Image();
                    newImage.setValue("campusparty.png");
                    newImage.setImageType(ImageType.DYNAMIC);
                    HMIScreenManager.getInstance().newShow.setGraphic(newImage);

                    HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                    HMIScreenManager.getInstance().newShow.setMainField2("New Teste");
                    HMIScreenManager.getInstance().newShow.setMainField3("Teste Data Collection");

                    softButtons.clear();
                    softButtons.add(btnGetData);
                    softButtons.add(btnSubsData);

                    HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);
                    HMIScreenManager.getInstance().mostrarTela();




                }
            };

            ////------------------------------------------------------------------------------------------
            /**
             * HMI Status Listener
             */
            ServiceListeners.getInstance().listenerHMIStatus = new ServiceListeners.ListenerHMIStatus() {
                @Override
                public void onHMIFull(OnHMIStatus notification) {

                    if (notification.getFirstRun()) {
                        /** Upload images */
                        ArrayList<ServiceListeners.SdlImage> listaImagens = new ArrayList<>();
                        listaImagens.add(new ServiceListeners.SdlImage("send.png",R.drawable.send));
                        listaImagens.add(new ServiceListeners.SdlImage("start.png",R.drawable.start));
                        listaImagens.add(new ServiceListeners.SdlImage("stop.png",R.drawable.stop));
                        listaImagens.add(new ServiceListeners.SdlImage("diag.png",R.drawable.diag));
                        listaImagens.add(new ServiceListeners.SdlImage("campusparty.png",R.drawable.campusparty));
                        Config.instance.uploadListImages(listaImagens);


                        //esperando 3 segundos para finalizar o upload das imagens
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        ServiceListeners.getInstance().listenerScreen.showWelcomeScreen();
                    }

                }

                @Override
                public void onHMIBackground(OnHMIStatus notification) {



                }

                @Override
                public void onHMINone(OnHMIStatus notification) {


                }

                @Override
                public void onHMILimited(OnHMIStatus notification) {


                }
            };

            /**
             * Get Data Listener
             *
             */
            ServiceListeners.getInstance().listenerGetData = new ServiceListeners.ListenerGetData() {
                @Override
                public void onGetVehicleDataResponse(Context ctx, GetVehicleDataResponse response) {
                    HMIScreenManager.getInstance().newShow.setMainField3("Data Collected!");
                    HMIScreenManager.getInstance().mostrarTela();
                    //CurrentHMIState.dataCollectionActive = false;

                    CarData obj = new CarData();
                    obj.processVehicleData(response);

                    Shared.getInstance().ListCarDataSort("asc");
                    Shared.getInstance().setListCarData(obj);
                    Shared.getInstance().ListCarDataSort("desc");

                    CarData.getInstance().processVehicleData(response);
                }
            };


            /**
             * Subscribe Data Listener
             */
            ServiceListeners.getInstance().listenerSubscribeData = new ServiceListeners.ListenerSubscribeData() {

                @Override
                public void onVehicleData(OnVehicleData notification) {

                    for (HashMap.Entry<String, Object> obj : ((Hashtable<String, Object>)notification.getStore().get("notification") ).entrySet() ) {
                        if (obj.getKey().equals("parameters")) {
                            for (HashMap.Entry<String, Object> item : ((Hashtable<String, Object>)obj.getValue()).entrySet() ) {
                                HMIScreenManager.getInstance().newShow.setMainField3(item.getKey() + " : " + item.getValue() );
                            }
                        }
                    }

                    CarData.getInstance().processVehicleSubscribe(notification);
                    HMIScreenManager.getInstance().mostrarTela();

                    CarData obj = new CarData();
                    obj.newCarDataSubscribe();

                    Shared.getInstance().ListCarDataSort("asc");
                    Shared.getInstance().setListCarData(obj);
                    Shared.getInstance().ListCarDataSort("desc");
                }

            };


            /**
             * On Commands Listener
             */
            ServiceListeners.getInstance().listenerOnCommand = new ServiceListeners.ListenerOnCommand() {
                @Override
                public void onCommand(OnCommand notification) {


                    switch (notification.getCmdID()) {
                        case 71:
                            HMIScreenManager.getInstance().setNewShow();
                            HMIScreenManager.getInstance().setShowLayout(EnumDisplayLayout.GRAPHIC_WITH_TILES);

                            //HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                            //HMIScreenManager.getInstance().newShow.setMainField2("Tela Media");
                            //HMIScreenManager.getInstance().newShow.setMainField3("Nova Tela Media");

                            softButtons.clear();


                            SoftButton btn1 = new SoftButton();
                            btn1.setSoftButtonID(101);
                            btn1.setType(SoftButtonType.SBT_TEXT);
                            btn1.setText("BTN1");
                            softButtons.add(btn1);

                            SoftButton btn2 = new SoftButton();
                            btn2.setSoftButtonID(102);
                            btn2.setType(SoftButtonType.SBT_TEXT);
                            btn2.setText("BTN2");
                            softButtons.add(btn2);

                            SoftButton btn3 = new SoftButton();
                            btn3.setSoftButtonID(103);
                            btn3.setType(SoftButtonType.SBT_TEXT);
                            btn3.setText("BTN3");
                            softButtons.add(btn3);

                            HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);
                            HMIScreenManager.getInstance().mostrarTela();
                            CurrentHMIState.dataCollectionActive = false;
                            break;
                        case 70:



                            ServiceListeners.getInstance().listenerScreen.showWelcomeScreen();

                            break;
                    }

                }

                @Override
                public void onAddCommandResponse(AddCommandResponse response) {

                    System.out.print("teste");

                }

                @Override
                public void onAddSubMenuResponse(AddSubMenuResponse addSubMenuResponse) {

                    System.out.print("teste");

                }

                @Override
                public void onOnButtonPress(OnButtonPress notification) {

                    switch (notification.getCustomButtonName()){
                        case 1: //Button GETDATA Clicked

                            //button locked when subscribe is active
                            if (!btnGetData.getIsHighlighted()) {
                                CurrentHMIState.dataCollectionActive = true;
                                TelematicsCollector.getInstance().setGetInit();

                                //HMIScreenManager.getInstance().setNewShow();

                                //HMIScreenManager.getInstance().setShowLayout( EnumDisplayLayout.NON_MEDIA );

                                HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                                HMIScreenManager.getInstance().newShow.setMainField2("Getting Car Data");
                                HMIScreenManager.getInstance().newShow.setMainField3("Data colleting...");
                                HMIScreenManager.getInstance().mostrarTela();
                            }

                            break;
                        case 2: //Button SUBSCRIBE Clicked

                            CurrentHMIState.dataCollectionActive = true;
                            TelematicsCollector.getInstance().setGetInit();

                            TelematicsCollector.getInstance().setSubscribeVehicleData();


                            //HMIScreenManager.getInstance().setNewShow();

                            //HMIScreenManager.getInstance().setShowLayout( EnumDisplayLayout.NON_MEDIA );



                            HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                            HMIScreenManager.getInstance().newShow.setMainField2("Subscribing Car Data");
                            HMIScreenManager.getInstance().newShow.setMainField3("Data colleting...");

                            softButtons.clear();
                            btnGetData.setIsHighlighted(true);
                            softButtons.add(btnGetData);
                            softButtons.add(btnStopSub);

                            HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);
                            HMIScreenManager.getInstance().mostrarTela();

                            break;
                        case 3: //Button STOP SUBSCRIBE Clicked

                            TelematicsCollector.getInstance().setUnsubscribeVehicleData();
                            //HMIScreenManager.getInstance().setNewShow();
                            //HMIScreenManager.getInstance().setShowLayout(EnumDisplayLayout.TEXT_AND_SOFTBUTTONS_WITH_GRAPHIC);

                            HMIScreenManager.getInstance().newShow.setMainField1("Hello New Campus Party");
                            HMIScreenManager.getInstance().newShow.setMainField2("Subscribing Car Data");
                            HMIScreenManager.getInstance().newShow.setMainField3("Subscribe Stoped");

                            softButtons.clear();
                            btnGetData.setIsHighlighted(false);
                            softButtons.add(btnGetData);
                            softButtons.add(btnSubsData);

                            HMIScreenManager.getInstance().newShow.setSoftButtons(softButtons);
                            HMIScreenManager.getInstance().mostrarTela();
                            CurrentHMIState.dataCollectionActive = false;
                            break;
                        case 101:

                            Image newImage1 = new Image();
                            newImage1.setValue("start.png");
                            newImage1.setImageType(ImageType.DYNAMIC);
                            HMIScreenManager.getInstance().newShow.setGraphic(newImage1);
                            HMIScreenManager.getInstance().mostrarTela();


                            break;
                        case 102:

                            Image newImage2 = new Image();
                            newImage2.setValue("stop.png");
                            newImage2.setImageType(ImageType.DYNAMIC);
                            HMIScreenManager.getInstance().newShow.setGraphic(newImage2);
                            HMIScreenManager.getInstance().mostrarTela();

                            break;
                        case 103:

                            Image newImage3 = new Image();
                            newImage3.setValue("send.png");
                            newImage3.setImageType(ImageType.DYNAMIC);
                            HMIScreenManager.getInstance().newShow.setGraphic(newImage3);
                            HMIScreenManager.getInstance().mostrarTela();

                            break;
                    }

                }
            };


            ServiceListeners.getInstance().listenerLockScreenEvents = new ServiceListeners.ListenerLockScreenEvents() {
                @Override
                public void onDisposeSyncProxy() {
                    LockScreenActivity.updateLockScreenStatus(LockScreenStatus.OFF);
                }

                @Override
                public void onLockScreenNotification(OnLockScreenStatus notification) {
                    LockScreenActivity.updateLockScreenStatus(notification.getShowLockScreen());
                }

                @Override
                public void onCreate(Activity activity, Bundle bundle) {
                    //if you want to implement a custom Lock Screen:
                    //activity.setContentView(R.layout.custom_activity_lock_screen_name);
                }

                @Override
                public void onBroadcastReceive(Context context, Intent intent) { }
            };

            //for connection with SYNC
            Config.ConnectionType = ServiceListeners.ProxyConnection.SYNC;
            //for connection with MANTICORE
            //Config.ConnectionType = ServiceListeners.ProxyConnection.MANTICORE;
            Config.ManticorePort = 5356;

            initSdlService();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }





}
