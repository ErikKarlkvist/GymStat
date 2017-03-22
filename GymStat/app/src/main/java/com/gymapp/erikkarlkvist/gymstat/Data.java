package com.gymapp.erikkarlkvist.gymstat;

import android.content.res.Resources;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erik Karlkvist on 2016-10-03.
 */
public class Data {

    private String desc;
    private ArrayList<String> dates;

    private List<Double> weights;
    private List<String> reps;
    public int size;
    private List<String> difficulty;

    public Data(String date, double weight, String rep, String desc, String diff){
        size = 0;
        this.desc = desc;
        dates = new ArrayList<>();
        dates.add(date);
        difficulty = new ArrayList<>();
        difficulty.add(diff);
        weights = new ArrayList<>();
        weights.add(weight);

        reps = new ArrayList<>();
        reps.add(rep);
    }

    public String getDesc() {
        return desc;
    }

    //returns date at index
    public String getDate(int index){
        return dates.get(index);
    }

    //adds new date at start of list
    public void addData(String date, double weight, String rep, String desc, String diff){
        dates.add(date);
        weights.add(weight);
        reps.add(rep);
        difficulty.add(diff);
        this.desc = desc;
        size++;
    }

    public double   getWeight(int index){
        return weights.get(index);
    }

    public String getRep(int index){
        return reps.get(index);
    }

    public ArrayList<String> getDateList(){
        return dates;
    }

    public String getDifficulty(int index) {return difficulty.get(index); }
}
