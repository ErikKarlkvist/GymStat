package com.gymapp.erikkarlkvist.gymstat;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static List<Set> sets;
    public static int size;
    private static SaveData sd;

    public MainFragment() {
        if(sets == null){
            sets = load();
            size = 0;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ArrayAdapter<Set> listAdapter = new CustomListAdapter(this.getContext(), R.layout.set_view, sets);
        ListView setList = (ListView) getView().findViewById(R.id.set_list);
        setList.setAdapter(listAdapter);
        System.out.println(listAdapter.getCount());
        setList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String set = String.valueOf(parent.getItemAtPosition(position));
                //Toast.makeText(MainFragment.this, set, Toast.LENGTH_LONG).show();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


    public static void addSet(Set set){
        sets.add(set);
        size++;
    }

    String filename = "gymstat";
    public void save(List<Set> sets){
        FileOutputStream outputStream;

        try {
            outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
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
            fileInputStream = getContext().openFileInput(filename);
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
