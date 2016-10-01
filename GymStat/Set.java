package com.gymapp.erikkarlkvist.gymstat;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Erik Karlkvist on 2016-09-29.
 */
public class Set {
    private String setName;
    private String excersise;
    private String weight;
    private String imagesrc;

    public Set(String setName, String excersise, String weight, String image){
        this.setName = setName;
        this.excersise = excersise;
        this.weight = weight;
        imagesrc = image;
    }

    public String getName(){
        return setName;
    }

    public String getExcersise(){
        return excersise;
    }

    public String getweight(){
        return weight;
    }

    public String getImage(){

        return imagesrc;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public void setExcersise(String excersise) {
        this.excersise = excersise;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }
}
