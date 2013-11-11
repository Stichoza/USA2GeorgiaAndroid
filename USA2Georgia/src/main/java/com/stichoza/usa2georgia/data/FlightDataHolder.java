package com.stichoza.usa2georgia.data;

/**
 * Created by Stichoza on 12/11/13.
 */
public class FlightDataHolder {

    private String name;
    private String date;
    private String text;
    private Boolean arrived;

    public FlightDataHolder(String name, String date, String text, Boolean arrived){
        this.name = name;
        this.date = date;
        this.text = text;
        this.arrived = arrived;
    }

    // TODO Read http://stackoverflow.com/questions/17988054/populating-json-from-this-link-to-android-listview
}
