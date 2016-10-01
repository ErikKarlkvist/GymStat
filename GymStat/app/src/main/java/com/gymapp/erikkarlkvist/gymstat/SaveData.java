package com.gymapp.erikkarlkvist.gymstat;

import android.content.Context;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik Karlkvist on 2016-09-30.
 */
public class SaveData {

    private static SaveData sd;
    private Context context;
    private String gymstat = "gymstat";

    private SaveData(Context context){
        this.context = context;
        load();
    }

    public static SaveData getSaveData(Context context){
        if(sd == null){
            return new SaveData(context);
        } else {
            return sd;
        }
    }

    public void save(List<Set> sets){
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(gymstat, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(sets);
            oos.close();
            System.out.println("successs!");
        } catch (Exception e) {
            System.out.println("Stack trace:");
            e.printStackTrace();
        }
    }


    public List<Set> load(){
        FileInputStream fileInputStream;

        try{
            fileInputStream = context.openFileInput(gymstat);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            List<Set> sets = (List<Set>) ois.readObject();
            ois.close();
            return sets;
        } catch(Exception e){
            System.out.println("Load stack trace:");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
