package com.module.ford.smarthealthcar;

import com.module.ford.smarthealthcar.model.CarData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shared {

    public static Shared INSTANCE;

    public static Shared getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Shared();
        return INSTANCE;
    }


    public List<CarData> ListCarData = new ArrayList<>();

    public List<CarData> getListCarData() {
        return ListCarData;
    }

    public void setListCarData(CarData itemCarData) {
        ListCarData.add(itemCarData);
    }


    public void ListCarDataSort(String direction){

        if (direction == "asc") {
            Collections.sort(Shared.getInstance().getListCarData(), new Comparator<CarData>() {
                public int compare(CarData o1, CarData o2) {
                    return Long.compare(o1.getTimestamp(), o2.getTimestamp());
                }
            });
        } else {
            Collections.sort(Shared.getInstance().getListCarData(), new Comparator<CarData>() {
                public int compare(CarData o1, CarData o2) {
                    return Long.compare(o2.getTimestamp(), o1.getTimestamp());
                }
            });
        }


    }


}
